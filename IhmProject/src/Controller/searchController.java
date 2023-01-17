package src.Controller;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.collections.*;
import javafx.event.*;//hadu should be f gaa controller sinn athm9kom matkhdmch 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.Model.DatabaseConnection;
import src.Model.SearchModel;

public class searchController implements Initializable {
	private DialogPane dialog;
	@FXML
	private Button btn_information;

	@FXML
	private Button btn_notification;

	@FXML
	private ComboBox<String> cityChoice;

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
	private TextField searchBar;

	@FXML
	private TableColumn<SearchModel, String> specialityC3;

	@FXML
	private TableView<SearchModel> tableViewId;
	
	@FXML
	private TableColumn<SearchModel, ImageView> photoC0;

	@FXML
	private TableColumn<SearchModel, String> usernameC2;
	
	@FXML
	private Image image;
	private FileInputStream fis;
	

	ObservableList<SearchModel> SearchResultList = FXCollections.observableArrayList();

	private String[] Cities = { "Rabat", "Agadir", "Fez", "Tanger", "Tetouan", "Sale", "Casablanca", "Zagora", "Nador",
			"Oujda", "Marrakech", "Safi", "Meknes", "Kenitra", "Asila", "Ifrane", "Ouarzazate", "Alhoceima" };
	private String[] specialities = { "CARPENTER", "PLUMBER", "CLEANER", "TAILOR HAIR STYLIST", "ELECTRICIAN" };

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Use a data access object (DAO) to retrieve the images from the database
	   
	    
		specialityChoice.getItems().addAll(specialities);
		cityChoice.getItems().addAll(Cities);
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connect = connectNow.getConnection();
		String SearchQuery = "SELECT name, username, speciality,Likes,Dislikes, phone_number, city FROM service_provider";

		try {

			PreparedStatement st = connect.prepareStatement(SearchQuery);

			ResultSet rs = st.executeQuery(SearchQuery);

			while (rs.next()) {
				String name = rs.getString("name");
				String username = rs.getString("username");
				String speciality = rs.getString("speciality");
				String city = rs.getString("city");
				Integer Likes = rs.getInt("Likes");
				Integer Dislikes = rs.getInt("Dislikes");
				Integer phone_number = rs.getInt("phone_number");

				InputStream is = rs.getBinaryStream(1);
				OutputStream os = new FileOutputStream(new File("photo.jpg"));
				byte[] contents = new byte[1024];
				int size =0;
				try {
					while((size=is.read(contents))!=-1) {
						os.write(contents,0,size);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				image=new Image("file.photo.jpg");
			    System.out.println(image);
				SearchResultList.add(new SearchModel(name, username, speciality, phone_number, Likes, Dislikes, city));
			}
			nameC1.setCellValueFactory(new PropertyValueFactory<>("name"));
			usernameC2.setCellValueFactory(new PropertyValueFactory<>("username"));
			specialityC3.setCellValueFactory(new PropertyValueFactory<>("speciality"));
			phoneNumberC4.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
			likeC5.setCellValueFactory(new PropertyValueFactory<>("Likes"));
			dislikeC6.setCellValueFactory(new PropertyValueFactory<>("Dislikes"));
			cityC7.setCellValueFactory(new PropertyValueFactory<>("city"));

			tableViewId.setItems(SearchResultList);

			// list filtree
			FilteredList<SearchModel> FiltetredData = new FilteredList<>(SearchResultList, b -> true);

			searchBar.textProperty().addListener((observable, oldValue, newValue) -> {

				FiltetredData.setPredicate(SearchModel -> {
					if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
						return true;
					}

					String searchKeyword = newValue.toLowerCase(); // fonctionality that changes uper case words to
																	// lower case
					if (SearchModel.getName().toLowerCase().indexOf(searchKeyword) > -1) {
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
			
			specialityChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
			    //Filter the list of data based on the selected item in the combo box
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
			    
				
				cityChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
				    //Filter the list of data based on the selected item in the combo box
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
			    SortedList<SearchModel> SortedDataC = new SortedList<>(FiltetredData);
				SortedDataC.comparatorProperty().bind(tableViewId.comparatorProperty());
				tableViewId.setItems(SortedDataC);
			
			
				
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		
	}
	

	@FXML
	void gonotification(ActionEvent event) {

		try {
			Parent parent;
			parent = FXMLLoader.load(getClass().getClassLoader().getResource("src/View/Notification.fxml"));

			Scene scene = new Scene(parent);

			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();

			Stage stage = (Stage) btn_notification.getScene().getWindow();
			// do what you have to do
			stage.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

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

}
