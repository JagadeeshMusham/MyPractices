package spm_prototyping.dao;

public class PMCoreValue {
	private int validity;
	private int pmParmId;
	private int pmParmType;
	private int value;

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
}
