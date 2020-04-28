package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

	
		/**
		 * Sets the default view for the Gui, and the title of the gui
		 */
		@Override
		public void start(Stage primaryStage) {
			
			
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/application/Scene1.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				
				primaryStage.setScene(scene);
				primaryStage.setTitle("Stock Market Data");
				primaryStage.show();
				
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		
		}
		
		
		/**
		 * launches the gui
		 * @param args
		 */
		public static void main(String[] args) {
			launch(args);
		}
	}
