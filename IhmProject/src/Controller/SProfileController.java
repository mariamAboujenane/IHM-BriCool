package src.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SProfileController {
	
	  @FXML
	    private Button AddBtn;
	   @FXML
	    private TextField BioTxt;

    @FXML
    private Button editbtn;
    
    @FXML
    void AddBio(ActionEvent event) {
    	String sql="insert into bio(Bio) values(?)";
    	  try {
           	Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
   			PreparedStatement st = cnx.prepareStatement(sql);   
   			st.setString(1,BioTxt.getText());
   			

   			BioTxt.setText("");

   			st.execute();
   			Alert alert = new Alert(AlertType.WARNING, "Bio added.", javafx.scene.control.ButtonType.OK);
   			 alert.showAndWait();
   			 
   	  }catch(SQLException e1) {
   		e1.printStackTrace();

   	  }



    }
    @FXML
    void EditProfile(ActionEvent event) {
    	Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/EditProfile.fxml"));
			Scene scene = new Scene(parent);
			
			Stage  primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			Stage stage1 = (Stage) editbtn.getScene().getWindow();
		    stage1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}

