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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;

import javafx.stage.Stage;

public class AccountProvider implements Initializable{

	    @FXML
	    private ComboBox<String> Mychoicebox; 
	    private String [] speciality = {"CARPENTER","PLUMBER","CLEANER","TAILOR HAIR STYLIST","ELECTRICIAN"};
	   
	    @FXML
	    private ComboBox<String> txtcity;
	    private String [] city = {"Tanger","Casablanca","Fes","Zagora","Oujda","Tétouan","Rabat","El Jadida","Agadir","Salé","Meknès","Marrakech","Kénitra","Laayoune","Mohammédia","Béni Mellal","Nador","Safi","Témara","Berkane"};

	    @FXML
	    private Button back;
	    @FXML
	    private PasswordField comPasswordField;
	    @FXML
	    private Button comhide;


	    @FXML
	    private DialogPane dialog;
	    @FXML
	    private Button create;
	    @FXML
	    private Button hide;

	    @FXML
	    private PasswordField passwordField;

	    @FXML
	    private Button show;

	    @FXML
	    private Button showcom;
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
	    
		public static String id;
		static String password ;
		static String compassword;
	    
	    public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public void AddPhotoOnAction(ActionEvent e) {
	    	try {
				Parent parent;
				// Load the FXML file using FXMLLoader
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/AddPhoto.fxml"));
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
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	
	    }
	    
		
	    
	    
	
	    
	   public void Back_To_choose(ActionEvent event) {
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
			System.out.println(password);

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
			System.out.println(compassword);

	    }
	    
	    @FXML
		public void createOnAction(ActionEvent e) throws FileNotFoundException {
			password =  passwordField.getText();
			compassword =  comPasswordField.getText();
            // SQL statement
			String sql="insert into service_provider(name,username,speciality,phone_number,address,password,status,city,photo) values(?,?,?,?,?,?,?,?,?)";
	             if(password.equals(compassword)){
		          	   try {
		          		    // adding a default profile photo to the user in case he doesn't want to specify one
		          		   
		          	     	// Create a File object for the image file
		          		    File imageFile = new File("src/View/icons/defaultphoto.jpg");
		          		    // Create a FileInputStream to read the contents of the image file
		          		    FileInputStream fis = new FileInputStream(imageFile);
		                    //write the contents of the file into a byte array
		          		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		                    byte[] buffer = new byte[1024];
		                    //Variable to store the number of bytes read from the file
		                    int read;
		                    // Read the contents of the file in chunks and write them to the ByteArrayOutputStream until the entire file has been read
		                    while ((read = fis.read(buffer)) != -1) {
		                        baos.write(buffer, 0, read);
		                    }
		                    // Convert the contents of the ByteArrayOutputStream into a byte array
		                    byte[] imageBytes = baos.toByteArray();
		          		   
		                    // Establish a connection to the MySQL database
		                  	Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
		                    // Create a prepared statement to execute the SQL query
		                  	PreparedStatement st = cnx.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);   
		          		
		                  	// Setting the values of the parameters in the prepared statement
		                  	st.setString(1,txtname.getText());
		          			st.setString(2,txtuser.getText());
		          			st.setString(3,Mychoicebox.getValue().toString());
		          			st.setString(4,txtnumber.getText());
		          			st.setString(5,txtaddress.getText());
		          			st.setString(6,password);
		          			st.setString(7,"0");
		          			st.setString(8,txtcity.getValue().toString());
                            st.setBytes(9, imageBytes);
		          			
                            // Clear the text fields in the user interface
		          			txtname.setText("");
		          			txtuser.setText(""); 
		          			txtpswd.setText("");
		          			txtaddress.setText("");
		          			txtnumber.setText(""); 
		          			Mychoicebox.setValue("");
		          			txtconfirmpswd.setText("");
		          			txtcity.setValue("");

		          			st.execute();
		          			
		          		    // Getting the generated key from the statement
		        	   		ResultSet rs= st.getGeneratedKeys();
		        	   		if(rs.next()) {
		        	   			 id= rs.getString(1);
		        	   		}
		          			
		          			
		          			
		          			Parent parent1 = null;
		    				try {
		    					// Load the FXML file using FXMLLoader
								parent1 = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/AddPhoto.fxml"));
								
							} catch (IOException e1) {
								e1.printStackTrace();
							}
		    				Scene scene = new Scene(parent1);
		    				Stage  primaryStage = new Stage();
		    				// Set the Scene on the Stage
		    				primaryStage.setScene(scene);
		    				Image image = new Image("src/View/icons/logo3.png");	 
		    				primaryStage.getIcons().add(image);
		    				// Set the title
		    				primaryStage.setTitle("BriCOOL");
		    				//show the stage
		    				primaryStage.show(); 
		    				Stage stage1 = (Stage) create.getScene().getWindow();
		    			    //close the current scene
		    				stage1.close();
		
		 
		
		          			}catch(SQLException e1) {
		          				e1.printStackTrace();
		          			} catch (IOException e2) {
								e2.printStackTrace();
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
				 Mychoicebox.getItems().addAll(speciality);
				 txtcity.getItems().addAll(city);
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
