package spm_prototyping.kafka;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import spm_prototyping.dao.SPMData;

/**
 * @author Centina
 *
 */
public class SLAKafkaProducer {
	/**
	 * This is used to convert object to JSON and vice versa
	 */
	private ObjectMapper objectMapper;

	/**
	 * This is used store Kafka properties
	 */
	private Properties properties;

	/**
	 * Kafka producer
	 */
	private final Producer<String, String> producer;

	/**
	 * The constructor
	 */
	public SLAKafkaProducer(String kafkaIP, String kafkaPort) {
		StringBuilder bootstrapServers = new StringBuilder(kafkaIP);
		bootstrapServers.append(':').append(kafkaPort);

		properties = new Properties();
		properties.put("bootstrap.servers", bootstrapServers.toString());
		properties.put("group.id", "test");
		properties.put("enable.auto.commit", "false");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("session.timeout.ms", "30000");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		producer = new KafkaProducer<String, String>(properties);

		objectMapper = new ObjectMapper();
	}

	/**
	 * Push SPM data into Kafka
	 * 
	 * @param spmDataList, the list of SPM data
	 */
	public void runProducer(List<SPMData> spmDataList) {
		StringBuilder topicName = new StringBuilder();
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);

		topicName.append("input_sla_").append(strDate);

		for (SPMData spmData : spmDataList) {

			// convert the object into JSON
			String spmJson;
			try {
				spmJson = objectMapper.writeValueAsString(spmData);
				pushJSONDataIntoKafka(spmJson, topicName.toString());
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		closeWriter();
	}

	/**
	 * push JSON SPM formatted data into Kafka
	 * 
	 * @param jsonData,  The SPM data in JSON format
	 * @param topicName, The topic name to insert the data
	 */
	private void pushJSONDataIntoKafka(String jsonData, String topicName) {
		try {

			System.out.println("Feeding data to Kafka started");

			ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName.toString(), jsonData);
			producer.send(rec);
			System.out.println("Feeding data to Kafka completed");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void closeWriter() {
		producer.close();
	}

}
