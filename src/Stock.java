import java.util.Map;

/**
 * This class is to hold the info of individual stock
 * @author Xi Peng
 *
 */
public class Stock {
	private String symbol;
	private String name;
	private String exchange;
	private String currency;
	private double price;
	private int marketTime;
	private double change;
	private double changePercent;
	private double bid;
	private double ask;
	private double dayHigh;
	private double dayLow;
	private int volumn;
	private double previousClose;
	private double todaysOpen;
	private double fiftyTwoWeekLow;
	private double fiftyTwoWeekHigh;
	private int dividendDate;
	private int annualDividendRate;
	private float PE;
	private float annualDividendYield;
	private Map<Integer, Float> history;
	
	/**
	 * Constructor. Requires a the symbol of the stock as input, 
	 * then call method of API class to get the info of the stock,
	 * and assign them to each instance variable.
	 * @param symbol
	 */
	public Stock(String symbol) {
		this.symbol = symbol;
		
		// Call getInfo() method of API class to get the quote for the stock
		Map<String, String> quote = API.getInfo(symbol);
		
		// TODO convert string to appropriate type for each variable and initialize the instance
	
	}

	public String getSymbol() {
		return symbol;
	}

	public String getName() {
		return name;
	}

	public String getExchange() {
		return exchange;
	}

	public String getCurrency() {
		return currency;
	}

	public double getPrice() {
		return price;
	}

	public int getMarketTime() {
		return marketTime;
	}

	public double getChange() {
		return change;
	}

	public double getChangePercent() {
		return changePercent;
	}

	public double getBid() {
		return bid;
	}

	public double getAsk() {
		return ask;
	}

	public double getDayHigh() {
		return dayHigh;
	}

	public double getDayLow() {
		return dayLow;
	}

	public int getVolumn() {
		return volumn;
	}

	public double getPreviousClose() {
		return previousClose;
	}

	public double getTodaysOpen() {
		return todaysOpen;
	}

	public double getFiftyTwoWeekLow() {
		return fiftyTwoWeekLow;
	}

	public double getFiftyTwoWeekHigh() {
		return fiftyTwoWeekHigh;
	}

	public int getDividendDate() {
		return dividendDate;
	}

	public int getAnnualDividendRate() {
		return annualDividendRate;
	}

	public float getPE() {
		return PE;
	}

	public float getAnnualDividendYield() {
		return annualDividendYield;
	}

	public Map<Integer, Float> getHistory() {
		return history;
	}
	
	
	
}
