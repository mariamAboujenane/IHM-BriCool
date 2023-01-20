package src.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReservationController implements Initializable {
	 @FXML
	    private TextField addresstxt;

    @FXML
    private Button back;
    @FXML
    private TextField descriptiontxt;

    @FXML
    private TextField nametxt;

    @FXML
    private TextField phonetxt;

    @FXML
    private TextField servicetxt;

    @FXML
    private DatePicker time;

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		int id =MyAppContext.selectedIdPersonInSearch;
		String name_client = MyAppContext.workerUsername;
		String service_name = null;
		String sql = "select name from service_provider where idprovider='" +id+ "'";
		try {

			Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
			Statement statement = cnx.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				service_name = rs.getString("name");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		servicetxt.setText(service_name);
		nametxt.setText(name_client);
		
	}

}

