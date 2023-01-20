package src.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class EditProfileController implements Initializable  {
	
    @FXML
    private ComboBox<String> Mychoicebox;
    private String [] speciality = {"CARPENTER","PLUMBER","CLEANER","TAILOR HAIR STYLIST","ELECTRICIAN"};

    @FXML
    private Button back;

    @FXML
    void Back_To_choose(ActionEvent event) {
    	Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/search.fxml"));
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 Mychoicebox.getItems().addAll(speciality);		
	}

}
