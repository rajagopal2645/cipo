package uspto;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class APCFileTrimmer 
{

	String asburl = "https://bulkdata.uspto.gov/data/trademark/dailyxml/assignments/asb"; 
	static List<String> apc = null;
	static List<String> asb = null;
	static List<String> ttab = null;
    	
	public static void main(String[] args) throws TransformerConfigurationException, ParserConfigurationException, SAXException, IOException 
	{
		ZoneId zone = ZoneId.of("US/Eastern");
		Instant start = Instant.now();
		System.out.println("Start : " + start);
		FileWriter fw1 = new FileWriter("c:/raj/uspto.txt");
		
		//Date currentDate = Calendar.getInstance(TimeZone.getDefault()).getTime();
		//DateFormat df = new SimpleDateFormat("MMdd");
		//currentDate.from(start.minusSeconds(60*60*24));
		 ZonedDateTime start1 = start.minusSeconds(60*60*24).atZone(zone);
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYMMdd");

		String datestr = start1.format(formatter);
		//datestr = "200909";
		
		
		//countSerialNumbers("APC",datestr,fw1, apc);
		//countSerialNumbers("ASB",datestr,fw1, asb);
		countSerialNumbers("TTAB",datestr,fw1, ttab);
		//walkTree(new File("c:/raj/hr200909"), fw1);
		//System.out.println("Intersection of List1 & List2 Method 1: " + getIntersectOfLists2(apc, asb));	
		
		fw1.close();		
		Instant finish = Instant.now();
	    System.out.println("End : " + finish+ "  "+datestr);
	    long dur = Duration.between(start, finish).getSeconds();
	    System.out.println("Took "+dur+" seconds to run");  
	}
	
	
	
	public static void countSerialNumbers(String filetype, String datestr, FileWriter fw, List<String> list) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException 
	{
		File file =  null;
		String path = "C:\\raj\\";
		String remElement = "case-file";
		remElement = "serial-number";
		
		
		
		file = getXMLFileFromUSPTO(filetype, datestr);
		System.out.println("filepath: "+file.getPath()+file.getName()); 
		
		/*
		if (filetype =="APC")
		{
			file =  new File(path+"apc"+datestr+".xml");
			
			
		}
		
		else if (filetype =="ASB")
		{
			file =  new File(path+"asb"+datestr+".xml");
			remElement = "serial-no";
		}
		

		else if (filetype =="TTAB")
			file =  new File(path+"tt"+datestr+".xml");

		*/
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(file);
		   
		   //TransformerFactory tFactory = TransformerFactory.newInstance();
		   //Transformer tFormer = tFactory.newTransformer();
		   
		NodeList nodeList = document.getElementsByTagName(remElement);
		   
		System.out.println(" Number of "+remElement+" snippets ="+nodeList.getLength());
		   
		   
		   //  THIS DID NOT DELETE FROM SOURCE DOCUMENT - REVISIT LATER 
		   
		   //Element element = (Element) document.getElementsByTagName(remElement).item(0);
		   //element.getParentNode().removeChild(element);

		   for (int i = 0; i < nodeList.getLength(); i++) 
		   {
			   //Element element = (Element) document.getElementsByTagName(remElement).item(0);
			   //list.
			   //list.add(nodeList.item(i).getTextContent().toString());
			   fw.write("\n"+filetype+" :"+nodeList.item(i).getTextContent());
           }
		   
				   
		}

	


	private static File getXMLFileFromUSPTO(String filetype, String datestr) throws IOException
    {
		String httpfile = "";
		String localfilename = "";
		String localfilepath = "c:\\raj\\"; 
		
		if (filetype =="APC")
		{
			httpfile = "https://bulkdata.uspto.gov/data/trademark/dailyxml/applications/apc"+datestr+".zip";
			localfilename = "apc"+datestr+".zip";
		}
		
		else if (filetype =="ASB")
		{
			httpfile = "https://bulkdata.uspto.gov/data/trademark/dailyxml/assignments/asb"+datestr+".zip";
			localfilename = "asb"+datestr+".zip";
		}
		
		else if (filetype =="TTAB")
		{
			httpfile = "https://bulkdata.uspto.gov/data/trademark/dailyxml/ttab/tt"+datestr+".zip";
			localfilename = "tt"+datestr+".zip";
		}	
		
    	System.out.println("  filetype :"+filetype);
    	System.out.println("  httpfile :"+httpfile);
    	System.out.println("  localfilepath :"+localfilepath);
    	System.out.println("  localfilename :"+localfilename);
    	
		URL url = new URL(httpfile);
    		
    	
    	FileUtils.copyURLToFile(url, new File(localfilepath+localfilename));
    	
    	String searchFile = "";
    	
                
			        searchFile = "1548781-00.xml";
			        
			        //
			        //File targetFile = new File("C:\\raj\\TESTRUN\\"+searchFile);
			        //OutputStream outStream = new FileOutputStream(targetFile);
			        
			        //
			        boolean fileFound = false;
			 
			        try {
			             
			            // open a zip file for reading
			        	ZipFile zipFile = new ZipFile(localfilepath+localfilename);
	    		        
			            System.out.println(" zipfile" +zipFile.toString());
			            ZipEntry e = zipFile.getEntry(localfilename.replaceAll("zip", "xml"));
			            //ZipEntry e = zipFile.getEntry(name)
			            System.out.println(" zipentry getname" +e.getName());
			            InputStream is = zipFile.getInputStream(e);
			            //Files.copy(is, Paths.get("C:\\temp\\myfile.java"));
			            FileUtils.copyInputStreamToFile(is, new File(localfilepath+localfilename.replaceAll("zip", "xml")));
			            zipFile.close();

			            // get an enumeration of the ZIP file entries
			            
			            //Enumeration<? extends ZipEntry> e = zipFile.entries();
			            
			        }
			        catch (IOException ioe) {
			            System.out.println("Error opening zip file" + ioe);
			        }
			         
			 
			        System.out.println("File found: " + fileFound);
					return new File(localfilename);
			        
			    	//
        
    	
    }
	
	public static void walkTree(File dir, FileWriter fw) throws IOException 
	{

	    File listFile[] = dir.listFiles();
	    if (listFile != null) {
	        for (int i=0; i<listFile.length; i++) {
	            if (listFile[i].isDirectory()) {
	              //System.out.println("|\t\t");  
	              walkTree(listFile[i], fw);
	            } else {
	            	
	            	fw.write("\n path "+listFile[i].getPath()+" filename"+listFile[i].getName().toString());

	            }
	        }
	    }
	}

}



