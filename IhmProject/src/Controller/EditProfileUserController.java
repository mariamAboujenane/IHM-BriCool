package src.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EditProfileUserController implements Initializable  {
	String username_Client = MyAppContext.workerUsername;
	String password_Client = MyAppContext.workerPassword;
	
    @FXML
    private TextField txtconpassword;
	
	@FXML
    private Button back;

    @FXML
    private Button edit;
    

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtusername;
    @FXML
    private PasswordField comPasswordField;

    @FXML
    private Button comhide;
    @FXML
    private Button hide;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button show;

    @FXML
    private Button showcom;
    
	static String password;
	static String compassword;


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
    void comhide(ActionEvent event) {
		// Hide the confirmed password
    	compassword = txtconpassword.getText();
    	txtconpassword.setVisible(false);
    	comhide.setVisible(false);
    	comPasswordField.setVisible(true);
		showcom.setVisible(true);
		comPasswordField.setText(compassword);

    }
    @FXML
    void hide(ActionEvent event) {
	    // Hide the password
    	password = txtpassword.getText();
    	txtpassword.setVisible(false);
    	hide.setVisible(false);
		passwordField.setVisible(true);
		show.setVisible(true);
		passwordField.setText(password);

    }

    @FXML
    void show(ActionEvent event) {
    	// show the password
    	password = passwordField.getText();
    	txtpassword.setVisible(true);
    	hide.setVisible(true);
		passwordField.setVisible(false);
		show.setVisible(false);
		txtpassword.setText(password);

    }

    @FXML
    void showcom(ActionEvent event) {
    	// show the confirmed password
    	compassword = comPasswordField.getText();
    	txtconpassword.setVisible(true);
    	comhide.setVisible(true);
    	comPasswordField.setVisible(false);
		showcom.setVisible(false);
		txtconpassword.setText(compassword);

    }
    
    @FXML
    void edit_profile(ActionEvent event) {
    	//getting the values from the user interface
		String name = txtname.getText();
		String username = txtusername.getText();
		password =  passwordField.getText();
		compassword =  comPasswordField.getText();
		if(password.equals(compassword)) {
		  String updateQuery = "UPDATE user SET name = ?, username = ?, password = ? WHERE username='" +username_Client+ "' and password = '"+password_Client+"'";
		 
  	  try {
  		  //establish a connection
          Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
          PreparedStatement preparedStmt = cnx.prepareStatement(updateQuery);
          // Setting the values of the parameters in the prepared statement
          preparedStmt.setString   (1,name);
   		  preparedStmt.setString   (2,username);
   		  preparedStmt.setString   (3,password);

   		  preparedStmt.execute();
     	//Acknowledgment alert
   		Alert alert = new Alert(AlertType.WARNING, "Your profile have been updated", javafx.scene.control.ButtonType.OK);
		alert.showAndWait();
		Parent parent;
			try {
				// Load the FXML file using FXMLLoader
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/search.fxml"));
				// Create a new Scene with the loaded FXML file as the root node
				Scene scene = new Scene(parent);
				Stage  primaryStage = new Stage();
				primaryStage.setScene(scene);
				Image image = new Image("src/View/icons/logo3.png");	 
				primaryStage.getIcons().add(image);
			    // Set the title of the primaryStage
				primaryStage.setTitle("BriCOOL");
				primaryStage.show();
				//close the current scene
				Stage stage1 = (Stage) back.getScene().getWindow();
			    stage1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

 			 
 	  }catch(SQLException e1) {
 		e1.printStackTrace();

 	  }
		}else {
			//warning alert if the pswd doesn't match
			Alert alert = new Alert(AlertType.WARNING, "The passwords should match", javafx.scene.control.ButtonType.OK);
			 alert.showAndWait();
		}

    }

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//initializing the interface with the user's informations

		String name = null;
		String username = null;

		String sql = "select name,username,password from user where username='" +username_Client+ "' and password = '"+password_Client+"'";
		try {
			//establish a connection with the database
			Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
			Statement statement = cnx.createStatement();
			//Setting the result of the statement in a ResultSet
			ResultSet rs = statement.executeQuery(sql);
            //reading the results
			while (rs.next()) {
				name = rs.getString("name");
				username = rs.getString("username");
				password = rs.getString("password");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//setting the results on the user's interface
		txtname.setText(name);
		txtusername.setText(username);
		txtpassword.setText(password);
		txtconpassword.setText(password);
		passwordField.setText(password);
		comPasswordField.setText(password);
		
		//to show the the textfield of password and comfirm password and the buttons to show the characters of password of each one
		txtpassword.setVisible(false);
		txtconpassword.setVisible(false);
		hide.setVisible(false);
		comhide.setVisible(false);
		passwordField.setVisible(true);
		comPasswordField.setVisible(true);
		show.setVisible(true);
		showcom.setVisible(true);

		
	}


}
