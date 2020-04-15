import java.util.Map;

/**
 * This class is to hold the info of individual stock
 * @author Xi Peng
 *
 */
public class Stock {
	private String symbol;
	private String currency;  //"currency":"USD"
	private String name; //"shortName":"Microsoft Corporation"
	private double change;  //"regularMarketChange":11.029999
	private double changePercent;  //"regularMarketChangePercent":7.170252,
	private int marketTime; //"regularMarketTime":1586202655
	private double price; //"regularMarketPrice":164.86
	private double dayHigh;  //"regularMarketDayHigh":164.99
	private double dayLow;  //"regularMarketDayLow":157.59
	private int volumn;  //"regularMarketVolume":47170448
	private double previousClose;  //"regularMarketPreviousClose":153.83
	private double bid;  //"bid":162.52,
	private double ask;  //"ask":162.64
	private double todaysOpen;  //"regularMarketOpen":160.32
	private double fiftyTwoWeekLow;  //"fiftyTwoWeekLow":118.58
	private double fiftyTwoWeekHigh;  //"fiftyTwoWeekHigh":190.7
	private Map<Integer, Double> history; //因为数据多而且时间范围和interval可以变化，所以不要实时更新，最好是单独列一个method来获取，由用户选择range和interval，或者根据range确定interval
	
	/**
	 * Constructor. Requires the symbol of the stock as input, 
	 * then call method of API class to get the info of the stock,
	 * and assign them to each instance variable.
	 * @param symbol
	 */
	public Stock(String symbol) {
		this.symbol = symbol;
		update();
	}
	
	/**
	 * Update info of the stock except for the history variable
	 */
	public void update() {
		Map<String, String> newQuote = API.parseQuoteResponse(API.getResponse(API.constructUrl(this.symbol)));
		
		setCurrency(newQuote.get("currency"));
		setName(newQuote.get("shortName"));
		setChange(Double.parseDouble(newQuote.get("regularMarketChange")));
		setChangePercent(Double.parseDouble(newQuote.get("regularMarketChangePercent")));
		setMarketTime(Integer.parseInt(newQuote.get("regularMarketTime")));
		setPrice(Double.parseDouble(newQuote.get("regularMarketPrice")));
		setDayHigh(Double.parseDouble(newQuote.get("regularMarketDayHigh")));
		setDayLow(Double.parseDouble(newQuote.get("regularMarketDayLow")));
		setVolumn(Integer.parseInt(newQuote.get("regularMarketVolume")));
		setPreviousClose(Double.parseDouble(newQuote.get("regularMarketPreviousClose")));
		setBid(Double.parseDouble(newQuote.get("bid")));
		setAsk(Double.parseDouble(newQuote.get("ask")));
		setTodaysOpen(Double.parseDouble(newQuote.get("regularMarketOpen")));
		setFiftyTwoWeekLow(Double.parseDouble(newQuote.get("fiftyTwoWeekLow")));
		setFiftyTwoWeekHigh(Double.parseDouble(newQuote.get("fiftyTwoWeekHigh")));
	}
	
	/**
	 * Assign value to the history variable by calling API class methods, with user decided interval as argument
	 * @param interval - String. User decided time interval
	 */
	public void getHistory(String interval) {
		Map<Integer, Double> history = API.parseHistoryResponse(API.getResponse(API.constructUrl(this.symbol, interval)));
		this.history = history;
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setCurrency(String currency) {
		this.currency = currency;
	}
	private void setPrice(double price) {
		this.price = price;
	}

	private void setMarketTime(int marketTime) {
		this.marketTime = marketTime;
	}

	private void setChange(double change) {
		this.change = change;
	}

	private void setChangePercent(double changePercent) {
		this.changePercent = changePercent;
	}

	private void setBid(double bid) {
		this.bid = bid;
	}

	private void setAsk(double ask) {
		this.ask = ask;
	}

	private void setDayHigh(double dayHigh) {
		this.dayHigh = dayHigh;
	}

	private void setDayLow(double dayLow) {
		this.dayLow = dayLow;
	}

	private void setVolumn(int volumn) {
		this.volumn = volumn;
	}

	private void setPreviousClose(double previousClose) {
		this.previousClose = previousClose;
	}

	private void setTodaysOpen(double todaysOpen) {
		this.todaysOpen = todaysOpen;
	}

	private void setFiftyTwoWeekLow(double fiftyTwoWeekLow) {
		this.fiftyTwoWeekLow = fiftyTwoWeekLow;
	}

	private void setFiftyTwoWeekHigh(double fiftyTwoWeekHigh) {
		this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getName() {
		return name;
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

	public Map<Integer, Double> getHistory() {
		return history;
	}
}
