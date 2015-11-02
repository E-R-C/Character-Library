package Data;

/**
 * Created by Eric on 10/25/2015.
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Character {
	
    private StringProperty name;
    private StringProperty age;
    private StringProperty gender;
    private StringProperty race;
    private StringProperty occupation;
    private StringProperty cID;
  
    public Character(String name, String gender, String age, String race, String occupation, String cID){
    	this.name = new SimpleStringProperty(name);
    	this.gender = new SimpleStringProperty(gender);
    	this.age = new SimpleStringProperty(age);
    	this.race = new SimpleStringProperty(race);
    	this.occupation = new SimpleStringProperty(occupation);
    	this.cID = new SimpleStringProperty(cID);
    }
    
    public void setName(String word){
    	name.set(word);
    }
    
    public void setAge(String word){
    	age.set(word);
    }
    
    public void setGender(String word){
    	gender.set(word);
    }
    
    public void setRace(String word){
    	race.set(word);
    }
    
    public void setOccupation(String word){
    	occupation.set(word);
    }
    
    public void setcID(String c){
    	cID.set(c);
    }
    
    public String getName() {return name.get();} 
	public StringProperty getNameProperty() {return name;}
	public String getAge() {return age.get();}
	public StringProperty getAgeProperty() {return age;}
	public String getGender() {return gender.get();}
	public StringProperty getGenderProperty() {return gender;}
	public String getRace() {return race.get();} 
	public StringProperty getRaceProperty() {return race;}
	public String getOccupation() {return occupation.get();}
	public StringProperty getOccupationProperty() {return occupation;}
	public String getcID() {return cID.get();}
	public StringProperty getcIDProperty() {return cID;}
}