import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * This class is to test the API class methods.
 * @author Xi Peng
 *
 */
public class APITest {

	@Test
	/*
	 * Since I haven't finished the parseResponse() method and the raw response is a huge string, 
	 * for now I will only test the get Response() method, 
	 * and only validate part of the results, the first two characters of the huge string. 
	 * I will improve it when I finalize the parseResponse() method.
	 */
	public void getResponseMethodTest() {
		
		/*
		 * These are just parts of the output, because the entire output is too long.
		 * Will improve it when I finalize the parseResponse() method of API class().
		 */
		String quote = "{\"quoteResponse\":{\"result\":[{\"language\":\"en-US\",\"r";
		String history = "{\"chart\":{\"result\":[{\"meta\":{\"currency\":\"USD\",\"sym";
		String wrongSymbol = "{\"quoteResponse\":{\"result\":[],\"error\":null}}";
		assertEquals(quote, API.getResponse("MSFT", false).substring(0, 50), "The quote response is wrong.");
		assertEquals(history, API.getResponse("MSFT", true).substring(0,50), "The history response is wrong.");
		
		// To test when the input stock symbol is not a qualified one
		assertEquals(wrongSymbol, API.getResponse("MSFT123", false), "Can't handle wrong stock symbol.");
	}
}
