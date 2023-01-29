package src.Controller;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class historyController {

	private DialogPane dialog;
	   @FXML
	    private Button btn_information;
	   
	  
	   @FXML
	    private Button btn_notification;
	   @FXML
	    private Button back;

	    @FXML
	    void Back_To_choose(ActionEvent event) {
	    	Parent parent;
	    	try {
				// Load the FXML file using FXMLLoader
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/search.fxml"));
				// Create a new Scene with the loaded FXML file as the root node
				Scene scene = new Scene(parent);
				Stage  primaryStage = new Stage();
				// Set the Scene on the Stage
				primaryStage.setScene(scene);
				// Create a new Image object 
				Image image = new Image("src/View/icons/logo3.png");
				// Add the image to the icon list of the primaryStage
				primaryStage.getIcons().add(image);
				// Set the title of the primaryStage
				primaryStage.setTitle("BriCOOL");
				primaryStage.show();
				Stage stage1 = (Stage) back.getScene().getWindow();
			    stage1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }
	   @FXML
	    void gonotification(ActionEvent event) {
        	
		   try {
		    	Parent parent;

				// Load the FXML file using FXMLLoader
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/Notification.fxml"));
				// Create a new Scene with the loaded FXML file as the root node
				Scene scene = new Scene(parent);
				Stage  primaryStage = new Stage();
				// Set the Scene on the Stage
				primaryStage.setScene(scene);
				// Create a new Image object 
				Image image = new Image("src/View/icons/logo3.png");
				// Add the image to the icon list of the primaryStage
				primaryStage.getIcons().add(image);
				// Set the title of the primaryStage
				primaryStage.setTitle("BriCOOL");
				primaryStage.show();
				Stage stage1 = (Stage) btn_notification.getScene().getWindow();
			    stage1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
			
	    }
	    @FXML
	    void goinformation(ActionEvent event) {
	 	   try {
		    	Parent parent;

				// Load the FXML file using FXMLLoader
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/Information.fxml"));
				// Create a new Scene with the loaded FXML file as the root node
				Scene scene = new Scene(parent);
				Stage  primaryStage = new Stage();
				// Set the Scene on the Stage
				primaryStage.setScene(scene);
				// Create a new Image object 
				Image image = new Image("src/View/icons/logo3.png");
				// Add the image to the icon list of the primaryStage
				primaryStage.getIcons().add(image);
				// Set the title of the primaryStage
				primaryStage.setTitle("BriCOOL");
				primaryStage.show();
				Stage stage1 = (Stage) btn_information.getScene().getWindow();
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

				// Load the FXML file using FXMLLoader
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/ClientHistory.fxml"));
				// Create a new Scene with the loaded FXML file as the root node
				Scene scene = new Scene(parent);
				Stage  primaryStage = new Stage();
				// Set the Scene on the Stage
				primaryStage.setScene(scene);
				// Create a new Image object 
				Image image = new Image("src/View/icons/logo3.png");
				// Add the image to the icon list of the primaryStage
				primaryStage.getIcons().add(image);
				// Set the title of the primaryStage
				primaryStage.setTitle("BriCOOL");
				primaryStage.show();
				Stage stage1 = (Stage) btn_information.getScene().getWindow();
			    stage1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    }
	    @FXML
	    void singnout(ActionEvent event) {
	    	
	    	try {
	    		//Acknowledgment alert
	    		
	    		Alert alert = new Alert(AlertType.CONFIRMATION );
	    		alert.setHeaderText(null);
	    		alert.setContentText("Are you sure that you want to log out !");
	    		alert.initModality(Modality.NONE);
	    		dialog= alert.getDialogPane();  
   			    dialog.getStylesheets().add(getClass().getResource("style2.css").toString());
   			    dialog.getStyleClass().add("dialog");
     			Optional<ButtonType> result = alert.showAndWait();
     			 if(result.isEmpty()) {
     				System.out.print("Alert closed ");
     		     }else if(result.get()==ButtonType.OK) {
     	
     		    	Parent parent;
     		   	    // Load the FXML file using FXMLLoader
    				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/login.fxml"));
    				// Create a new Scene with the loaded FXML file as the root node
    				Scene scene = new Scene(parent);
    				Stage  primaryStage = new Stage();
    				// Set the Scene on the Stage
    				primaryStage.setScene(scene);
    				// Create a new Image object 
    				Image image = new Image("src/View/icons/logo3.png");
    				// Add the image to the icon list of the primaryStage
    				primaryStage.getIcons().add(image);
    				// Set the title of the primaryStage
    				primaryStage.setTitle("BriCOOL");
    				primaryStage.show();
    				Stage stage1 = (Stage) btn_information.getScene().getWindow();
    			    stage1.close();
     		     }else if(result.get()==ButtonType.OK) {  
     		    	System.out.print("Alert closed "); 
     		     }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
	    }
	    @FXML
	    void goeditprofil(ActionEvent event) {
	    	try {
 		    	Parent parent;

	    		// Load the FXML file using FXMLLoader
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/EditProfilUser.fxml"));
				// Create a new Scene with the loaded FXML file as the root node
				Scene scene = new Scene(parent);
				Stage  primaryStage = new Stage();
				// Set the Scene on the Stage
				primaryStage.setScene(scene);
				// Create a new Image object 
				Image image = new Image("src/View/icons/logo3.png");
				// Add the image to the icon list of the primaryStage
				primaryStage.getIcons().add(image);
				// Set the title of the primaryStage
				primaryStage.setTitle("BriCOOL");
				primaryStage.show();
				Stage stage1 = (Stage) btn_information.getScene().getWindow();
			    stage1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
	    }
	

	

	   
}
