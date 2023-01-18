package src.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import src.Model.DatabaseConnection;
	
	
	public class LoginController {
		
		Image closeeye = new Image("src/View/icons/closeeye.png");
		Image openeye = new Image("src/View/icons/openeye.png");
		
	    @FXML
	    private Button create;
	    @FXML
	    private PasswordField passwordTextField;
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
	    private ImageView eyeimg;

	    @FXML
	    void ShowHide(ActionEvent event) {
	    	
	    	eyeimg.setImage(closeeye);
	    	passwordTextField.setText( passwordTextField.getText());
	    
	    }

	    
	    @FXML
	    void createAccount(ActionEvent event) {
	    	try {
				Parent parent;
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/clientorprovider.fxml"));
				
				Scene scene = new Scene(parent);
				
				Stage  primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.show();
				
				Stage stage = (Stage) create.getScene().getWindow();
			    // do what you have to do
			  stage.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
	    	Connection connect = connectNow.getConnection();
			if(clientbtn.isSelected()) {
			    String verifyLogin ="Select count(1) from user where username = '"+usernameTextField.getText()+ "' and password= '"+passwordTextField.getText()+"'";
				try {
					Statement statement=connect.createStatement();
					ResultSet queryResult=statement.executeQuery(verifyLogin);	
					while(queryResult.next()){
			    		if(queryResult.getInt(1)==1) {
			    			testlabel.setText("welcome");
			    		}else {
			    			testlabel.setText("username or password is not correct. Please try again");
			    		}
			    	}
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}else if(providerbtn.isSelected()) {
				   String verifyLogin ="Select count(1) from service_provider where username = '"+usernameTextField.getText()+ "' and password= '"+passwordTextField.getText()+"'";
					try {
						Statement statement=connect.createStatement();
						ResultSet queryResult=statement.executeQuery(verifyLogin);
						while(queryResult.next()){
				    		if(queryResult.getInt(1)==1) {
				    			testlabel.setText("welcome");
				    		}else {
				    			testlabel.setText("username or password is not correct. Please try again");
				    		}
				    	}
					} catch (SQLException e) {
						e.printStackTrace();
					}
		  }
	    	
	    	
	    	
	    
	    	
	    	
 }

}
	    
