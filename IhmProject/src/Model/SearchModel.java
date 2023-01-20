package src.Model;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import com.mysql.cj.jdbc.Blob;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


public class SearchModel {
	int id ;
     String name;
     String username;
     String speciality;
     String city;
     int phone_number;
     int Likes;
     int Dislikes;
     
     ImageView photo;
     
     

     public SearchModel() {
  	   super();
     }

     
public SearchModel (int id ,String name, String username, String speciality, int phone_number, int Likes, int Dislikes, String city , ImageView photo) {
	this.id=id;
	this.name=name;
	this.username=username;
	this.speciality=speciality;
	this.phone_number=phone_number;
	this.city=city;
	this.Likes=Likes;
	this.Dislikes=Dislikes;
	this.photo=photo;
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

public String getSpeciality() {
	return speciality;
}

public void setSpeciality(String speciality) {
	this.speciality = speciality;
}

public int getPhone_number() {
	return phone_number;
}

public void setPhone_number(int phone_number) {
	this.phone_number = phone_number;
}

public int getLikes() {
	return Likes;
}

public void setLikes(int likes) {
	Likes = likes;
}

public int getDislikes() {
	return Dislikes;
}


public void setDislikes(int dislikes) {
	Dislikes = dislikes;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public ImageView getPhoto() {
	return photo;
}

public void setPhoto(ImageView photo) {
	this.photo=photo;
}
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

}