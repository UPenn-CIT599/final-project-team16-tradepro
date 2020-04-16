package application;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class Scene1Controller implements Initializable{
	
	
	@FXML
    private AnchorPane introScene;

    @FXML
    private ImageView imageBackground;

    @FXML
    private Button yourStockInfo;

    @FXML
    private Button yourPortfolio;
    
    
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Button1Listener();
		Button2Listener();
		
	}
	
	
	
	public void Button1Listener() {
		
		yourStockInfo.setOnAction((ActionEvent e)->{
			
			try {
				
			Parent part = FXMLLoader.load(getClass().getResource("/application/Sample.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(part);
			stage.setScene(scene);
			stage.setFullScreen(true);
			stage.show();
			}
			catch (Exception e1) {
				// TODO: handle exception
			}
			
	});
		
		
		
	}
	
public void Button2Listener() {
		
		yourPortfolio.setOnAction((ActionEvent e)->{
			
			try {
				
			Parent part = FXMLLoader.load(getClass().getResource("/application/Portfolio.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(part);
			stage.setScene(scene);
			stage.show();
			}
			catch (Exception e1) {
				// TODO: handle exception
			}
			
	});
		
		
		
	}
	

}
