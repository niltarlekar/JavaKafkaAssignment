package Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OrderManagementClass {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String basePath = new File("").getAbsolutePath();
		basePath=basePath+"\\SampleTestFileOrderCreation\\";
		System.out.println(basePath);
		File folder = new File(basePath);
		File[] listOfFiles = folder.listFiles();
		SimpleProducer producer =new SimpleProducer();
		//Consumer consumer =new Consumer();
		String TopicName="";
        try {
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        String data = ""; 
		        data = new String(Files.readAllBytes(Paths.get(file.getPath()))); 
		        //System.out.println(data);
		        TopicName="order.create";
		        producer.publishMessage(TopicName,data.toString(),file.getName().toString());
		        
		    }
		}
        }catch(Exception e) {
        	System.out.println("Error while Reading File");
        }

	}

}
