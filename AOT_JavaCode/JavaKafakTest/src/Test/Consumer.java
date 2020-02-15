package Test;
import java.util.Properties;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
public class Consumer {

	public Consumer() {
		
	}
public static void main(String[] args) throws Exception{
	
	OrderFulfilmentApplication orderfull=new OrderFulfilmentApplication();
	String basePath = new File("").getAbsolutePath();
	basePath=basePath+"\\KafkaProperties.properties";
	System.out.println(basePath);
	FileReader reader=new FileReader(basePath); 
	Properties p=new Properties();
	p.load(reader);
	String topic = "order.create";
	Properties props = new Properties();
	props.put("bootstrap.servers", p.getProperty("bootstrap.servers"));   
	props.put("group.id", "TEST");
    props.put("acks", "all");
    props.put("retries", 0);
    props.put("batch.size", 16384);
    props.put("linger.ms", 1);
    props.put("buffer.memory", 33554432);
    props.put("key.deserializer",          
    	       "org.apache.kafka.common.serialization.StringDeserializer");
    	    props.put("value.deserializer", 
    	       "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("security.protocol",p.getProperty("ssl.protocol"));
    props.put("ssl.protocol",p.getProperty("ssl.protocol"));
	props.put("ssl.truststore.location",p.getProperty("ssl.truststore.location"));
	props.put("ssl.truststore.password",p.getProperty("ssl.truststore.password"));
	props.put("ssl.truststore.type",p.getProperty("ssl.truststore.type"));
	props.put("ssl.endpoint.identification.algorithm","");
	props.put("ssl.enabled.protocals","TLSv1.2,TLSv1.1,TLSv1");
	props.put("ssl.truststore.type","JKS");
	props.put("ssl.keystore.type","JKS");
	
    KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
    consumer.subscribe(Arrays.asList(topic));
    System.out.println("Subscribed to topic " + topic);
    int i = 0;
    String OrderXml="";
    while (true) {
        ConsumerRecords<String, String> records = consumer.poll(100);
           for (ConsumerRecord<String, String> record : records) {
        	   System.out.printf("Order Received offset = %d, key = %s, value = %s\n",record.offset(), record.key(), record.value());
        	    orderfull.getOrders(record.value());
             // System.out.printf("offset = %d, key = %s, value = %s\n",record.offset(), record.key(), record.value());
           }
           
     }     
	}

}
