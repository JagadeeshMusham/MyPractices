package com.centina.KafkaUsage;

import java.io.IOException;

import org.json.simple.parser.ParseException;

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

	public static void main(String[] args) throws IOException, ParseException {
		
//		Employee emp = new Employee("Lokesh Gupta", "BNG");
		 
		//Object mapper instance
		ObjectMapper mapper = new ObjectMapper();
		 
		//Convert POJO to JSON
//		String json = mapper.writeValueAsString(emp);
		
		String json = "{\r\n" + 
				"	\"alarms\": [{\r\n" + 
				"\r\n" + 
				"		\"lastmodifiedtime\": \"Mon Feb 01 08:19:13 UTC 2021\",\r\n" + 
				"		\"sequencenumber\": 242,\r\n" + 
				"		\"raisedtime\": \"Mon Feb 01 08:19:12 UTC 2021\",\r\n" + 
				"		\"receivedtime\": \"Mon Feb 01 08:19:12 UTC 2021\",\r\n" + 
				"		\"subnetworkname\": null,\r\n" + 
				"		\"subnetworkid\": null,\r\n" + 
				"		\"networkelementname\": null,\r\n" + 
				"		\"networkelementid\": null,\r\n" + 
				"		\"eventtype\": \"Correlated service alarm\",\r\n" + 
				"		\"networkelementsequencenumber\": null,\r\n" + 
				"		\"sourcename\": null,\r\n" + 
				"		\"sourceid\": null,\r\n" + 
				"		\"perceivedseverity\": \"Critical\",\r\n" + 
				"		\"probablecause\": null,\r\n" + 
				"		\"specificproblem\": \"Service affecting network alarms\",\r\n" + 
				"		\"additionalinformation\": null,\r\n" + 
				"		\"acknowledged\": false,\r\n" + 
				"		\"owner\": null,\r\n" + 
				"		\"groupowned\": false,\r\n" + 
				"		\"acknowledgedtime\": null,\r\n" + 
				"		\"timetoacknowledge\": 0,\r\n" + 
				"		\"commented\": false,\r\n" + 
				"		\"cleared\": false,\r\n" + 
				"		\"clearedby\": null,\r\n" + 
				"		\"clearedtime\": null,\r\n" + 
				"		\"clearsequencenumber\": 0,\r\n" + 
				"		\"priority\": \"Normal\",\r\n" + 
				"		\"count\": 6,\r\n" + 
				"		\"transitory\": false,\r\n" + 
				"		\"direction\": null,\r\n" + 
				"		\"serviceaffecting\": false,\r\n" + 
				"		\"location\": null,\r\n" + 
				"		\"monitorvalue\": null,\r\n" + 
				"		\"thresholdlevel\": null,\r\n" + 
				"		\"timeperiod\": null,\r\n" + 
				"		\"sourcedescription\": null,\r\n" + 
				"		\"duration\": \"3 days 23:24:41 \",\r\n" + 
				"		\"servicealarmsequencenumber\": null,\r\n" + 
				"		\"deferred\": false,\r\n" + 
				"		\"deferreduntil\": null,\r\n" + 
				"		\"servicename\": \"to_A-3906-0009-backup_MPLS1-MPLS1<TVC>to_B-3926-0011-active_MPLS1-MPLS1\",\r\n" + 
				"		\"serviceid\": \"adcfe560-4411-4176-b2a9-31b7ab5a3a05\",\r\n" + 
				"		\"servicedescription\": \"Source Edge: B-3926-0011-active to Destination Edge: A-3906-0009-backup\",\r\n" + 
				"		\"servicetype\": \"TVC\",\r\n" + 
				"		\"profileid\": null,\r\n" + 
				"		\"resyncable\": null,\r\n" + 
				"		\"suppressed\": false,\r\n" + 
				"		\"suppressedbysequencenumber\": 0,\r\n" + 
				"		\"troubleticketid\": null,\r\n" + 
				"		\"lastraisedtime\": \"Thu Feb 04 12:31:22 UTC 2021\",\r\n" + 
				"		\"lastreceivedtime\": \"Thu Feb 04 12:31:22 UTC 2021\",\r\n" + 
				"		\"customername\": null,\r\n" + 
				"		\"customerid\": null,\r\n" + 
				"		\"undermaintenance\": false,\r\n" + 
				"		\"scheduledmaintenancename\": null,\r\n" + 
				"		\"lastcomment\": null,\r\n" + 
				"		\"id\": \"adcfe560-4411-4176-b2a9-31b7ab5a3a05|771787b2-0c5f-4072-9f8a-1f0e1b838d58|DefaultServiceCorrelationAlarm\",\r\n" + 
				"		\"idhash\": -459698749,\r\n" + 
				"		\"correlationpolicyname\": \"DefaultServiceCorrelationAlarm\",\r\n" + 
				"		\"correlationpolicyid\": \"771787b2-0c5f-4072-9f8a-1f0e1b838d58\",\r\n" + 
				"		\"correlationgroupid\": \"-266189345\",\r\n" + 
				"		\"lastactivecount\": 0,\r\n" + 
				"		\"historysequencenumber\": 0,\r\n" + 
				"		\"reraisedtime\": null,\r\n" + 
				"		\"domainname\": \"Default\",\r\n" + 
				"		\"classification\": \"Reachability failure\",\r\n" + 
				"		\"isrootcause\": true,\r\n" + 
				"		\"flapping\": false,\r\n" + 
				"		\"important\": false,\r\n" + 
				"		\"probability\": 0,\r\n" + 
				"		\"networkelementnamestr\": null,\r\n" + 
				"		\"timePeriod\": null\r\n" + 
				"	}]\r\n" + 
				"}";
		
//		System.out.println("JSON object:" + json);

		
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
