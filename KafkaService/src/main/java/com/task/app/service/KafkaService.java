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
	
	public Message messagesFromMongoDB(int id)
	{
		Message msg = null;
		System.out.println("set");
		RestTemplate restTemp = new RestTemplate();
		try
		{
			msg = restTemp.getForObject("http://localhost:8084/message/getMessage?id="+id+"",Message.class);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		template.send("test2", msg);
		return msg;
	}
	
	

}
