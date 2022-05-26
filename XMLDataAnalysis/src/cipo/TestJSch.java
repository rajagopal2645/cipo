package cipo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

import com.jcraft.jsch.*;
//https://epaul.github.io/jsch-documentation/javadoc/com/jcraft/jsch/ChannelSftp.html

public class TestJSch {
	
	
	String host = "38.117.69.16";
	String login = "cipo.customer65";
	String password = "Iexaeb0wei^shi";
	//String directory = "/dev/cipo-d1/www/clients/client1/web3/web/cipo/client_downloads/Trademarks_Weekly";
	String directory = "/dev/cipo-d1/www/clients/client1/web3/web/cipo/client_downloads/Trademarks_Weekly_Uncompressed";
	File file = new File("C:/raj/WEEKLY_2020-06-16_00-04-58.zip");
	
    
	
	public  void connectSFTP() throws FileNotFoundException
    {
        JSch jsch = new JSch();
        
        // OVER-RIDE FOR LOCAL
        //host = "pftp-vl201.int.compumark.com";
    	//login = "cipo";
    	//password = "madrid";
    	//directory = "perseus-test-int";

        Session session = null;
        try {
            session = jsch.getSession(login, host, 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            System.out.println("home dir "+sftpChannel.getHome());
            sftpChannel.cd(directory);
            System.out.println("pwd "+sftpChannel.pwd());
            
            
            //sftpChannel.get(src)
            System.out.println(sftpChannel.getHome()+"/"+directory+"/"+file.getName());
            sftpChannel.put(new FileInputStream(file), sftpChannel.getHome()+"/"+directory+"/"+file.getName());
            //String indexfile ="";
            Vector filedire = sftpChannel.ls("*");
            for (int f =0; f<filedire.size(); f++)
            {       
            	System.out.println("inside loop "+filedire.get(f));
            			if (filedire.get(f).toString().contains("_index.txt"))
            			{
            				String indexfile = filedire.get(f).toString();
            				indexfile = indexfile.substring(indexfile.indexOf("CA-TMK-UPDATE"));
            				//System.out.println(" index:"+filedire.get(f));
            				System.out.println(" indexfile:"+indexfile);

            				//sftpChannel.get("CA-TMK-UPDATE_2020-06-23-2020-06-29_index.txt", "C:/raj/CA-TMK-UPDATE_2020-06-23-2020-06-29_index.txt");
            			}
            			//CA-TMK-UPDATE_2020-06-23-2020-06-29_26624_2037007_001.zip
            	}
            
            sftpChannel.get("CA-TMK-UPDATE_2020-06-23-2020-06-29_26624_2037007_001.zip", "C:/raj/LatestCIPOWeekly.zip");
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();  
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }
}