package Test;
import java.util.Properties;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.io.*;
public class SimpleProducer {
 
 public static void main(String[] args) throws Exception{
    
	
 
    
 }
 
 public String publishMessage(String Topic,String Value,String FileName) {
	 Properties p=new Properties();
	 FileReader reader=null;
	 String Message="";
	 String basePath = new File("").getAbsolutePath();
	 basePath=basePath+"\\KafkaProperties.properties";
	 System.out.println(basePath);
	 try {
	 reader=new FileReader(basePath); 
	 p.load(reader);
	 }catch(Exception e) {
		 
	 }
	    
     String topicName = Topic;
     Properties props = new Properties();   
     props.put("bootstrap.servers", p.getProperty("bootstrap.servers"));     
     props.put("acks", "all");
     props.put("retries", 0);
     props.put("batch.size", 16384);
     props.put("linger.ms", 1);
     props.put("buffer.memory", 33554432);
     props.put("key.serializer", 
       "org.apache.kafka.common.serialization.StringSerializer"); 
     props.put("value.serializer", 
       "org.apache.kafka.common.serialization.StringSerializer");
    props.put("security.protocol",p.getProperty("ssl.protocol"));
    props.put("ssl.protocol",p.getProperty("ssl.protocol"));
	props.put("ssl.truststore.location",p.getProperty("ssl.truststore.location"));
	props.put("ssl.truststore.password",p.getProperty("ssl.truststore.password"));
	props.put("ssl.truststore.type",p.getProperty("ssl.truststore.type"));
	props.put("ssl.endpoint.identification.algorithm",p.getProperty("ssl.endpoint.identification.algorithm"));
	props.put("ssl.enabled.protocals","TLSv1.2,TLSv1.1,TLSv1");
	try {
    Producer<String, String> producer = new KafkaProducer
       <String, String>(props);  
    	Message= "Order Published Successfully to Topic :"+topicName ;
        producer.send(new ProducerRecord<String, String>(topicName, 
        		FileName,Value));
              System.out.println(Message);
              producer.close();
              
	}catch(Exception e) {
		System.out.println("Error while publishing Message");
		Message= "Error while publishing Message";
	}
	return Message;
	
}
}