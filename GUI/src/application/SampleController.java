package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;

public class SampleController implements Initializable{

    @FXML
    private MenuBar mainMenuBar;

    @FXML
    private ListView<String> mainListView;

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

	/**
	 * Initializing the class
	 */
    @Override
	public void initialize(URL url, ResourceBundle rb) {
    
    	mainListView.getItems().addAll(mainListViewGetter());
    	
	
	}
    
    /**
     * This method allows the list view to be populated 
     * @return ObservableList
     */
    public ObservableList<String> mainListViewGetter(){
    	
    	ObservableList<String>financialInstruments = FXCollections.observableArrayList();
    	financialInstruments.add("SPY");
    	financialInstruments.add("QQQ");
    	financialInstruments.add("Rus2000");
    	return financialInstruments;
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










