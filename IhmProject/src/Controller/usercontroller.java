package src.Controller;
import javafx.event.ActionEvent;
import src.View.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class usercontroller {
    private ResourceBundle resources;
    private URL location;
    @FXML
    private Button create;
    @FXML
    private  TextField txtconfirmpswd;
    @FXML
    private  TextField txtname;
    @FXML
    private  TextField txtpswd;
    @FXML
    private  TextField txtuser;
 	
   //zineb
   
	@FXML
	public void createOnAction(ActionEvent e) {
		String sql="insert into user(name,username,password) values(?,?,?)";

             if(txtpswd.getText().equalsIgnoreCase(txtconfirmpswd.getText())){
          	   try {
                  	Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
          			PreparedStatement st = cnx.prepareStatement(sql);   
          			st.setString(1,txtname.getText());
          			st.setString(2,txtuser.getText());
          			st.setString(3,txtpswd.getText());
          			txtname.setText("");
          			txtuser.setText(""); 
          			txtpswd.setText("");
          			txtconfirmpswd.setText("");
          			st.execute();
          			Alert alert = new Alert(AlertType.CONFIRMATION, "User added succesfully!", javafx.scene.control.ButtonType.OK);
          			alert.showAndWait();
          			}catch(SQLException e1) {
          				e1.printStackTrace();
                    }
              }else {
            	  Alert alert = new Alert(AlertType.WARNING, "Your password does not match, check again please", javafx.scene.control.ButtonType.OK);
      			  alert.showAndWait();
              }
       
              
     		
	}

    
   


	
}