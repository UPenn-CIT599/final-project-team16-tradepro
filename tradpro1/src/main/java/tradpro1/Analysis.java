package tradpro1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.ejml.simple.SimpleMatrix;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
/*import java.io.IOException;*/
import java.lang.Math;
import java.math.BigDecimal;



public class Analysis {
	
	/* history of stock price */
	private double[][] stockPrice;
	
	/*index of bench mark column*/
	int benchMark;
	
	/* history of return data */
	private double[][] returnData;
	
	/* history of stock price date */
	private Calendar[] stockDate;
	
	private double[][] benchReturn;
	
	/* average log return of stocks and convaraince matrix*/
	ArrayList<SimpleMatrix> result;
	
	
	/* weights on each stock we need to invest*/
	private ArrayList<Double> weights;
	
	/* risks on each stock we need to invest*/
	private ArrayList<Double> risks;
	
	/* recommendation on each stock we need to invest*/
	private ArrayList<String> advice;
	
	
	
	
 
	
	/**
	 * Set the matrix of stock price and stock date 
	 * @param  symbols are the string containing stock tick names
	 * @param  stocks are the map contains object stocks
	 *
	 */
	
	public void getStockprice(String[] symbols, Map<String, Stock> stocks) throws IOException{
		
		
		//initialize stock name
		String name = symbols[0];
		
		
		//System.out.println(name);
		
		//initialize stock data
		Stock temp = stocks.get(name);
		
		//initialize stock history price data
		List<HistoricalQuote> price =  temp.getHistory();
		
		//get the size of the data
		int sizeOfdata = price.size();
	    
		//allocate space to the double matrix
		stockPrice = new double[sizeOfdata][symbols.length];
		
		stockDate = new Calendar[sizeOfdata];
		
		
		//assigning data to double matrix
		for(int j = 0; j < symbols.length;j++) {
			
			name = symbols[j];
			
			temp = stocks.get(name);
			
			
			//mark down the spy index in stock price 2d array
			if(name == "SPY") {
				
				benchMark = j;
			}
			
			price =  temp.getHistory();
			
			for(int i = 0; i < sizeOfdata; i++) {
				
				double d = price.get(i).getAdjClose().doubleValue();
				
				stockPrice[i][j] = d;
				
				//System.out.println(d);
				
				if(j==0) {
					stockDate[i] = price.get(i).getDate();
				}
				
				
			}
						
			
		}
				
		
	}
	
  
	
	/**
	 * Returns the matrix of stock return. The return here is the log return.
	 * @param  stockPrice  is the stock price matrix. Each column represent one stock.
	 * @return stock return and covariance of stock return
	 */
	
	public void getStockreturn(int contain_spy){
		
		int rowLen = stockPrice.length;
		
		int colLen = stockPrice[0].length;
		
		
		//for the case that portfolio contains spy
		if (contain_spy != 0) {
			
			returnData = new double[rowLen-1][colLen];
			
			benchReturn = new double[rowLen-1][1];
			
			for (int col = 0; col < (colLen); col++)
			{
			    for (int row=0; row < (rowLen - 1); row++)
			    {
			        
			    	returnData[row][col] = Math.log(stockPrice[row+1][col]) - Math.log(stockPrice[row][col]);
			    	
			    	if(col == benchMark) {
			    		
			    		benchReturn[row][0] =  Math.log(stockPrice[row+1][col]) - Math.log(stockPrice[row][col]);
				    	
			    	}
			    	
			    	//System.out.print(returnData[row][col] + "\t\t\t");
			    	
			        // Do stuff
			    }//System.out.print("\n");
			}
		
	    //for the case that portfolio does not contain spy
		}else {
			
			returnData = new double[rowLen-1][colLen-1];
			
			benchReturn = new double[rowLen-1][1];
			
			for (int col = 0, curcol = 0; col < (colLen); col++, curcol++){
				
				if(col != benchMark) {
					
					for (int row=0; row < (rowLen - 1); row++)
				    {
				        
				    	returnData[row][curcol] = Math.log(stockPrice[row+1][col]) - Math.log(stockPrice[row][col]);
				    	
				}}
					
				else {
					for (int row=0; row < (rowLen - 1); row++) {
					benchReturn[row][0] =  Math.log(stockPrice[row+1][col]) - Math.log(stockPrice[row][col]);
				}
					curcol--;
					
				}
			       	
			    	//System.out.print(returnData[row][col] + "\t\t\t");
			    	
			        // Do stuff
			    }//System.out.print("\n");
			}
		}
		
		
	
	
	
	
	
	
	
	/**
	 * Returns an ArrayList of SimpleMatrix that the first element is
	 * the mean of stock returns, the second of the element is the covariance
	 * of the  stock returns. 
	 * @param  returnData  is the stock price return matrix. Each column represent one stock.
	 * @return mean of stock return and covariance of stock return
	 */
	public ArrayList<SimpleMatrix> getCovariance(SimpleMatrix X){
		
		//initialize the return result
		result = new ArrayList<SimpleMatrix>();
		
		
		//SimpleMatrix X = new SimpleMatrix(returnD);
        int n = X.numRows();
        SimpleMatrix Xt = X.transpose();
        int m = Xt.numRows();

        // Means:
        SimpleMatrix x = new SimpleMatrix(m, 1);
        for(int r=0; r<m; r++ ){
            x.set(r, 0, Xt.extractVector(true, r).elementSum() / n);
        }
        //System.out.println(x);
        
        result.add(x);
        
        // Covariance matrix:
        SimpleMatrix S = new SimpleMatrix(m, m);
        for(int r=0; r<m; r++){
            for(int c=0; c<m; c++){
                if(r > c){
                    S.set(r, c, S.get(c, r));
                } else {
                    double cov = Xt.extractVector(true, r).minus( x.get((r), 0) ).dot(Xt.extractVector(true, c).minus( x.get((c), 0) ).transpose());
                    S.set(r, c, (cov / n));
                }
            }
        }
        // System.out.println(S);

        
        /*for(int r=0; r<m; r++){
            for(int c=0; c<m; c++) { System.out.print(S.get(r, c) + "\t\t\t"); }
            System.out.print("\n");
        }*/

       
        result.add(S);
        
        return result;
        
    }
	
     
	/**
	 * Returns the array of weights on each stock we should invest along the history.
	 * @param  Window size for estimating the variance or momentum
	 * balance Period is how long need to be re balanced
	 * len is the returndata length
	 * @return window index
	 */
	public int[][] getWindow(int windowSize, int balancePeriod){
		
		int len = returnData.length;
		
		int start = (len - windowSize) % balancePeriod - 1;
		
		int windowNumber = ((len - windowSize)/balancePeriod) + 1;
		
		int[][] window = new int[2][windowNumber];
		
		for(int i = 0; i < windowNumber; i = i + 1) {
			
			window[0][i] = start;
			
			window[1][i] = start + windowSize;
			
			//System.out.println(start);
			
			//System.out.println(start + windowSize);
			
			start = start + balancePeriod;
			
		}
		
		return window;
		
	}
	
	
	
	
	
	
	/**
	 * Returns the array of weights on each stock we should invest. The maximizing profit algorithm is
	 * still under discussing and constructing
	 * @param  X_sub is the return data matrix the period, and re is the total return of each stock
	 * strategy is which strategy to pick.
	 * @return array of weights on each stock we need to invest
	 * @throws Exception 
	 */
	
	
	public double[][] getWeights(SimpleMatrix X_sub, SimpleMatrix periodReturn, int strategy) throws Exception {
		
		int stockNumber = returnData[0].length;
		
		double[][] weights = new double[1][stockNumber];
		
		
		switch(strategy) {
		   case 1 :
		      
			  result = getCovariance(X_sub) ;
			  
			  SimpleMatrix cov = result.get(1);
			  
			  SimpleMatrix mean = result.get(0);
			  
			  Optimizer op = new Optimizer();
			  
			  weights = op.testPrimalDualMethod(cov, mean);
		      
			  break; // optional
		   
		   case 2 :
			   
               double totalReturn = periodReturn.elementSum();
               
               
			   
			   for(int i = 0; i < stockNumber; i++) {
				   
				    weights[0][i] =  periodReturn.get(0, i)/totalReturn;
				    
				    
			   }
			  
		      
		      break; // optional
		   
		   // You can have any number of case statements.
		   default : // Optional
			   
			   for(int i = 0; i < stockNumber; i++) {
				   
				   weights[0][i] = 1/stockNumber ;
			   }
			  
			   
		}
			
		return weights;
		
	}
    
	
	/**
	 * Returns the accumlated return of stocks we invested along history.(back testing of strategy) 
	 * @param  double[][] hisreturn, the historical return in that balancing period
	 * @return accumlated return along history
	 * @throws Exception 
	 */
	public double[][] getFinalReturn(double[][] hisReturn){
		
		int size1 = hisReturn.length;
		
		int size2 = hisReturn[0].length;
		
		double[][] finalReturn = new double[size1][size2];
		
		for(int i = 0; i < size1;i++) {
			
			finalReturn[i][0] = hisReturn[i][0];
			
			for(int j = 1; j < size2;j++) {
				
				finalReturn[i][j] = finalReturn[i][j-1] + hisReturn[i][j];
			}
			
		}
		
		return finalReturn;
		
	}
	
	
	
	/**
	 * Returns the accumlated return of stocks we invested along history.(back testing of strategy) 
	 * @param  double[][] hisreturn, the historical return in that balancing period
	 * @return accumlated return along history
	 * @throws Exception 
	 */
	public Calendar[] getCalendar(int[][] window){
		
		int size = window[0].length - 1;
		
		Calendar[] investDate  = new Calendar[size];
		
		for(int i = 0; i < size; i++) {
			
			investDate[i] = stockDate[window[1][i+1]];
			
		}
		
		return investDate;
		
	}
	
	
	/**
	 * Returns the return of stocks we invested along history in that period.(back testing of strategy) 
	 * @param  
	 * @return array of weights on each stock we need to invest
	 * @throws Exception 
	 */
	public double[][] backTesting(int windowSize, int balancePeriod, int strategy) throws Exception{
		
		/*int strategy = 0; equal weighted strategy*/
		
		int rollen = returnData.length;
		
		int collen = returnData[0].length;
		
		
		//transform into SimplexMatrix for matrix operation
		SimpleMatrix X = new SimpleMatrix(returnData);
		//bechReturn transform in to matrix
		SimpleMatrix Y = new SimpleMatrix(benchReturn);
		
		int[][] window = getWindow(windowSize, balancePeriod);
		
		double[][] hisReturn =new double[2][window[0].length - 1];
		
		double[][] finalReturn = new double[2][window[0].length - 1];
		
		//loop through history 
		for(int i = 0; i<window[0].length-1; i++) {
			
			int begin = window[0][i];
			int end = window[1][i];
			
			//extract certain period of data
			SimpleMatrix X_sub = X.rows(begin, end); 
			//SimpleMatrix Y_sub = Y.rows(begin, end);
			
			SimpleMatrix X_act = X.rows(end, end + balancePeriod);
			SimpleMatrix Y_act = Y.rows(end, end + balancePeriod);
			//initialize the portfolio return over this period
			double re[][] =new double[1][collen];
			double act_re[][] =new double[1][collen];
			
			
			//get return over the period
			for(int j =0; j< collen; j++) {
				
				re[0][j] = X_sub.cols(j, j+1).elementSum();
				
				//System.out.println(X_sub.get(0, j));
			
				act_re[0][j] = X_act.cols(j, j+1).elementSum();
				
			}
			
			
			SimpleMatrix periodReturn = new SimpleMatrix(re);
			
			SimpleMatrix act_periodReturn = new SimpleMatrix(act_re);
			
			double[][] weights = getWeights(X_sub, periodReturn, strategy);
			
			SimpleMatrix W = new SimpleMatrix(weights);
			
			double portReturn = act_periodReturn.dot(W);
			
			hisReturn[0][i] = portReturn;
			
			hisReturn[1][i] = Y_act.elementSum();
			
			/*System.out.println(portReturn);
			
			System.out.println(Y_act.elementSum());
			
			System.out.println(portReturn - Y_act.elementSum());*/
			
		}
		
		
		finalReturn = getFinalReturn(hisReturn);
		
		
		/*System.out.println(finalReturn[0][window[0].length - 2]);
		
		System.out.println(finalReturn[1][window[0].length - 2]);*/
		
		return finalReturn;
		
		
		
	}
	
	
	
	
	
	
	/**
	 * Returns the array of risks on each stock we should invest. The risk algorithm is
	 * still under discussing and constructing
	 * @param  ArrayList of SimpleMatrix, which the first element is mean and second element is covariance
	 * @return array of risks on each stock we need to invest
	 */
	
	
	public String[] getRisks(int contain_spy) {
		
		int size = returnData[0].length;
		
		String[] riskAdvice = new String[size];
		
		SimpleMatrix X = new SimpleMatrix(returnData);
		
		ArrayList<SimpleMatrix> result = getCovariance(X);
		
		SimpleMatrix risk = result.get(1);
		
		if (contain_spy == 1) {
			
			double benchRisk = risk.get(benchMark, benchMark);
			
			for(int i = 0; i< size ; i++ ){
				
				double tempRisk = risk.get(i, i);
				
				if (tempRisk > 1.5*benchRisk) {
					
					riskAdvice[i] = "high risk";
				
				}else if(tempRisk < 0.8*benchRisk) {
					
					riskAdvice[i] = "low risk";
				
				}else {
					
					riskAdvice[i] = "mid risk";
				}
				
				
			}	
			
		}else {
			
			SimpleMatrix Y = new SimpleMatrix(benchReturn);
		
			ArrayList<SimpleMatrix> bench_result = getCovariance(Y);
			
			double benchRisk = bench_result.get(1).get(0,0);
			
            for(int i = 0; i< size ; i++ ){
				
				double tempRisk = risk.get(i, i);
				
				if (tempRisk > 1.5*benchRisk) {
					
					riskAdvice[i] = "high risk";
				
				}else if(tempRisk < 0.8*benchRisk) {
					
					riskAdvice[i] = "low risk";
				
				}else {
					
					riskAdvice[i] = "mid risk";
				}
				
				
			}	
			
			
		}
		
		
		return riskAdvice;	
		
	}
	
	
	/**
	 * Returns the array of advice on each stock we should invest. The advice algorithm is
	 * still under discussing and constructing
	 * @param  ArrayList of SimpleMatrix, which the first element is mean and second element is covariance
	 * @return array of string of advice on each stock we need to invest, include buy, hold and sell
	 * @throws Exception 
	 */
	
	
	public double[][] getAdvice(int windowSize, int balancePeriod, int strategy) throws Exception{
		 
        //int rollen = returnData.length;
		
		int collen = returnData[0].length;
		
		//transform into SimplexMatrix for matrix operation
	    SimpleMatrix X = new SimpleMatrix(returnData);
	    
	    int[][] window = getWindow(windowSize, balancePeriod);
	    
	    int begin = window[0][window[0].length -1];
		int end = window[1][window[0].length -1];
		
		//extract certain period of data
		SimpleMatrix X_sub = X.rows(begin, end);
		
		double re[][] =new double[1][collen];
		
		
		//get return over the period
		for(int j =0; j< collen; j++) {
			
			re[0][j] = X_sub.cols(j, j+1).elementSum();
			
			//System.out.println(X_sub.get(0, j));			
		}
		
		
		SimpleMatrix periodReturn = new SimpleMatrix(re);
		
		double[][] weights = getWeights(X_sub, periodReturn, strategy);
		
		//SimpleMatrix W = new SimpleMatrix(weights);
		
		return weights;
		
		
		
	}
	
	
	
	
	
	public static void main(String[] args) throws Exception {
    	
    	/*double[][] testData = new double[5][6];
    	
    	for (int col=0; col < (testData[0].length); col++)
		{
		    for (int row=0; row < (testData.length); row++)
		    {
		        
		    	
		    	testData[row][col] = 25 + row;
		    	System.out.print(testData[row][col] + "\t\t\t");
		    	
		    	
		        // what we print out is the transpose of the matrix
		    }System.out.print("\n");
		} */
        String interval = "DAY";
		
		//String[] symbols_user = new String[] {"INTC", "BABA", "TSLA"};
		
		List<String> symbols = new ArrayList<String>();
		
		symbols.add("INTC");
		
		symbols.add("BABA");
		
		symbols.add("TSLA");
		
		int contain_spy = 1;
		
		if(symbols.contains("SPY") != true){
            
			symbols.add("SPY");
			
			contain_spy = 0;
			
        }
		
		String[] symbols1 = symbols.toArray(new String[symbols.size()]);
		
		API testAPI = new API();
		
		testAPI.setup();
		
		testAPI.multiYearData(symbols1,interval);
		
		
		// Can also be done with explicit from, to and Interval parameters
		Map<String, Stock> stocks = testAPI.getStocks();
		
		
    	
    	Analysis testAnalysis =  new Analysis();
    	
    	testAnalysis.getStockprice(symbols1,stocks);
    	
    	testAnalysis.getStockreturn(contain_spy);
    	
    	testAnalysis.backTesting(20, 5, 1);
    	
    	
		
		
    }


}
