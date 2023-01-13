package src.Model;
import java.sql.Date;


public class user {
   int iduser;
   String name;
   String username;
   String password;
   
   public user() {
	   super();
   }
   
   public user(int iduser, String name, String username, String password) {
	   this.iduser=iduser;
	   this.name=name;
	   this.username=username;
	   this.password=password;
   }

public int getIduser() {
	return iduser;
}

public void setIduser(int iduser) {
	this.iduser = iduser;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}
}
