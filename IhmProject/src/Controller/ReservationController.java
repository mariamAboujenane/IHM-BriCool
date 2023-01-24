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
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/PreProfile.fxml"));
			
			Scene scene = new Scene(parent);
			
			Stage  primaryStage = new Stage();
			primaryStage.setScene(scene);
			 Image image = new Image("src/View/icons/logo3.png");	 
				primaryStage.getIcons().add(image);
				primaryStage.setTitle("BriCOOL");
			primaryStage.show();
			
			Stage stage = (Stage) back.getScene().getWindow();
		    // do what you have to do
		    stage.close();
			
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

			Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
			Statement statement = cnx.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				service_name = rs.getString("name");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		servicetxt.setText(service_name);
		nametxt.setText(name_client);
		try {

			Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
			Statement statement = cnx.createStatement();
			ResultSet rs = statement.executeQuery(sql_client);

			while (rs.next()) {
				id_client = rs.getInt("iduser");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

    @FXML
    void submit(ActionEvent event) {
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
   				primaryStage.setScene(scene);
   			 Image image = new Image("src/View/icons/logo3.png");	 
 			primaryStage.getIcons().add(image);
 			primaryStage.setTitle("BriCOOL");
   				primaryStage.show(); 
   				Stage stage1 = (Stage) submit.getScene().getWindow();
   			     stage1.close();



         			}catch(SQLException e1) {
         				e1.printStackTrace();
      
    }
    }}

