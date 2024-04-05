package com.centina.Kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author Centina
 *
 */
public class ProducerOperations {
	/**
	 * The Kafka producer
	 */
	private Producer<String, String> producer;

	/**
	 * The Kafka topic to be used as output topic
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
	public ProducerOperations(String topic, String kafkaIP, String kafkaPort) {
		producer = null;
		this.topicName = topic;
		this.kafkaIP = kafkaIP;
		this.kafkaPort = kafkaPort;
	}

	/**
	 * This method will start the Kafka producer
	 */
	public void startProducer() {
		StringBuilder bootstrapServers = new StringBuilder(kafkaIP);
		bootstrapServers.append(':').append(kafkaPort);

		Properties properties = new Properties();
		properties.put("bootstrap.servers", bootstrapServers.toString());
		properties.put("group.id", "test");
		properties.put("enable.auto.commit", "false");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("session.timeout.ms", "30000");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		producer = new KafkaProducer<String, String>(properties);
	}

	/**
	 * Save SLA data to Kafka
	 * 
	 * @param slaData, The SLA data which has to be saved at Kafka server
	 */
	public void pushData(String stringData) {
		ProducerRecord<String, String> rec = new ProducerRecord<String, String>(this.topicName, stringData);
		producer.send(rec);
	}

	/**
	 * This method is used to close the Kafka producer
	 */
	public void closeProducer() {
		producer.close();
	}

}
