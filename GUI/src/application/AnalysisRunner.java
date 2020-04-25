package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import yahoofinance.Stock;

public class AnalysisRunner {
	
	    
	    //return variables
	    private Calendar[] investmentDate; //these are dates in history that we made balance
	    private double[][] weights; //these are the weights for each stocks. Dimension is 1 x number of stocks.
	    private double[][] histReturn; //these are the returns following our strategy and also 
	    //contains the SPY return for bechmark. Dimension is 2 x number of dates in history that we made balance
	    private String[] risks; //these are the risks rating for each stocks. Length is the number of stocks.
	    
	    
	    //variables needs to be setup to run
	    private int Strategy; //this is the strategy to be followed
	    private int windowSize; //this is the rolling window size for historical data to be analyzed
	    private int balancePeriod;//this is how frequent we adjust our portfolio
	    private String Score;//this is the score coming from assessment test so that we can determine above three parameters
	    //the default total historical data size being adopted is set in API.
	    //the default frequency of adjustment close price for analyzing is daily.
	    
	    
	    public AnalysisRunner(String Score) {
	    	
	    	this.Score = Score;
	    	
	    	if(Score.equals("High")) {
	    		
	    		this.Strategy = 1;
	    		this.windowSize = 20;
	    		this.balancePeriod = 5;
	    		
	    	}
	    	else if(Score.equals("Mid")) {
	    		
	    		this.Strategy = 1;
	    		this.windowSize = 60;
	    		this.balancePeriod = 10;
	    	
	    	}else {
	    		
	    		this.Strategy = 1;
	    		this.windowSize = 120;
	    		this.balancePeriod = 30;
	    		
	    	}
	    	
	    	
	    }
	   
	    
	    public void AnalysisCompute(List<String> symbols) throws Exception {
	    	
	    	
	    	//this section is to include SPY as an benchmark regardless of user's choice if they want to invest in SPY
	    	int contain_spy = 1;
	    	if(symbols.contains("SPY") != true){
	         
				symbols.add("SPY");
				
				contain_spy = 0;
				
	        }
			String[] symbols1 = symbols.toArray(new String[symbols.size()]);
			
			
			
			//this API is external, will adopt another built in house API
			API testAPI = new API();
			testAPI.setup();
			testAPI.multiYearData(symbols1,Score);
			Map<String, Stock> stocks = testAPI.getStocks();
			
			
	 	
			//this is where portfolio gets built
			Analysis testAnalysis =  new Analysis();
	 
	 	    testAnalysis.getStockprice(symbols1,stocks);
	 	
	 	    testAnalysis.getStockreturn(contain_spy);
	 	
	 	    histReturn = testAnalysis.backTesting(windowSize, balancePeriod, Strategy);

	 	    for(int i = 0; i < histReturn.length; i++) {
	 	    	
	 	    	 System.out.println("histReturn" + i + ": "+ Arrays.toString(histReturn[i]));
	 	    }
	 	    
	 	   
	 	    
	 	    weights = testAnalysis.getAdvice(windowSize, balancePeriod, Strategy);
	 	   
	 	    	
	 	    System.out.println("weights: "+ Arrays.toString(weights[0]));
	 	    	
	 	    
	 	    //System.out.println("weights: " + Arrays.toString(weights));
	 	    
	 	    int[][] window = testAnalysis.getWindow(windowSize, balancePeriod);
	 	    
	 	    
	 	    investmentDate = testAnalysis.getCalendar(window);
	 	    
	 	    System.out.println("investment times: "+ investmentDate.length);
	    	
	    	
	 	    risks = testAnalysis.getRisks(contain_spy);
	 	    
	 	    System.out.println("risks: " + Arrays.toString(risks));
	    	
	    }
	    
	    
	    public Calendar[] getInvestmentDate() {
			return investmentDate;
		}



		public double[][] getWeights() {
			return weights;
		}



		public double[][] getHistReturn() {
			return histReturn;
		}



		public String[] getRisks() {
			return risks;
		}
		
	

		
		//String[] symbols_user = new String[] {"INTC", "BABA", "TSLA"};
	    
	    public static void main(String[] args) throws Exception {
	    	
	 		
	 		
	 		//Here is a test case of user picking three stocks and assessment test being high
	 		List<String> symbols = new ArrayList<String>();
	 		
	 		symbols.add("INTC");
	 		
	 		symbols.add("BABA");
	 		
	 		symbols.add("TSLA");
	 		
	 		String Score = "High";
	 		
	 		//Portfoilo gets built
	 		
	 		AnalysisRunner runtest = new AnalysisRunner(Score);
	 		runtest.AnalysisCompute(symbols);
	 		
	 		//it will print: histReturn0: [-0.09850874504512777.....]
	 		//it will print: histReturn1: [0.001997609315795401.....]
	 		//it will print: weights: [-0.14032848132841422, 1.2426438972162417, -0.10231541588782739]
	 		//it will print:risks: [high risk, mid risk, high risk]
			
	 		
	 		
	 		//to get above results, one can type
	 		
	 		//runtest.getHistReturn();
	 		//runtest.getRisks();
	 		//runtest.getWeights();
	 		//runtest.getInvestmentDate()
	 		
	    }



		
		
 	
 	
		

}
