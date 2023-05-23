package com.task.app.service;

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
		RestTemplate restTemp = new RestTemplate();
		try
		{
			restTemp.getForObject("http://localhost:8082/message/addMessage?id="+id+"&message="+val+"",Message.class);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return "Message generated successfully";
	}

}
