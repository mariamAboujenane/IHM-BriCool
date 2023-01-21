package src.Controller;

import java.awt.Color;
import java.awt.Paint;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import src.Model.DatabaseConnection;

public class SProfileController implements Initializable {

	@FXML
	private Button AddBtn;

	@FXML
    private TextArea BioP;
	@FXML
	private Label DislikeP;

	@FXML
	private Label LikeP;

	@FXML
	private Label addressP;
	@FXML
    private RadioButton dispo;

	@FXML
	private Label phoneP;

	@FXML
	private ImageView photoP;

	@FXML
	private Label specialityP;

	@FXML
	private Label usernameP;
	@FXML
	ListView<GridPane> listView = new ListView<>();
	@FXML
	private Button addPostBtn;

	@FXML
	    void addPost(ActionEvent event) {
	    	Parent parent;
			
				try {
					parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/AddPost.fxml"));
					Scene scene1 = new Scene(parent);
					
					Stage  primaryStage1 = new Stage();
					primaryStage1.setScene(scene1);
					primaryStage1.show();
					Stage stage1 = (Stage) addPostBtn.getScene().getWindow();
				     stage1.close(); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}

	@FXML
	private Button editbtn;

	int id;
	 boolean status = false;

	@FXML
	void EditProfile(ActionEvent event) {
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/EditProfile.fxml"));
			Scene scene = new Scene(parent);

			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			Stage stage1 = (Stage) editbtn.getScene().getWindow();
			stage1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	  @FXML
	    void disponible(ActionEvent event) {
		 
		  String name = MyAppContext.workerUsername;
			String password = MyAppContext.workerPassword;
		  if(dispo.isSelected() == true) {
			  status = true;
			  
		  }	 else {
			  status = false;
		  }
		  
		  String disponible="update service_provider set status= ? where username= '"+name+"' and password= '"+password+"'";
		  try {
		       	Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
		       	 PreparedStatement preparedStmt = cnx.prepareStatement(disponible);
		 		  preparedStmt.setBoolean   (1, status);
		 		 preparedStmt.execute();
					 
			  }catch(SQLException e1) {
				e1.printStackTrace();

			  }
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO Auto-generated method stub

		selectDataFromDatabase();
		selectPostsFromDatabase();
	}

	public int getIdProvider() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connect = connectNow.getConnection();

		String selectId = "SELECT idprovider FROM service_provider WHERE username = ? and password = ?";
		String name = MyAppContext.workerUsername;
		String password = MyAppContext.workerPassword;

		try {
			PreparedStatement st = connect.prepareStatement(selectId);
			st.setString(1, name);
			st.setString(2, password);
			ResultSet result = st.executeQuery();
			id = -1;
			if (result.next()) {
				id = result.getInt("idprovider");
			}
			if (id != -1) {
				System.out.println("id =" + id);
				// do something with the retrieved id
			} else {
				System.out.println("No matching user found");
			}
		} catch (SQLException e) {
			System.out.println("An error occurred while retrieving the id: " + e.getMessage());
		}
		return id;

	}

	public void selectDataFromDatabase() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connect = connectNow.getConnection();

		String selectData = "SELECT  speciality , phone_number,address,status,Bio , Likes, Dislikes,photo FROM service_provider WHERE username = ? and password = ?";
		String name = MyAppContext.workerUsername;
		String password = MyAppContext.workerPassword;
		System.out.println("Name: " + name + " Password: " + password);
		try {
			PreparedStatement st = connect.prepareStatement(selectData);
			st.setString(1, name);
			st.setString(2, password);

			ResultSet result = st.executeQuery();

			while (result.next()) {

				String speciality = result.getString("speciality");
				String phone_number = Integer.toString(result.getInt("phone_number"));
				String address = result.getString("address");
				status = result.getBoolean("status");
				String Bio = result.getString("Bio");
				String Likes = Integer.toString(result.getInt("Likes"));
				String Dislikes = Integer.toString(result.getInt("Dislikes"));
				java.sql.Blob blob = result.getBlob("photo");
				byte[] imageBytes = blob.getBytes(1, (int) blob.length());

				// Create an InputStream from the byte array
				InputStream inputStream = new ByteArrayInputStream(imageBytes);

				Image imge = new Image(inputStream);
				photoP.setImage(imge);

				photoP.setFitWidth(60);
				photoP.setFitHeight(60);
				Circle circle = new Circle(20, 20, 20);
				photoP.setClip(circle);
				System.out.println(speciality);
				specialityP.setText(speciality);
				phoneP.setText(phone_number);
				addressP.setText(address);
				BioP.setText(Bio);
				LikeP.setText(Likes);
				DislikeP.setText(Dislikes);
				usernameP.setText(name);

			}

		} catch (SQLException e) {
			System.out.println("An error occurred while retrieving the id: " + e.getMessage());
		}
		if(status == true) {
			dispo.setSelected(true);
		}else if(status == false) {
			dispo.setSelected(false);	
		}

	}

	public ImageView selectPhoto() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connect = connectNow.getConnection();
		int id = getIdProvider();
		String selectPhoto = "SELECT  photo FROM service_provider WHERE idprovider=?";
		String name = MyAppContext.workerUsername;
		String password = MyAppContext.workerPassword;
		ImageView photo = new ImageView();
		try {
			PreparedStatement st = connect.prepareStatement(selectPhoto);
			st.setInt(1, id);

			ResultSet result = st.executeQuery();

			while (result.next()) {

				java.sql.Blob blob = result.getBlob("photo");
				byte[] imageBytes = blob.getBytes(1, (int) blob.length());

				// Create an InputStream from the byte array
				InputStream inputStream = new ByteArrayInputStream(imageBytes);

				Image imge = new Image(inputStream);
				photo.setImage(imge);

				photo.setFitWidth(35);
				photo.setFitHeight(35);
				Circle circle = new Circle(15, 15, 15);
				photo.setClip(circle);
				System.out.println("photo of the service provdier" + photo);

			}

		} catch (SQLException e) {
			System.out.println("An error occurred while retrieving the id: " + e.getMessage());
		}

		return photo;
	}

	public void selectPostsFromDatabase() {

		ObservableList<GridPane> postGrids = FXCollections.observableArrayList();
		DatabaseConnection connectNow = new DatabaseConnection();

		Connection connect = connectNow.getConnection();
		int id = getIdProvider();
		try {
			PreparedStatement st = connect
					.prepareStatement("SELECT postTitle, postContent, publishedAt FROM post where idp=?");
			st.setInt(1, id);
			ResultSet result = st.executeQuery();
			while (result.next()) {
				String title = result.getString("postTitle");
				java.sql.Blob blob = result.getBlob("postContent");
				byte[] imageBytes = blob.getBytes(1, (int) blob.length());

				// Create an InputStream from the byte array
				InputStream inputStream = new ByteArrayInputStream(imageBytes);

				Image imge = new Image(inputStream);
				ImageView postContent = new ImageView(imge);

				postContent.setFitWidth(355);
				postContent.setFitHeight(330);

				String publishedAt = result.getString("publishedAt");
				ImageView photo = selectPhoto();
				Label username = new Label(MyAppContext.workerUsername);
				Label titleLabel = new Label(title);
				System.out.println("photo:" + postContent);

				Label publishedAtLabel = new Label("Published At: " + publishedAt);
				publishedAtLabel.setStyle("-fx-text-fill: gray;-fx-font-size:10px;");

				username.setStyle("-fx-font-weight: bold; -fx-font-size:12px;");

				GridPane postGrid = new GridPane();
				// Create ColumnConstraints for each column
				ColumnConstraints col1 = new ColumnConstraints();
				ColumnConstraints col2 = new ColumnConstraints();
				ColumnConstraints col3 = new ColumnConstraints();
				ColumnConstraints col4 = new ColumnConstraints();
				// Add the ColumnConstraints to the GridPane
				postGrid.getColumnConstraints().addAll(col1, col2, col3);
				col1.setPrefWidth(40); // set width of column1
				col2.setPrefWidth(160); // set width of column2
				col3.setPrefWidth(140); // set width of column3
				// Create RowConstraints for each row
				RowConstraints row1 = new RowConstraints();
				RowConstraints row2 = new RowConstraints();
				RowConstraints row3 = new RowConstraints();
				RowConstraints row4 = new RowConstraints();

				RowConstraints row5 = new RowConstraints();
				RowConstraints row6 = new RowConstraints();
				// Add the RowConstraints to the GridPane
				postGrid.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6);

				row1.setPrefHeight(5); // set width of column1
				row2.setPrefHeight(10);
				row3.setPrefHeight(10); // set width of column1

				postGrid.setConstraints(photo, 0, 0, 1, 3);
				postGrid.setConstraints(username, 1, 1, 2, 1);

				postGrid.setConstraints(publishedAtLabel, 1, 2, 2, 1);
				postGrid.setConstraints(titleLabel, 0, 3, 3, 1);
				postGrid.setConstraints(postContent, 0, 5, 3, 1); // This element will span 2 columns in the 1st row
				postGrid.getChildren().addAll(photo, username, publishedAtLabel, titleLabel, postContent);
				postGrids.add(postGrid);
				listView.setItems(postGrids);
			}
		} catch (SQLException e) {
			System.out.println("An error occurred while retrieving the data: " + e.getMessage());
		}

	}

}
