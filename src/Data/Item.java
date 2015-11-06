package Data;

/**
 * Created by Eric on 10/25/2015.
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item {
    
    private StringProperty name;
    private StringProperty location;
    private StringProperty owner;
    private StringProperty iID;
    private StringProperty oID;
    
    public void deleteOwner(String id){
    	if (id.equals(oID.get())) {
    		owner.set("None");
    		oID.set("0");
    	}
    }    
    
    public Item(String name, String location, String owner, String oID, String iID){
    	this.name = new SimpleStringProperty(blankChecker(name));
    	this.location = new SimpleStringProperty(blankChecker(location));
    	this.owner = new SimpleStringProperty(blankChecker(owner));
    	this.oID = new SimpleStringProperty(blankChecker(oID));
    	this.iID = new SimpleStringProperty(blankChecker(iID));
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
    
    public void setLocation(String word){
    	location.set(blankChecker(word));
    }
    
    public void setOwner(String word){
    	owner.set(blankChecker(word));
    }
   
    public void setiID(String i){
    	iID.set(blankChecker(i));
    }
    
    public void setoID(String o){
    	oID.set(blankChecker(o));
    }
    
    public String getName() {return name.get();} 
	public StringProperty getNameProperty() {return name;}
	public String getLocation() {return location.get();}
	public StringProperty getLocationProperty() {return location;}
	public String getOwner() {return owner.get();}
	public StringProperty getOwnerProperty() {return owner;}
	public String getiID() {return iID.get();}
	public StringProperty getiIDProperty() {return iID;}
	public String getoID() {return oID.get();}
	public StringProperty getoIDProperty() {return oID;}
}