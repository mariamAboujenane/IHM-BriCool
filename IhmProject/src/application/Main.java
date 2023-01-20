package src.application;
	
//sss

import src.Model.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	 DatabaseConnection  databaseConnection=new  DatabaseConnection();
	@Override
	
	public void start(Stage primaryStage) {
		try {
<<<<<<< HEAD
			Parent parent =FXMLLoader.load(getClass().getClassLoader().getResource("src/View/Search.fxml"));
=======



			Parent parent =FXMLLoader.load(getClass().getClassLoader().getResource("src/View/login.fxml"));


>>>>>>> branch 'main' of https://github.com/mariamAboujenane/IHM-BriCool.git
			Scene scene = new Scene(parent);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
