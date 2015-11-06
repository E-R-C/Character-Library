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
    	this.name = new SimpleStringProperty(blankChecker(name));
    	this.gender = new SimpleStringProperty(blankChecker(gender));
    	this.age = new SimpleStringProperty(blankChecker(age));
    	this.race = new SimpleStringProperty(blankChecker(race));
    	this.occupation = new SimpleStringProperty(blankChecker(occupation));
    	this.cID = new SimpleStringProperty(blankChecker(cID));
    }
    
    public String blankChecker(String word){
    	if(!word.equals(""))
    		return word;
    	else
    		return "Unknown";
    }
    
    public void setName(String word){
    	name.set(blankChecker(word));
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