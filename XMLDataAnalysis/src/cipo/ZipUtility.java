package cipo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
//import java.time.DayOfWeek;
import java.time.LocalDate;
//import java.time.temporal.TemporalAdjuster;
//import java.time.temporal.TemporalAdjusters;

import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;

public class ZipUtility {
	
	List<String> filesListInDir = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        
    	String localfolder = "C:\\raj\\";
    	File dir = new File("C:\\raj\\TESTRUN");
    	boolean checkforexistingfiles = false;
    	boolean putfilesflag = false;
    	boolean getspecificCIPOXMLfile = true; //***** LARGE DOWNLOAD IF SET TRUE
    	                 
    	if (getspecificCIPOXMLfile)
    		getXMLFileFromCIPOHistorical("1548781-00.xml");
    		
    	
    	LocalDate dt = LocalDate.now();   
    	 //System.out.println("\n  dt.getDayOfWeek( "+dt.getDayOfWeek()); 
    	 //TemporalAdjuster temporalAdjuster = TemporalAdjusters.previous(DayOfWeek.TUESDAY);
    	 
    	 int offset = 0;
    	 switch(dt.getDayOfWeek().toString()) 
    	 {
    	  case "TUESDAY":
    	    offset = 1;
    	    break;
    	  case "WEDNESDAY":
    		  offset = 2;
    	    break;
    	  case "THURSDAY":
      	    offset = 3;
      	    break;
      	  case "FRIDAY":
      		  offset = 4;
      	    break;
      	  case "SATURDAY":
      		  offset = 5;
  		  		break;
      	  case "SUNDAY":
    	    offset = 6;
    	    break;
    	  case "MONDAY":
    		  offset = 7;
    	    break;
    	  default:
    	    offset = 0;
    	 }
    	    
    	 SFTPClass sftp = new SFTPClass();
    	 
    	String manifestfile = "CA-TMK-UPDATE_"+dt.minusDays(offset+6).toString()+"-"+dt.minusDays(offset).toString()+"_index.txt";
    	
    	Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        String strDate = sdf.format(cal.getTime());
        
        String zipfilename = "CA-TMK-UPDATE_TESTFILE_"+strDate+".zip";
        
        //manifestfile = "CA-TMK-UPDATE_2020-07-14-2020-07-20_index.txt";
        
        FileWriter fw1 = new FileWriter(localfolder+manifestfile);
        fw1.write("List of filename(s) within this export batch: ");
        fw1.write("\n");
        fw1.write("1- "+zipfilename);
        
        /*
        int c = 0;
        while (c <4)
        {
        	fw1.write("\n");
        	fw1.write("1- "+zipfilename);
        	c++;
        }
        
        fw1.write("\n");
        */
        
        fw1.close();
        
        int seq = 0;
        
               
        seq = countnumlinesinfile(localfolder+manifestfile);
        System.out.println("************  seq  *********"+seq);
        
        
        if (checkforexistingfiles)
        {
        	 String newfile = sftp.connectSFTPandgetFile(localfolder, manifestfile);
        	 if (newfile!= null)
        			{
        		 		seq = countnumlinesinfile(localfolder+manifestfile);
        		 		System.out.println("overwriting file ...   : "+localfolder+manifestfile+"  seq  "+seq);
        		 		fw1 = new FileWriter(newfile, true);
        		 		fw1.write("\n");
        		 		fw1.write(seq+"- "+zipfilename);
        		 		fw1.write("\n");
        		 		fw1.close();
		               	     	   
		
		         	}
        	
        }
       
        	        
        
        
        String zipDirName = localfolder+zipfilename;
              
        ZipUtility zipFiles = new ZipUtility();
        System.out.println("dir: "+dir+" zipDirName  "+zipDirName);
        zipFiles.zipDirectory(dir, zipDirName);
        
        
        
        
        if (putfilesflag)
        
        {	
        sftp.setSourceFile(new File(localfolder+zipfilename));
        sftp.connectSFTPandputFile();
        // write manifest file
        sftp.setSourceFile(new File(localfolder+manifestfile));
        sftp.connectSFTPandputFile();
        }
   }

	
	
	  void zipDirectory(File dir, String zipDirName) {
	        try {
	        	//List<String> filesListInDir = new ArrayList<String>();
	        	populateFilesList(dir);
	            //now zip files one by one
	            //create ZipOutputStream to write to the zip file
	            FileOutputStream fos = new FileOutputStream(zipDirName);
	            ZipOutputStream zos = new ZipOutputStream(fos);
	            for(String filePath : filesListInDir){
	                //System.out.println("Zipping .."+filePath);
	                ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length()+1, filePath.length()));
	                zos.putNextEntry(ze);
	                //read the file and write to ZipOutputStream
	                FileInputStream fis = new FileInputStream(filePath);
	                byte[] buffer = new byte[1024];
	                int len;
	                while ((len = fis.read(buffer)) > 0) {
	                    zos.write(buffer, 0, len);
	                }
	                zos.closeEntry();
	                fis.close();
	            }
	            zos.close();
	            fos.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    private void populateFilesList(File dir) throws IOException {
	        File[] files = dir.listFiles();
	        for(File file : files){
	        	//System.out.println("for loop inside populateFilesList "+file.getAbsolutePath());
	            if(file.isFile()) 
	            	filesListInDir.add(file.getAbsolutePath());
	            else populateFilesList(file);
	        }
	    }
	    
	    
	  /*  private static int countnumlinesinfile (File file) throws IOException
	    {
		    	FileInputStream fis = new FileInputStream(file);
	        byte[] byteArray = new byte[(int)file.length()];
	        fis.read(byteArray);
	        String data = new String(byteArray);
	        String[] stringArray = data.split("\r\n");
	        fis.close();
	        System.out.println(" stringArray.length"+stringArray.length);
	        return stringArray.length;
	        
	    }
	    */
	    
	    
	    
	    //
	    private static int countnumlinesinfile (String filepath) throws IOException
	    {
		    
	    //Path path = Paths.get();
	    //long lineCount = Files.lines(path).count();
	    
	    
	    BufferedReader reader = new BufferedReader(new FileReader(filepath));
	    int lines = 0;
	    while (reader.readLine() != null) lines++;
	    	reader.close();
	    
	    return lines;
	    }
	    //
	    
	    private static void getXMLFileFromCIPOHistorical(String searchFile) throws IOException
	    {
	    	String cipourl = "https://opic-cipo.ca/cipo/client_downloads/Trademarks_Historical_2019_10/";
	    	
	    	String cipozipfilename = "CA-TMK-GLOBAL_2019-10-05_1542307_1555226_063.zip";

	    	String filename = cipourl+cipozipfilename;
	    	//filename = "https://opic-cipo.ca/cipo/client_downloads/Trademarks_Historical_2019_10/CA-TMK-GLOBAL_2019-10-05_index.txt";
	    	
	    	URL url = new URL(filename);
	    	//FileUtils.copyURLToFile(url, new File("C:\\raj\\"+cipozipfilename));
	    	
	    		        
	        if (true)
	        {
	        
				        searchFile = "1548781-00.xml";
				        
				        ZipFile zipFile = null;
				        
				        //
				        //File targetFile = new File("C:\\raj\\TESTRUN\\"+searchFile);
				        //OutputStream outStream = new FileOutputStream(targetFile);
				        
				        //
				         
				        boolean fileFound = false;
				 
				        try {
				             
				            // open a zip file for reading
				            zipFile = new ZipFile("C:\\raj\\"+cipozipfilename);
				            System.out.println(" zipfile" +zipFile.toString());
				            ZipEntry e = zipFile.getEntry(searchFile);
				            System.out.println(" zipentry getname" +e.getName());
				            InputStream is = zipFile.getInputStream(e);
				            //Files.copy(is, Paths.get("C:\\temp\\myfile.java"));
				            FileUtils.copyInputStreamToFile(is, new File("C:\\raj\\"+searchFile));
				            
				            
				            				 
				            // get an enumeration of the ZIP file entries
				            
				            //Enumeration<? extends ZipEntry> e = zipFile.entries();
				 
				            
				        }
				        catch (IOException ioe) {
				            System.out.println("Error opening zip file" + ioe);
				        }
				         finally {
				             try {
				                 if (zipFile!=null) {
				                     zipFile.close();
				                 }
				             }
				             catch (IOException ioe) {
				                    System.out.println("Error while closing zip file" + ioe);
				             }
				         }
				 
				        System.out.println("File found: " + fileFound);
				         
				    	//
	        }
	    	
	    }

}


/*
while (e.hasMoreElements()) {
     
    ZipEntry entry = e.nextElement();

    // get the name of the entry
    String entryName = entry.getName();
     
    if (entryName.equalsIgnoreCase(searchFile)) {
        fileFound = true;
        InputStream is = entry.getInputStream(entry);
        System.out.println("Found zip file" + entryName);
        FileUtils.copyURLToFile(url, new File("C:\\raj\\"+cipozipfilename));
        //targetFile = new File("C:\\raj\\TESTRUN\\"+searchFile);
        //outStream = new FileOutputStream(targetFile);
        
        break;
    }

}
*/

