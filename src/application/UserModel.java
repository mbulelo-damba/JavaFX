package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class UserModel {
	
	private final StringProperty eid;
	private final StringProperty name;
	private final StringProperty surname;
	private final StringProperty age;
	
	public UserModel(String eid_, String name_, String surname_, String age_){
		this.eid = new SimpleStringProperty(eid_);
		this.name = new SimpleStringProperty(name_);
		this.surname = new SimpleStringProperty(surname_);
		this.age = new SimpleStringProperty(age_);
	}
	
	//Getters
	
	public String getEID(){
		return eid.get();
	}
	
	public String getName(){
		return name.get();
	}
	
	public String getSurname(){
		return surname.get();
	}
	
	public String getAge(){
		return age.get();
	}
	
	// Setters
	
	public void setEID(String eid_){
		eid.set(eid_);
	}
	
	
	public void setName(String name_){
		name.set(name_);
	}
	
	public void setSurname(String surname_){
		surname.set(surname_);
	}
	
	public void setAge(String age_){
		age.set(age_);
	}
	
	
	//Property values
	
	public StringProperty eidProperty(){
		return eid;
	}
	
	public StringProperty nameProperty(){
		return name;
	}
	
	public StringProperty surnameProperty(){
		return surname;
	}
	
	public StringProperty ageProperty(){
		return age;
	}
	
}
