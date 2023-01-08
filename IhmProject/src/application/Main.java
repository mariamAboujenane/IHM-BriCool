package application;
	

import Model.MainModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	MainModel model=new MainModel();
	@Override
	
	public void start(Stage primaryStage) {
		try {
			Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("View/AccountClient.fxml"));
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
