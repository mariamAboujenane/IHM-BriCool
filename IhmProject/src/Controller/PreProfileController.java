package src.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PreProfileController implements Initializable {
	
	int id =MyAppContext.selectedIdPersonInSearch;
	Image dislikeimage = new Image("src/View/icons/down.png");
	Image likeimage = new Image("src/View/icons/upp.png");
	Image dislikeimage_gris = new Image("src/View/icons/down_gris_new.png");
	Image likeimage_gris = new Image("src/View/icons/up_gris_new.png");
	Image dispoimage = new Image("src/View/icons/green_circle.png");
	Image nodispoimage = new Image("src/View/icons/red_circle.png");
	int contour_like = 0;
	int contour_dislike = 0;
	private DialogPane dialog;

    @FXML
    private Label usernameLabel;
    
	@FXML
	private Button DislikeBtn;

	@FXML
	private ImageView DislikeImage;

	@FXML
	private Label DislikeLbl;

	@FXML
	private Button LikeBtn;

    @FXML
    private MenuItem edit;
    @FXML
    private MenuItem history;

	@FXML
	private ImageView LikeImage;
	
    @FXML
    private Label addresslabel;
    @FXML
    private Label biolabel;
    @FXML
    private Button btn_information;
    @FXML
    private Button btn_notification;

    @FXML
    private Label nameLabel;
    
    @FXML
    private ImageView dispo;

    @FXML
    private Label phonelabel;

    @FXML
    private ImageView photolabel;
    @FXML
    private Label specialitylabel;

	@FXML
	private Label LikeLbl;
	@FXML
    private Button report;
    
    @FXML
	
	private Button reservebtn;
    @FXML
    private MenuItem signout;

	@FXML
	void Dislike(ActionEvent event) {
		String dislike_modify;
		String dislike = DislikeLbl.getText();
		if(contour_dislike % 2 == 0) {
		DislikeImage.setImage(dislikeimage);
		int dislike_int = Integer.parseInt(dislike);
		int dislike_number = dislike_int +1;
		dislike_modify = String.valueOf(dislike_number);
		DislikeLbl.setText(dislike_modify);
		contour_dislike ++;
		}else {
			DislikeImage.setImage(dislikeimage_gris);
			int dislike_int = Integer.parseInt(dislike);
			int dislike_number = dislike_int -1;
			dislike_modify = String.valueOf(dislike_number);
			DislikeLbl.setText(dislike_modify);
			contour_dislike ++;
		}
		  String updateQuery = "UPDATE bio SET Dislikes = ? WHERE id = '" +id+ "'";
		 
  	  try {
         	Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
         	 PreparedStatement preparedStmt = cnx.prepareStatement(updateQuery);
   		  preparedStmt.setString   (1, dislike_modify);

   		 preparedStmt.execute();
 			Alert alert = new Alert(AlertType.WARNING, "You have dis disliked this service provider.", javafx.scene.control.ButtonType.OK);
 			 alert.showAndWait();
 			 
 	  }catch(SQLException e1) {
 		e1.printStackTrace();

 	  }

	}

	@FXML
	void Like(ActionEvent event) {
		
		String like_modify;
		String like = LikeLbl.getText();
		if(contour_like % 2 == 0) {
		LikeImage.setImage(likeimage);
		int like_int = Integer.parseInt(like);
		int like_number = like_int +1;
		like_modify = String.valueOf(like_number);
		LikeLbl.setText(like_modify);
		contour_like ++;
		}else {
			LikeImage.setImage(likeimage_gris);
			int like_int = Integer.parseInt(like);
			int like_number = like_int -1;
			like_modify = String.valueOf(like_number);
			LikeLbl.setText(like_modify);
			contour_like ++;
		}
		  String updateQuery = "UPDATE bio SET Likes = ? WHERE id = '" +id+ "'";
		 
  	  try {
         	Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
         	 PreparedStatement preparedStmt = cnx.prepareStatement(updateQuery);
   		  preparedStmt.setString   (1, like_modify);

   		 preparedStmt.execute();
 			Alert alert = new Alert(AlertType.WARNING, "You have liked this service provider.", javafx.scene.control.ButtonType.OK);
 			 alert.showAndWait();
 			 
 	  }catch(SQLException e1) {
 		e1.printStackTrace();

 	  }



	}
	   @FXML
	    void goeditprofile(ActionEvent event) {
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
	    void signout(ActionEvent event) {
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
    void report(ActionEvent event) {
        
    	String signaler = null;
		String sql1 = "select nbr_signal from service_provider where idprovider='" +id+ "'";
	
		try {

			Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
			Statement statement = cnx.createStatement();
			ResultSet rs = statement.executeQuery(sql1);

			while (rs.next()) {
				signaler = rs.getString("nbr_signal");
			}


		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		int signaler_int = Integer.parseInt(signaler);
		signaler_int++;
		String signaler_modify = String.valueOf(signaler_int);
		
		 String updateQuery = "UPDATE service_provider SET nbr_signal = ? WHERE idprovider = '" +id+ "'";
		 
	  	  try {
	         	Connection cnx2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
	         	 PreparedStatement preparedStmt2 = cnx2.prepareStatement(updateQuery);
	   		  preparedStmt2.setString   (1, signaler_modify);

	   		 preparedStmt2.execute();
	 			Alert alert = new Alert(AlertType.WARNING, "You have signaled this service provider.", javafx.scene.control.ButtonType.OK);
	 			 alert.showAndWait();
	 			 
	 	  }catch(SQLException e1) {
	 		e1.printStackTrace();

	 	  }
	  	  
	  	  int users = 0;
	  	  String count ="SELECT COUNT(iduser) FROM user";
	  	try {

			Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
			Statement statement = cnx.createStatement();
			ResultSet rs = statement.executeQuery(count);
			

			while (rs.next()) {
				users = rs.getInt(1);
				
			}
		    System.out.println(users);

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	  	
	  	int test = (users*20)/100;
	  	  
	  	  if(signaler_int == test) {
	  		  String delete = "DELETE FROM service_provider WHERE idprovider = '" +id+ "'";
	  	  	  try {
		         	Connection cnx2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
		         	 PreparedStatement preparedStmt2 = cnx2.prepareStatement(delete);
		   

		   		 preparedStmt2.execute();
		 			Alert alert = new Alert(AlertType.WARNING, "this service provider is deleted.", javafx.scene.control.ButtonType.OK);
		 			 alert.showAndWait();
		 			 
		 	  }catch(SQLException e1) {
		 		e1.printStackTrace();

		 	  }
	  		  
	  	  }

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
	   
		String name = null;
		int status = 0;
		String address = null;
		String phone = null;
		String speciality = null;
		String bio = null;
		String likes = null;
		String dislikes = null;
		java.sql.Blob photo;
		byte[] imageBytes = null;
		String sql = "select name,speciality,phone_number,address,status,Bio,Likes,Dislikes,photo from service_provider where idprovider='" +id+ "'";
		
		try {

			Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
			Statement statement = cnx.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				name = rs.getString("name");
				address = rs.getString("address");
				status = rs.getInt("status");
				phone = rs.getString("phone_number");
				speciality = rs.getString("speciality");
				bio = rs.getString("Bio");
				likes = rs.getString("Likes");
				dislikes = rs.getString("Dislikes");
				photo = rs.getBlob("photo");
				imageBytes = photo.getBytes(1, (int) photo.length());
			}
			

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		nameLabel.setText(name);
		addresslabel.setText(address);
		phonelabel.setText(phone);
		specialitylabel.setText(speciality);
		biolabel.setText(bio);
		LikeLbl.setText(likes);
		DislikeLbl.setText(dislikes);
		InputStream inputStream = new ByteArrayInputStream(imageBytes);

		   Image imge = new Image(inputStream);
		  photolabel.setImage(imge);
		  if(status == 0) {
			  dispo.setImage(nodispoimage);
		  }else if(status == 1){
			  dispo.setImage(dispoimage);
		  }

	}

}
