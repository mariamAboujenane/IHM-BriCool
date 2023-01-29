package src.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import src.Controller.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
//hadu should be f gaa controller sinn athm9kom matkhdmch 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.Model.DatabaseConnection;
import src.Model.SearchModel;

public class searchController implements Initializable {
	private DialogPane dialog;
	//private DialogPane dialog1;
	 @FXML
	    private Button back;
	@FXML
	private Button btn_information;

	@FXML
	private Button btn_notification;
	@FXML
    private MenuItem goeditprofil;
	@FXML
	 private MenuItem gohistory;
	 @FXML
	 private MenuItem singnout;


	@FXML
	private ComboBox<String> cityChoice;
	
	@FXML
    private Button searchbtn;

	@FXML
	private ComboBox<String> specialityChoice;

	@FXML
	private TableColumn<SearchModel, Integer> dislikeC6;

	@FXML
	private TableColumn<SearchModel, Integer> likeC5;

	@FXML
	private TableColumn<SearchModel, String> nameC1;

	@FXML
	private TableColumn<SearchModel, Integer> phoneNumberC4;

	@FXML
	private TableColumn<SearchModel, String> cityC7;

    @FXML
    private TableColumn<SearchModel, Integer> idColumn;


	@FXML
	private TableColumn<SearchModel, Image> imageColumn;

	@FXML
	private TextField searchBar;

	@FXML
	private TableColumn<SearchModel, String> specialityC3;

	@FXML
	private TableView<SearchModel> tableViewId;
	

	@FXML
	private TableColumn<SearchModel, String> usernameC2;

	ObservableList<SearchModel> SearchResultList = FXCollections.observableArrayList();

	private String[] Cities = {"Tanger","Casablanca","Fes","Zagora","Oujda","Tétouan","Rabat","El Jadida","Agadir","Salé","Meknès","Marrakech","Kénitra","Laayoune","Mohammédia","Béni Mellal","Nador","Safi"};
 
	private String[] specialities = { "CARPENTER", "PLUMBER", "CLEANER", "TAILOR HAIR STYLIST", "ELECTRICIAN" };

	
	//

    @FXML
    void back(ActionEvent event) {
    	
		try {
			Parent parent;
			// Load the FXML file using FXMLLoader
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/search.fxml"));
			// Create a new Scene with the loaded FXML file as the root node
			Scene scene = new Scene(parent);
			Stage  primaryStage = new Stage();
			// Set the Scene on the Stage
			primaryStage.setScene(scene);
			// Create a new Image object 
			Image image = new Image("src/View/icons/logo3.png");	 
		    // Add the image to the icon list of the primaryStage
			primaryStage.getIcons().add(image);
		    // Set the title of the primaryStage
			primaryStage.setTitle("BriCOOL");
			primaryStage.show();
			Stage stage = (Stage) back.getScene().getWindow();
		    stage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	
	//
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Use a data access object (DAO) to retrieve the images from the database
		
		specialityChoice.getItems().addAll(specialities);
		cityChoice.getItems().addAll(Cities);
		//establish a connection with the database
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connect = connectNow.getConnection();
		//sql query
		String SearchQuery = "SELECT idprovider , name, username, speciality,Likes,Dislikes, phone_number, city, photo FROM service_provider";
		try {
			PreparedStatement st = connect.prepareStatement(SearchQuery);
            //set the results in a ResultSet
			ResultSet rs = st.executeQuery(SearchQuery);
            //Read the results
			while (rs.next()) {
				Integer id = rs.getInt("idprovider");
				String name = rs.getString("name");
				String username = rs.getString("username");
				String speciality = rs.getString("speciality");
				String city = rs.getString("city");
				Integer Likes = rs.getInt("Likes");
				Integer Dislikes = rs.getInt("Dislikes");
				Integer phone_number = rs.getInt("phone_number");
				java.sql.Blob blob = rs.getBlob("photo");
				byte[] imageBytes = blob.getBytes(1, (int) blob.length());
				// Create an InputStream from the byte array
				InputStream inputStream = new ByteArrayInputStream(imageBytes);

				Image imge = new Image(inputStream);
				ImageView image = new ImageView(imge);

				image.setFitWidth(60);
				image.setFitHeight(60);
                
				Circle circle = new Circle(25,25,25);
				image.setClip(circle);

				SearchResultList.add(new SearchModel(id,name, username, speciality, phone_number, Likes, Dislikes, city, image));
			}
			idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
			nameC1.setCellValueFactory(new PropertyValueFactory<>("name"));
			usernameC2.setCellValueFactory(new PropertyValueFactory<>("username"));
			specialityC3.setCellValueFactory(new PropertyValueFactory<>("speciality"));
			phoneNumberC4.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
			likeC5.setCellValueFactory(new PropertyValueFactory<>("Likes"));
			dislikeC6.setCellValueFactory(new PropertyValueFactory<>("Dislikes"));
			cityC7.setCellValueFactory(new PropertyValueFactory<>("city"));
			imageColumn.setCellValueFactory(new PropertyValueFactory<>("photo"));

			tableViewId.setItems(SearchResultList);

			// list filtre
			FilteredList<SearchModel> FiltetredData = new FilteredList<>(SearchResultList, b -> true);

			searchBar.textProperty().addListener((observable, oldValue, newValue) -> {

				FiltetredData.setPredicate(SearchModel -> {
					if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
						return true;
					}
					// fonctionality that changes uper case words to lower case
					String searchKeyword = newValue.toLowerCase(); 
					if (String.valueOf(SearchModel.getId()).indexOf(searchKeyword) > -1) {
						return true;
					} else if (SearchModel.getName().toLowerCase().indexOf(searchKeyword) > -1) {
						return true;

					} else if (SearchModel.getUsername().toLowerCase().indexOf(searchKeyword) > -1) {
						return true;

					} else if (SearchModel.getSpeciality().toLowerCase().indexOf(searchKeyword) > -1) {
						return true;

					} else if (SearchModel.getCity().toLowerCase().indexOf(searchKeyword) > -1) {
						return true;

					} else if (String.valueOf(SearchModel.getPhone_number()).indexOf(searchKeyword) > -1) {
						return true;
					} else if (String.valueOf(SearchModel.getLikes()).indexOf(searchKeyword) > -1) {
						return true;
					} else if (String.valueOf(SearchModel.getLikes()).indexOf(searchKeyword) > -1) {
						return true;
					} else {
						return false;

					}
				});

			});

			SortedList<SearchModel> SortedData = new SortedList<>(FiltetredData);
			SortedData.comparatorProperty().bind(tableViewId.comparatorProperty());
			tableViewId.setItems(SortedData);
			tableViewId.getStylesheets().add(getClass().getResource("table.css").toString());
			
			cityChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
				// Filter the list of data based on the selected item in the combo box
				String selectedValue = (String) newValue;
				FiltetredData.setPredicate(data -> {
					if (selectedValue == null || selectedValue.isEmpty()) {
						return true;
					}
				String lowerCaseFilter = selectedValue.toLowerCase();
					if (data.getCity().toLowerCase().contains(lowerCaseFilter)) {
						return true;
					}
					return false;
				});

			});
			
			specialityChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
				// Filter the list of data based on the selected item in the combo box
				String selectedValue = (String) newValue;
				FiltetredData.setPredicate(data -> {
					if (selectedValue == null || selectedValue.isEmpty()) {
						return true;
					}
					String lowerCaseFilter = selectedValue.toLowerCase();
					if (data.getSpeciality().toLowerCase().contains(lowerCaseFilter)) {
						return true;
					}
					return false;
				});
			});
		
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void gonotification(ActionEvent event) {

		 try {
		    	Parent parent;

				// Load the FXML file using FXMLLoader
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/Notification.fxml"));
				// Create a new Scene with the loaded FXML file as the root node
				Scene scene = new Scene(parent);
				Stage  primaryStage = new Stage();
				// Set the Scene on the Stage
				primaryStage.setScene(scene);
				// Create a new Image object 
				Image image = new Image("src/View/icons/logo3.png");
				// Add the image to the icon list of the primaryStage
				primaryStage.getIcons().add(image);
				// Set the title of the primaryStage
				primaryStage.setTitle("BriCOOL");
				primaryStage.show();
				Stage stage1 = (Stage) btn_notification.getScene().getWindow();
			    stage1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}

	@FXML
	void goinformation(ActionEvent event) {
		 try {
		    	Parent parent;

				// Load the FXML file using FXMLLoader
				parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/Information.fxml"));
				// Create a new Scene with the loaded FXML file as the root node
				Scene scene = new Scene(parent);
				Stage  primaryStage = new Stage();
				// Set the Scene on the Stage
				primaryStage.setScene(scene);
				// Create a new Image object 
				Image image = new Image("src/View/icons/logo3.png");
				// Add the image to the icon list of the primaryStage
				primaryStage.getIcons().add(image);
				// Set the title of the primaryStage
				primaryStage.setTitle("BriCOOL");
				primaryStage.show();
				Stage stage1 = (Stage) btn_information.getScene().getWindow();
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

			// Load the FXML file using FXMLLoader
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/ClientHistory.fxml"));
			// Create a new Scene with the loaded FXML file as the root node
			Scene scene = new Scene(parent);
			Stage  primaryStage = new Stage();
			// Set the Scene on the Stage
			primaryStage.setScene(scene);
			// Create a new Image object 
			Image image = new Image("src/View/icons/logo3.png");
			// Add the image to the icon list of the primaryStage
			primaryStage.getIcons().add(image);
			// Set the title of the primaryStage
			primaryStage.setTitle("BriCOOL");
			primaryStage.show();
			Stage stage1 = (Stage) btn_information.getScene().getWindow();
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
				 Image image = new Image("src/View/icons/logo3.png");	 
					primaryStage1.getIcons().add(image);
					primaryStage1.setTitle("BriCOOL");
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

			// Load the FXML file using FXMLLoader
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/EditProfile.fxml"));
			// Create a new Scene with the loaded FXML file as the root node
			Scene scene = new Scene(parent);
			Stage  primaryStage = new Stage();
			// Set the Scene on the Stage
			primaryStage.setScene(scene);
			// Create a new Image object 
			Image image = new Image("src/View/icons/logo3.png");
			// Add the image to the icon list of the primaryStage
			primaryStage.getIcons().add(image);
			// Set the title of the primaryStage
			primaryStage.setTitle("BriCOOL");
			primaryStage.show();
			Stage stage1 = (Stage) btn_information.getScene().getWindow();
		    stage1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 

	

    @FXML
    void search(ActionEvent event) {
    	SearchModel selectedPerson = tableViewId.getSelectionModel().getSelectedItem();
    	 
    	int id = selectedPerson.getId();
    	System.out.println(id);
    	MyAppContext.selectedIdPersonInSearch = id;
    	try {
			Parent parent;
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/PreProfile.fxml"));
			
			Scene scene = new Scene(parent);
			
			Stage  primaryStage = new Stage();
			primaryStage.setScene(scene);
			 Image image = new Image("src/View/icons/logo3.png");	 
				primaryStage.getIcons().add(image);
				primaryStage.setTitle("BriCOOL");
			primaryStage.show();
			
			Stage stage = (Stage) searchbtn.getScene().getWindow();
		    // do what you have to do
		  stage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
    }

}
