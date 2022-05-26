import java.util.Scanner;
import java.awt.List;
import java.io.*;
import java.util.ArrayList;
import java.time.Instant;
import java.time.Duration;

public class HelloWorld {

	static ArrayList<Long> prime = new ArrayList<Long>();
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		
		//int i = 10;
		
		String text = "Thank You  ";
		
		String[] teachers= { "Ms.Schwartz", "Ms.Pederson", "Mr.McManus", "Mr.Stella" };
		
		
	
		Instant start = Instant.now();
		System.out.println("Start : " + start);
		int limit = 150;
		int[] value = new int[(limit+2)];
		value[0]=0;
		value[1]=1;
		
		/*
		for (int i=2;i<limit;i++)
		{
			value[i] = value[i-1]+value[i-2];
			System.out.println("\n value[i]: " + value[i]);
		}
		*/
		
		for (int i=0;i<=10;i++)
			doRandomTrials(1500+100*i,0.7);	
		
		Instant finish = Instant.now();
	    System.out.println("\n End : " + finish);
	    long dur = Duration.between(start, finish).toMillis();
	    System.out.println("Took "+dur+" milliseconds to run");  
	}
	
	
	
	 public static boolean checkprime(long i) 
	   {
		 boolean retflag = true;
		 		 
		 
		 for (long c=2;c<Math.sqrt(i);c++)
			 if (i%c==0)
				 {
				 	retflag = false;
				 	break;
				 }
			 
		 	return retflag;
	   }
	
	
	
	 public static void doRandomTrials(int number, double probability) 
	   {
		 
		 int success = 0;
		 int failure = 0;
		 float succrate = 0;
		 for (int c=0;c<number;c++)
		 {
			 if (Math.random() < probability)
			 {
				 //System.out.println(c+" SUCCESS");
				 success++;
			 }
			 else
			 {
				 //System.out.println( c+" FAIL");
				 failure++;
			 } 
		 
		 }
		 succrate = (float) success/number;
		 //succrate = 8/10;
		 //System.out.println("total trials:"+number+" SUCCESS "+success+" FAILURE "+failure+" ** success rate **"+succrate);
		 System.out.println("total trials:"+number+" SUCCESS "+success+" FAILURE "+failure+" ** success rate **"+succrate);
		 	
	   }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 public static void showFiles(File[] files) throws FileNotFoundException
	   {
		   
		   String tagName = "<tmk:Trademark com:operationCategory=\"Create\">";
		    for (File file : files) {
		        if (file.isDirectory()) {
		            System.out.println("Directory: " + file.getName());
		            showFiles(file.listFiles()); // Calls same method again.
		        } 
		        else 
		        {
		            if (file.getName().contains(".xml"))
		            	{
		            		//System.out.println("File: " + file.getName());
		            		lookforTag(file, tagName);
		            	}
		        }
		    }
		  System.out.println("finished looping");
		}
	 
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
		   
		   
	   }
	   

}
