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
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import src.Model.DatabaseConnection;

public class acceptOrDeclineController implements Initializable{

	    @FXML
	    private Button acceptbtn;

	    @FXML
	    private Label addressLabel;

	    @FXML
	    private Button back;

	    @FXML
	    private Label clientNameLabel;

	    @FXML
	    private Label dateLabel;

	    @FXML
	    private Button declinebtn;

	    @FXML
	    private Label descriLabel;

	    @FXML
	    private Label hourLabel;

	    @FXML
	    private Label phoneLabel;
	    int idReser;
	    int iduser;
	    int idprovider;
	    @FXML
	    void Back_To_choose(ActionEvent event) {

	    }

	    @FXML
	    void accept(ActionEvent event) {
            MyAppContext.acceptOrDecline="accepted";
            insertInNotifiClient() ;
	    }

	    @FXML
	    void decline(ActionEvent event) {
	    	  MyAppContext.acceptOrDecline="refused";
	    	  insertInNotifiClient() ;
	    }
	    
	    public void selectDataFromReser() {
			DatabaseConnection connectNow = new DatabaseConnection();
			Connection connect = connectNow.getConnection();
		
			String selectPhoto = "SELECT  * FROM reservation WHERE Service_Provider=? and full_name=?";
		
			
			try {
				PreparedStatement st = connect.prepareStatement(selectPhoto);
				st.setString(1,MyAppContext.workerUsername );
				st.setString(2,MyAppContext.ClientWhoReserved );
		
				ResultSet result = st.executeQuery();

				while (result.next()) {
					 iduser=result.getInt("iduser");
					 idprovider=result.getInt("idprovider");
                    idReser=result.getInt("idreservation");
					String clientName = result.getString("full_name");
					String clientAddress = result.getString("Address");
					String clientPhone = result.getString("phone_number");
					String clientDate= result.getString("Date");
					String clientTime = result.getString("hour");
					String clientDescri = result.getString("Description");
		
                  clientNameLabel.setText(clientName);
                  addressLabel.setText(clientAddress);
                  phoneLabel.setText(clientPhone);
                  dateLabel.setText(clientDate);
                  hourLabel.setText(clientTime);
                  descriLabel.setText(clientDescri);
				}

			} catch (SQLException e) {
				System.out.println("An error occurred while retrieving the id: " + e.getMessage());
			}

	
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			selectDataFromReser();
		}
		
		public void insertInNotifiClient() {
			String sql="insert into notificationClient(idReser,sourceId,destiId,SPname,createdAtDate,createdAtTime,acceptedOrRefused) values(?,?,?,?,?,?,?)";
      	  
  
              	Connection cnx;
				try {
					cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
			
      			PreparedStatement st = cnx.prepareStatement(sql);  
      			st.setInt(1,idReser);
      			st.setInt(2,idprovider);
      			st.setInt(3,iduser);
      			st.setString(4,MyAppContext.workerUsername);
 
      			st.setString(5,dateLabel.getText());
      			st.setString(6,hourLabel.getText());
                st.setString(7, MyAppContext.acceptOrDecline);
      			
      			st.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
    	   	 Alert alert = new Alert(AlertType.WARNING, "your response has been sent .", javafx.scene.control.ButtonType.OK);
    	  alert.showAndWait();

 		

}
}		
		
		
