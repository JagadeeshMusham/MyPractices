package com.centina.KafkaUsage;

import java.io.IOException;

import com.centina.Kafka.ConsumerOperaions;
import com.centina.Kafka.ProducerOperations;
import com.centina.Kafka.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * Hello world!
 *
 */
/**
 * @author Centina
 *
 */
public class KafkaUsageApp {
	/**
	 * The topic name
	 */
	private static final String TOPIC_NAME = "test1";

	/**
	 * The Kafka IP
	 */
	private static final String KAFKA_IP = "127.0.0.1";

	/**
	 * The Kafka Port
	 */
	private static final String KAFKA_PORT = "9092";

	public static void main(String[] args) throws IOException {
		
		Employee emp = new Employee("Lokesh Gupta", "BNG");
		 
		//Object mapper instance
		ObjectMapper mapper = new ObjectMapper();
		 
		//Convert POJO to JSON
		String json = mapper.writeValueAsString(emp);
		
		System.out.println("JSON object:" + json);

		
		// The producer
		ProducerOperations producer = new ProducerOperations(TOPIC_NAME, KAFKA_IP, KAFKA_PORT);
		producer.startProducer();
		producer.pushData(json);

		// The Consumer
		ConsumerOperaions consumer = new ConsumerOperaions(TOPIC_NAME, KAFKA_IP, KAFKA_PORT);
		consumer.startConsumer();
		consumer.fetchData();
		consumer.closeConsumer();
	}
}
