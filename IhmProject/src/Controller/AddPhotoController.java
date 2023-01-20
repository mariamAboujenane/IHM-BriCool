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
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/login.fxml"));
				Scene scene = new Scene(parent);
				
				Stage  primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.show();
				Stage stage1 = (Stage) cancel.getScene().getWindow();
			    stage1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

	    }
	


    @FXML
    void handleDragOver(DragEvent event) {  //we use drag event if we went to drag something from another application and drop it in our fxml file
        if(event.getDragboard().hasFiles()) { //we did a condition here to accept just files .
    	event.acceptTransferModes(TransferMode.ANY); // it means we can accept any kind of files
    	addButton.setDisable(false);

        }
    }
    
    @FXML
    void handledrop(DragEvent event) throws FileNotFoundException {
     files =event.getDragboard().getFiles();
     path=(files.get(0)).getAbsolutePath();
     postImage =new FileInputStream(files.get(0));
     image=new Image(postImage);
     imageView.setImage(image);
     draganddrop.setVisible(false);
     uploadImage.setVisible(false);
     
    }
    
   
 
    @FXML
    void addAction(ActionEvent event) throws FileNotFoundException {	
    	
    	DatabaseConnection connectNow=new  DatabaseConnection();
		Connection connect = connectNow.getConnection();
		String addPhotoQuery ="update service_provider set photo = ? where idprovider= '"+AccountProvider.id+"'";
		try {
            FileInputStream fis = new FileInputStream(path);

			ByteArrayOutputStream baos= new ByteArrayOutputStream();
			 byte[] buffer = new byte[1024];
	            int read;
	            try {
					while ((read = fis.read(buffer)) != -1) {
					    baos.write(buffer, 0, read);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
	            byte[] imageBytes = baos.toByteArray();

	   	 	    PreparedStatement st = connect.prepareStatement(addPhotoQuery);
	   	        st.setBytes(1, imageBytes);
				st.execute();
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
