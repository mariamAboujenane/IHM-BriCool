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
    void facebooklink(ActionEvent event) {
    	try {
    	    Desktop.getDesktop().browse(new URL("https://www.facebook.com/").toURI());
    	} catch (IOException e) {
    	    e.printStackTrace();
    	} catch (URISyntaxException e) {
    	    e.printStackTrace();
    	}
    }
   
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
    	Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/search.fxml"));
			Scene scene = new Scene(parent);
			
			Stage  primaryStage = new Stage();
			primaryStage.setScene(scene);
			 Image image = new Image("src/View/icons/logo3.png");	 
				primaryStage.getIcons().add(image);
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
