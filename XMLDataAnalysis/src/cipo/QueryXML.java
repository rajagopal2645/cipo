package cipo;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;
import java.net.HttpURLConnection;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.List;
import java.net.*;


/*
 * import org.apache.http.HttpResponse;
 
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
*/


//Google API classes
//import com.google.api.GoogleAPI;
//import com.google.api.translate.Language;
//import com.google.api.translate.Translate;

public class QueryXML {
	
	String SOAPAction =
			"https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20200424T214035Z.326aa634301fe0f9.0a28585faa04ea5e86aa45105f345ccb804fdbcd&lang=fr-en&text=";
	String responseString = "";
	String outputString = "";
	
	
	public ArrayList<String> getUniqueValues(File file, String xpathExpression, ArrayList<String> myarraylist ) throws ParserConfigurationException, SAXException, Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		  	     
		Document document = builder.parse(file);
		// CHANGE ATTRIB NAME HERE 
		String xpath = "//OppositionCaseTypeDescriptionBag/OppositionCaseTypeDescription/text()";
		xpath = "//FootnoteStructured";
		xpath = "//InternationalMarkIdentifierBag/InternationalMarkIdentifier/text()";
		//xpath = "//TrademarkBag/UseLimitationText/text()";
		xpathExpression = "//TrademarkBag/Trademark/ApplicationNumber/ST13ApplicationNumber/text()";
		// "//PlaintiffBag/Plaintiff/RepresentativeBag/Representative/Contact/Name/EntityName/text()"
		List<String> mylist= evaluateXPath(document, xpath );

		  if (!mylist.isEmpty())
			  myarraylist.add(mylist.get(0));
		  
		  return myarraylist;

	}
	
	/*
	 public void callTranslateHTTP(String translatestring)
	 
	{
		 HttpClient client = new DefaultHttpClient();
		  HttpPost post = new HttpPost(SOAPAction+translatestring);
		  HttpResponse response = client.execute(post);
		  
		  BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			
			while ((responseString = in.readLine()) != null) 
			{
			outputString = outputString + responseString;
							}
			in.close();
			System.out.println(outputString);

	}
	
	
	public void callTranslate(String translatestring) throws IOException
	{
				
		java.net.URL url = new java.net.URL(SOAPAction+translatestring);
		URLConnection connection = url.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection)connection;
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
				
		byte[] buffer = new byte[SOAPAction.length()];
		buffer = SOAPAction.getBytes();
		bout.write(buffer);
		byte[] b = bout.toByteArray();
		
				//SOAPAction = SOAPAction+translatestring;
		httpConn.setRequestMethod("POST");		
		//httpConn.setRequestProperty("Content-Length",b);
				httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) Version/7.0.3 Safari/7046A194A");
				httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
				//httpConn.setRequestProperty("SOAPAction", SOAPAction);
				
				httpConn.setDoOutput(true);
				//httpConn.setDoInput(true);
				DataOutputStream wr = new DataOutputStream(httpConn.getOutputStream());
				wr.writeBytes("");
				wr.flush();
				wr.close();
				//Write the content of the request to the outputstream of the HTTP Connection.
				//out.write(b);
				
				//Ready with sending the request.
				 
				//Read the response.
				BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
				
				//Write the SOAP message response to a String.
				while ((responseString = in.readLine()) != null) 
				{
				outputString = outputString + responseString;
								}
				in.close();
				System.out.println(outputString);
				
				
	}
	
	
	public String translateString( String source ) {
	    String translatedText = "";
	    GoogleAPI.setHttpReferrer("http://code.google.com/p/google-api-translate-java/");
	    //Translate.setHttpReferrer("http://code.google.com/p/google-api-translate-java/");
	    try {
	        translatedText = Translate.execute(source, Language.ENGLISH, Language.FRENCH);
	    } catch (Exception e) {
	        System.err.println( "Exception " + e.getMessage() );
	    }
	    return removeSpaces ( translatedText );
	}
	 
	
	public translateFrenchtoEnglish()
	{
		//https://translation.googleapis.com/language/translate/v2
		Translate translate = TranslateOptions.getDefaultInstance().getService();

		 //Detection detection = translate.detect("Hola");
		 //String detectedLanguage = detection.getLanguage();

		 Translation translation = translate.translate("World",TranslateOption.sourceLanguage("en"),TranslateOption.targetLanguage("fr"));

		 System.out.printf("", translation.getTranslatedText());
	}
	
	*/
	
	
	

	public void getValueforXpath(FileWriter fw, File file, String xpathExpression ) throws ParserConfigurationException, SAXException, Exception
	{
		
		
		//filename = "C:/raj/CA-TMK-UPDATE_2020-03-31-2020-04-06_147635_2021252_001/147635-02.xml";
		   //xpathExpression = "//MarkEvent/NationalMarkEvent/MarkEventCode/text()";
			   
		   //xpathExpression =  "//FootnoteStructured/RegisteredDate/text()";
		   //xpathExpression =  "//InterestedPartyCategory/text()";
		   //xpathExpression =  "//MarkEvent/NationalMarkEvent/MarkEventAdditionalText/text()";
		   //xpathExpression = "//InternationalMarkIdentifierBag/InternationalMarkIdentifier/text()";
		   //xpathExpression = "//Section9GCNumber/text()";
		   //xpathExpression = "//CategorizedTextBag/CategorizedText/TextLineBag/TextLine/text()";
		   //xpathExpression = "//CommentText/text()";
		   //xpathExpression = "//CommentText[@languageCode=\"en\"]";
		   //xpathExpression = "//AssociatedApplicationNumber/ST13ApplicationNumber/text()";
		   //xpathExpression = "//AssociatedApplicationNumber/ST13ApplicationNumber/text()";
		   //xpathExpression= "//TrademarkBag/Trademark/UseLimitationText/text()";
			
		   //xpathExpression = "";
		   //xpathExpression = "";
		   //xpathExpression = "";
		   
		   //Get Document Builder
		   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		   DocumentBuilder builder = factory.newDocumentBuilder();
		   
		  	     
		  Document document = builder.parse(file);
		  List<String> mylist= evaluateXPath(document, xpathExpression);
	  
		  
		  		  		  
		if (mylist.isEmpty())
			
			{
			if  (true)		 //"CU"	
				//if (mylist.toString().contains(Ch1.toString()) || mylist.toString().contains(Ch2.toString()))			
			
				  {
					  //System.out.println("str ="+mylist.toString());	
					  //if (!mylist.toString().contains(" services (")                    )	
					  {
						  //System.out.println("** "+file.getName() + " | "+xpathExpression+" | "+mylist.toString() +" | "+str+" | "+str.length());
						  fw.write("\n"+file.getName() + " | "+xpathExpression+" | "+mylist.toString() +" | ");
					  }
					  //else 	
					  {
						  //System.out.println("** "+file.getName() + " | "+xpathExpression+" | "+mylist.toString() +" | "+str+" | "+str.length());
						  //fw.write("\n"+file.getName() + " | NO services ( | "+xpathExpression+" | "+mylist.toString() +" | ");
					  }
					  //fw.write(" | "+evaluateXPath(document, "//PlaintiffBag/Plaintiff/RepresentativeBag/Representative/Contact/Name/EntityName/text()"));
					  //fw.write(" | "+evaluateXPath(document, "//MarkCurrentStatusCode/text()"));
					  //fw.write(" | "+evaluateXPath(document, "//ApplicantFileReference/text()"));
					  //fw.write(" | "+evaluateXPath(document, "//ST13ApplicationNumber/text()"));
					  //fw.write(" | "+evaluateXPath(document, "//Trademark/@operationCategory"));
					  //fw.write(" | "+evaluateXPath(document, "//MarkEvent/NationalMarkEvent/MarkEventDescriptionText/text() | //MarkEvent/MarkEventDate/text()"));
					  //fw.write(" | "+evaluateXPath(document, "//MarkEvent/MarkEventDate/text()"));
					}
		
			}
		
		  		
	
		
	}
	
	
	
	
	 public <T> ArrayList<T> removeDuplicates(ArrayList<T> list) 
	    { 
	  
	        // Create a new ArrayList 
	        ArrayList<T> newList = new ArrayList<T>(); 
	  
	        // Traverse through the first list 
	        for (T element : list) { 
	  
	            // If this element is not present in newList 
	            // then add it 
	            if (!newList.contains(element)) { 
	  
	                newList.add(element); 
	            }
	            else System.out.println("FOUND DUPLICATE "+element.toString());
	        } 
	  
	        // return the new list 
	        return newList; 
	    } 
	  
	 
	
	  private static List<String> evaluateXPath(Document document, String xpathExpression) throws Exception 
	    {
	        // Create XPathFactory object
	        XPathFactory xpathFactory = XPathFactory.newInstance();
	         
	        // Create XPath object
	        XPath xpath = xpathFactory.newXPath();
	 
	        List<String> values = new ArrayList<>();
	        try
	        {
	            // Create XPathExpression object
	            XPathExpression expr = xpath.compile(xpathExpression);
	             
	            // Evaluate expression result on XML document
	            NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
	             
	            for (int i = 0; i < nodes.getLength(); i++) {
	                values.add(nodes.item(i).getNodeValue());
	            }
	                 
	        } catch (XPathExpressionException e) {
	            e.printStackTrace();
	        }
	        return values;
	    }
	
	
	  public String getnonstandardChar(String inspect)
	  {
		  int nameLenght = inspect.length(); // length of the string used for the loop
		  String returnval = "";

	    for(int i = 0; i < nameLenght ; i++)
	    {   // while counting characters if less than the length add one        
	        char character = inspect.charAt(i); // start on the first character
	        int asciivalue = (int) character;
	        if (asciivalue == 60 || asciivalue == 176) 
	        {
	        	//System.out.println("Found char " +asciivalue+ " in "+inspect);
	        	returnval = inspect;
	        	break;
	        }
	        
	        
	        	
	    }
	   //System.out.println(character+" = "+ ascii); // print the character and it's value in ascii
	    return returnval;
	  }

}
