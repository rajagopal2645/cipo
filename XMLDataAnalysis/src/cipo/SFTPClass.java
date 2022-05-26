package cipo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Vector;
import com.jcraft.jsch.*;

public class SFTPClass {
	
	
	String host = "pftp-vl201.int.compumark.com";
	String login = "cipo";
	String password = "madrid";
	String directory = "perseus-test-int";
	File sourcefile = null;
	FileWriter fw = null;
	
	public SFTPClass() 
	{
	    // do nothing
	}
	
	
	public SFTPClass(String server, String id, String pwd, String folder) 
	{
	    host = server;  
	    login = id;
	    password = pwd;
	    directory = folder;
	}
	
	public void setSourceFile(File sourcefilename)
	{
		sourcefile =  sourcefilename;
	}
	
	public void setFileWriter(FileWriter fw1)
	{
		fw =  fw1;
	}
	
	public  void connectSFTPandputFile() throws FileNotFoundException
    {
        JSch jsch = new JSch();
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
            
            sftpChannel.put(new FileInputStream(sourcefile), sftpChannel.getHome()+"/"+directory+"/"+sourcefile.getName());
            
            Vector filedire = sftpChannel.ls("*");
            for (int f =0; f<filedire.size(); f++)
            {       
            	System.out.println(filedire.get(f).toString());            				
            }

            
            
            
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();  
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }
	
	public  String connectSFTPandgetFile(String localfolder, String manifestfile) throws FileNotFoundException
    {
        JSch jsch = new JSch();
        Session session = null;
        String locatedfile = null;
        try {
            session = jsch.getSession(login, host, 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            System.out.println("localfolder"+localfolder+" manifestfile : "+manifestfile);
            sftpChannel.cd(directory);
            System.out.println("pwd "+sftpChannel.pwd());
            
        
            Vector filedire = sftpChannel.ls("*");
            sftpChannel.rm("CA-TMK-UPDATE_TESTFILE_*.zip");
            for (int f =0; f<filedire.size(); f++)
            {       
            	String dirlist = filedire.get(f).toString();
            	System.out.println(dirlist);    
            	
            	if (dirlist.endsWith(manifestfile))
            	//if (dirlist.contains(manifestfile) && !dirlist.toUpperCase().contains("DONE") )
            	{
            	
            		  sftpChannel.get(manifestfile, localfolder+manifestfile);
            		  locatedfile = localfolder+manifestfile;
            		  System.out.println("found file "+locatedfile);
            		  
            	}
            	
            	//fw = new File()
            }
            
            
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();  
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return locatedfile;

    }

	
	//rm CA-TMK-UPDATE_TESTFILE_*.zip
	
}
