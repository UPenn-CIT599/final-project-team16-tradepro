package tradpro1;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Calendar;
import java.io.IOException;

public class APItest {
	
	private Calendar today;
	private Calendar from;
	static API testApi;
	
	
	@Before
	public void setup() {
		today = Calendar.getInstance();
        today.set(Calendar.YEAR, 2016);
        today.set(Calendar.MONTH, 8);
        today.set(Calendar.DATE, 11);

        from = (Calendar) today.clone();
        from.add(Calendar.YEAR, -1);
        
        testApi = new API();
		
		testApi.setup();
		
		try{
			testApi.multiYearTest();
		}catch(IOException ex) {
			
		}
	}
		
		
	
	
	@Test
    public void historicalQuoteTest() throws IOException {
        
		assertEquals(272.3192, testApi.getHistoricalQuote().getAdjClose());

	}}
