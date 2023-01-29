package src.Controller;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class usercontroller implements Initializable {
    private DialogPane dialog;
    @FXML
    private Button create;
    @FXML
    private TextField txtconfirmpswd;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtpswd;
    @FXML
    private TextField txtuser;
   //zineb
    @FXML
    private Button back;
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
    	try {
			Parent parent;
			// Load the FXML file using FXMLLoader
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/clientorprovider.fxml"));
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
			Stage stage = (Stage) back.getScene().getWindow();
		    stage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    @FXML
    void comhide(ActionEvent event) {
		// Hide the confirmed password
    	compassword = txtconfirmpswd.getText();
    	txtconfirmpswd.setVisible(false);
    	comhide.setVisible(false);
    	comPasswordField.setVisible(true);
		showcom.setVisible(true);
		comPasswordField.setText(compassword);

    }
    @FXML
    void hide(ActionEvent event) {
		    // Hide the password
    	    password = txtpswd.getText();
	    	txtpswd.setVisible(false);
	    	hide.setVisible(false);
			passwordField.setVisible(true);
			show.setVisible(true);
			passwordField.setText(password);

    }

    @FXML
    void show(ActionEvent event) {
    	// show the password
    	password = passwordField.getText();
    	txtpswd.setVisible(true);
    	hide.setVisible(true);
		passwordField.setVisible(false);
		show.setVisible(false);
		txtpswd.setText(password);

    }

    @FXML
    void showcom(ActionEvent event) {
    	// show the confirmed password
    	compassword = comPasswordField.getText();
    	txtconfirmpswd.setVisible(true);
    	comhide.setVisible(true);
    	comPasswordField.setVisible(false);
		showcom.setVisible(false);
		txtconfirmpswd.setText(compassword);

    }

	@FXML
	public void createOnAction(ActionEvent e) {
		password =  passwordField.getText();
		compassword =  comPasswordField.getText();
		System.out.println(password);
		System.out.println(compassword);
        // SQL query
		String sql="insert into user(name,username,password) values(?,?,?)";
             if(password.equals(compassword)){
	          	   try {
	          		    //establish a connection with the database
	                  	Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
	          			PreparedStatement st = cnx.prepareStatement(sql);   
	                  	// Setting the values of the parameters in the prepared statement
	          			st.setString(1,txtname.getText());
	          			st.setString(2,txtuser.getText());
	          			st.setString(3,password);
	          			txtname.setText("");
	          			txtuser.setText(""); 
	          			txtpswd.setText("");
	          			txtconfirmpswd.setText("");
	          			st.execute();
	          			Parent parent = null;
	    				try {
	    					// Load the FXML file using FXMLLoader
							parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/login.fxml"));
							// Create a new Scene with the loaded FXML file as the root node
		    				Scene scene = new Scene(parent);
		    				Stage  primaryStage = new Stage();
							// Set the Scene on the Stage
		    				primaryStage.setScene(scene);
		    				Image image = new Image("src/View/icons/logo3.png");	 
							// Create a new Image object 
		    				primaryStage.getIcons().add(image);
							// Set the title of the primaryStage
		    				primaryStage.setTitle("BriCOOL");
		    				primaryStage.show(); 
		    				Stage stage1 = (Stage) create.getScene().getWindow();
		    			     stage1.close();
	    				} catch (IOException e1) {
							e1.printStackTrace();
						}
	          			}catch(SQLException e1) {
	          				e1.printStackTrace();
	          			}
              }else {
        	          //Alert advertising the user that his password does not match 
	            	  Alert alert = new Alert(AlertType.WARNING, "Your password does not match, check again please.", javafx.scene.control.ButtonType.OK);
	            	  alert.setHeaderText("Something happend... :( !");
	      			  dialog= alert.getDialogPane();  
	      			  dialog.getStylesheets().add(getClass().getResource("style.css").toString());
	      			  dialog.getStyleClass().add("dialog");
	      			  alert.showAndWait();
              }
       }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//to show the the textfield of password and comfirm password and the buttons to show the characters of password of each one
		txtpswd.setVisible(false);
		txtconfirmpswd.setVisible(false);
		hide.setVisible(false);
		comhide.setVisible(false);
		passwordField.setVisible(true);
		comPasswordField.setVisible(true);
		show.setVisible(true);
		showcom.setVisible(true);

		
	}


   


	
}
