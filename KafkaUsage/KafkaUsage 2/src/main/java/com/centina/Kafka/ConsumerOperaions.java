package com.centina.Kafka;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.centina.Kafka.model.Employee;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Centina
 *
 */
public class ConsumerOperaions {

	/**
	 * The Kafka consumer
	 */
	private Consumer<String, String> consumer;

	/**
	 * The input topic
	 */
	private final String topicName;

	/**
	 * The Kafka IP
	 */
	private String kafkaIP;

	/**
	 * The Kafka Port
	 */
	private String kafkaPort;

	/**
	 * The Constructor to define default values
	 * 
	 * @param topic,     The topic name
	 * @param kafkaIP,   The Kafka IP
	 * @param kafkaPort, The Kafka Port
	 */
	public ConsumerOperaions(String topic, String kafkaIP, String kafkaPort) {
		consumer = null;
		this.topicName = topic;
		this.kafkaIP = kafkaIP;
		this.kafkaPort = kafkaPort;
	}

	/**
	 * This method is used to start the Kafka consumer
	 */
	public void startConsumer() {
		StringBuilder bootstrapServers = new StringBuilder(kafkaIP);
		bootstrapServers.append(':').append(kafkaPort);

		Properties props = new Properties();
		props.put("bootstrap.servers", bootstrapServers.toString());
		props.put("group.id", "test12");
		props.put("enable.auto.commit", "false");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("auto.offset.reset", "latest");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		consumer = new KafkaConsumer<String, String>(props);
	}

	/**
	 * This method is used to process the SPM data to SLA data
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public void fetchData() throws JsonParseException, JsonMappingException, IOException {
		long startingTime = System.currentTimeMillis();

		// subscribe to kafka topic list
		consumer.subscribe(Collections.singletonList(topicName));

		// poll the Kafka to fetch the data from the subscribed Kafka topics
		ConsumerRecords<String, String> records = consumer.poll(1000);

		// Object mapper instance
		ObjectMapper mapper = new ObjectMapper();
		Employee emp = null;

		// Process the SPM data
		for (ConsumerRecord<String, String> record : records) {
			System.out.println(
					"Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());

			emp = mapper.readValue(record.value(), Employee.class);

		}

		if (emp != null) {
			System.out.println(emp);
		}

		long endingTime = System.currentTimeMillis();
		consumer.commitSync();

		System.out.println("\n\n\nThe recieved data count is: " + records.count() + " in " + (endingTime - startingTime)
				+ " milliseconds \n\n");
	}

	/**
	 * This method is used to close the consumer
	 */
	public void closeConsumer() {
		consumer.close();
	}
}
