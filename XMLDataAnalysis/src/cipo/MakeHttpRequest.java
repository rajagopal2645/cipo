package cipo;

import java.io.*;
import java.net.*;

public class MakeHttpRequest {
	

	   public String getHTML(String urlToRead) throws MalformedURLException, IOException {
	      StringBuilder result = new StringBuilder();
	      //String httprequeststring = "https://weather.com/weather/today/l/0335a70c951ae04e9d5525474901062baaa48c7106b0f335fa1efe2e0fc14dbb";
		  //String encodedURL = encodeValue(urlToRead);
	      URL url = new URL(urlToRead);
	     	
	    
	      javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
	    		  new javax.net.ssl.HostnameVerifier(){

	    		      public boolean verify(String hostname,
	    		              javax.net.ssl.SSLSession sslSession) {
	    		    	  
	    		    	  if (hostname.equals("perseus-crud-test-257461168.us-west-2.elb.amazonaws.com")) {
	    		                return true;
	    		            }
	    		    	  if (hostname.equals("perseus-crud-test.eks.test.int.compumark.com")) {
	    		                return true;
	    		            }
	    		    	  if (hostname.equals("pmgo-pl201.int.compumark.com")) {
	    		                return true;
	    		            }
	    		            return false;
	    		    	  
	    		          }
	    		  });
	    
	      
	     try
	     {
		      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		      conn.setRequestMethod("GET");
		      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		      String line;
		      while ((line = rd.readLine()) != null) {
		         result.append(line);
		      }
		      rd.close();
		      
	     }
	     
	     catch (java.io.IOException e)
	     {
	    	 System.out.println("Server returned HTTP response code: 400 for "+urlToRead);
	    	 //e.printStackTrace(System.out);
	     }
	     	     
	     /*
	       catch (MalformedURLException e)
	      
	     {
	    	 System.out.println("caught malformedurl");
	    	 e.printStackTrace(System.out);
	     }
	     catch ( Exception e)
	     {
	    	 e.printStackTrace(System.out);
	     }
	     */
	     
	     return result.toString();
	 }

	  

}
