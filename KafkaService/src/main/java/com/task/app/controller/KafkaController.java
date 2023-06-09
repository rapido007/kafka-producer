package com.task.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.app.model.Message;
import com.task.app.service.KafkaService;

@RestController
@RequestMapping("kafka")
public class KafkaController
{
	@Autowired
	private KafkaService service;
	
	public KafkaController()
	{
		System.out.println("Kafka Controller DC");
	}
	
//	http://localhost:9091/kafka/send?id=1&val=Third
	@RequestMapping("send")
	public String generateMessage(@RequestParam int id,@RequestParam String val)
	{
		String status = service.generateMessage(id,val);
		return status;
	}
	
	@RequestMapping("getFromMongo")
	public Message getFromMongoToKafkaTopic2(@RequestParam int id)
	{
		Message msg= service.messagesFromMongoDB(id);
		return msg;
	}
	

}
