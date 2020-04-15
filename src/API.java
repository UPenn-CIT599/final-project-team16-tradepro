import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * This class uses Yahoo Finance API to get information of a stock.
 * @author Xi Peng
 *
 */
public class API {
	
	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String QUOTE_URL_BASE = "https://query1.finance.yahoo.com//v7/finance/quote?symbols=";
	private static final String HISTORY_URL_BASE = "https://query1.finance.yahoo.com//v8/finance/chart/";
	
	/**
	 * Given symbol of stock and time interval for history data, it will construct url for Yahoo Finance API
	 * @param symbol - String. The official symbol of stock
	 * @param interval - String. The time interval for history data
	 * @return String - The url
	 */
	public static String constructUrl(String symbol, String interval) {
		String url;
		if (interval == null) { // If no interval is given, it will construct url to get quote dat
			url = QUOTE_URL_BASE + symbol;
		} else { // Otherwise it will construct url to get history data
			url = HISTORY_URL_BASE + symbol + "?range=5y&interval=" + interval; // Time scale for the history data is set to be 5 years
		}
		return url;
	}
	
	/**
	 * Given only the symbol of stock, it will construct the url for Yahoo Fiance API to get the quote data
	 * @param symbol - String. The official symbol of stock
	 * @return String - The url
	 */
	public static String constructUrl(String symbol) {
		String interval = null;
		return constructUrl(symbol, interval);
	}
	
	/**
	 * Given the url, it will get the response by Yahoo Finance API
	 * @param url
	 * @return the response - String in Jason format
	 */
	public static String getResponse(String url) {
		try {
			URL url_obj = new URL(url);
			try {
				HttpURLConnection con = (HttpURLConnection) url_obj.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("User-Agent", USER_AGENT);
				
				int responseCode = con.getResponseCode();
				//System.out.println(responseCode);
				
				if (responseCode == HttpURLConnection.HTTP_OK) {
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					
					StringBuilder response = new StringBuilder(); 
					String line;
					while ((line = in.readLine()) != null) {
						response.append(line);
					}
					
					in.close();
					return response.toString();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Give the quote response in string, parse it to a HashMap
	 * @param s - String
	 * @return HashMap
	 */
	public static Map<String, String> parseQuoteResponse(String s) {
		/*
		 * TODO throw exception?
		 * TODO should consider if user input a wrong symbol, 
		 * the results field in response will be empty
		 */
		Map<String, String> infoMap = new HashMap<String, String>();
		
		String[] info = {"currency", "shortName", "regularMarketChange", "regularMarketChangePercent",
				"regularMarketTime", "regularMarketPrice", "regularMarketDayHigh", "regularMarketDayLow", 
				"regularMarketVolume", "regularMarketPreviousClose", "bid", "ask", "regularMarketOpen", 
				"fiftyTwoWeekLow\"", "fiftyTwoWeekHigh\""};
		
		for (String para: info) {
			int index1 = s.indexOf(para);
			String remain = s.substring(index1);
			index1 = remain.indexOf("\":") + 2; // get the starting index of the string of the para's value
			remain = remain.substring(index1); 
			int index2 = remain.indexOf(","); // get the ending index of the string of the para's value
			String value = remain.substring(0, index2); // get the string slice of the para's vlaue
			
			// some values have quotation marks, get rid of them
			if (value.charAt(0) == '"') {
				value = value.substring(1, value.length() - 1);
			}
			
			// There are two para names in the info array have " at the end, get rid of it
			if (para.charAt(para.length() - 1) == '"') {
				para = para.substring(0, para.length() - 1);
			}
			
			infoMap.put(para, value);
			System.out.println(para + ":" + value);
		}
		
		return infoMap;
	}
	
	/**
	 * Give the history data in string, extract the time stamp and close price part, 
	 * and parse into a HashMap
	 * @param s - String, the history data
	 * @return A HashMap with time stamp as key and close price as value
	 */
	public static Map<Integer, Double> parseHistoryResponse(String s) {
		// TODO throw exception?
		
		Map<Integer, Double> history = new HashMap<Integer, Double>();
		
		// Parse time stamps into a String[]
		int index1 = s.indexOf("timestamp") + "timestamp\":[".length(); // Get the index of the first digit of the first time stamp
		String remain = s.substring(index1);
		int index2 = remain.indexOf("]"); // Get the index of the ending ] of time stamps
		String timeStamp = remain.substring(0, index2);
		String[] timeStampArray = timeStamp.split(",");
		
		// Parse history price into a String[]
		index1 = remain.indexOf("close") + "close\":[".length(); //Get the index of the first digit of the first close price
		remain = remain.substring(index1);
		index2 = remain.indexOf("]"); // Get the index of the ending ] of close prices
		String closePrice = remain.substring(0, index2);
		String[] closePriceArray = closePrice.split(",");
		
		for (int i = 0; i < timeStampArray.length; i++) {
			history.put(Integer.parseInt(timeStampArray[i]), Double.parseDouble(closePriceArray[i]));
		}
		
		return history;
	}
	
	
	public static void main(String[] args) {
		//API.parseHistoryResponse(API.getResponse(API.constructUrl("MSFT", "1mo")));
		
	}
	
}
