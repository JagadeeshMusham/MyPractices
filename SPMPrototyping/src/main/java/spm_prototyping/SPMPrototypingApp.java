package spm_prototyping;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import spm_prototyping.ElasticSearch.ESOperations;

/**
 * @author Centina
 *
 */
public class SPMPrototypingApp {

	/**
	 * Constant value for spark ip header argument
	 */
	private final static String SPARK_IP = "-sparkip";

	/**
	 * Constant value for spark port header argument
	 */
	private final static String SPARK_PORT = "-sparkport";

	/**
	 * Constant value for elastic-search ip header argument
	 */
	private final static String ELASTICSEARCH_IP = "-elasticip";

	/**
	 * Constant value for elastic-search index header argument, this is optional
	 * field
	 */
	private final static String INPUT_INDEX = "-index";

	/**
	 * Constant value for Kafka IP header argument
	 */
	private final static String KAFKA_IP = "-kafkaip";

	/**
	 * Constant value for kafka port header argument
	 */
	private final static String KAFKA_PORT = "-kafkaport";

	/**
	 * This method will return the command line usage.
	 */
	private static void displayUsage() {
		System.out.println("\n\nPlease use following format of command to run the program:");

		StringBuilder usage = new StringBuilder("java -jar SPM_Prototyping-0.0.1-SNAPSHOT-jar-with-dependencies.jar ");
		usage.append(SPARK_IP).append(" <sparkIPValue> ").append(SPARK_PORT).append(" <SparkPortValue> ")
				.append(ELASTICSEARCH_IP).append(" <ElasticIPValue> ").append(INPUT_INDEX).append(" <IndexValue> ")
				.append(KAFKA_IP).append(" <KafkaIP> ").append(KAFKA_PORT).append(" <kafkaPort> ");

		System.out.println(usage.toString());
	}

	/**
	 * The main method where actual execution getting started
	 * 
	 * @param args, The arguments
	 */
	public static void main(String[] args) {

		Logger.getLogger(SPMPrototypingApp.class).setLevel(Level.WARN);

		String sparkIP = null;
		String sparkPort = "7077";
		String elasticIP = null;
		String indexName = null;

		String kafkaIP = "localhost";
		String kafkaPort = "9092";

		if (args.length > 0) {
			// The number of arguments has to be exactly multiple of two as each argument
			// should have name and value
			if (args.length % 2 != 0) {
				System.out.println("\n\nReceived Invalid command line arguments");
				displayUsage();
				return;
			}

			// displaying the received argument list
			System.out.println("\n\n\nThe received argument list are");
			int counter;
			for (counter = 0; counter < args.length; counter += 2) {
				System.out.println(args[counter] + " : " + "\t" + args[counter + 1]);
			}
			System.out.println("The above are argument list\n\n\n");

			// parsing the arguments
			for (counter = 0; counter < args.length; counter += 2) {
				switch (args[counter]) {
				case SPARK_IP:
					sparkIP = args[counter + 1];
					break;
				case SPARK_PORT:
					sparkPort = args[counter + 1];
					break;
				case ELASTICSEARCH_IP:
					elasticIP = args[counter + 1];
					break;
				case INPUT_INDEX:
					indexName = args[counter + 1];
					break;
				case KAFKA_IP:
					kafkaIP = args[counter + 1];
					break;
				case KAFKA_PORT:
					kafkaPort = args[counter + 1];
					break;
				default:
					displayUsage();
					return;
				}
			}
		} else {
			System.out.println("No arguments received");
		}

		// The received values as arguments
		System.out.println("\n\n\nInput variables are");
		System.out.println("SparkIP: " + sparkIP);
		System.out.println("Spark Port: " + sparkPort);
		System.out.println("ESIP: " + elasticIP);
		System.out.println("IndexName: " + indexName);
		System.out.println("Above are input variables \n\n\n");

		StringBuilder masterToken = new StringBuilder();
		if (sparkIP != null) {
			// .master("spark://172.31.7.29:7077") // 7.42
			// Un comment this to use master running in local

			// generating the spark master token
			if ("local".equals(sparkIP) == false && "127.0.0.1".equals(sparkIP) == false) {
				masterToken.append("spark://").append(sparkIP).append(":").append(sparkPort);
			}
		}

		// master token not initialized then initiate with default value as local
		if (masterToken.length() == 0) {
			masterToken.append("local[*]");
		}

		System.out.println("\n\n\nmaster token: " + masterToken.toString() + "\n\n");

		System.out.println("===========>\tSpark initiating...");

		// Spark initialization
		SparkSession spark = new SparkSession.Builder().appName("SPMPrototyping").master(masterToken.toString())
				.getOrCreate();

//		SparkConf conf = new SparkConf().setAppName("SPMPrototyping").setMaster(masterToken.toString());
//		JavaSparkContext sparkContext = new JavaSparkContext(conf);
//
//		List<Integer> stringList = new ArrayList<Integer>();
//		stringList.add(5);
//		stringList.add(9);
//
//		JavaRDD<Integer> javaRDD = sparkContext.parallelize(stringList);

		System.out.println("Spark started...");

		// creating an Object to perform the CRUD operations on Elastic search
		ESOperations crudOperations = new ESOperations();

		// create a rest high level client
		crudOperations.makeRestHighLevelClient(elasticIP, kafkaIP, kafkaPort);

		try {
			// Process the PM data to convert SPM data
			System.out.println("Started to process the PM data to convert SPM data");
			crudOperations.convertPMtoSPMData(indexName); // todoj
		} catch (Exception e) {
			System.out.println("Caught exception: " + e.getMessage());
			e.printStackTrace();
		}

		crudOperations.closeConnection();
	}
}
