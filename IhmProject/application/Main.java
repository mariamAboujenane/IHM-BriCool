package application;
	

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.Model.DatabaseConnection;
import src.Model.MainModel;
import javafx.event.ActionEvent;


public class Main extends Application {
	DatabaseConnection databaseConnection = new DatabaseConnection();

	@Override

	public void start(Stage primaryStage) {
		try {
			Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/view/AccountClient.fxml"));

			Scene scene = new Scene(parent);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		launch(args);
	}
}