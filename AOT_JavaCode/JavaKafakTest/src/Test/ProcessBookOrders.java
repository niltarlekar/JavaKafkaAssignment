package Test;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ProcessBookOrders {

	
	public ProcessBookOrders() {
		// TODO Auto-generated constructor stub
		
		
	}
	
	public void BookOrders(Element element ) throws Exception{
		// TODO Auto-generated constructor stub
		String TopicName="";
		String Message="";
	    
		/*System.out.println("Order Received : " 
	               + element.getAttribute("category"));
	            System.out.println("OrderNumber : " 
	               + element
	               .getElementsByTagName("OrderNumber")
	               .item(0)
	               .getTextContent());
	            System.out.println("BookName : " 
	               + element
	               .getElementsByTagName("BookName")
	               .item(0)
	               .getTextContent());
	            System.out.println("BookAuthor : " 
	               + element
	               .getElementsByTagName("BookAuthor")
	               .item(0)
	               .getTextContent());
	            System.out.println("Price : " 
	               + element
	               .getElementsByTagName("Price")
	               .item(0)
	               .getTextContent());*/
	            
	            //Call New Status to process New Order
	             NewStatus(element);
	}
	
	public void NewStatus(Element element ) throws Exception{
		String Message="";
		String TopicName="order.status";
		SimpleProducer producer =new SimpleProducer();
		Message="OrderNumber: "+element.getElementsByTagName("OrderNumber").item(0).getTextContent()+"  Revceived with Status NEW";
        producer.publishMessage(TopicName,Message,"");
        
        //After First Step process call ShippedStatus
        
        ShippedStatus(element);
		
	}
	public void ShippedStatus(Element element ) throws Exception{
		String Message="";
		String TopicName="order.status";
		SimpleProducer producer =new SimpleProducer();
		Message="OrderNumber: "+element.getElementsByTagName("OrderNumber").item(0).getTextContent()+"  Changed to Status SHIPPED";
        producer.publishMessage(TopicName,Message,"");
        
        //After First Step process call DeliveredStatus
        
        DeliveredStatus(element);
		
	}
	public void DeliveredStatus(Element element ) throws Exception{
		String Message="";
		String TopicName="order.status";
		SimpleProducer producer =new SimpleProducer();
		Message="OrderNumber: "+element.getElementsByTagName("OrderNumber").item(0).getTextContent()+"  Changed to  Status DEVLIVERED";
        producer.publishMessage(TopicName,Message,"");
		
	}

}
