package src.Controller;

import javafx.event.*;//hadu should be f gaa controller sinn athm9kom matkhdmch 

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class searchController {
	   @FXML
	    private Button btn_information;
	   
	  
	   @FXML
	    private Button btn_notification;

	    @FXML
	    void gonotification(ActionEvent event) {
         	
			try {
				Parent parent;
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/Notification.fxml"));
				
				Scene scene = new Scene(parent);
				
				Stage  primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.show();
				
				Stage stage = (Stage) btn_notification.getScene().getWindow();
			    // do what you have to do
			  stage.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
			
	    }
	    @FXML
	    void goinformation(ActionEvent event) {
	    	try {
				Parent parent;
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/information.fxml"));
				
				Scene scene1 = new Scene(parent);
				
				Stage  primaryStage1 = new Stage();
				primaryStage1.setScene(scene1);
				primaryStage1.show();
				
				Stage stage1 = (Stage) btn_information.getScene().getWindow();
			    // do what you have to do
			  stage1.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	

	    }
	    
	  
	    @FXML
	    void gohistory(ActionEvent event) {
	    	try {
				Parent parent;
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/ClientHistory.fxml"));
				
				Scene scene1 = new Scene(parent);
				
				Stage  primaryStage1 = new Stage();
				primaryStage1.setScene(scene1);
				primaryStage1.show();
				
				Stage stage1 = (Stage) btn_information.getScene().getWindow();
			    // do what you have to do
			     stage1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    @FXML
	    void singnout(ActionEvent event) {
	    	try {
				Parent parent;
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/login.fxml"));
				
				Scene scene1 = new Scene(parent);
				
				Stage  primaryStage1 = new Stage();
				primaryStage1.setScene(scene1);
				primaryStage1.show();
				
				Stage stage1 = (Stage) btn_information.getScene().getWindow();
			    // do what you have to do
			     stage1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
	    }
	    @FXML
	    void goeditprofil(ActionEvent event) {
	    	try {
				Parent parent;
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/EditProfile.fxml"));
				
				Scene scene1 = new Scene(parent);
				
				Stage  primaryStage1 = new Stage();
				primaryStage1.setScene(scene1);
				primaryStage1.show();
				
				Stage stage1 = (Stage) btn_information.getScene().getWindow();
			    // do what you have to do
			     stage1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
	    }

	

}
