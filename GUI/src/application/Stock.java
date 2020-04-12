package application;

public class Stock {
	
	private String Name;
	private int metricOne;
	private int metricTwo;
	private int metricThree;
	private int metricFour;
	private String analystRecommendation;
	
	
	public Stock(String Name, int metricOne, int metricTwo, int metricThree, int metricFour, String analystRecommendation) {
		
		
			this.Name = Name;
			this.metricOne = metricOne;
			this.metricTwo = metricTwo;
			this.metricThree = metricThree;
			this.metricFour = metricFour;
			this.analystRecommendation = analystRecommendation;
			
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public int getMetricOne() {
		return metricOne;
	}


	public void setMetricOne(int metricOne) {
		this.metricOne = metricOne;
	}


	public int getMetricTwo() {
		return metricTwo;
	}


	public void setMetricTwo(int metricTwo) {
		this.metricTwo = metricTwo;
	}


	public int getMetricThree() {
		return metricThree;
	}


	public void setMetricThree(int metricThree) {
		this.metricThree = metricThree;
	}


	public int getMetricFour() {
		return metricFour;
	}


	public void setMetricFour(int metricFour) {
		this.metricFour = metricFour;
	}


	public String getAnalystRecommendation() {
		return analystRecommendation;
	}


	public void setAnalystRecommendation(String analystRecommendation) {
		this.analystRecommendation = analystRecommendation;
	}


	
}
