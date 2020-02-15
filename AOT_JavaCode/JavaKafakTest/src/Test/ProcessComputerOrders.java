package Test;

import org.w3c.dom.Element;

public class ProcessComputerOrders {
	SimpleProducer producer =new SimpleProducer();
	 String Message="";
	public ProcessComputerOrders() {
		// TODO Auto-generated constructor stub
	}
	
	public void ComputerOrders(Element eElement ) throws Exception {
		// TODO Auto-generated constructor stub
		
		String TopicName="";
		/*System.out.println("category : " 
                + eElement.getAttribute("category"));
             System.out.println("OrderNumber : " 
                + eElement
                .getElementsByTagName("OrderNumber")
                .item(0)
                .getTextContent());
             System.out.println("ComputerModel : " 
                + eElement
                .getElementsByTagName("ComputerModel")
                .item(0)
                .getTextContent());
             System.out.println("ComputerMake : " 
                + eElement
                .getElementsByTagName("ComputerMake")
                .item(0)
                .getTextContent());
             System.out.println("Price : " 
                + eElement
                .getElementsByTagName("Price")
                .item(0)
                .getTextContent());*/
                 TopicName="order.status";
                 //Thread.sleep(999);
                 //Call New Status to process New Order
                 NewStatus(eElement);
	               
	}
	
	public void NewStatus(Element element ) throws Exception{
		String Message="";
		String TopicName="order.status";
		Message="OrderNumber: "+element.getElementsByTagName("OrderNumber").item(0).getTextContent()+"  Revceived with Status NEW";
        producer.publishMessage(TopicName,Message,"");
        
        
        
        ShippedStatus(element);
		
	}
	public void ShippedStatus(Element element ) throws Exception{
		String Message="";
		String TopicName="order.status";
		Message="OrderNumber: "+element.getElementsByTagName("OrderNumber").item(0).getTextContent()+"  Changed to Status SHIPPED";
        producer.publishMessage(TopicName,Message,"");
        
        //After First Step process call ShippedStatus
        
        DeliveredStatus(element);
		
	}
	public void DeliveredStatus(Element element ) throws Exception{
		String Message="";
		String TopicName="order.status";
		Message="OrderNumber: "+element.getElementsByTagName("OrderNumber").item(0).getTextContent()+"  Changed to  Status DEVLIVERED";
        producer.publishMessage(TopicName,Message,"");
		
	}
}
