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


	@Override
	public void start(Stage primaryStage) {

		try {

			FXMLLoader loader = FXMLLoader.load(getClass().getResource("/application/Sample.fxml"));

			primaryStage.setTitle("Stock Market Data");
			
			SampleController sc = new SampleController();
			
			loader.setController(sc);
			
			FlowPane fp = loader.load();
			
			Scene scene = new Scene(fp,600,700);
			
			primaryStage.setScene(scene);
			primaryStage.show();


		}

		catch (Exception e) {

		}




	}

	public static void main(String[] args) {
		launch(args);
	}


}
