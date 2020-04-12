package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class SampleController implements Initializable{

	@FXML
	private MenuBar mainMenuBar;

	/*@FXML
	private ListView<String> mainListView;*/

	@FXML
	private LineChart<String, Integer> mainGraphView;

	@FXML
	private CategoryAxis x;

	@FXML
	private NumberAxis y;

	@FXML
	private TextField textBox1;

	@FXML
	private TextField textBox2;

	@FXML
	private TextField textBox4;

	@FXML
	private TextField textBox3;
	
	@FXML
    private TextField metricOneShower;

	@FXML
	private TextField metricTwoShower;

	@FXML
	private TextField metricThreeShower;

	@FXML
	private TextField metricFourShower;

    
    @FXML
    private VBox mainVBox;
    
    @FXML
    private TextField actualAnalystRecommendation;

	/**
	 * Initializing the class
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		buttonPopulator();


	}

	/**
	 * This method allows the list view to be populated 
	 * @return ObservableList
	 */
	public void buttonPopulator(){

		
		ObservableList<Stock>stocks = GetStockList();
		
		for (Stock stock : stocks) {
			
			
			Button tempButton = new Button(stock.getName());
			
			tempButton.setPrefWidth(240.0);
			tempButton.setId("stockButtons");
			mainVBox.getChildren().add(tempButton);
			
			tempButton.setOnAction((ActionEvent e)->{
			
				metricOneShower.setText("" + stock.getMetricOne());
				metricTwoShower.setText("" + stock.getMetricTwo());
				metricThreeShower.setText("" + stock.getMetricThree());
				metricFourShower.setText("" + stock.getMetricFour());

				actualAnalystRecommendation.setText("" + stock.getAnalystRecommendation());
				
			});
		}

	}
	
	
	public ObservableList<Stock> GetStockList(){

		
		ObservableList<Stock>financialInstruments = FXCollections.observableArrayList();
		
		financialInstruments.add(new Stock("SPY", 1, 2, 3, 4, "Buy"));
		financialInstruments.add(new Stock("QQQ", 1, 3, 4, 4, "Buy"));
		financialInstruments.add(new Stock("Rus2000", 6, 2, 7, 4, "Buy"));
		financialInstruments.add(new Stock("AAPL", 1, 8, 3, 4, "Buy"));
		financialInstruments.add(new Stock("MSFT", 1, 7, 3, 4, "Buy"));
		financialInstruments.add(new Stock("SPY", 1, 2, 3, 4, "Buy"));
		financialInstruments.add(new Stock("QQQ", 1, 3, 4, 4, "Buy"));
		financialInstruments.add(new Stock("Rus2000", 6, 2, 7, 4, "Buy"));
		financialInstruments.add(new Stock("AAPL", 1, 8, 3, 4, "Buy"));
		financialInstruments.add(new Stock("MSFT", 1, 7, 3, 4, "Buy"));
		financialInstruments.add(new Stock("SPY", 1, 2, 3, 4, "Buy"));
		financialInstruments.add(new Stock("QQQ", 1, 3, 4, 4, "Buy"));
		financialInstruments.add(new Stock("Rus2000", 6, 2, 7, 4, "Buy"));
		financialInstruments.add(new Stock("AAPL", 1, 8, 3, 4, "Buy"));
		financialInstruments.add(new Stock("MSFT", 1, 7, 3, 4, "Buy"));
		financialInstruments.add(new Stock("SPY", 1, 2, 3, 4, "Buy"));
		financialInstruments.add(new Stock("QQQ", 1, 3, 4, 4, "Buy"));
		financialInstruments.add(new Stock("Rus2000", 6, 2, 7, 4, "Buy"));
		financialInstruments.add(new Stock("AAPL", 1, 8, 3, 4, "Buy"));
		financialInstruments.add(new Stock("MSFT", 1, 7, 3, 4, "Buy"));

		
		/*financialInstruments.add("SPY");
		financialInstruments.add("QQQ");
		financialInstruments.add("Rus2000");
		financialInstruments.add("AAPL");
		financialInstruments.add("MSFT");
		financialInstruments.add("AMZN");
		financialInstruments.add("INTC");
		financialInstruments.add("NEW");
		financialInstruments.add("JPM");
		financialInstruments.add("WMT");
		financialInstruments.add("BAC");
		financialInstruments.add("GE");
		financialInstruments.add("GOOG");
		financialInstruments.add("TSLA");
		financialInstruments.add("XOM");*/
		
		
		
		return financialInstruments;
	
	}
	
	
	
	
	
	public void registerEventHandlers() {
		
		
		
		
	}
	





	/**
	 * Going to be used to call a method that my group mates have made that scrapes data using the yahoo finance api
	 */
	public void populateLineGraph() {

	}


	/**
	 * Generating metrics about certain financial instruments that will then be put on the gui
	 */
	public void getMetrics() {

	}






}










