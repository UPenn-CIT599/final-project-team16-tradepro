package tradpro1;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.ejml.simple.SimpleMatrix;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class test {
	
     public int[][] getWindow(int windowSize, int balanceSize){
		
		int len = 36;
		
		//get the index of the first starting day
		int start = (len - windowSize + 1) % balanceSize - 1;
		
		//window number of the investment
		int windowNumber = ((len - windowSize)/balanceSize) + 1;
		
		int[][] window = new int[2][windowNumber];
		
		for(int i = 0; i < windowNumber; i = i + 1) {
			
			window[0][i] = start;
			
			window[1][i] = start + windowSize - 1;
			
			System.out.println(start);
			
			System.out.println(start + windowSize -1);
			
			start = start + balanceSize;
			
		}
		
		return window;
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
		test wtest = new test();
		
		int[][] show;
		
		show = wtest.getWindow(20, 5);
		
		
		double data[][] = new double[][]{
            { 90, 60, 90 },
            { 90, 90, 30 },
            { 60, 60, 60 },
            { 60, 60, 90 },
            { 30, 30, 30 }
        };
    
    
        SimpleMatrix X = new SimpleMatrix(data); 
        
        System.out.println(X.cols(2, 3).toString());
        
		
		
		/*Calendar today = Calendar.getInstance();
		
		int dayOfWeek = today.get(Calendar.DAY_OF_WEEK);
		
		System.out.print(dayOfWeek);
		
		int lag = 5 - dayOfWeek;
		
		today.add(Calendar.DATE, -7);
		
		today.add(Calendar.DATE, lag);
		
		Calendar from = (Calendar)today.clone();
		
		from.add(Calendar.YEAR, -2); // from 10 years ago
		
		int dayOfWeek1 = today.get(Calendar.DAY_OF_WEEK);
		
		System.out.print(dayOfWeek1);
		
		String[] symbols = new String[] {"INTC", "BABA", "TSLA"};
		// Can also be done with explicit from, to and Interval parameters
		Map<String, Stock> stocks = YahooFinance.get(symbols, from, today, Interval.WEEKLY);
		
		List<HistoricalQuote> intel = stocks.get("INTC").getHistory();
		
		System.out.println(intel.get(0).getAdjClose());
		
		System.out.println(intel.get(0).getDate());
		
		System.out.println(intel.get(1).getDate());*/
		
		
	}

}
