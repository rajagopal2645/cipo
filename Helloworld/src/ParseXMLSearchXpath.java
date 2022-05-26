import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.util.*;
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.List;


public class ParseXMLSearchXpath {

	
	   public static void main(String[] args) throws ParserConfigurationException, SAXException, Exception
	   {
		   String filename = "C:/temp/employees.xml";
		   filename = "C:/raj/CA-TMK-UPDATE_2020-03-31-2020-04-06_147635_2021252_001/147635-02.xml";
		   String xpathExpression = "";
			   
		   //Get Document Builder
		   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		   DocumentBuilder builder = factory.newDocumentBuilder();
		  	     
		  Document document = builder.parse(new File(filename));
		   
		   //xpathExpression = "/employees/employee/@id";
		  //xpathExpression = "/employees/employee[lastName='Beckham']/department/name/text()";
		  //xpathExpression = "//department/name/text()";
		  xpathExpression = "//ST13ApplicationNumber/text() | //RegistrationOfficeCode/text() | //ClassTitleText/text()";
		  xpathExpression = "//TrademarkBag/Trademark/RegistrationOfficeCode/text()";
		  
	      System.out.println( evaluateXPath(document, xpathExpression) );
		
	      
	            
	      
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
	 
	   
	  
	   
	   
	   
	   //This function is called recursively
	   private static void visitChildNodes(NodeList nList)
	   {
		   System.out.println("* nList.getLength() "+nList.getLength());
		   for (int temp = 0; temp < nList.getLength(); temp++)
	      {
	         Node node = nList.item(temp);
	         System.out.println("** node.getNodeName() :" + node.getNodeName());
	         if (node.getNodeType() == Node.ELEMENT_NODE)
	         {
	            System.out.println("*** Node Name = " + node.getNodeName() + "; Value = " + node.getTextContent()+ " ** end getNodeName node.hasChildNodes() "+node.hasChildNodes());
	            //Check all attributes
	            if (node.hasAttributes()) {
	               // get attributes names and values
	            	System.out.println("**** "+node.getNodeName() + "has attributes "+node.hasAttributes());
	               NamedNodeMap nodeMap = node.getAttributes();
	               for (int i = 0; i < nodeMap.getLength(); i++)
	               {
	                   Node tempNode = nodeMap.item(i);
	                   System.out.println("Attr name : " + tempNode.getNodeName()+ "; Value = " + tempNode.getNodeValue());
	               }
	               if (node.hasChildNodes()) {
	                  //We got more childs; Let's visit them as well
	                  visitChildNodes(node.getChildNodes());
	               }
	           }
	         }
	      }
	   }
	   // end of visitChildNodes(NodeList nList)
	   
	   
	   
	   
}




/*
private static Date formatDatesfromCollectionandReturnMaxDate(NameofBAg, NameofField, 'ddd/yy/mm')
{
   
	  // 1. 
	  // 2. 
	  // 3. 
	  // 4. 
	  // 5. 
	   return Date;
}
*/
// end of FormatDatesfromaCollectionm(NodeList nList) 


/* private static String doSomething(Attorney[], AgentNames[], 'MM/DD/YYYY')
{
   
      // 1. Step1  Sort array 1 and find most recent value for Attorney based on date and find Attorney id
      // 2. Step2  Find agent corresponding to Attorney id and get last Contact Date
      // 3. Step3  Format the date to desired format
       
       int i = getMostRecentAttorney(Attorney[]);
       Date lastcontactdate = getAgentContactDateforAttorney(AgentNames[],i);
       return dateFormat(lastcontactdate, 'MM/DD/YYYY');

       
}*/


/*
//throws ParserConfigurationException, SAXException, IOException
	   
 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
 DocumentBuilder builder = factory.newDocumentBuilder();
	     
Document document = builder.parse(new File(filename));
 
Normalize the XML Structure; It's just too important 
document.getDocumentElement().normalize();
 
Here comes the root node
Element root = document.getDocumentElement();
System.out.println(root.getNodeName());
 
NodeList nList = document.getElementsByTagName("<tmk:TagName");
//System.out.println("============================");

visitChildNodes(nList);
*/
