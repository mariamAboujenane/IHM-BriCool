package src.Controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import src.Model.DatabaseConnection;
	
	
	public class LoginController implements Initializable {
		String password;
		
	    @FXML
	    private Button create;
	    @FXML
	    private PasswordField passwordTextField;
	    @FXML
	    private TextField passwordtxt;
	    @FXML
	    private Button submitButton;
	    @FXML
	    private TextField usernameTextField;
	    @FXML
	    private Label testlabel;
	    @FXML
	    private RadioButton providerbtn;
	    @FXML
	    private RadioButton clientbtn;
	    
	    @FXML
	    private Button showhide_btn;
	    @FXML
	    private Button showhide_btn1;
	    @FXML
	    private ImageView eyeimg;
	    
	    @FXML
	    private DialogPane dialog;
	    @FXML
		private Hyperlink facebook;
		@FXML
		private Hyperlink insta;
		@FXML
	    private Hyperlink twitter;
	    @FXML
	    private ToggleGroup type;
		
	    @FXML
	    void facebooklink(ActionEvent event) {
	    	try {
	    	    Desktop.getDesktop().browse(new URL("https://www.facebook.com/").toURI());// Open the default web browser to the Facebook homepage
	    	} catch (IOException e) {
	    	    e.printStackTrace();
	    	} catch (URISyntaxException e) {
	    	    e.printStackTrace();
	    	}
	    }
	   
	    @FXML
	    void instalink(ActionEvent event) {
	    	try {
	    	    Desktop.getDesktop().browse(new URL("https://www.instagram.com/").toURI());// Open the default web browser to the Instagram homepage
	    	} catch (IOException e) {
	    	    e.printStackTrace();
	    	} catch (URISyntaxException e) {
	    	    e.printStackTrace();
	    	}
	    }

	    @FXML
	    void twitterlink(ActionEvent event) {
	    	try {
	    	    Desktop.getDesktop().browse(new URL("https://twitter.com/").toURI());// Open the default web browser to the twitter homepage
	    	} catch (IOException e) {
	    	    e.printStackTrace();
	    	} catch (URISyntaxException e) {
	    	    e.printStackTrace();
	    	}
	    }
	    


	    @FXML
	    void ShowHide(ActionEvent event) {
	  
	    	password = passwordTextField.getText();
	    	passwordtxt.setVisible(true);// Make the password label visible
			showhide_btn1.setVisible(true);// Make the "show password" button visible
			passwordTextField.setVisible(false);// Make the password text field invisible
			showhide_btn.setVisible(false);// Make the "hide password" button invisible
			passwordtxt.setText(password);
	    
	    }
	    @FXML
	    void ShowHide1(ActionEvent event) {
	    	password = passwordtxt.getText();
	    	passwordtxt.setVisible(false);
			showhide_btn1.setVisible(false);
			passwordTextField.setVisible(true);
			showhide_btn.setVisible(true);
			passwordTextField.setText(password);
	
	    }


	    
	    @FXML
	    void createAccount(ActionEvent event) {
	
	        try {
	            // Load the FXML file
	            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/clientorprovider.fxml"));
	            Scene scene = new Scene(parent);// Create a new scene with the loaded FXML file as the root node
	            Stage primaryStage = new Stage();// Create a new stage for the scene
	            primaryStage.setScene(scene);// Set the scene on the stage
	            primaryStage.show();
	            // Get the current stage and close it
	            Stage stage = (Stage) create.getScene().getWindow(); 
	            stage.close();
	        } catch (IOException e) {
	            // Print a stack trace of the exception if there is an error loading the FXML file
	            e.printStackTrace();
	        }
	 	
	    }

	    	
	    
	    @FXML
	    void submitButtonOnAction(ActionEvent e) {
	    	
	      //testing if the user has entered his username and password before clicking on the button submit
	    	if(usernameTextField.getText().isBlank()==false && passwordTextField.getText().isBlank()==false) {
	    		validateLogin();
	    	}else {
	    		testlabel.setText("please enter your username and password");
	    	}
	
	    }
	
	    public void validateLogin(){
	    	DatabaseConnection connectNow=new  DatabaseConnection();
	    	Connection connect = connectNow.getConnection();// Establish a connection to the database
			if(clientbtn.isSelected()) {
			    String verifyLogin ="Select count(1) from user where username = '"+usernameTextField.getText()+ "' and password= '"+passwordTextField.getText()+"'";
				try {
					Statement statement=connect.createStatement();// Create a statement object
					ResultSet queryResult=statement.executeQuery(verifyLogin);// Execute the SQL query and store the result in a ResultSet
					
					while(queryResult.next()){
			    		if(queryResult.getInt(1)==1) {

			    			String 	connectedWorkerUsername = usernameTextField.getText();
				    		String 	connectedWorkerPassword=passwordTextField.getText();
				    			MyAppContext.workerUsername=connectedWorkerUsername;// Set the value of the workerPassword static variable in MyAppContext class
				    			MyAppContext.workerPassword=connectedWorkerPassword;
			    			try {
			    				// Load the FXML file
								Parent parent;
								parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/search.fxml"));
								Scene scene = new Scene(parent);// Create a new scene with the loaded FXML file as the root node
								Stage  primaryStage = new Stage();// Create a new stage for the scene
								primaryStage.setScene(scene);// Set the scene on the stage
								primaryStage.show();
								// Get the current stage and close it
								Stage stage = (Stage) submitButton.getScene().getWindow();
							    stage.close();
								
							} catch (IOException e) {
								e.printStackTrace();
							}

			    	
			    		}else {
			    			  // Create an alert dialog box
			    			  Alert alert = new Alert(AlertType.WARNING, "Your Username or password is incorrect, check again please.", javafx.scene.control.ButtonType.OK);
			            	  alert.setHeaderText("Something happend... :( !");
			      			  dialog= alert.getDialogPane();  
			      			  dialog.getStylesheets().add(getClass().getResource("style.css").toString());// Add a stylesheet to the dialog pane
			      			  dialog.getStyleClass().add("dialog");
			      			  alert.showAndWait();
			    		}
			    	}
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}else if(providerbtn.isSelected()) {
				   String verifyLogin ="Select count(1) from service_provider where username = '"+usernameTextField.getText()+ "' and password= '"+passwordTextField.getText()+"'";
					try {
						Statement statement=connect.createStatement();// Create a statement object
						ResultSet queryResult=statement.executeQuery(verifyLogin);// Execute the SQL query and store the result in a ResultSet
						while(queryResult.next()){
				    		if(queryResult.getInt(1)==1) {
				    			String 	connectedWorkerUsername = usernameTextField.getText();
					    		String 	connectedWorkerPassword=passwordTextField.getText();
					    			MyAppContext.workerUsername=connectedWorkerUsername;// Set the value of the workerUsername static variable
				           			MyAppContext.workerPassword=connectedWorkerPassword;
					    		try {
					    			// Load the FXML file
									Parent parent;
									parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/SProfile.fxml"));
									Scene scene = new Scene(parent);// Create a new scene with the loaded FXML file as the root node
									Stage  primaryStage = new Stage();
									primaryStage.setScene(scene);
									primaryStage.show();
									// Get the current stage and close it
									Stage stage = (Stage) submitButton.getScene().getWindow();
								    stage.close();
									
								} catch (IOException e) {
								   e.printStackTrace();
								}
				    		}else {
				    			  // Create an alert dialog box
				    			  Alert alert = new Alert(AlertType.WARNING, "username or password is not correct. Please try again", javafx.scene.control.ButtonType.OK);
				            	  alert.setHeaderText("Something happend... :( !");
				      			  dialog= alert.getDialogPane();  
				      			  dialog.getStylesheets().add(getClass().getResource("style.css").toString());
				      			  dialog.getStyleClass().add("dialog");
				      			  alert.showAndWait();
				    	
				    		}
				    	}
					} catch (SQLException e) {
						e.printStackTrace();
					}
		  }else {
			  Alert alert = new Alert(AlertType.WARNING, "You must select one .", javafx.scene.control.ButtonType.OK);
        	  alert.setHeaderText("Something happend... :( !");
  			  dialog= alert.getDialogPane();  
  			  dialog.getStylesheets().add(getClass().getResource("style.css").toString());
  			  dialog.getStyleClass().add("dialog");
  			  alert.showAndWait();
		  }
	    	
	    	
	    	
	    
	    	
	    	
 }
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
	    	passwordtxt.setVisible(false);
			showhide_btn1.setVisible(false);
			passwordTextField.setVisible(true);
			showhide_btn.setVisible(true);
			
		}

}
	    
