package com.centina.Kafka;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

	private JSONObject getTMFFormattedAlarm(JSONObject uaaAlarm) {
		JSONObject tmfAlarm = new JSONObject();
		tmfAlarm.put("alarmType", uaaAlarm.get("eventtype"));
		tmfAlarm.put("perceivedSeverity", uaaAlarm.get("perceivedseverity"));
		tmfAlarm.put("probableCause", uaaAlarm.get("probablecause"));
		tmfAlarm.put("specificProblem", uaaAlarm.get("specificproblem"));
//		tmfAlarm.put("alarmedObjectType", uaaAlarm.get("sourceType"));
		tmfAlarm.put("alarmedObject", uaaAlarm.get("sourceid"));
		tmfAlarm.put("SourceSystemId", uaaAlarm.get("networkelementname"));
		tmfAlarm.put("alarmDetails", uaaAlarm.get("additionalinformation"));
		tmfAlarm.put("alarmState", uaaAlarm.get("cleared"));
		tmfAlarm.put("alarmRaisedTime", uaaAlarm.get("raisedtime"));
		tmfAlarm.put("alarmChangedTime", uaaAlarm.get("lastmodifiedtime"));
		tmfAlarm.put("alarmClearedTime", uaaAlarm.get("clearedtime"));
		tmfAlarm.put("alarmReportingTime", uaaAlarm.get("receivedtime"));
		tmfAlarm.put("ackState", uaaAlarm.get("acknowledged"));
		tmfAlarm.put("ackTime", uaaAlarm.get("acknowledgedtime"));
		tmfAlarm.put("ackUserId", uaaAlarm.get("owner"));
		tmfAlarm.put("clearUserId", uaaAlarm.get("clearedby"));
		tmfAlarm.put("serviceAffecting", uaaAlarm.get("serviceaffecting"));
		tmfAlarm.put("id", uaaAlarm.get("id"));
		tmfAlarm.put("isRootCause", uaaAlarm.get("isrootcause"));
		tmfAlarm.put("comment", uaaAlarm.get("lastcomment"));
		return tmfAlarm;
	}

	/**
	 * This method is used to process the SPM data to SLA data
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws ParseException
	 */
	public void fetchData() throws JsonParseException, JsonMappingException, IOException, ParseException {
		long startingTime = System.currentTimeMillis();

		// subscribe to kafka topic list
		consumer.subscribe(Collections.singletonList(topicName));

		// poll the Kafka to fetch the data from the subscribed Kafka topics
		ConsumerRecords<String, String> records = consumer.poll(1000);

		// Object mapper instance
		ObjectMapper mapper = new ObjectMapper();
		Employee emp = null;

		JSONParser parser = new JSONParser();

		// Process the SPM data
		for (ConsumerRecord<String, String> record : records) {
//			System.out.println(
//					"Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());

//			emp = mapper.readValue(record.value(), Employee.class);

			JSONObject responseObject = (JSONObject) parser.parse(record.value());

			List<JSONObject> listofAlarms = (List<JSONObject>) responseObject.get("alarms");

			for (JSONObject alarm : listofAlarms) {

				JSONObject tmfAlarm = getTMFFormattedAlarm(alarm);

				String eventtype = (String) alarm.get("eventtype");
				System.out.println("tmfAlarm   : " + tmfAlarm.toJSONString());
				System.out.println("Object JSON: " + JSONValue.toJSONString(responseObject));
//				System.out.println("Kafka  JSON: " + record.value());
			}

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
