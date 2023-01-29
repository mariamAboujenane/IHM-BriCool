package src.Controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ReservationController implements Initializable {
	int id =MyAppContext.selectedIdPersonInSearch;
	int id_client;
	 @FXML
	    private TextField addresstxt;

    @FXML
    private Button back;
    @FXML
    private TextField descriptiontxt;

    @FXML
    private TextField nametxt;

    @FXML
    private TextField phonetxt;

    @FXML
    private TextField servicetxt;
    
    @FXML
    private Button submit;

    @FXML
    private DatePicker time;

    @FXML
    void Back_To_choose(ActionEvent event) {
    	try {
    		Parent parent;

			// Load the FXML file using FXMLLoader
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/PreProfile.fxml"));
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		String name_client = MyAppContext.workerUsername;
		String pass_client = MyAppContext.workerPassword;
		String service_name = null;
		String sql = "select name from service_provider where idprovider='" +id+ "'";
		String sql_client = "select iduser from user where username='" +name_client+ "' and password = '"+pass_client+"'";
		try {
			//establish a connection with the database
			Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
			Statement statement = cnx.createStatement();
			//Setting the result of the statement in a ResultSet
			ResultSet rs = statement.executeQuery(sql);
            //reading the results
			while (rs.next()) {
				service_name = rs.getString("name");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//setting the results on the user's interface
		servicetxt.setText(service_name);
		nametxt.setText(name_client);
		try {
			//establish a connection with the database
			Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
			Statement statement = cnx.createStatement();
			//Setting the result of the statement in a ResultSet
			ResultSet rs = statement.executeQuery(sql_client);
            //reading the results
			while (rs.next()) {
				id_client = rs.getInt("iduser");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

    @FXML
    void submit(ActionEvent event) {
		//sending the values to the database

    	String sql="insert into reservation(iduser,idprovider,Service_Provider,full_name,Address,phone_number,Date,Description) values(?,?,?,?,?,?,?,?)";
         	   try {
         	
                 	Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
         			PreparedStatement st = cnx.prepareStatement(sql);  
         			st.setInt(1,id);
         			st.setInt(2,id_client);
         			st.setString(3,servicetxt.getText());
         			st.setString(4,nametxt.getText());
         			st.setString(5,addresstxt.getText());
         			st.setString(6,phonetxt.getText());
         			st.setDate(7,java.sql.Date.valueOf(time.getValue()));
         			st.setString(8,descriptiontxt.getText());
         		
         			st.execute();
         	//Acknowledgment alert	
       	   	 Alert alert = new Alert(AlertType.WARNING, "Reservation made.", javafx.scene.control.ButtonType.OK);
       	     alert.showAndWait();
         			
         			Parent parent1 = null;
   				   try {
						parent1 = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/search.fxml"));
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
   				Scene scene = new Scene(parent1);
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
   				Stage stage1 = (Stage) submit.getScene().getWindow();
   			    stage1.close();
   				



         			}catch(SQLException e1) {
         				e1.printStackTrace();
      
    }
    }
    }

