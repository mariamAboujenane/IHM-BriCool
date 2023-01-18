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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EditProfileUserController implements Initializable  {
	
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
    void edit_profile(ActionEvent event) {
		String name = txtname.getText();
		String username = txtusername.getText();
		String password = txtpassword.getText();
		String conpassword = txtconpassword.getText();
		if(password.equals(conpassword)) {
		  String updateQuery = "UPDATE user SET name = ?, username = ?, password = ? WHERE iduser = '1'";
		 
  	  try {
         	Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
         	 PreparedStatement preparedStmt = cnx.prepareStatement(updateQuery);
   		  preparedStmt.setString   (1,name);
   		preparedStmt.setString   (2,username);
   		preparedStmt.setString   (3,password);

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
			Alert alert = new Alert(AlertType.WARNING, "The passwords should match", javafx.scene.control.ButtonType.OK);
			 alert.showAndWait();
		}

    }

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String name = null;
		String username = null;
		String password = null;

		String sql = "select * from user where iduser='1'";
		try {

			Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
			Statement statement = cnx.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				name = rs.getString("name");
				username = rs.getString("username");
				password = rs.getString("password");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		txtname.setText(name);
		txtusername.setText(username);
		txtpassword.setText(password);
		txtconpassword.setText(password);
		
	}


}
