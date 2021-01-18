package havis.net.rest.middleware.model;

public class License {
	/**
	 * The name of the licensee
	 */
	private String licensee;

	/**
	 * The starting time of the license in {@code YYYY-MM-DDTHH:MM:SS}
	 */
	private String startingTime;

	/**
	 * The end point of the license in {@code YYYY-MM-DDTHH:MM:SS}
	 */
	private String endTime;

	
	public License(){
		
	}
	
	public License(String licensee, String startingTime, String endTime){
		this.licensee = licensee;
		this.startingTime = startingTime;
		this.endTime = endTime;
	}
	
	/**
	 * 
	 * @return {@link #licensee}
	 */
	public String getLicensee() {
		return licensee;
	}

	/**
	 * 
	 * @param licensee
	 */
	public void setLicensee(String licensee) {
		this.licensee = licensee;
	}

	/**
	 * 
	 * @return {@link #startingTime}
	 */
	public String getStartingTime() {
		return startingTime;
	}

	/**
	 * 
	 * @param startingTime
	 */
	public void setStartingTime(String startingTime) {
		this.startingTime = startingTime;
	}

	/**
	 * 
	 * @return {@link #endTime}
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * 
	 * @param endTime
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
