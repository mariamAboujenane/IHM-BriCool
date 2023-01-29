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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

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
 

public class AddPhotoController {

    @FXML
    private Button addButton;
    @FXML
    private Button Back;
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
    private Image image;
    private String path;
	   @FXML
	    private Button cancel;

	    @FXML
	    void cancelOnAction(ActionEvent event) {
	    	Parent parent;
			try {
				// Load the FXML file using FXMLLoader
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/login.fxml"));
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
				//Get the current scene and close it
				Stage stage1 = (Stage) cancel.getScene().getWindow();
			    stage1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

	    }
	
   //method that handles the drag-and-drop event of a file over a specific area

    @FXML
    void handleDragOver(DragEvent event) { 
    	//checks if the event contains any files
        if(event.getDragboard().hasFiles()) { 
        //accepts the transfer mode of the files
    	event.acceptTransferModes(TransferMode.ANY); 
    	addButton.setDisable(false);

        }
    }
    
    @FXML
    void handledrop(DragEvent event) throws FileNotFoundException {
     // Retrieve the files from the Dragboard
     files =event.getDragboard().getFiles();
     // Retrieve the path of the first file dropped
     path=(files.get(0)).getAbsolutePath();
     // Create a new FileInputStream using the first file
     postImage =new FileInputStream(files.get(0));
     // Create a new Image object using the FileInputStream
     image=new Image(postImage);
     // Set the Image object on the ImageView
     imageView.setImage(image);
     draganddrop.setVisible(false);
     uploadImage.setVisible(false);
     
    }
    @FXML
    void backOnAction(ActionEvent event) {
    	Parent parent1 = null;
		try {
			// Load the FXML file using FXMLLoader
			parent1 = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/AccountProvider.fxml"));
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// Create a new Scene with the loaded FXML file as the root node
		Scene scene = new Scene(parent1);
		Stage  primaryStage = new Stage();
		// Set the Scene on the Stage
		primaryStage.setScene(scene);
		Image image = new Image("src/View/icons/logo3.png");	 
	    // Set the Scene on the Stage
		primaryStage.getIcons().add(image);
	    // Set the title of the primaryStage
		primaryStage.setTitle("BriCOOL");
		primaryStage.show();
		//get the current scene and close it
		Stage stage1 = (Stage) Back.getScene().getWindow();
	    stage1.close();



    }
    
   
 
    @FXML
    void addAction(ActionEvent event) throws FileNotFoundException {	
        // Establish a connection to the MySQL database
    	DatabaseConnection connectNow=new  DatabaseConnection();
		Connection connect = connectNow.getConnection();
		String addPhotoQuery ="update service_provider set photo = ? where idprovider= '"+AccountProvider.id+"'";
		try {
  		    // Create a FileInputStream to read the contents of the image file
            FileInputStream fis = new FileInputStream(path);
            //write the contents of the file into a byte array
			ByteArrayOutputStream baos= new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
            //Variable to store the number of bytes read from the file
	        int read;
	        try {
            // Read the contents of the file in chunks and write them to the ByteArrayOutputStream until the entire file has been read
			while ((read = fis.read(buffer)) != -1) {
			   baos.write(buffer, 0, read);
			}
			} catch (IOException e) {
				e.printStackTrace();
			}
	            byte[] imageBytes = baos.toByteArray();
                // Create a prepared statement to execute the SQL query
	   	 	    PreparedStatement st = connect.prepareStatement(addPhotoQuery);
	   	        //setting the image 
	   	 	    st.setBytes(1, imageBytes);
				st.execute();
				//confirmation alert
				   Alert alert = new Alert(Alert.AlertType.ERROR);
				    alert.setTitle("Confirmation");
				    alert.setHeaderText("Photo added succesfully !");
				    dialog= alert.getDialogPane();  
				    dialog.getStylesheets().add(getClass().getResource("style.css").toString());
	      			dialog.getStyleClass().add("dialog");
				    ImageView icon = new ImageView(new Image(String.valueOf(this.getClass().getResource("/src/View/icons/checkmark.png"))));
				    icon.setFitHeight(48);
				    icon.setFitWidth(48);
				    alert.getDialogPane().setGraphic(icon);
				    alert.show();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		

    }
  
}
