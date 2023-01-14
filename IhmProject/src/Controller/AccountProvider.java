package src.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class AccountProvider implements Initializable{
	
	
	 @FXML
	    private ChoiceBox<String> Mychoicebox; 
	 
	 
	   private String [] specialty = {"CARPENTER","PLUMBER","CLEANER","TAILOR HAIR STYLIST","ELECTRICIAN"};
	   
	   @FXML
	    private Button back;

	    @FXML
	    void Back_To_choose(ActionEvent event) {
	    	
	    	try {
				Parent parent;
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/clientorprovider.fxml"));
				
				Scene scene = new Scene(parent);
				
				Stage  primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.show();
				
				Stage stage = (Stage) back.getScene().getWindow();
			    // do what you have to do
			    stage.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	

	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			 Mychoicebox.getItems().addAll(specialty);
			
		}

}
