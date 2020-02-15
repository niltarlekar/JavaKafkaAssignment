package Test;

import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import java.io.StringReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OrderFulfilmentApplication {

	public static void main(String[] args) {
		
		
		
	}
public void getOrders(String Orders) throws Exception{
	Document doc = convertStringToXMLDocument(Orders);
	System.out.println(doc.getFirstChild().getNodeName());
	NodeList nList = doc.getElementsByTagName("Order");
	ProcessBookOrders books=new ProcessBookOrders();
	ProcessComputerOrders computer=new ProcessComputerOrders();
	for (int temp = 0; temp < nList.getLength(); temp++) {
		Node nNode = nList.item(temp);
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            if(eElement.getAttribute("category").equalsIgnoreCase("Book")) {
            	books.BookOrders(eElement);
            }else {
            	computer.ComputerOrders(eElement);
            	
            }
         }
		
		
	  }
	}

public  Document convertStringToXMLDocument(String xmlString) 
{
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = null;
    try
    {
        //Create DocumentBuilder with default configuration
        builder = factory.newDocumentBuilder();
        //Parse the content to Document object
        Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
        return doc;
    } 
    catch (Exception e) 
    {
        e.printStackTrace();
    }
    return null;
}

}
