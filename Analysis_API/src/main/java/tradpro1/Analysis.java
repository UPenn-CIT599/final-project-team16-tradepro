package tradpro1;


import java.util.ArrayList;

import org.ejml.simple.SimpleMatrix;

/*import java.io.IOException;*/
import java.lang.Math;



public class Analysis {
	
	
	/* weights on each stock we need to invest*/
	private ArrayList<Double> weights;
	
	/* risks on each stock we need to invest*/
	private ArrayList<Double> risks;
	
	/* recommendation on each stock we need to invest*/
	private ArrayList<String> advice;
	
 
	
	
	
	
	/**
	 * Returns the matrix of stock return. The return here is the log return.
	 * @param  stockPrice  is the stock price matrix. Each column represent one stock.
	 * @return stock return and covarince of stock return
	 */
	
	public double[][] getStockreturn(double[][] stockPrice){
		
		int rowLen = stockPrice.length;
		
		int colLen = stockPrice[0].length;
		
		double[][] returnData = new double[rowLen][colLen];
		
		for (int col=0; col < (stockPrice[0].length - 1); col++)
		{
		    for (int row=0; row < (stockPrice.length - 1); row++)
		    {
		        
		    	returnData[row][col] = Math.log(stockPrice[row][col]) - Math.log(stockPrice[row+1][col+1]);
		    	
		    	System.out.print(returnData[row][col] + "\t\t\t");
		    	
		        // Do stuff
		    }System.out.print("\n");
		}
		
		return returnData;
		
	
	}
	
	
	
	
	
	
	/**
	 * Returns an ArrayList of SimpleMatrix that the first element is
	 * the mean of stock returns, the second of the element is the covarince
	 * of the  stock returns. 
	 * @param  returnData  is the stock price return matrix. Each column represent one stock.
	 * @return mean of stock return and covarince of stock return
	 */
	public ArrayList<SimpleMatrix> getConvariance(double[][] returnData){
		
		//initialize the return result
		
		ArrayList<SimpleMatrix> result = new ArrayList<SimpleMatrix>();
		
		
		SimpleMatrix X = new SimpleMatrix(returnData);
        int n = X.numRows();
        SimpleMatrix Xt = X.transpose();
        int m = Xt.numRows();

        // Means:
        SimpleMatrix x = new SimpleMatrix(m, 1);
        for(int r=0; r<m; r++ ){
            x.set(r, 0, Xt.extractVector(true, r).elementSum() / n);
        }
        // System.out.println(x);
        
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

        
        for(int r=0; r<m; r++){
            for(int c=0; c<m; c++) { System.out.print(S.get(r, c) + "\t\t\t"); }
            System.out.print("\n");
        }

       
        result.add(S);
        
        return result;
    }
	
     
	/**
	 * Returns the array of weights on each stock we should invest. The maximizing profit algorithm is
	 * still under discussing and constructing
	 * @param  ArrayList of SimpleMatrix, which the first element is mean and second element is covariance
	 * @return array of weights on each stock we need to invest
	 */
	
	
	/*public double[] getWeights(ArrayList<SimpleMatrix> result) {
		
		
	}*/
    
	
	
	/**
	 * Returns the array of risks on each stock we should invest. The risk algorithm is
	 * still under discussing and constructing
	 * @param  ArrayList of SimpleMatrix, which the first element is mean and second element is covariance
	 * @return array of risks on each stock we need to invest
	 */
	
	
	/*public double[] getRisks(ArrayList<SimpleMatrix> result) {
		
		
	}*/
	
	
	/**
	 * Returns the array of advice on each stock we should invest. The advice algorithm is
	 * still under discussing and constructing
	 * @param  ArrayList of SimpleMatrix, which the first element is mean and second element is covariance
	 * @return array of string of advice on each stock we need to invest, include buy, hold and sell
	 */
	
	
	/*public double[] getAdvice(ArrayList<SimpleMatrix> result) {
		
		
	}*/
	
	
	
	
	
	public static void main(String[] args) {
    	
    	double[][] testData = new double[5][6];
    	
    	for (int col=0; col < (testData[0].length); col++)
		{
		    for (int row=0; row < (testData.length); row++)
		    {
		        
		    	
		    	testData[row][col] = 25 + row;
		    	System.out.print(testData[row][col] + "\t\t\t");
		    	
		    	
		        // what we print out is the transpose of the matrix
		    }System.out.print("\n");
		} 
    	
    	Analysis testAnalysis =  new Analysis();
    	
    	testAnalysis.getStockreturn(testData);
    	
    	testAnalysis.getConvariance(testData);
		
		
    }


}
