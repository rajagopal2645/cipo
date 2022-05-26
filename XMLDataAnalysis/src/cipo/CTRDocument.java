package cipo;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class CTRDocument 
{
	

	   public static String getHTML(String urlToRead) throws Exception {
	      StringBuilder result = new StringBuilder();
	      //String encodedURL = encodeValue(urlToRead);
	      URL url = new URL(urlToRead);
	      
	    
	      javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
	    		  new javax.net.ssl.HostnameVerifier(){

	    		      public boolean verify(String hostname,
	    		              javax.net.ssl.SSLSession sslSession) {
	    		    	  
	    		    	  if (hostname.equals("perseus-crud-test-257461168.us-west-2.elb.amazonaws.com")) {
	    		                return true;
	    		            }
	    		            return false;
	    		    	  
	    		          }
	    		  });
	    
	      
	     
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	      return result.toString();
	   }

	   public static void main(String[] args) throws Exception
	   {
	     System.out.println(getHTML(args[0]));
	   }
	   
	   
}


