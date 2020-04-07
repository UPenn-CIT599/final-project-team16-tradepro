import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
	 * Given symbol of stock, it will extract info of the stock by Yahoo Finance API
	 * @param symbol - String. The official symbol of stock
	 * @param history - boolean. True means getting the historic data, otherwise getting the quote
	 * @return String - The response. 
	 */
	public static String getResponse(String symbol, boolean history) {
		String url;
		if (!history) {
			url = QUOTE_URL_BASE + symbol;
		} else {
			url = HISTORY_URL_BASE + symbol;
		}
		
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
	 * Give the response in string, parse it to a HashMap
	 * @param s - String
	 * @return HashMap
	 */
	public static Map<String, String> parseResponse(String s) {
		// 
		/*
		 * TODO use regex to parse the response String into a HashMap.
		 * TODO should consider if user input a wrong symbol, 
		 * the results field in response will be empty
		 */
		
	}
	
	/**
	 * Give the symbol of a stock, call getResponse() method to get the quote and historic data.
	 * Then call parseResponse() method to parse the response to HashMaps, 
	 * and integrate the two maps together
	 * @param symbol -  symbol of a stock
	 * @return - a HashMap containing info of the stock
	 */
	public static Map<String, String> getInfo(String symbol) {
		Map<String, String> quote = parseResponse(getResponse(symbol, false));
		Map<String, String> history = parseResponse(getResponse(symbol, true));
		
		// TODO integrate the two HashMap to one HashMap
	}
	
	
	public static void main(String[] args) {
		
	}
	
}
