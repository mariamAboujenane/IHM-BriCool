package src.application;
	
//sss

import src.Model.*;

import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	 DatabaseConnection  databaseConnection=new  DatabaseConnection();

	@Override
	
	public void start(Stage primaryStage) {
		try {

			Parent parent =FXMLLoader.load(getClass().getClassLoader().getResource("src/View/AccountClient.fxml"));
			Scene scene = new Scene(parent);
			primaryStage.setScene(scene);
			 Image image = new Image("src/View/icons/logo3.png");	 
			primaryStage.getIcons().add(image);
			primaryStage.setTitle("BriCOOL");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
