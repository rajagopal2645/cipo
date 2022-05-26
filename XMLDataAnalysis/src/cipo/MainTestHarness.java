package cipo;

import java.io.FileWriter;
import java.io.IOException;

public class MainTestHarness {

	public static void main(String[] args) throws IOException 
	
	{
		FileWriter fw1=new FileWriter("c:\\raj\\CTRLookupResults.txt");

		
		//String[] appid = {"100014100","100015100","100016100","100017100","100018100", "090160900", "100047900","100350100", "101197600","101491400" };
		//String[] appid = {"300000100014100","300000083817100"};
		
		//"101491400","090160900"};  101491400; 10991290
		//String[] appid = {"002360300"};//"300000109912900", "300000101491400"};
		//String[] appid = {"300000193156200","300000100014100","300000100015100","300000100016100","300000100017100","300000100018100", "090160900", "300000100047900","300000100350100", "300000101197600" };
		//"300000090160900", "300000100047900","300000100350100", "300000101197600"
		
		String[] appid = {"1822351"
				//,"1822351","2035626","1939439"
				//"1939439","2035571",
		//"1014914",
		//"300000203551600",
		//"300000203552100",
		//"300000203554500",
		//"300000203555400",
		//"300000203557900",
		//"300000203555500",
		//"300000203560700",
		//"300000203554000",
		//"300000203560800"
				};
				
		
		JdbcPostgresqlConnection pgconn =  new JdbcPostgresqlConnection();
		String trimmedAppNum = "";
		for (int i = 0; i < appid.length; i++)
		{
			System.out.println("***"+appid[i]+"***");
			trimmedAppNum = appid[i];
			//trimmedAppNum = appid[i].substring(appid[i].length() - 7);
			pgconn.retrieveCTRforAppID(trimmedAppNum, fw1);
			pgconn.getGUIDPROD(trimmedAppNum, fw1);
			System.out.println("--------------------------------------------------------");
		}
		
		//String httprequeststring = "https://weather.com/weather/today/l/0335a70c951ae04e9d5525474901062baaa48c7106b0f335fa1efe2e0fc14dbb";
		

	}

}
