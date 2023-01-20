package src.Controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;

import javafx.stage.Stage;

public class AccountProvider implements Initializable{

	    @FXML
	    private ComboBox<String> Mychoicebox; 
	    private String [] speciality = {"CARPENTER","PLUMBER","CLEANER","TAILOR HAIR STYLIST","ELECTRICIAN"};
	    @FXML
	    private Button back;
	    @FXML
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
	    @FXML
	    private TextField txtnumber;
	    @FXML
	    private TextField txtaddress;
	    @FXML
	    private Button AddPhoto;
	    @FXML
	    private TextField txtcity;
		public static String id;
	    
	    public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public void AddPhotoOnAction(ActionEvent e) {
	    	try {
				Parent parent;
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/AddPhoto.fxml"));
				Scene scene = new Scene(parent);
				Stage  primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    	
	    }
	    
	    
		public void createOnAction(ActionEvent e) throws FileNotFoundException {
			String sql="insert into service_provider(name,username,speciality,phone_number,address,password,status,city,photo) values(?,?,?,?,?,?,?,?,?)";
	             if(txtpswd.getText().equalsIgnoreCase(txtconfirmpswd.getText())){
		          	   try {
		          		   
		          		   
		          		    File imageFile = new File("C:/Users/Zineb/git/IHM-BriCool/IhmProject/src/View/icons/robot.jpg");
		                    FileInputStream fis = new FileInputStream(imageFile);
		                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		                    byte[] buffer = new byte[1024];
		                    int read;
		                    while ((read = fis.read(buffer)) != -1) {
		                        baos.write(buffer, 0, read);
		                    }
		                    byte[] imageBytes = baos.toByteArray();
		          		   
		          	
		                  	Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
		          			PreparedStatement st = cnx.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);   
		          			st.setString(1,txtname.getText());
		          			st.setString(2,txtuser.getText());
		          			st.setString(3,Mychoicebox.getValue().toString());
		          			st.setString(4,txtnumber.getText());
		          			st.setString(5,txtaddress.getText());
		          			st.setString(6,txtpswd.getText());
		          			st.setString(7,"0");
		          			st.setString(8,txtcity.getText());
		          			st.setBytes(9, imageBytes);
		          			
		          			txtname.setText("");
		          			txtuser.setText(""); 
		          			txtpswd.setText("");
		          			txtaddress.setText("");
		          			txtnumber.setText(""); 
		          			Mychoicebox.setValue("");
		          			txtconfirmpswd.setText("");
		          			txtcity.setText("");

		          			st.execute();
		          			
		        	   		ResultSet rs= st.getGeneratedKeys();
		        	   		if(rs.next()) {
		        	   			 id= rs.getString(1);
		        	   		}
		          			
		          			
		          			
		          			Parent parent1 = null;
		    				try {
								parent1 = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/AddPhoto.fxml"));
								
							} catch (IOException e1) {
								e1.printStackTrace();
							}
		    				Scene scene = new Scene(parent1);
		    				Stage  primaryStage = new Stage();
		    				primaryStage.setScene(scene);
		    				primaryStage.show(); 
		    				Stage stage1 = (Stage) create.getScene().getWindow();
		    			     stage1.close();
		
		 
		
		          			}catch(SQLException e1) {
		          				e1.printStackTrace();
		          			} catch (IOException e2) {
								e2.printStackTrace();
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
	    
	    
	
	    
	   public void Back_To_choose(ActionEvent event) {
	    	try {
				Parent parent;
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/clientorprovider.fxml"));
				Scene scene = new Scene(parent);
				Stage  primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.show();
				Stage stage = (Stage) back.getScene().getWindow();
			    stage.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }


		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			 Mychoicebox.getItems().addAll(speciality);
			
		}

}
