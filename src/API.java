import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class uses the public available Yahoo Finance API to get and parse information of a stock.
 * @author Xi Peng
 *
 */
public class API {
	
	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String QUOTE_URL_BASE = "https://query1.finance.yahoo.com//v7/finance/quote?symbols=";
	private static final String HISTORY_URL_BASE = "https://query1.finance.yahoo.com//v8/finance/chart/";
	
	/**
	 * Given the symbol of a stock, time range, and time interval for history data, 
	 * it will construct url for Yahoo Finance API to request the history data
	 * @param symbol - String. The official symbol of the stock
	 * @param range - String. The time range for history data
	 * @param interval - String. The time interval for history data
	 * @return String - The url
	 */
	public static String constructUrl(String symbol, String range, String interval) {
		String url;
		url = HISTORY_URL_BASE + symbol + "?range=" + range + "&interval=" + interval;
		return url;
	}
	
	/**
	 * Given only the symbol of stock, it will construct the url for Yahoo Fiance API to get the quote data
	 * @param symbol - String. The official symbol of stock
	 * @return String - The url
	 */
	public static String constructUrl(String symbol) {
		String url;
		url = QUOTE_URL_BASE + symbol;
		return url;
	}
	
	/**
	 * Given the url, it will get the response by Yahoo Finance API
	 * @param url
	 * @return the response - String in Json format
	 * @throws IOException -  Will be handled by GUI and Analysis classes
	 */
	public static String getResponse(String url) throws IOException {
		
		URL url_obj = new URL(url);  
		HttpURLConnection con = (HttpURLConnection) url_obj.openConnection(); 
		con.setRequestMethod("GET"); 
		con.setRequestProperty("User-Agent", USER_AGENT);
				
		int responseCode = con.getResponseCode(); 
				
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
		
		return null;
	}
	
	/**
	 * Given the quote response in string, parse it into a HashMap
	 * @param s - String. The quote response in Json format
	 * @return HashMap - <String, String>. With parameter name as key and corresponding result as value 
	 * @throws IllegalArgumentException when the API response has no data. Will be handled by GUI and Analysis classes
	 */
	public static Map<String, String> parseQuoteResponse(String s) 
			throws IllegalArgumentException {
		// Throw Exception if returned data is empty
		if (s == null) {
			throw new IllegalArgumentException();
		} else {
			int index = s.indexOf("result") + "result\":".length(); // Get starting index of returned result string
			if ((s.substring(index, index + 2).contentEquals("[]"))) { // Check if the "result" block is empty
				throw new IllegalArgumentException();
			}
		}
		
		Map<String, String> infoMap = new HashMap<String, String>();
		
		String[] info = {"currency", "shortName", "regularMarketChange", "regularMarketChangePercent",
				"regularMarketTime", "regularMarketPrice", "regularMarketDayHigh", "regularMarketDayLow", 
				"regularMarketVolume", "regularMarketPreviousClose", "bid", "ask", "regularMarketOpen", 
				"fiftyTwoWeekLow\"", "fiftyTwoWeekHigh\""};
		
		for (String para: info) {
			int index1 = s.indexOf(para);
			String remain = s.substring(index1);
			index1 = remain.indexOf("\":") + 2; // get the starting index of the para's corresponding result
			remain = remain.substring(index1); 
			int index2 = remain.indexOf(","); // get the ending index of the para's corresponding result
			String value = remain.substring(0, index2); // get the substring of the para's corresponding result
			
			// some values have quotation marks, get rid of them
			if (value.charAt(0) == '"') {
				value = value.substring(1, value.length() - 1);
			}
			
			// There are two para names in the info array have quotation mark at the end, get rid of it
			if (para.charAt(para.length() - 1) == '"') {
				para = para.substring(0, para.length() - 1);
			}
			
			infoMap.put(para, value);
		}
		
		return infoMap;
	}
	
	/**
	 * Given the history data in string, extract the time stamp and close price part, 
	 * convert to Date and Double type, and parse into a TreeMap ordered by time
	 * @param s - String, the history data in Json format
	 * @return TreeMap - <Date, Double> with time as key and close price as value ordered by time
	 * @throws IllegalArgumentException when the API response has no data. Will be handled by GUI and Analysis classes
	 */
	public static Map<Date, Double> parseHistoryResponse(String s) 
			throws IllegalArgumentException {
		// Throw Exception if returned data is empty
		if (s == null) {
			throw new IllegalArgumentException();
		} else {
			int index = s.indexOf("result") + "result\":".length(); // Get starting index of returned result string
			if ((s.substring(index, index + 2).contentEquals("[]"))) { // Check if the "result" block is empty
				throw new IllegalArgumentException();
			}
		}
		
		Map<Date, Double> history = new TreeMap<Date, Double>();
		
		// Parse time stamps into a String[]
		int index1 = s.indexOf("timestamp") + "timestamp\":[".length(); // Get the index of the first digit of the first time stamp
		String remain = s.substring(index1);
		int index2 = remain.indexOf("]"); // Get the index of the ending ] of time stamps
		String timeStamp = remain.substring(0, index2);
		String[] timeStampArray = timeStamp.split(",");
		
		// Convert timeStamp to Date() object
		Date[] timeFormatted = new Date[timeStampArray.length];
		for (int i = 0; i < timeStampArray.length; i++) {
			timeFormatted[i] = new Date(Long.parseLong(timeStampArray[i]) * 1000);
		}
		
		// Parse price into String[], then convert to Double, and put into a TreeMap with Date() object
		index1 = remain.indexOf("close") + "close\":[".length(); //Get the index of the first digit of the first close price
		remain = remain.substring(index1);
		index2 = remain.indexOf("]"); // Get the index of the ending "]" of close prices
		String closePrice = remain.substring(0, index2);
		String[] closePriceArray = closePrice.split(",");
		
		for (int i = 0; i < timeFormatted.length; i++) {
			history.put(timeFormatted[i], Double.parseDouble(closePriceArray[i]));
		}
		
		return history;
	}
	
}
