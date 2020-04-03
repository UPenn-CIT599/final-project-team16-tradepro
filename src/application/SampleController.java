package application;

import java.awt.Label;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class SampleController implements Initializable{

	@FXML 
	private ListView<String>mainListView;



	


	/**
	 * Initializing the class
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		ObservableList<String>thisMainListView = FXCollections.observableArrayList("SPY","QQQ","Rus2000");

		mainListView.setItems(thisMainListView);

	}








}
