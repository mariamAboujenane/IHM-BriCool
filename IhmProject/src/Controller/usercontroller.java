package src.Controller;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.sql.SQLException;



public class usercontroller {
	 @FXML
	    private Button back;
	
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
    void Back_To_choose(ActionEvent event) {
    	
      	try {
			Parent parent;
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/clientorprovider.fxml"));
			
			Scene scene = new Scene(parent);
			
			Stage  primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
			Stage stage = (Stage) back.getScene().getWindow();
		    // do what you have to do
		    stage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
   
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
	          			Parent parent = null;
	    				try {
							parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/login.fxml"));
							
						} catch (IOException e1) {
							e1.printStackTrace();
						}
	    				
	    				Scene scene = new Scene(parent);
	    				Stage  primaryStage = new Stage();
	    				primaryStage.setScene(scene);
	    				primaryStage.show(); 
	    				
	    				
	    				Stage stage1 = (Stage) create.getScene().getWindow();
	    			     stage1.close();
	
	          			}catch(SQLException e1) {
	          				e1.printStackTrace();
	          			}
              }else {
	            	  Alert alert = new Alert(AlertType.WARNING, "Your password does not match, check again please.", javafx.scene.control.ButtonType.OK);
	            	  alert.setHeaderText("Something happend... :( !");
	      			  dialog= alert.getDialogPane();  
	      			  dialog.getStylesheets().add(getClass().getResource("style.css").toString());
	      			  dialog.getStyleClass().add("dialog");
	      			  alert.showAndWait();
              }
       }

    
   


	
}