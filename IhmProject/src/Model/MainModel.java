package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class MainModel {
	private Connection myConnection;
	Statement statement;
	ResultSet resultat;


	
	public MainModel() {

		String hostname = "sql6.freesqldatabase.com";
	    String port = "3306";
	    String database = "sql6586993";
	    String username = "sql6586993";
	    String password = "2vHvuWvuWZ";

	    // Création de la chaîne de connexion à la base de données
	    String jdbcUrl = String.format("jdbc:mysql://%s:%s/%s?useSSL=false", hostname, port, database);

	    // Connexion à la base de données
	    try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
	      System.out.println("Connecté à la base de données");
	    } catch (SQLException e) {
	      System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
	    }
	 }
}

