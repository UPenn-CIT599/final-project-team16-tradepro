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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PortfolioController implements Initializable{
	
    @FXML
    private AnchorPane thisPortfolio;

    @FXML
    private MenuItem thisRiskAssessmentMenuItem;
    
    @FXML
    private VBox thisVBox;
    
    @FXML
    public CategoryAxis timeCategoryAxis;

    @FXML
    public NumberAxis stockReturnPercentageNumberAxis;
    
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
    
    @FXML
    private Text time;
  
    @FXML
    private Text metricOneText;

    @FXML
    private Text metricTwoText;

    @FXML
    private Text metricThreeText;
    
    @FXML
    private TextField portfolioBuilderTextField;
    
    @FXML
    public MenuBar menuBar;
    
    @FXML
    public Menu fileButton;

    @FXML
    public Menu editButton;

    @FXML
    public Menu helpButton;

    @FXML
    public Menu optionsButton;
    
    @FXML
    private MenuItem menuItemClose;
    
    @FXML
    private MenuItem menuItemDelete;
    
    @FXML
    private MenuItem menuItemAbout;


    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		CSSButtonIdentifier();
		ThisRiskAssesmentMenuItemListener();
		ButtonPopulator();
		
	}
	
	/**
	 * 
	 */
	public void CSSButtonIdentifier() {
				
		menuBar.setId("menuBar");
		fileButton.setId("fileButton");
		editButton.setId("editButton");
		helpButton.setId("helpButton");
		optionsButton.setId("optionsButton");
		timeCategoryAxis.setId("timeCategoryAxis");
		stockReturnPercentageNumberAxis.setId("stockReturnPercentageNumberAxis");
		thisRiskAssessmentMenuItem.setId("thisRiskAssessmentMenuItem");
		menuItemClose.setId("menuItemClose");
		menuItemDelete.setId("menuItemDelete");
		menuItemAbout.setId("menuItemAbout");
		
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
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);			
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
			tempButton.setId("tempButton");
			thisVBox.getChildren().add(tempButton);
			
			
		}
		
		
	}

	
}
