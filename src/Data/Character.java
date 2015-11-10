package Data;

/**
 * Created by Eric on 10/25/2015.
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Character extends Entry{
	
    private StringProperty age;
    private StringProperty gender;
    private StringProperty race;
    private StringProperty occupation;
    private StringProperty cID;
  
    public Character(String name, String gender, String age, String race, String occupation, String cID){
    	this.name = new SimpleStringProperty(blankChecker(name));
    	this.gender = new SimpleStringProperty(blankChecker(gender));
    	this.age = new SimpleStringProperty(blankChecker(age));
    	this.race = new SimpleStringProperty(blankChecker(race));
    	this.occupation = new SimpleStringProperty(blankChecker(occupation));
    	this.cID = new SimpleStringProperty(cID);
    }
    
    public void setAge(String word){
    	age.set(blankChecker(word));
    }
    
    public void setGender(String word){
    	gender.set(blankChecker(word));
    }
    
    public void setRace(String word){
    	race.set(blankChecker(word));
    }
    
    public void setOccupation(String word){
    	occupation.set(blankChecker(word));
    }
    
    public void setcID(String c){
    	cID.set(blankChecker(c));
    }
    
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