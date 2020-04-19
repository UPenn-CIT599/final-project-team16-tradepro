package application;

import java.awt.Desktop.Action;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PortfolioController implements Initializable{
	
    @FXML
    private AnchorPane thisPortfolio;

    @FXML
    private MenuItem thisStockMarketDataMenuItem;

    @FXML
    private MenuItem thisRiskAssessmentMenuItem;
    
    @FXML
    private VBox thisVBox;
    

    @FXML
    private CategoryAxis timeCategoryAxis;

    @FXML
    private NumberAxis stockReturnPercentageNumberAxis;
    
    @FXML
    private TextField metricOne;

    @FXML
    private TextField metricTwo;

    @FXML
    private TextField metricThree;
    
    @FXML
    private Button weeklyButton;

    @FXML
    private Button monthlyButton;

    @FXML
    private Button yearlyButton;


    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ThisStockMarketDataMenuItemListener();
		ThisRiskAssesmentMenuItemListener();
		ButtonPopulator();
	}
	
	/**
	 * 
	 */
	public void ThisStockMarketDataMenuItemListener() {
		
		thisPortfolio.setId("thisPortfolio");
		
		thisStockMarketDataMenuItem.setOnAction((ActionEvent e)->{
			
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
	
	/**
	 * 
	 */
	public void ThisRiskAssesmentMenuItemListener() {
		
		thisRiskAssessmentMenuItem.setOnAction((ActionEvent e)->{
		
		try {
			
			Parent part = FXMLLoader.load(getClass().getResource("/application/RiskAssessmentTest.fxml"));
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
	
	public void ButtonPopulator() {
		
		ArrayList<String>thisGuy = new ArrayList<String>();
		
		thisGuy.add("SPY");
		thisGuy.add("RUT");
		thisGuy.add("QQQ");
		thisGuy.add("SCHH");
		thisGuy.add("XLF");
		thisGuy.add("RWM");
		thisGuy.add("UVXY");
		thisGuy.add("GLD");
		thisGuy.add("REK");
		
		for (String string : thisGuy) {
			
			Button tempButton = new Button(string);
			tempButton.setPrefWidth(190);
			thisVBox.getChildren().add(tempButton);
			
			
		}
		
	}
	
	
	
	
}
