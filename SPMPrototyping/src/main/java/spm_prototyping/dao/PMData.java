package spm_prototyping.dao;

import java.util.ArrayList;
import java.util.List;

public class PMData {

	private int pointId;

	private int minValue;
	private int maxValue;
	private String timestamp;
	private int timePeriod;
	private int consolidationFunction;
	private int medServerId;

	private List<PMCoreValue> pmObject;

	public PMData() {
		pmObject = new ArrayList<PMCoreValue>();
	}

	/**
	 * @return the pointId
	 */
	public int getPointId() {
		return pointId;
	}

	/**
	 * @param pointId the pointId to set
	 */
	public void setPointId(int pointId) {
		this.pointId = pointId;
	}

	/**
	 * @return the minValue
	 */
	public int getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue the minValue to set
	 */
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	/**
	 * @return the maxValue
	 */
	public int getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue the maxValue to set
	 */
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return the timeStamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimestamp(String timeStamp) {
		this.timestamp = timeStamp;
	}

	/**
	 * @return the timePeriod
	 */
	public int getTimePeriod() {
		return timePeriod;
	}

	/**
	 * @param timePeriod the timePeriod to set
	 */
	public void setTimePeriod(int timePeriod) {
		this.timePeriod = timePeriod;
	}

	/**
	 * @return the consolidationFunction
	 */
	public int getConsolidationFunction() {
		return consolidationFunction;
	}

	/**
	 * @param consolidationFunction the consolidationFunction to set
	 */
	public void setConsolidationFunction(int consolidationFunction) {
		this.consolidationFunction = consolidationFunction;
	}

	/**
	 * @return the medServer
	 */
	public int getMedServerId() {
		return medServerId;
	}

	/**
	 * @param medServer the medServer to set
	 */
	public void setMedServerId(int medServerId) {
		this.medServerId = medServerId;
	}

	/**
	 * @return the pMCoreValueList
	 */
	public List<PMCoreValue> getPmObject() {
		return pmObject;
	}

	/**
	 * @param pmObject the pMCoreValueList to set
	 */
	public void setPmObject(List<PMCoreValue> pmObject) {
		this.pmObject = pmObject;
	}

}
