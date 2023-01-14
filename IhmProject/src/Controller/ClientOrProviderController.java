package src.Controller;

import java.io.IOException;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ClientOrProviderController {

    @FXML
    private RadioButton clientbtn;

    @FXML
    private Button createbtn;

    @FXML
    private RadioButton providerbtn;

    @FXML
    private ToggleGroup type;

    @FXML
    void CreateAccount(ActionEvent event) {
    	Parent parent = null;
		try {
			if(clientbtn.isSelected()) {
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/AccountClient.fxml"));
			}else if(providerbtn.isSelected()) {
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/AccountProvider.fxml"));
			}else {
				Alert alert = new Alert(AlertType.CONFIRMATION, "You should select", javafx.scene.control.ButtonType.OK);
      			alert.showAndWait();
			}
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
