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
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/search.fxml"));
			Scene scene = new Scene(parent);
			
			Stage  primaryStage = new Stage();
			primaryStage.setScene(scene);
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
    	compassword = txtconfirmpswd.getText();
    	txtconfirmpswd.setVisible(false);
    	comhide.setVisible(false);
    	comPasswordField.setVisible(true);
		showcom.setVisible(true);
		comPasswordField.setText(compassword);

    }
    @FXML
    void hide(ActionEvent event) {
    	 password_field = txtpswd.getText();
	    	txtpswd.setVisible(false);
	    	hide.setVisible(false);
			passwordField.setVisible(true);
			show.setVisible(true);
			passwordField.setText(password_field);

    }

    @FXML
    void show(ActionEvent event) {
    	password_field = passwordField.getText();
    	txtpswd.setVisible(true);
    	hide.setVisible(true);
		passwordField.setVisible(false);
		show.setVisible(false);
		txtpswd.setText(password_field);

    }

    @FXML
    void showcom(ActionEvent event) {
    	compassword = comPasswordField.getText();
    	txtconfirmpswd.setVisible(true);
    	comhide.setVisible(true);
    	comPasswordField.setVisible(false);
		showcom.setVisible(false);
		txtconfirmpswd.setText(compassword);

    }


    @FXML
    void edit_profile(ActionEvent event) {
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
		  String updateQuery = "UPDATE service_provider SET name = ?, username = ?, speciality = ?, phone_number = ?, address = ?, password = ?,Bio =?, city =? WHERE username='" +username_Provider+ "' and password = '"+password_Provider+"'";
		 
  	  try {
         	Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
         	 PreparedStatement preparedStmt = cnx.prepareStatement(updateQuery);
   		  preparedStmt.setString   (1,name);
   		preparedStmt.setString   (2,username);
   		preparedStmt.setString   (3,speciality);
   		preparedStmt.setString   (4,phone_number);
   		preparedStmt.setString   (5,address);
   		preparedStmt.setString   (6,password_field);
   		preparedStmt.setString   (7,Bio);
   		preparedStmt.setString   (8,city);

   		 preparedStmt.execute();
   		 
   		Alert alert = new Alert(AlertType.WARNING, "Your profile have been updated", javafx.scene.control.ButtonType.OK);
		 alert.showAndWait();
		 Parent parent;
			try {
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/SProfile.fxml"));
				Scene scene = new Scene(parent);
				
				Stage  primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.show();
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
			Alert alert = new Alert(AlertType.WARNING, "The passwords should match", javafx.scene.control.ButtonType.OK);
			 alert.showAndWait();
		}

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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

			Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
			Statement statement = cnx.createStatement();
			ResultSet rs = statement.executeQuery(sql);

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
