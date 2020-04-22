package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RiskAssessmentTestController implements Initializable {
	
	
    @FXML
    public AnchorPane ratAnchorPane;

    @FXML
    public MenuBar ratMenuBar;

    @FXML
    private MenuItem portfolioBuilderMenuItem;

    @FXML
    private TextField riskRating;

    @FXML
    private TextField recommendedInvestmentTimeline;

    @FXML
    private TextField q1TextBox;

    @FXML
    private TextField q2TextBox;

    @FXML
    private TextField q3TextBox;

    @FXML
    private TextField q4TextBox;

    @FXML
    private TextField q5TextBox;

    @FXML
    private TextField q6TextBox;

    @FXML
    private TextField q7TextBox;

    @FXML
    private TextField q8TextBox;

    @FXML
    private TextField q9TextBox;

    @FXML
    private TextField q10TextBox;

    @FXML
    private TextField q11TextBox;

    @FXML
    private TextField q12TextBox;
    
    @FXML
    public Menu ratFileButton;

    @FXML
    public Menu ratEditButton;

    @FXML
    public Menu ratHelpButton;

    @FXML
    public Menu ratOptionsButton;
    
    @FXML
    private MenuItem ratMenuItemClose;
    
    @FXML
    private MenuItem ratMenuItemDelete;
    
    @FXML
    private MenuItem ratMenuItemAbout;
    
    @FXML
    private MenuItem ratq1MenuItem1;

    @FXML
    private MenuItem ratq1MenuItem2;

    @FXML
    private MenuItem ratq1MenuItem3;

    @FXML
    private MenuItem ratq1MenuItem4;

    @FXML
    private MenuItem ratq1MenuItem5;

    @FXML
    private MenuItem ratq2MenuItem1;

    @FXML
    private MenuItem ratq2MenuItem2;

    @FXML
    private MenuItem ratq2MenuItem3;

    @FXML
    private MenuItem ratq2MenuItem4;

    @FXML
    private MenuItem ratq2MenuItem5;

    @FXML
    private MenuItem ratq3MenuItem1;

    @FXML
    private MenuItem ratq3MenuItem2;

    @FXML
    private MenuItem ratq3MenuItem3;

    @FXML
    private MenuItem ratq3MenuItem4;

    @FXML
    private MenuItem ratq3MenuItem5;
    
    @FXML
    private MenuItem ratq4MenuItem1;

    @FXML
    private MenuItem ratq4MenuItem2;

    @FXML
    private MenuItem ratq4MenuItem3;

    @FXML
    private MenuItem ratq4MenuItem4;

    @FXML
    private MenuItem ratq4MenuItem5;
    
    @FXML
    private MenuItem ratq5MenuItem1;

    @FXML
    private MenuItem ratq5MenuItem2;

    @FXML
    private MenuItem ratq5MenuItem3;

    @FXML
    private MenuItem ratq5MenuItem4;

    @FXML
    private MenuItem ratq5MenuItem5;
    
    @FXML
    private MenuItem ratq6MenuItem1;

    @FXML
    private MenuItem ratq6MenuItem2;

    @FXML
    private MenuItem ratq6MenuItem3;

    @FXML
    private MenuItem ratq6MenuItem4;

    @FXML
    private MenuItem ratq6MenuItem5;
    
    @FXML
    private MenuItem ratq7MenuItem1;

    @FXML
    private MenuItem ratq7MenuItem2;

    @FXML
    private MenuItem ratq7MenuItem3;

    @FXML
    private MenuItem ratq7MenuItem4;

    @FXML
    private MenuItem ratq7MenuItem5;
    
    @FXML
    private MenuItem ratq8MenuItem1;

    @FXML
    private MenuItem ratq8MenuItem2;

    @FXML
    private MenuItem ratq8MenuItem3;

    @FXML
    private MenuItem ratq8MenuItem4;

    @FXML
    private MenuItem ratq8MenuItem5;
    
    @FXML
    private MenuItem ratq9MenuItem1;

    @FXML
    private MenuItem ratq9MenuItem2;

    @FXML
    private MenuItem ratq9MenuItem3;

    @FXML
    private MenuItem ratq9MenuItem4;

    @FXML
    private MenuItem ratq9MenuItem5;

    
    @FXML
    private MenuItem ratq10MenuItem1;

    @FXML
    private MenuItem ratq10MenuItem2;

    @FXML
    private MenuItem ratq10MenuItem3;

    @FXML
    private MenuItem ratq10MenuItem4;

    @FXML
    private MenuItem ratq10MenuItem5;
    
    @FXML
    private MenuItem ratq11MenuItem1;

    @FXML
    private MenuItem ratq11MenuItem2;

    @FXML
    private MenuItem ratq11MenuItem3;

    @FXML
    private MenuItem ratq11MenuItem4;

    @FXML
    private MenuItem ratq11MenuItem5;
    
    @FXML
    private MenuItem ratq12MenuItem1;

    @FXML
    private MenuItem ratq12MenuItem2;

    @FXML
    private MenuItem ratq12MenuItem3;

    @FXML
    private MenuItem ratq12MenuItem4;

    @FXML
    private MenuItem ratq12MenuItem5;

    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SwitchScenes();
		MakeEverythingUnModifiable();
	}
	
	public void SwitchScenes() {
		ratAnchorPane.setId("ratAnchorPane");
		ratMenuBar.setId("ratMenuBar");
		ratFileButton.setId("ratFileButton");
		ratEditButton.setId("ratEditButton");
		ratHelpButton.setId("ratHelpButton");
		ratOptionsButton.setId("ratOptionsButton");
		portfolioBuilderMenuItem.setId("portfolioBuilderMenuItem");
		ratMenuItemClose.setId("ratMenuItemClose");
		ratMenuItemDelete.setId("ratMenuItemDelete");
		ratMenuItemAbout.setId("ratMenuItemAbout");
		
		portfolioBuilderMenuItem.setOnAction((ActionEvent e)->{

			try {

				Parent part = FXMLLoader.load(getClass().getResource("/application/Portfolio.fxml"));
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
	
	public void CSSIdenitfier() {
		ratAnchorPane.setId("ratAnchorPane");
		ratMenuBar.setId("ratMenuBar");
	}
	
	public void MakeEverythingUnModifiable() {
		riskRating.setEditable(false);
		recommendedInvestmentTimeline.setEditable(false);
		q1TextBox.setEditable(false);
		q2TextBox.setEditable(false);
		q3TextBox.setEditable(false);
		q4TextBox.setEditable(false);
		q5TextBox.setEditable(false);
		q6TextBox.setEditable(false);
		q7TextBox.setEditable(false);
		q8TextBox.setEditable(false);
		q9TextBox.setEditable(false);
		q10TextBox.setEditable(false);
		q11TextBox.setEditable(false);
		q12TextBox.setEditable(false);
		

	}
	

}
