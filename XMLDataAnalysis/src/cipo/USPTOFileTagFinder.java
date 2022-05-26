package cipo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class USPTOFileTagFinder {
	
	
	
	public static void main(String[] args) throws IOException, FileNotFoundException, ParserConfigurationException, SAXException, Exception, InterruptedException
	{
	
		
	Instant start = Instant.now();
	System.out.println("Start : " + start);
	FileWriter fw = new FileWriter("c:\\raj\\USPTOFileOutput.txt");
	
	
	// SWITCHES FOR RUNNING CODE
			boolean connectRESTService = false;
			boolean processFilesInFolder = true;
			boolean connecttoFTP = false;
			boolean connecttoSFTP = false;
			boolean lookfortaginFolder = false;
			boolean createtestzip = false;
			boolean scheduler = false;
			
			String inputfilename = "C:\\Users\\rajkrish\\Desktop\\TICR0819.txt";
			File inputfile = new File(inputfilename);
			String searchstring = "<serial-number>";
			
			
			
			//
			lookforTag(inputfile,searchstring, fw);
				
			
			
			
			 	fw.close();
			    Instant finish = Instant.now();
			    System.out.println("End : " + finish);
			    long dur = Duration.between(start, finish).getSeconds();
			    System.out.println("Took "+dur+" seconds to run");  
			    
	}
			    

	public static void lookforTag(File file, String tagName, FileWriter fw) throws IOException
	{
					   //System.out.println("Inside lookforTag"); 
					   
					   final Scanner scanner = new Scanner(file);
					   
					   while (scanner.hasNextLine()) 
					   {
					      final String lineFromFile = scanner.nextLine();
					      if(lineFromFile.contains(tagName)) { 
					          // a match!
					          System.out.println("Found " +tagName+ "   "+lineFromFile );
					          fw.write("\n Found " +tagName+ " value :"+lineFromFile);
					          //break;
					      }
					   }
					   
					   
	}


}

