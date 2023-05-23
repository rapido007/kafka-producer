package com.task.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.task.app.model.Message;

@Service
public class KafkaService 
{
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	
	public KafkaService()
	{
		System.out.println("Kafka Service DC");
	}
	

	public String generateMessage(int id,String val)
	{
		Message msg = new Message(id, val);
		template.send("test1", msg);
		System.out.println("set");
		RestTemplate restTemp = new RestTemplate();
		try
		{
			restTemp.getForObject("http://localhost:8084/message/addMessage?id="+id+"&message="+val+"",Message.class);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return "Message generated successfully";
	}
	
	public String messagesFromMongoDB()
	{
		List<Message> list = null;
		System.out.println("set");
		RestTemplate restTemp = new RestTemplate();
		try
		{
			list = restTemp.getForObject("http://localhost:8084/message/getAllMessages",List.class);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		template.send("test2", list);
		return "Data set from MongoDB to Kafka";
	}
	
	

}
