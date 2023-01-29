package src.Controller;

import java.io.IOException;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClientOrProviderController {
	
    private DialogPane dialog;

    @FXML
    private RadioButton clientbtn;

    @FXML
    private Button createbtn;

    @FXML
    private RadioButton providerbtn;

    @FXML
    private ToggleGroup type;
    
    @FXML
    private Button loginbtn;
    
    ///////////////////////////// Registration

    @FXML
    void CreateAccount(ActionEvent event) {
    	Parent parent = null ;
		try {
			// if the user is a client
			
			if(clientbtn.isSelected()) {
			// Load the FXML file using FXMLLoader
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/AccountClient.fxml"));
			// if the user is a service provider
			
			}else if(providerbtn.isSelected()) {
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/AccountProvider.fxml"));
			
			}else {
				//Error alert
				Alert alert  = new Alert(AlertType.NONE,"You should select one ",ButtonType.OK);
				alert.setHeaderText(null);
				alert.initModality(Modality.NONE);
			    dialog= alert.getDialogPane(); 
				dialog.getStylesheets().add(getClass().getResource("style2.css").toString());
   			    dialog.getStyleClass().add("dialog");
      			alert.showAndWait();
      			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/clientorprovider.fxml"));
			}
			Scene scene = new Scene(parent);
			Stage  primaryStage = new Stage();
			// Set the Scene on the Stage
			primaryStage.setScene(scene);
			Image image = new Image("src/View/icons/logo3.png");	 
			primaryStage.getIcons().add(image);
			// Set the title
			primaryStage.setTitle("BriCOOL");
			primaryStage.show();
		        //close the current scene
			Stage stage1 = (Stage) createbtn.getScene().getWindow();
		    stage1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    
    ////////////////////////////login 
    
    @FXML
    void login(ActionEvent event) {
    	Parent parent;
		try {
			// Load the FXML file using FXMLLoader
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/login.fxml"));
			Scene scene = new Scene(parent);
			Stage  primaryStage = new Stage();
			// Set the Scene on the Stage
			primaryStage.setScene(scene);
			Image image = new Image("src/View/icons/logo3.png");	 
			primaryStage.getIcons().add(image);
			// Set the title
			primaryStage.setTitle("BriCOOL");
			primaryStage.show();
		        //close the current scene
			Stage stage1 = (Stage) loginbtn.getScene().getWindow();
		    stage1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

    }
}
