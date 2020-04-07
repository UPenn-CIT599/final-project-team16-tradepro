package tradpro1;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;


import java.io.IOException;
/*import java.math.BigDecimal;*/
import java.util.Calendar;
/*import java.util.List;*/
/*import java.util.Map;*/


public class API {
	
	
	private Calendar today;
    private Calendar from;
    
    private HistoricalQuote histQuote;
    
    public void setup() {
        today = Calendar.getInstance();
        today.set(Calendar.YEAR, 2016);
        today.set(Calendar.MONTH, 8);
        today.set(Calendar.DATE, 11);

        from = (Calendar) today.clone();
        from.add(Calendar.YEAR, -1);
    }

	
	public void multiYearTest() throws IOException {
        
		Calendar from = (Calendar) today.clone();
        Calendar to = (Calendar) today.clone();
        
        from.add(Calendar.YEAR, -5); // from 5 years ago

        Stock goog = YahooFinance.get("GOOG", from, to, Interval.WEEKLY);

       

        histQuote = goog.getHistory().get(0);
        
        
       

    }
	
	public HistoricalQuote getHistoricalQuote() {
		
		return histQuote;
	}
	
	
	
	public static void main(String[] args) {
		
		API testApi = new API();
		
		testApi.setup();
		
		try{
			testApi.multiYearTest();
		}catch(IOException ex) {
			
		}
		
		
		System.out.print(testApi.getHistoricalQuote().getAdjClose());
		
	}
	
	
}
