package cipo;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.FileWriter;


public class JdbcPostgresqlConnection 
{
	
	public void retrieveCTRforAppID(String appid, FileWriter fw1) 
	{
       Connection conn = null;
        
       //appid = "300000100015100";
          
        
 
        try {
            
        	Properties parameters = new Properties();
             
        	String dbURL = "jdbc:postgresql://perseus-db-test.cxhruenwvrrt.us-west-2.rds.amazonaws.com:5432/perseustest"; // this is incorrect
        	 //dbURL = "jdbc:postgresql://perseus.caqoel1neeqc.us-east-2.rds.amazonaws.com:5432/perseus"; // correct url DEV //master pwd: pgAdmin01
        	 //parameters.put("user", "postgres");  // DEV
             //parameters.put("password", "postgres"); // DEV
             
        	 dbURL = "jdbc:postgresql://perseus-db-test.cxhruenwvrrt.us-west-2.rds.amazonaws.com:5432/perseustest"; // correct url QA
        	 parameters.put("user", "sapient"); // QA
             parameters.put("password", "foobarbaz"); // QA
             conn = DriverManager.getConnection(dbURL, parameters);
        	
        	
            if (conn != null) 
                System.out.println("Connection successful");
                
            String SQL = "select guid, document_id from public.record_info where document_id = '"+appid+"'";
            //System.out.println(SQL);
               		
            		//
                		Statement stmt = conn.createStatement();
                		
                        ResultSet rs = stmt.executeQuery(SQL);
                        String guid = "";
                        //while (rs.next())
                        if (rs.next() == false )
                        {
                        	System.out.println("  No GUID found for document_id  "+appid);
                        	fw1.write("  No GUID found for document_id  "+appid);
                        	return;
                        }
                        else
                        {
                        	
		                        //rs.next();
		                        {
		                        guid = rs.getString("guid");
		                        String document_id = rs.getString("document_id");
		                        //System.out.println("  guid = "+guid +"   document_id  "+document_id);
		                        }
                        }
                        //guid = "I829A2E8DA5D911EA96B502B65F6480E0";
                		
                        // this does not change anything - modify in printHTTPRequest method
                        String httprequest = "http://ctr-test.us-west-2.elasticbeanstalk.com/edit/cana/"+guid;
                        //httprequest = "https://perseus-crud-test-257461168.us-west-2.elb.amazonaws.com/trademark/cipo/"+guid;
                        //httprequest = "https://weather.com/weather/today/l/0335a70c951ae04e9d5525474901062baaa48c7106b0f335fa1efe2e0fc14dbb";
                        
                        printHTTPRequest("QA",guid, fw1);
                        
                    //
                		
                		
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        catch (Exception e) {
            e.printStackTrace();
        } 
        finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
	
	
	void printHTTPRequest(String region, String guid, FileWriter fw1) throws MalformedURLException, IOException
	{
				
		String httprequest = "";
		if (region == "QA")
				//httprequest = "https://perseus-crud-test-257461168.us-west-2.elb.amazonaws.com/trademark/cipo/";
			    //httprequest = "https://perseus-crud-test.eks.test.int.compumark.com/trademark/cipo/";
				httprequest = "http://ctr-test.us-west-2.elasticbeanstalk.com/edit/cana/";
		if (region == "PROD")		
				httprequest = "http://pmgo-pl201.int.compumark.com:8080/trademark/latest/CANA/";
		
		httprequest = httprequest+guid;
		//httprequest = "https://perseus-crud-test-257461168.us-west-2.elb.amazonaws.com/trademark/cipo/"+guid;
        //httprequest = "https://weather.com/weather/today/l/0335a70c951ae04e9d5525474901062baaa48c7106b0f335fa1efe2e0fc14dbb";
        
        MakeHttpRequest req = new MakeHttpRequest();
        System.out.println(httprequest);
        fw1.write("\n  "+ region +" : "+httprequest+"\n"+req.getHTML(httprequest));
        
	}
	
	
	
	public void getGUIDPROD(String appid, FileWriter fw1)
	{
		String prodGUID = "";
		
		try
		{
			Connection conn = getConn();
			Statement stmt = conn.createStatement();
			ResultSet rset = null;
			String strSelect = "select guid from item_guid  where item_id='"+appid+"00' and item_source='TMCA'";
			System.out.println("The SQL query is: " + strSelect); // Echo For debugging
			
			rset = stmt.executeQuery(strSelect);

			//int rowCount = 0;
				 while(rset.next())
				 {   // Move the cursor to the next row
					 prodGUID = rset.getString("guid");
					//rowCount++;
				 }
			 System.out.println("PROD GUID = " + prodGUID);
			 conn.close();
			 printHTTPRequest("PROD",prodGUID, fw1);

		 }
		 catch(SQLException | IOException ex)
		  {
			 System.out.println("Exception loop");
			 ex.printStackTrace();
		  }

		//return prodGUID;
	}
	
	
	Connection getConn()
	{
		Connection conn = null;
		try 		{
						//DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
						//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						//Class.forName("oracle.jdbc.driver.JdbcOdbcDriver");
						//Class.forName ("oracle.jdbc.driver.OracleDriver");
						Class.forName ("oracle.jdbc.OracleDriver");
					    System.setProperty("oracle.net.tns_admin", "C:/raj");
						String dbURL = "jdbc:oracle:thin:@pnvscnt.int.compumark.com";
						//Username :nvscnt
						//Password :nvscnt
						//Service name: pnvscnt.int.compumark.com
						//(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=10.213.130.224)(PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=10.213.130.225)(PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=10.213.130.226)(PORT=1521))(LOAD_BALANCE=yes)(FAILOVER=yes))(CONNECT_DATA=(SERVER=DEDICATED)

						String tnsname = "(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=10.213.130.224)(PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=10.213.130.225)(PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=10.213.130.226)(PORT=1521))(LOAD_BALANCE=yes)(FAILOVER=yes))(CONNECT_DATA=(SERVICE_NAME=pnvscnt.int.compumark.com)(SERVER=DEDICATED)))"; 
						tnsname = "jdbc:oracle:thin:@"+tnsname;
						//old fidelity String tnssname = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=ortp03-scan) (PORT=1521))(CONNECT_DATA=(SERVICE_NAME=fiordxs)))";
						conn = DriverManager.getConnection(tnsname, "nvscnt","nvscnt");
					}
				catch(Exception x)
					{
						System.out.println( "Unable to load the driver class!" );
						x.printStackTrace(System.out);
					}
		return conn;

	}

	
	
}
