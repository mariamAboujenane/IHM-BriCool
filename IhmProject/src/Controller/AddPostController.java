package src.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
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
   	String addPostQuery ="insert into post(postTitle,postContent,publishedAt) values(?,?,?)";
   
   	try {
   		
   	 	PreparedStatement st = connect.prepareStatement(addPostQuery); 
			
			st.setString(1, posttitle.getText());
			if(postImage==null) {
				st.setBinaryStream(2, null);
			}else {
		    st.setBinaryStream(2, (InputStream) postImage);
			}
			st.setString(3, currentDate);
			st.execute();
  			Alert alert = new Alert(AlertType.CONFIRMATION, "post added succesfully!", javafx.scene.control.ButtonType.OK);
  			alert.showAndWait();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	
   	
}

    
    
}
