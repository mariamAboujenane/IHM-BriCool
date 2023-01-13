package src.Controller;

import java.io.IOException;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PreProfileController {
	 @FXML
	    private Button reservebtn;

	 @FXML
	    void reservation(ActionEvent event) {
      
				Parent parent;
				try {
					parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/Reservation.fxml"));
					Scene scene = new Scene(parent);
					
					Stage  primaryStage = new Stage();
					primaryStage.setScene(scene);
					primaryStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
	 }
			  
}
