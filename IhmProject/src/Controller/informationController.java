package src.Controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class informationController {
	@FXML
	private Hyperlink facebook;
	@FXML
	private Hyperlink insta;
	@FXML
    private Hyperlink twitter;
	@FXML
	private Hyperlink email;
	@FXML
    private Button back;
	
	@FXML
	//facebook btn
	
    void facebooklink(ActionEvent event) {
    	try {
    	    Desktop.getDesktop().browse(new URL("https://www.facebook.com/").toURI());
    	} catch (IOException e) {
    	    e.printStackTrace();
    	} catch (URISyntaxException e) {
    	    e.printStackTrace();
    	}
    }
   
	//instagram btn
	
    @FXML
    void instalink(ActionEvent event) {
    	try {
    	    Desktop.getDesktop().browse(new URL("https://www.instagram.com/").toURI());
    	} catch (IOException e) {
    	    e.printStackTrace();
    	} catch (URISyntaxException e) {
    	    e.printStackTrace();
    	}
    }

    //twitter btn
    
    @FXML
    void twitterlink(ActionEvent event) {
    	try {
    	    Desktop.getDesktop().browse(new URL("https://twitter.com/").toURI());
    	} catch (IOException e) {
    	    e.printStackTrace();
    	} catch (URISyntaxException e) {
    	    e.printStackTrace();
    	}
    }
    
    //email btn
    
    @FXML
    void emaillink(ActionEvent event) {
    	try {
    	    Desktop.getDesktop().browse(new URL("https://gmail.com/").toURI());
    	} catch (IOException e) {
    	    e.printStackTrace();
    	} catch (URISyntaxException e) {
    	    e.printStackTrace();
    	}
    }
    

    @FXML
    void Back_To_choose(ActionEvent event) {
    	try {
		    Parent parent;

    		// Load the FXML file using FXMLLoader
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/search.fxml"));
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
			Stage stage1 = (Stage) back.getScene().getWindow();
		    stage1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
