package src.Model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	public  Connection myConnection;
	Statement statement;
	ResultSet resultat;

	public Connection getConnection() {

		
		try {
			myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bricool", "root", "");
			statement = myConnection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myConnection;
	}
}