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
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PreProfileController implements Initializable {

	Image dislikeimage = new Image("src/View/icons/down.png");
	Image likeimage = new Image("src/View/icons/upp.png");
	int Dislike_increment;
	int Like_increment;

	@FXML
	private Button DislikeBtn;

	@FXML
	private ImageView DislikeImage;

	@FXML
	private Label DislikeLbl;

	@FXML
	private Button LikeBtn;

	@FXML
	private ImageView LikeImage;

	@FXML
	private Label LikeLbl;
	@FXML
	private Button reservebtn;

	@FXML
	void Dislike(ActionEvent event) {
		DislikeImage.setImage(dislikeimage);
		Dislike_increment++;

	}

	@FXML
	void Like(ActionEvent event) {
		LikeImage.setImage(likeimage);
		Like_increment++;

	}

	@FXML
	void reservation(ActionEvent event) {

		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/Reservation.fxml"));
			Scene scene = new Scene(parent);

			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			Stage stage1 = (Stage) reservebtn.getScene().getWindow();
			stage1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		String like = null;
		String dislike = null;
		String sql1 = "select Likes from bio where Id='1'";
		String sql2 = "select Dislikes from bio where Id='1'";
		try {

			Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
			Statement statement = cnx.createStatement();
			ResultSet rs = statement.executeQuery(sql1);

			while (rs.next()) {
				like = rs.getString("Likes");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		LikeLbl.setText(like);
		try {
			Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
			Statement statement = cnx.createStatement();
			ResultSet rs = statement.executeQuery(sql2);

			while (rs.next()) {
				dislike = rs.getString("Dislikes");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DislikeLbl.setText(dislike);
	}

}
