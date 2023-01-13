package src.Controller;

import src.Model.*;



	import javafx.fxml.FXML;
	import javafx.scene.control.Button;
	import javafx.scene.control.PasswordField;
	import javafx.scene.control.TextField;
	import javafx.scene.control.Label;

import java.sql.*;

import javafx.event.ActionEvent;
	
	
	public class LoginController {
		
	    @FXML
	    private PasswordField passwordTextField;

	    @FXML
	    private Button submitButton;

	    @FXML
	    private TextField usernameTextField;
	    
	    @FXML
	    private Label testlabel;

	    

	    	
	    
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
	    	String verifyLogin ="Select count(1) from service_provider where username = '"+usernameTextField.getText()+ "' and password= '"+passwordTextField.getText()+"'";
          
	    	try {
				Statement statement=connect.createStatement();
				ResultSet queryResult=statement.executeQuery(verifyLogin);
				
				while (queryResult.next()){
		    		if(queryResult.getInt(1)==1) {
		    			testlabel.setText("welcome");
		    		}else {
		    			testlabel.setText("username or password is not correct. Please try again");
		    		}
		    	}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
 }
}
