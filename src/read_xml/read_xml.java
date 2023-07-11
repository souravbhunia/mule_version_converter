package read_xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.util.Scanner;

public class read_xml {
	
	
	
	

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the directory where to search ");
		String directory = null;
	try {
		 directory = scan.next(); //"D:\\projects12MAY\\hello-world";
		
	}catch(Exception e ){
		e.printStackTrace();
	}
	
		
		File directoryPath = new File(directory);
		// List of all files and directories
		File filesList[] = directoryPath.listFiles();
		
		//String contents[] = directoryPath.list();
		//System.out.println("List of files and directories in the specified directory:"+ directory);
		// for(int i=0; i<contents.length; i++) {
		// if(contents[i].equalsIgnoreCase("pom.xml"))System.out.println(contents[i]);
		// }
		String filepath = null;
		for (File file : filesList) {
			if (file.getName().equalsIgnoreCase("pom.xml"))
			{
				filepath=file.getAbsolutePath();
				System.out.println("Absolute path of pom.xml is: "+ file.getAbsolutePath());
			}
				

		}
		//System.out.println(filepath);

		
		
		
		//Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Load the input XML document, parse it and return an instance of the
        // Document class.
        Document doc  = builder.parse(filepath);

        
//        NodeList nodeList = document.getDocumentElement().getChildNodes();
//        for (int i = 0; i < nodeList.getLength(); i++) {
//             Node node = nodeList.item(i);
//             if (node.getNodeType() == Node.ELEMENT_NODE) {
//                  Element elem = (Element) node;
//
//                  // Get the value of the ID attribute.
//                  //String ID = node.getAttributes().getNamedItem("project").getNodeName();
//                  String name= elem.getNodeName();
//                 // String value=elem.getElementsByTagName("dependency").item(0).getTextContent();
//                  
//                         
//               
//					
//                  System.out.println("name "+name+"   -------------");
//             }
        
        doc.getDocumentElement().normalize();

        XPath xPath =  XPathFactory.newInstance().newXPath();

        String expression = "/project/properties/app.runtime";	        
        NodeList nodeList = null;
		try {
			nodeList = (NodeList) xPath.compile(expression).evaluate(
			   doc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        for (int i = 0; i < nodeList.getLength(); i++) {
           Node nNode = nodeList.item(i);
           System.out.println("\nCurrent Element :" + nNode.getNodeName());
           
           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
              Element eElement = (Element) nNode;
        
              
              
          
             
            
          
              System.out.println("app.runtime version  : "+eElement.getTextContent());
              //eElement.setNodeValue("4.4.4-20211222");
              eElement.setTextContent("4.4.4-20211222");
              System.out.println("After replacement app.runtime version is : "+eElement.getTextContent());
              
              System.out.println("REPLACING DATA___________________");
              
              Transformer xformer = TransformerFactory.newInstance().newTransformer();
              xformer.transform(new DOMSource(doc), new StreamResult(filepath));
              System.out.println("END_____________________________END");
                   
        }

        }
        
        //for dependencies 
        
        String expression1 = "/project/dependencies/dependency";	        
        NodeList nodeList1 = null;
		try {
			nodeList1 = (NodeList) xPath.compile(expression1).evaluate(
			   doc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        for (int i = 0; i < nodeList1.getLength(); i++) {
           Node nNode = nodeList1.item(i);
          // System.out.println("\nCurrent Element :" + nNode.getNodeName());
           
           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
              Element eElement = (Element) nNode;
              String mule_code =eElement.getElementsByTagName("artifactId").item(0).getTextContent();
              //System.out.println("mule http is "+ mule_http);
              
             //mule-http-connector
              if( mule_code.equals("mule-http-connector")  )
              {
            	  
            	  System.out.println("version of mule-http-connector : "+ eElement.getElementsByTagName("version").item(0).getTextContent());
            	  //eElement.setTextContent("1.2.21");
            	  eElement.getElementsByTagName("version").item(0).setTextContent("2.2.20");
            	  System.out.println("REPLACING DATA___________________");
            	  System.out.println("After replacing version of mule-http-connector : "+ eElement.getElementsByTagName("version").item(0).getTextContent());
            	  Transformer xformer = TransformerFactory.newInstance().newTransformer();
                xformer.transform(new DOMSource(doc), new StreamResult(filepath));
                System.out.println("END_____________________________END");
              
              }
              //munit-runner
              if( mule_code.equals("munit-runner")  )
              {
            	  
            	  System.out.println("version of munit runner : "+ eElement.getElementsByTagName("version").item(0).getTextContent());
            	  //eElement.setTextContent("1.2.21");
            	  eElement.getElementsByTagName("version").item(0).setTextContent("1.2.22");
            	  System.out.println("REPLACING DATA___________________");
            	  System.out.println("After replacing version of munit runner : "+ eElement.getElementsByTagName("version").item(0).getTextContent());
            	  Transformer xformer = TransformerFactory.newInstance().newTransformer();
                xformer.transform(new DOMSource(doc), new StreamResult(filepath));
                System.out.println("END_____________________________END");
              
              }
             //munit-tools
              if( mule_code.equals("munit-tools")  )
              {
            	  
            	  System.out.println("version of munit-tools : "+ eElement.getElementsByTagName("version").item(0).getTextContent());
            	  //eElement.setTextContent("1.2.21");
            	  eElement.getElementsByTagName("version").item(0).setTextContent("1.3.22");
            	  System.out.println("REPLACING DATA___________________");
            	  System.out.println("After replacing version of munit-tools : "+ eElement.getElementsByTagName("version").item(0).getTextContent());
            	  Transformer xformer = TransformerFactory.newInstance().newTransformer();
                xformer.transform(new DOMSource(doc), new StreamResult(filepath));
                System.out.println("END_____________________________END");
              
              }
              
              //database
              if( mule_code.equals("mule-db-connector")  )
              {
            	  
            	  System.out.println("version of mule-db-connector : "+ eElement.getElementsByTagName("version").item(0).getTextContent());
            	  //eElement.setTextContent("1.2.21");
            	  eElement.getElementsByTagName("version").item(0).setTextContent("1.3.22");
            	  System.out.println("REPLACING DATA___________________");
            	  System.out.println("After replacing version of mule-db-connector : "+ eElement.getElementsByTagName("version").item(0).getTextContent());
            	  Transformer xformer = TransformerFactory.newInstance().newTransformer();
                xformer.transform(new DOMSource(doc), new StreamResult(filepath));
                System.out.println("END_____________________________END");
              
              }
              
              //mq
              if( mule_code.equals("anypoint-mq-connector")  )
              {
            	  
            	  System.out.println("version of anypoint-mq-connector : "+ eElement.getElementsByTagName("version").item(0).getTextContent());
            	  //eElement.setTextContent("1.2.21");
            	  eElement.getElementsByTagName("version").item(0).setTextContent("1.3.22");
            	  System.out.println("REPLACING DATA___________________");
            	  System.out.println("After replacing version of anypoint-mq-connector: "+ eElement.getElementsByTagName("version").item(0).getTextContent());
            	  Transformer xformer = TransformerFactory.newInstance().newTransformer();
                xformer.transform(new DOMSource(doc), new StreamResult(filepath));
                System.out.println("END_____________________________END");
              
              }
              //salesforce
              if( mule_code.equals("mule-salesforce-connector")  )
              {
            	  
            	  System.out.println("version of mule-salesforce-connector : "+ eElement.getElementsByTagName("version").item(0).getTextContent());
            	  //eElement.setTextContent("1.2.21");
            	  eElement.getElementsByTagName("version").item(0).setTextContent("1.3.22");
            	  System.out.println("REPLACING DATA___________________");
            	  System.out.println("After replacing version of mule-salesforce-connector : "+ eElement.getElementsByTagName("version").item(0).getTextContent());
            	  Transformer xformer = TransformerFactory.newInstance().newTransformer();
                xformer.transform(new DOMSource(doc), new StreamResult(filepath));
                System.out.println("END_____________________________END");
              
              }

        }
           
          
		
			
		
		
        }
        
        
        
        
        
        
	}
}