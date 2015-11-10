package Data;

import javafx.beans.property.StringProperty;

public class Entry{
	
	public StringProperty name;
	
    public String blankChecker(String word){
    	if(!word.equals(""))
    		return word;
    	else
    		return "Unknown";
    }
    
    public void setName(String word){
    	name.set(blankChecker(word));
    }
    
    public String getName() {return name.get();} 
	public StringProperty getNameProperty() {return name;}
}