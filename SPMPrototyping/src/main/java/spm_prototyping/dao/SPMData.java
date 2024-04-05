package spm_prototyping.dao;

import java.util.Date;

public class SPMData {
	private int serviceId;
	private int pmParmId;
	private int pmParmType;
	private int value;
	private int minValue;
	private int maxValue;
	private Date timeStamp; // todoJ, have to change this to Date
	private int timePeriod;
	private int validity;
	private int consolidationFunction;
	private int medServer;

	/**
	 * @return the serviceId
	 */
	public int getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * @return the pmParmId
	 */
	public int getPmParmId() {
		return pmParmId;
	}

	/**
	 * @param pmParmId the pmParmId to set
	 */
	public void setPmParmId(int pmParmId) {
		this.pmParmId = pmParmId;
	}

	/**
	 * @return the pmParmType
	 */
	public int getPmParmType() {
		return pmParmType;
	}

	/**
	 * @param pmParmType the pmParmType to set
	 */
	public void setPmParmType(int pmParmType) {
		this.pmParmType = pmParmType;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
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
	public Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
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
	 * @return the validity
	 */
	public int getValidity() {
		return validity;
	}

	/**
	 * @param validity the validity to set
	 */
	public void setValidity(int validity) {
		this.validity = validity;
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
	public int getMedServer() {
		return medServer;
	}

	/**
	 * @param medServer the medServer to set
	 */
	public void setMedServer(int medServer) {
		this.medServer = medServer;
	}

}
