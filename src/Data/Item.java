package Data;

/**
 * Created by Eric on 10/25/2015.
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item extends Entry{
    
    private StringProperty location;
    private StringProperty owner;
    private StringProperty iID;
    private StringProperty oID;
    
    public Item(String name, String location, String owner, String oID, String iID){
    	this.name = new SimpleStringProperty(blankChecker(name));
    	this.location = new SimpleStringProperty(blankChecker(location));
    	this.owner = new SimpleStringProperty(blankChecker(owner));
    	this.oID = new SimpleStringProperty(oID);
    	this.iID = new SimpleStringProperty(iID);
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
    
	public String getLocation() {return location.get();}
	public StringProperty getLocationProperty() {return location;}
	public String getOwner() {return owner.get();}
	public StringProperty getOwnerProperty() {return owner;}
	public String getiID() {return iID.get();}
	public StringProperty getiIDProperty() {return iID;}
	public String getoID() {return oID.get();}
	public StringProperty getoIDProperty() {return oID;}
}