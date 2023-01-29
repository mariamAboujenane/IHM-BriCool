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

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EditProfileController implements Initializable  {
	
    @FXML
    private ComboBox<String> Mychoicebox;
    private String [] speciality = {"CARPENTER","PLUMBER","CLEANER","TAILOR HAIR STYLIST","ELECTRICIAN"};
    private String [] city = {"Tanger","Casablanca","Fes","Zagora","Oujda","Tétouan","Rabat","El Jadida","Agadir","Salé","Meknès","Marrakech","Kénitra","Laayoune","Mohammédia","Béni Mellal","Nador","Safi","Témara","Berkane"};
	String username_Provider = MyAppContext.workerUsername;
	String password_Provider = MyAppContext.workerPassword;
    @FXML
    private Button back;
    @FXML
    private Button edit;
    @FXML
    private TextField txtaddress;
    @FXML
    private ComboBox<String> txtcity;
    @FXML
    private TextField txtconfirmpswd;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtnumber;
    @FXML
    private TextField txtpswd;
    @FXML
    private TextField bio;
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
    @FXML
    private TextField txtusername;
    
	static String password_field;
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
    //
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
	    	password_field = txtpswd.getText();
	    	txtpswd.setVisible(false);
	    	hide.setVisible(false);
			passwordField.setVisible(true);
			show.setVisible(true);
			passwordField.setText(password_field);

    }

    @FXML
    void show(ActionEvent event) {
    	// show the password
    	password_field = passwordField.getText();
    	txtpswd.setVisible(true);
    	hide.setVisible(true);
		passwordField.setVisible(false);
		show.setVisible(false);
		txtpswd.setText(password_field);

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
    void edit_profile(ActionEvent event) {
    	//getting the values from the user interface
		String name = txtname.getText();
		String username = txtusername.getText();
		String speciality = Mychoicebox.getValue();
		String phone_number = txtnumber.getText();
		String address = txtaddress.getText();
		String Bio = bio.getText();
		String city = txtcity.getValue();
		password_field =  passwordField.getText();
		compassword =  comPasswordField.getText();
		if(password_field.equals(compassword)) {
		  //creating a query
		  String updateQuery = "UPDATE service_provider SET name = ?, username = ?, speciality = ?, phone_number = ?, address = ?, password = ?,Bio =?, city =? WHERE username='" +username_Provider+ "' and password = '"+password_Provider+"'";
		 
  	  try {
  		//Establish a connection with the database
        Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
        // SQL statement
        PreparedStatement preparedStmt = cnx.prepareStatement(updateQuery);
      	// Setting the values of the parameters in the prepared statement
        preparedStmt.setString   (1,name);
   		preparedStmt.setString   (2,username);
   		preparedStmt.setString   (3,speciality);
   		preparedStmt.setString   (4,phone_number);
   		preparedStmt.setString   (5,address);
   		preparedStmt.setString   (6,password_field);
   		preparedStmt.setString   (7,Bio);
   		preparedStmt.setString   (8,city);

   		preparedStmt.execute();
   		//Acknowledgment alert
   		Alert alert = new Alert(AlertType.WARNING, "Your profile have been updated", javafx.scene.control.ButtonType.OK);
		 alert.showAndWait();
		 Parent parent;
			try {
				// Load the FXML file using FXMLLoader
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/SProfile.fxml"));
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
				Stage stage1 = (Stage) edit.getScene().getWindow();
			    stage1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

 			 
 	  }catch(SQLException e1) {
 		e1.printStackTrace();

 	  }
		}else {
			// warning alert if the pswd doesn't match
			Alert alert = new Alert(AlertType.WARNING, "The passwords should match", javafx.scene.control.ButtonType.OK);
			 alert.showAndWait();
		}

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//initializing the interface with the user's informations
		
		Mychoicebox.getItems().addAll(speciality);		
		txtcity.getItems().addAll(city);
		String name = null;
		String username = null;
		String speciality = null;
		String phone_number = null;
		String address = null;
		String Bio = null;
		String city = null;

		String sql = "select name,username,speciality,phone_number,address,password,Bio,city from service_provider where username='" +username_Provider+ "' and password = '"+password_Provider+"'";
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
				speciality = rs.getString("speciality");
				phone_number = rs.getString("phone_number");
				address = rs.getString("address");
				password_field = rs.getString("password");
				Bio = rs.getString("bio");
				city = rs.getString("city");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//setting the results on the user's interface
		txtname.setText(name);
		txtusername.setText(username);
		Mychoicebox.setValue(speciality);
		txtnumber.setText(phone_number);
		txtaddress.setText(address);
		txtpswd.setText(password_field);
		txtconfirmpswd.setText(password_field);
		passwordField.setText(password_field);
		comPasswordField.setText(password_field);
		bio.setText(Bio);
		txtcity.setValue(city);
		
		
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
