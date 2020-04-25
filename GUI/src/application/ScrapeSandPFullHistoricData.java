package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ScrapeSandPFullHistoricData {
	
	HashMap<String, Integer>SandPData = new HashMap<String, Integer>();


	public ScrapeSandPFullHistoricData(String fileName) {
		
		
		File file = new File(fileName);
		try {
			
			Scanner s = new Scanner(file);
			s.nextLine();
			while(s.hasNextLine()) {
				
				String column = s.nextLine();
				
				String[]columnData = column.split(",");
				
				
				
				String dates = columnData[0];
				
				System.out.println(dates);
				/*int prices = Integer.parseInt(columnData[4]);
				
				SandPData.put(dates, prices);*/

				
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
	}
	
	public void test() {
		System.out.println("test");
	}
	
	public HashMap<String, Integer> getSandPData() {
		return SandPData;
	}
	
	public void setSandPData(HashMap<String, Integer> sandPData) {
		SandPData = sandPData;
	}
	
	
	
	
	
	public static void main(String[] args) {
		ScrapeSandPFullHistoricData SSPFD = new ScrapeSandPFullHistoricData("/Users/stevebaca/Desktop/MCIT Final/GUI/SandPData - Sheet1.csv");
		SSPFD.test();
	}

}
