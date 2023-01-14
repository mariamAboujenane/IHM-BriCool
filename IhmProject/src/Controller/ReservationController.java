package src.Controller;

import java.io.IOException;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ReservationController {

    @FXML
    private Button back;

    @FXML
    void Back_To_choose(ActionEvent event) {
    	try {
			Parent parent;
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/PreProfile.fxml"));
			
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

}

