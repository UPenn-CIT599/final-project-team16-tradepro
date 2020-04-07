package tradpro1;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.io.IOException;
import org.ejml.simple.SimpleMatrix;

public class AnalysisTest {
	
	
	static double[][] testData = new double[5][6];
	
	static Analysis testAnalysis;
	
	
	@Before
	public void setup() {
		
		for (int col=0; col < (testData[0].length); col++)
		{
		    for (int row=0; row < (testData.length); row++)
		    {
		        
		    	
		    	testData[row][col] = 25 + row;
		    	System.out.print(testData[row][col] + "\t\t\t");
		    	
		    	
		        // what we print out is the transpose of the matrix
		    }System.out.print("\n");
		} 
		
		
		testAnalysis =  new Analysis();
        
        
	}
		
		
	
	
	@Test
    public void historicalQuoteTest() throws IOException {
        
		
		SimpleMatrix S = testAnalysis.getConvariance(testData).get(1);
		
    	
    	for (int col=0; col < (testData[0].length); col++)
		{
		    for (int row=0; row < (testData.length); row++)
		    {	
		    	
		    	double K = S.get(row, col);
		    	
		    	assertEquals(2.0, K, 0);
		        
		    }
		} 
		
    	

	}







}
