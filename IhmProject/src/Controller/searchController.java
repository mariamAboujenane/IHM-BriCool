package src.Controller;

import javafx.event.*;//hadu should be f gaa controller sinn athm9kom matkhdmch 

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class searchController implements Initializable{
	private DialogPane dialog;
	   @FXML
	    private Button btn_information;
	   
	  
	   @FXML
	    private Button btn_notification;
	   
	   @FXML
	    private ComboBox<String> choice;
	  
	   
	   
	   
	  private String [] Ville = {"Rabat","Agadir","Fez","Tanger","Tetouan","Sale","Casablanca","Zagora","Nador","Oujda","Marrakech","Safi","Meknes","Kenitra","Asila","Ifrane","Ouarzazate","Alhoceima"};

	  
	  
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
		
			choice.getItems().addAll( Ville);
		}
		
	  
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
    				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/login.fxml"));
    				
    				Scene scene1 = new Scene(parent);
    				
    				Stage  primaryStage1 = new Stage();
    				primaryStage1.setScene(scene1);
    				primaryStage1.show();
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
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/EditProfileUser.fxml"));
				
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
