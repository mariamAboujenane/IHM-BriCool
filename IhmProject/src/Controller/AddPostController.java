package src.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import javafx.event.ActionEvent;

import src.Model.DatabaseConnection;
 

public class AddPostController {

    @FXML
    private Button addButton;
    
    @FXML
    private TextField posttitle;
    
    @FXML
    private Button draganddrop;

    @FXML
    private ImageView imageView;

    @FXML
    private ImageView uploadImage;
    
    private FileInputStream postImage;
    List<File> files;
    private DialogPane dialog;
	   @FXML
	    private Button btn_information;
	   
	  
	   @FXML
	    private Button btn_notification;
	   @FXML
	    private Button back;
	   int id ;
	    @FXML
	    void Back_To_choose(ActionEvent event) {
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

	    }
	   @FXML
	    void gonotification(ActionEvent event) {
     	
			try {
				Parent parent;
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/Notification.fxml"));
				
				Scene scene = new Scene(parent);
				
				Stage  primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.show();
				
				Stage stage = (Stage) btn_notification.getScene().getWindow();
			    // do what you have to do
			  stage.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
			
	    }
	    @FXML
	    void goinformation(ActionEvent event) {
	    	try {
				Parent parent;
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/information.fxml"));
				
				Scene scene1 = new Scene(parent);
				
				Stage  primaryStage1 = new Stage();
				primaryStage1.setScene(scene1);
				primaryStage1.show();
				
				Stage stage1 = (Stage) btn_information.getScene().getWindow();
			    // do what you have to do
			  stage1.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	

	    }
	    
	  
	    @FXML
	    void gohistory(ActionEvent event) {
	    	try {
				Parent parent;
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/ClientHistory.fxml"));
				
				Scene scene1 = new Scene(parent);
				
				Stage  primaryStage1 = new Stage();
				primaryStage1.setScene(scene1);
				primaryStage1.show();
				
				Stage stage1 = (Stage) btn_information.getScene().getWindow();
			    // do what you have to do
			     stage1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    @FXML
	    void singnout(ActionEvent event) {
	    	
	    	try {
	    		
	    		Alert alert = new Alert(AlertType.CONFIRMATION );
	    		alert.setHeaderText(null);
	    		alert.setContentText("Are you sure that you want to log out !");
	    		alert.initModality(Modality.NONE);
	    		dialog= alert.getDialogPane();  
			dialog.getStylesheets().add(getClass().getResource("style2.css").toString());
			 dialog.getStyleClass().add("dialog");
  			Optional<ButtonType> result = alert.showAndWait();
  			 if(result.isEmpty()) {
  				System.out.print("Alert closed ");
  		     }else if(result.get()==ButtonType.OK) {
  	
  		    	Parent parent;
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/login.fxml"));
				
				Scene scene1 = new Scene(parent);
				
				Stage  primaryStage1 = new Stage();
				primaryStage1.setScene(scene1);
				primaryStage1.show();
				Stage stage1 = (Stage) btn_information.getScene().getWindow();
			     stage1.close(); 
  		     }else if(result.get()==ButtonType.OK) {  
  		    	System.out.print("Alert closed "); 
  		     }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    
	    }
	    @FXML
	    void goeditprofil(ActionEvent event) {
	    	try {
				Parent parent;
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/EditProfileUser.fxml"));
				
				Scene scene1 = new Scene(parent);
				
				Stage  primaryStage1 = new Stage();
				primaryStage1.setScene(scene1);
				primaryStage1.show();
				
				Stage stage1 = (Stage) btn_information.getScene().getWindow();
			    // do what you have to do
			     stage1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    
	    }
	

	

    @FXML
    void handleDragOver(DragEvent event) {  //we use drag event if we went to drag something from another application and drop it in our fxml file
        if(event.getDragboard().hasFiles()) { //we did a condition here to accept just files .
    	event.acceptTransferModes(TransferMode.ANY); // it means we can accept any kind of files
        }
    }
    
    @FXML
    void handledrop(DragEvent event) throws FileNotFoundException {
     files =event.getDragboard().getFiles();
     postImage =new FileInputStream(files.get(0));
     Image image=new Image(postImage);
     imageView.setImage(image);
     draganddrop.setVisible(false);
     uploadImage.setVisible(false);
     
    }
    
    @FXML
    void submitButtonOnAction(ActionEvent e) {
    	
    //testing if the user has entered his username and password before clicking on the button submit
    	

    }
    @FXML
    void addAction(ActionEvent event) {	
    		insertPostInDatabase();
    }
    
    @FXML
    void textChanged(ActionEvent event) {
    	if(posttitle.getText().isBlank()==false) {
    	addButton.setDisable(false);
    }
    }
    
    public void insertPostInDatabase(){
    	
    	ZoneId z = ZoneId.of( "America/Montreal" );
    	LocalDate today = LocalDate.now( z );
    	String currentDate = today.toString() ;
    	

   	 DatabaseConnection connectNow=new  DatabaseConnection();
   	Connection connect = connectNow.getConnection();
   
   	String selectId = "SELECT idprovider FROM service_provider WHERE username = ? and password = ?";
   	String name = MyAppContext.workerUsername;
   	String password = MyAppContext.workerPassword;

   	try {
   	    PreparedStatement st = connect.prepareStatement(selectId);
   	    st.setString(1, name);
   	    st.setString(2, password);
   	    ResultSet result = st.executeQuery();
   	   id = -1;
   	    if (result.next()) {
   	        id = result.getInt("idprovider");
   	    }
   	    if(id != -1){
   	        System.out.println("id =" + id);
   	        // do something with the retrieved id
   	    } else {
   	        System.out.println("No matching user found");
   	    }
   	} catch (SQLException e) {
   	    System.out.println("An error occurred while retrieving the id: " + e.getMessage());
   	}

   	
	String addPostQuery ="insert into post(idp,postTitle,postContent,publishedAt) values(?,?,?,?)";
   	try {
   		
   	 	PreparedStatement st1 = connect.prepareStatement(addPostQuery); 
   	 st1.setInt(1, id);
			st1.setString(2, posttitle.getText());
			if(postImage==null) {
				st1.setBinaryStream(3, null);
			}else {
		    st1.setBinaryStream(3, (InputStream) postImage);
			}
			st1.setString(4, currentDate);
			st1.execute();
  			Alert alert = new Alert(AlertType.CONFIRMATION, "post added succesfully!", javafx.scene.control.ButtonType.OK);
  			
  		alert.showAndWait();
  		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/SProfile.fxml"));
			Scene scene1 = new Scene(parent);
			
			Stage  primaryStage1 = new Stage();
			primaryStage1.setScene(scene1);
			primaryStage1.show();
			Stage stage1 = (Stage) addButton.getScene().getWindow();
		     stage1.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
   	
}



}
