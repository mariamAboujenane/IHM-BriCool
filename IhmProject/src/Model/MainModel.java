package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//imanes
//update
// mariam 
public class MainModel {
	private Connection myConnection;
	Statement statement;
	ResultSet resultat;

	public MainModel() {

		
		try {
			myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
			statement = myConnection.createStatement();
			myConnection.close();
			System.out.println("connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}