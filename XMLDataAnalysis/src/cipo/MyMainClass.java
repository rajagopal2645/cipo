package cipo;

import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

import java.awt.List;
import java.io.*;
import java.net.URL;
import java.time.Instant;
import java.time.Duration;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.Timer;
import java.util.Date;

public class MyMainClass {
	

	
	public static void main(String[] args) throws IOException, FileNotFoundException, ParserConfigurationException, SAXException, Exception, InterruptedException
	{
		
		Instant start = Instant.now();
		System.out.println("Start : " + start);
		
		/*
		String inspect = "Sani 360° \n 78º \n DOURTHE N°1 \n 20° ";
		
		
		 int nameLenght = inspect.length(); // length of the string used for the loop

		    for(int i = 0; i < nameLenght ; i++)
		    {   // while counting characters if less than the length add one        
		        char character = inspect.charAt(i); // start on the first character
		        int ascii = (int) character; //convert the first character
		        System.out.println(character+" = "+ ascii); // print the character and it's value in ascii
		    }
				
		*/
		
		//String replaceString = test.replaceAll("\n"," ");
		//replaceString = replaceString.replaceAll("  "," ");
		
		String test = "/ACC/THIS STRING HAS SEVERAL WORDS IN IT ELEVEN TO BE PRECISE |/BNF/ ANGELA BRAGG FLORIDA STATE JGJJHJHKHKHKHKHK |/INT/ 979797979797";
		char[] ch1 = test.toCharArray();
		String line[] = {"","","","","",""};
		int linenum = 0;
				
		for (int i=0;i<ch1.length;i++)
		{
			
				if (ch1[i]!='|')
					line[linenum]=line[linenum]+ch1[i];
						if (line[linenum].length() > 32)
							linenum++;
				else if (ch1[i]=='|')
					linenum++;
				
		}
				
			/*
			else if (i<(breakpoint+33))
				{
				linenum++;
					if (ch1[i]!='|')
						line[linenum]=line[linenum]+ch1[i];
					else 
						linenum++;
					breakpoint++;
				}
					
			else if (i<(breakpoint+33))
			{
				linenum++;
				if (ch1[i]!='|')
				//System.out.println("line 3");
					line[linenum]=line[linenum]+ch1[i];
				else 
					linenum++;
				breakpoint++;
			}
			else if (i<(breakpoint+33))
			{
				if (ch1[i]!='|')
				//System.out.println("line 4");
					line[3]=line[3]+ch1[i];
				else 
					i++;
				breakpoint++;
			}
			else if (i<(breakpoint+33))
			{
				if (ch1[i]!='|')
				//System.out.println("line 5");
					line[4]=line[4]+ch1[i];
				breakpoint++;
			}
			else if (i<(breakpoint+33))
			{	
				//System.out.println("line 6");
				if (ch1[i]!='|')
					line[5]=line[5]+ch1[i];
			}
			//System.out.println(ch1[i]);
			 
			 */
		
		
		
		for (int i=0;i<6;i++)
		{
			if (!line[i].startsWith("/"))
				System.out.println(" line "+i+" : "+"//"+line[i]+" length "+line[i].length());
			else
				System.out.println(" line "+i+" : "+line[i]+" length "+line[i].length());

		}

		
		//System.out.println("test string : " + test+"  char array"+ch1.length);
		System.out.println("replaceString: " + test +" "+test.length());

		

		
		char ch = '<';
		ch = '°';
		
		ch = '\u03A9';
		ch = '\u00BA';
		ch = '\u00B0';
		ch = (char)60;  // 60 less than
		ch = (char)176; // 176 degree 
		// exclude 48 to 57, 65 to 90, 97 to 122
		
		
		//&#176
        //int asciiCode = uniChar;
        // type casting char as int
        int asciiValue = (int)ch;
        //System.out.println("ch: " +ch+" asciiValue " + asciiValue);
		
		
		//System.out.println("File: " + file.getName());
		boolean deleteflag = false;
		
		// SWITCHES FOR RUNNING CODE
		boolean connectRESTService = false;
		boolean processFilesInFolder = false;
		boolean connecttoFTP = false;
		boolean connecttoSFTP = false;
		boolean lookfortaginFolder = false;
		boolean createtestzip = false;
		boolean scheduler = false;
		
		//Pattern p = Pattern.compile("^\d{4}\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$");
		//Matcher m = p.matcher("some text 2020-08-09");
		//System.out.println(" %%%% "+m.group(1));
				
		long timerdelay = 60000;  //one minute
		
		if (scheduler)
		{
			
			
			Timer time = new Timer(); // Instantiate Timer Object
			ScheduledTask st = new ScheduledTask(); // Instantiate SheduledTask class
			time.schedule(st, 5000, 30*timerdelay); // Create Repetitiv
					
			//TestJSch testsftp  = new TestJSch();
			//testsftp.connectSFTP();
			
			
		}
		
		if (createtestzip) 
		{
			FileWriter fw1=new FileWriter("c:\\raj\\CA-TMK-UPDATE_2020-04-07-2020-04-13_index.txt");
			String zipfilename = "CA-TMK-UPDATE_2020-04-07-2020-04-13_dummy_001.zip";
			//List of filename(s) within this export batch: 
				//1- CA-TMK-UPDATE_2020-04-07-2020-04-13_119278_2022287_001.zip

			fw1.write("List of filename(s) within this export batch: ");
			fw1.write("\n1- "+zipfilename);
			fw1.close();
			
			//"C:\\Users\\rajkrish\\Box Sync\\CompuMark Shared Folder\\Project Phase\\PI 2\\Testing\\Test Run 06172020";
			
		}
		
		
		if (connecttoFTP) 
		{
			//FTPConnectDemo ftpcd = new FTPConnectDemo();
			//ftpcd.connect();
				
		}
			
		if (connecttoSFTP) 
		{
			TestJSch testsftp  = new TestJSch();
			testsftp.connectSFTP();
					
		}
							
		//create objects needed at main level
				
		String foldername = "C:/Users/rajkrish/Box Sync/CompuMark Shared Folder/CompuMark provided Docs/Register Files/Canada/Source/";
		foldername = "C:/raj/CA-TMK-UPDATE_2020-04-07-2020-04-13_119278_2022287_001/";
		//foldername = "C:/raj/CA-TMK-UPDATE_2020-04-14-2020-04-20_48810_2023125_001/";
		foldername = "C:/raj/CA-TMK-GLOBAL_2019-10-05_1184341_1200002_037";
		//foldername = "C:/raj/CA-TMK-GLOBAL_2019-10-05_1000141_1019415_027";
		foldername = "C:/raj/CA-TMK-UPDATE_2020-01-28-2020-02-03_66640_1912867_001";
		foldername = "C:\\raj\\Nov11";
				
		System.out.println("foldername "+foldername);

		File[] files = new File(foldername).listFiles();
	    System.out.println("Files size "+files.length);
	    FileWriter fw=new FileWriter("c:\\raj\\CIPOXMLQueryOutput.txt");
		QueryXML qxml = new QueryXML();	    
	    String xpathExpression = "//ST13ApplicationNumber/text() | //RegistrationOfficeCode/text() | //ClassTitleText/text()";
	    
	    xpathExpression = "//GoodsServicesClassification/ClassNumber/text() | //ClassDescriptionBag/ClassDescription/ClassNumber/text()";
	    xpathExpression = "//GoodsServicesDescriptionText/text()";
	    xpathExpression = "//Section9Code/text()";
	   xpathExpression = "//FootnoteFormattedText/TextLineBag/TextLine/text()";
	   xpathExpression = "//ClaimBag/Claim/ClaimCategoryType/text()";
	   xpathExpression = "//ClaimBag/Claim/ClaimText/text()";
	   xpathExpression = "//OppositionProceedingBag/OppositionCurrentStatusCategory/text() |  //OppositionProceedingBag/PlaintiffBag/Plaintiff/Contact/Name/EntityName/text()";
	   xpathExpression = "//ClassNumber/text() | //TrademarkBag/Trademark/@operationCategory";
	   xpathExpression = "//ClassNumber/text()"; 
	   //xpathExpression = "//MarkEventNationalMarkEvent/MarkEventDescriptionText/text()";
	   //xpathExpression = "//case-file/serial-number/text() | //action-keys/case-file/case-file-statements/case-file-statement/type-code/text()";
	  //String path1 = "//LegalProceedingsBag/LegalProceedings/OppositionProceedingBag/ProceedingStageBag/ProceedingStage/ProceedingEventBag/ProceedingEvent/NationalMarkEvent/MarkEventCode/text()";
	  //String path2 = "//LegalProceedingsBag/LegalProceedings/OppositionProceedingBag/ProceedingStageBag/ProceedingStage/ProceedingEventBag/ProceedingEvent/MarkEventDate/text()";
		  
	    if (processFilesInFolder || lookfortaginFolder ) 
	    {
	    	
	   
			    	
			    // PROCESS ALL XML FILES, DELETE ALL NON XML FILES
			    ArrayList<String> myarraylist = new ArrayList<String>();
			    
			   fw.write("List of filename(s) within this weekly batch: ");
			   int countfiles = 0;
		
			    for (File file : files) 
			    {
			        if (file.isDirectory()) 
			        {
			            System.out.println("Directory: " + file.getName());
			            //deletenonXMLFiles(file.listFiles()); // Calls same method again.
			        } 
			        else 
			        {
			            if (file.getName().contains(".xml")) 
			            	{
			            	countfiles++;
			            	if (processFilesInFolder) 	
			            	{
			            		qxml.getValueforXpath(fw, file, xpathExpression);
			            		
			            		//qxml.getValueforXpath(fw, file, "//InterestedPartyBag/InterestedParty/InterestedPartyCategory/text() | //InterestedPartyBag/InterestedParty/Contact/Name/EntityName/text()");
			            	}
			            	
			            	//if (processFilesInFolder) qxml.getValueforXpath(fw, file, path1 + " | "+path2);
			            	if (lookfortaginFolder) 
			            		{
			            			lookforTag(file, "<tmk:OppositionProceedingBag", fw);
			            			lookforTag(file, "<tmk:CancellationProceedings", fw);
			            			lookforTag(file, "<catmk:InterestedPartyBag", fw);
			            			
			            			//findfileswithnotag(file, "<tmk:ClassNumber>", fw);
			            			//findfileswithnotag(file, "<tmk:ClassNumber>\\r\\n", fw);
			            			//findfileswithnotag(file, "<tmk:GoodsServicesDescriptionText", fw);
			            			//findfileswithnotag(file, "<com:FileName>", fw);
			            		}
			            		
			            	// TAKING ARRAY VALUES AND REMOVE DUPLICATES
			            	//myarraylist = qxml.getUniqueValues(file, "takeitfromQueryXMLClass", myarraylist);
			            	//ArrayList<String> removeDuplicates = qxml.removeDuplicates(myarraylist);
			    		    
			    		    //for (int i= 0; i < removeDuplicates.size(); i++)
			    		    //	System.out.println("\n"+i+" "+removeDuplicates.get(i));
		
			       	    
			            	}
			            else if (file.getName().contains(".png") && file.getName().indexOf('-') == -1 ) 
				            {
			            	 //fw.write("\n IMAGE file | "+file.getName());
				            }
			            
			        }
			   }
			    
			    System.out.println("Total count of source XML files: " + countfiles);
	            fw.write(" processed all files");
			    	    
			   // END OF PROCESS ALL XML FILES
	    
	    }    		
	    
	    fw.close();
	    Instant finish = Instant.now();
	    System.out.println("End : " + finish);
	    long dur = Duration.between(start, finish).getSeconds();
	    System.out.println("Took "+dur+" seconds to run");  

	}
	
	// DELETE ALL NON XML FILES
	 public static void deletenonXMLFiles(File[] files) throws FileNotFoundException
	   {
		   
		 boolean deleteflag = false; 
		 String tagName = "<tmk:Trademark com:operationCategory=\"Create\">";
		    for (File file : files) {
		        if (file.isDirectory()) {
		            System.out.println("Directory: " + file.getName());
		            deletenonXMLFiles(file.listFiles()); // Calls same method again.
		        } 
		        else 
		        {
		            if (!file.getName().contains(".xml"))
		            	deleteflag = file.delete();
		            	
		            else if (file.getName().contains(".xml"))
		            	{
		            		//System.out.println("File: " + file.getName());
		            		lookforTag(file, tagName);
		            	}
		        }
		    }
		  System.out.println("finished looping");
		}
	 
	 

	 
	 // SEARCH FOR A CERTAIN TAG IN A FILE
	 public static void lookforTag(File file, String tagName) throws FileNotFoundException
	   {
		   //System.out.println("Inside lookforTag"); 
		   
		   final Scanner scanner = new Scanner(file);
		   
		   while (scanner.hasNextLine()) 
		   {
		      final String lineFromFile = scanner.nextLine();
		      if(lineFromFile.contains(tagName)) { 
		          // a match!
		          System.out.println("I found " +tagName+ " in file " +file.getName());
		          break;
		      }
		   }
		   scanner.close();
		   
		   
	   }
	 
	 
	 // SEARCH FOR A CERTAIN TAG IN A FILE and write to TextFile
	 public static void lookforTag(File file, String tagName, FileWriter fw2) throws FileNotFoundException,  IOException
	   {
		   //System.out.println("Inside lookforTag"); 
		   
		   final Scanner scanner = new Scanner(file);
		   int linenumber = 0;
		   
		   while (scanner.hasNextLine()) 
		   {
		      final String lineFromFile = scanner.nextLine();
		      linenumber++;
		      if ( (lineFromFile.contains(tagName))  )
		      { 
		          // a match!
		          //System.out.println("I found " +tagName+ " in file " +file.getName());
		    	  fw2.write("\n"+file.getName()+"  tagName "+tagName+ " linenumber "+linenumber);
		          //break;
		      }
		   }
		   scanner.close();
		   
		   
	   }
	 
	 // SEARCH FOR FILEs THAT DO NOT HAVE A CERTAIN TAG
	 public static boolean findfileswithnotag(File file, String tagName, FileWriter fw2) throws IOException
	   {
		  
		   final Scanner scanner = new Scanner(file);
		   boolean matchfound = false;
		   while (scanner.hasNextLine()) 
		   {
		      final String lineFromFile = scanner.nextLine();
		      if(lineFromFile.contains(tagName)) 
		      { 
		    	  matchfound = true;
		    	  System.out.println("found match"+matchfound);
		    	  scanner.close();
		    	  return matchfound;
		      }
		   }
		   System.out.println("** found match"+matchfound);
		   if (!matchfound) 
			   System.out.println("\n no "+tagName+" found in file:"+file.getName());
		   
		   fw2.write("\n no "+tagName+" found in file:"+file.getName());
		   
		   scanner.close();
		   return true;
		   
	   }
	 
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


	   


	
	
	

