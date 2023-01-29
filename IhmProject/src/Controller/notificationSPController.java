package src.Controller;

import java.awt.TextField;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.Model.DatabaseConnection;

public class notificationSPController implements Initializable {
	private DialogPane dialog;

	@FXML
	private Button btn_information;
	Label clientNameLabel= new Label();
	


	String dateT;
	String timeT;
	String clientNameT;
	String contentT;
	int clientId;
	int idProvider;
  // the data will be displayed in a listView that contains elements of type GridPane.so , we can display the data with the layout we want .
	@FXML
	ListView<GridPane> listView = new ListView<>();

	@FXML
	void goinformation(ActionEvent event) {
		try {
			Parent parent;
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/information.fxml"));

			Scene scene1 = new Scene(parent);

			Stage primaryStage1 = new Stage();
			primaryStage1.setScene(scene1);
			primaryStage1.show();

			Stage stage1 = (Stage) btn_information.getScene().getWindow();
			// do what you have to do
			stage1.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void singnout(ActionEvent event) {

		try {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setContentText("Are you sure that you want to log out !");
			alert.initModality(Modality.NONE);
			dialog = alert.getDialogPane();
			dialog.getStylesheets().add(getClass().getResource("style2.css").toString());
			dialog.getStyleClass().add("dialog");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isEmpty()) {
				System.out.print("Alert closed ");
			} else if (result.get() == ButtonType.OK) {

				Parent parent;
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/login.fxml"));

				Scene scene1 = new Scene(parent);

				Stage primaryStage1 = new Stage();
				primaryStage1.setScene(scene1);
				primaryStage1.show();
				Stage stage1 = (Stage) btn_information.getScene().getWindow();
				stage1.close();
			} else if (result.get() == ButtonType.OK) {
				System.out.print("Alert closed ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void goeditprofil(ActionEvent event) {
		try {
			Parent parent;
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/EditProfileUser.fxml"));

			Scene scene1 = new Scene(parent);

			Stage primaryStage1 = new Stage();
			primaryStage1.setScene(scene1);
			primaryStage1.show();

			Stage stage1 = (Stage) btn_information.getScene().getWindow();
			// do what you have to do
			stage1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void gohistory(ActionEvent event) {
		try {
			Parent parent;
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/ClientHistory.fxml"));

			Scene scene1 = new Scene(parent);

			Stage primaryStage1 = new Stage();
			primaryStage1.setScene(scene1);
			primaryStage1.show();

			Stage stage1 = (Stage) btn_information.getScene().getWindow();
			// do what you have to do
			stage1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    
	// this method select the id provider from service_provider table using the username and passwork of the service provider who logged in .
	public int selectIdProvider() {

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
			idProvider = -1;
			// idProvider = -1; means that there is no service provider with this id .
			if (result.next()) {
				idProvider = result.getInt("idprovider");
			}
			if (idProvider != -1) {
				System.out.println("id =" + idProvider);
				// do something with the retrieved id
			} else {
				System.out.println("No matching user found");
			}
		} catch (SQLException e) {
			System.out.println("An error occurred while retrieving the id: " + e.getMessage());
		}

		return idProvider;

	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		ObservableList<GridPane> postGrids = FXCollections.observableArrayList();
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connect = connectNow.getConnection();
		//sleect data from notification .
		String selectData = "SELECT  * FROM notificationservicep WHERE destinationId=?";
		try {
			PreparedStatement st = connect.prepareStatement(selectData);
			st.setInt(1, selectIdProvider());
			System.out.println("selected id provider :" + selectIdProvider());
			ResultSet result = st.executeQuery();

			while (result.next()) {
				//get the results and put them in textfields.
				clientNameT = result.getString("clientName");
			    dateT = result.getString("createdAtDate");
			    timeT = result.getString("createdAtTime");
			    int idNot = result.getInt("idNotifi");
			    contentT = result.getString("content");
			   
		//put these textfileds in labels
			Image image = new Image("src/View/icons/shadia.jpg");
			Label dateLabel = new Label("Reservation Time : " + dateT + " at : " + timeT);
			clientNameLabel = new Label(clientNameT);
		Label contentLabel = new Label(contentT);
			ImageView notifiPhoto = new ImageView(image);
			clientNameLabel.setId("clientName");
			    dateLabel.setStyle("-fx-text-fill: gray;-fx-font-size:12px;");
				clientNameLabel.setStyle("-fx-font-weight: bold; -fx-font-size:14px;-fx-text-fill: #2c7a6a;");
				contentLabel.setStyle("-fx-font-size:12px;");

				notifiPhoto.setFitWidth(30);
				notifiPhoto.setFitHeight(30);
				Circle circle = new Circle(15, 15, 15);
				notifiPhoto.setClip(circle);
				// Create ColumnConstraints for each column
				GridPane postGrid = new GridPane();
				postGrid.setStyle("-fx-border-color: #2c7a6a; -fx-border-width: 1px;");
				postGrid.setVgap(2);
				postGrid.setHgap(2);
				//create colums and rows and set their widths and heights .
				ColumnConstraints col1 = new ColumnConstraints();
				ColumnConstraints col2 = new ColumnConstraints();
				ColumnConstraints col3 = new ColumnConstraints();
				ColumnConstraints col4 = new ColumnConstraints();
				// Add the ColumnConstraints to the GridPane
				postGrid.getColumnConstraints().addAll(col1, col2, col3);
				col1.setPrefWidth(50); // set width of column1
				col2.setPrefWidth(140); // set width of column2
				col3.setPrefWidth(340); // set width of column3
				// Create RowConstraints for each row
				RowConstraints row1 = new RowConstraints();
				RowConstraints row2 = new RowConstraints();
				RowConstraints row3 = new RowConstraints();
				RowConstraints row4 = new RowConstraints();

				RowConstraints row5 = new RowConstraints();
				RowConstraints row6 = new RowConstraints();
				// Add the RowConstraints to the GridPane
				postGrid.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6);

				row1.setPrefHeight(20); // set width of column1
				row2.setPrefHeight(7);
				row3.setPrefHeight(10); // set width of column1
				postGrid.setConstraints(notifiPhoto, 0, 0, 2, 3);
				postGrid.setConstraints(clientNameLabel, 1, 0, 1, 1);

				postGrid.setConstraints(dateLabel, 1, 1, 3, 6);

				postGrid.setConstraints(contentLabel, 2, 0, 3, 1); // This element will span 2 columns in the 1st row
			
		
          //add all elements to the grid
			postGrid.getChildren().addAll(notifiPhoto, clientNameLabel, dateLabel, contentLabel);
			//then , we added the gridPane to the listView
			postGrids.add(postGrid);
			//then , we added the gridPane to the listView
			listView.setItems(postGrids);
			listView.refresh();
			}
			} catch (SQLException e) {
			System.out.println("An error occurred while retrieving the id: " + e.getMessage());
		}
		listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
		
		    @Override
		    public void handle(MouseEvent event) {
		        if (event.getButton().equals(MouseButton.PRIMARY)) {
		            if (event.getClickCount() == 2) {
		            	GridPane selectedGrid = (GridPane) listView.getSelectionModel().getSelectedItem();
		            	 clientNameLabel = (Label) selectedGrid.lookup("#clientName");
		            	String clientName = clientNameLabel.getText();
		            	System.out.println("client who reserved :"+clientName);
                       MyAppContext.ClientWhoReserved=clientName;
		                
		                try {
		        			Parent parent;
		        			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/acceptOrDecline.fxml"));

		        			Scene scene1 = new Scene(parent);

		        			Stage primaryStage1 = new Stage();
		        			primaryStage1.setScene(scene1);
		        			primaryStage1.show();

		        			Stage stage1 = (Stage) btn_information.getScene().getWindow();
		        			// do what you have to do
		        			stage1.close();
		        		} catch (IOException e) {
		        			// TODO Auto-generated catch block
		        			e.printStackTrace();
		        		}
		            }
		        }
		    }
		});


	}
	
	
}
