package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;

public class SampleController implements Initializable{

    @FXML
    private MenuBar mainMenuBar;

    @FXML
    private ListView<String> mainListView;

    @FXML
    private LineChart<?, ?> mainGraphView;
    
    

	/**
	 * Initializing the class
	 */
    @Override
	public void initialize(URL url, ResourceBundle rb) {
    
    	mainListView.getItems().addAll(mainListViewGetter());
    	
	
	}
    
    
    public ObservableList<String> mainListViewGetter(){
    	ObservableList<String>financialInstruments = FXCollections.observableArrayList();
    	financialInstruments.add("SPY");
    	financialInstruments.add("QQQ");
    	financialInstruments.add("Rus2000");
    	return financialInstruments;
    }

    

}










