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
    

    public void update(String variabletochange, String new_val){

    }
    public void changeOwner(){

    }    
    
    public Item(String name){
    	this.name = new SimpleStringProperty(name);
    	location = new SimpleStringProperty("N/A");
    	owner = new SimpleStringProperty("None");
    	iID = new SimpleStringProperty();
    	oID = new SimpleStringProperty();
    }
    
    public void setName(String word){
    	name.set(word);
    }
    
    public void setLocation(String word){
    	location.set(word);
    }
    
    public void setOwner(String word){
    	owner.set(word);
    }
   
    public void setiID(String i){
    	iID.set(i);
    }
    
    public void setoID(String o){
    	oID.set(o);
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