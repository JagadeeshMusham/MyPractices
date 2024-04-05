package spm_prototyping.ElasticSearch;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import spm_prototyping.dao.PMCoreValue;
import spm_prototyping.dao.PMData;
import spm_prototyping.dao.SPMData;
import spm_prototyping.kafka.SLAKafkaProducer;

/**
 * @author Centina
 *
 */
public class ESOperations {

	// The config parameters for the connection
	private final String ELASTIC_HOST = "localhost";
	private final int PORT_ONE = 9200;
	private final int PORT_TWO = 9300;
	private final String HTTPProtocol = "http";

	/**
	 * This is used to convert object to JSON and vice versa
	 */
	private ObjectMapper objectMapper = new ObjectMapper();

	// Target Index to operate
	private final String TARGET_INDEX = "spm_data";

	// rest client which is used to connect to elasticsearch
	private RestHighLevelClient restHighLevelClient;

	private SLAKafkaProducer slaKafkaProducer;

	/**
	 * Initiate rest client
	 * 
	 * @param elasticSearchIP
	 * 
	 * @return rest client
	 */
	public synchronized RestHighLevelClient makeRestHighLevelClient(String elasticSearchIP, String kafkaIP,
			String kafkaPort) {
		System.out.println("===========>\tmakeRestHighLevelClient started");
		if (restHighLevelClient == null) {
			restHighLevelClient = new RestHighLevelClient(RestClient.builder(
					new HttpHost(elasticSearchIP == null ? ELASTIC_HOST : elasticSearchIP, PORT_ONE, HTTPProtocol),
					new HttpHost(elasticSearchIP == null ? ELASTIC_HOST : elasticSearchIP, PORT_TWO, HTTPProtocol)));
		}

		System.out.println("===========>\tmakeRestHighLevelClient ended");

		slaKafkaProducer = new SLAKafkaProducer(kafkaIP, kafkaPort);

		return restHighLevelClient;
	}

	/**
	 * This method convert toPM data into SPM data
	 * 
	 * @param idxName, The user provided Index name which consists the PM data to
	 *                 process
	 */
	public void convertPMtoSPMData(String idxName) {

		StringBuilder indexName = new StringBuilder();

		if (idxName == null) {
			// if no index is provided then considering current day PM data
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(date);

			indexName.append("service_pm_").append(strDate);
		} else {
			indexName.append(idxName);
		}

		System.out.println("\n\n\nStarted reading the PM data from the Index: " + indexName.toString() + "\n\n");

		// Create the source builder and prepare the query
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());

		// Set the size option that determines the number of search hits to return. Max
		// size is 10K
		searchSourceBuilder.size(10000);

		// Prepare search request
		SearchRequest searchRequest = new SearchRequest(indexName.toString());
		searchRequest.source(searchSourceBuilder);

		try {
			// Search the Index and map
			SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

			// get the hits from the search response
			List<SearchHit> searchHits = Arrays.asList(searchResponse.getHits().getHits());
			List<SPMData> spmDataList = new ArrayList<>();

			System.out.println("\n\n\nThe received PM data count is: " + searchHits.size() + "\n\n");

			long startingTime = System.currentTimeMillis();

			// traverse the received hits
			for (SearchHit hit : searchHits) {
				PMData pmData = objectMapper.readValue(hit.getSourceAsString(), PMData.class);

				List<PMCoreValue> pmObject = pmData.getPmObject();

				for (PMCoreValue pmCoreValue : pmObject) {

					// get the respective SPM data for pmCoreValues
					SPMData spmData = getSPMData(pmData, pmCoreValue);

					// Save the SPM data to Elasticsearch
					saveToElasticsearch(spmData);

					// Add spmData to local list
					spmDataList.add(spmData);
				}
			}

			slaKafkaProducer.runProducer(spmDataList);

			long endingTime = System.currentTimeMillis();

			System.out.println("\n\n\nThe generated SPM data count is: " + spmDataList.size() + " in "
					+ (endingTime - startingTime) + " milliseconds \n\n");

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method convert the SPMData object to JSON and the JSON to Elasticsearch
	 * 
	 * @param spmData, The service PM data to store in the Elasticsearch
	 */
	private void saveToElasticsearch(SPMData spmData) {
		try {
			// create the index
			IndexRequest indexRequest = new IndexRequest(TARGET_INDEX);

			// convert the object into JSON
			String spmJson = objectMapper.writeValueAsString(spmData);

			// Add JSON string to index request
			indexRequest.source(spmJson, XContentType.JSON);

			// Index the request
			IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

			// verify the result
			if (indexResponse.getResult() != DocWriteResponse.Result.CREATED) {
				System.out.println("Error! SPM data insertion got failed");
			}
		} catch (JsonProcessingException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * This method's job is to construct the SPM data and assign appropriate values
	 * 
	 * @param pmData,      The PMData which holds the performance data
	 * @param pmCoreValue, The actual values of the PM data
	 * 
	 * @return it returns the SPM object
	 */
	private SPMData getSPMData(PMData pmData, PMCoreValue pmCoreValue) {
		// Create SPM data
		SPMData spmData = new SPMData();

		// prepare SPM data
		spmData.setConsolidationFunction(pmData.getConsolidationFunction());

		spmData.setMaxValue(pmData.getMaxValue());
		spmData.setMinValue(pmData.getMinValue());

		spmData.setPmParmId(pmCoreValue.getPmParmId());
		spmData.setPmParmType(pmCoreValue.getPmParmType());

		// Hard coded the service ID to 4434118
		spmData.setServiceId(4434118);

		// put current time for SPM object
		spmData.setTimeStamp(Calendar.getInstance().getTime());

		spmData.setTimePeriod(pmData.getTimePeriod());
		spmData.setValidity(pmCoreValue.getValidity());
		spmData.setValue(pmCoreValue.getValue());

		return spmData;
	}

	/**
	 * Closing the high level rest client
	 */
	public synchronized void closeConnection() {
		try {

			// closing the rest high level client
			if (restHighLevelClient != null) {
				restHighLevelClient.close();
				restHighLevelClient = null;
			}

		} catch (IOException e) {
			System.out.println("Got exception while closing the restHighLevelClient" + e.toString());
		}
	}

}
