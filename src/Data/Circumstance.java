package Data;

/**
 * Created by Devin on 11/4/2015.
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Circumstance extends Entry{
   
    private StringProperty date;
    private StringProperty subject;
    private StringProperty sID;
    private StringProperty eID;
    
    
    public Circumstance(String name, String date, String subject, String sID, String eID){
    	this.name = new SimpleStringProperty(blankChecker(name));
    	this.date = new SimpleStringProperty(blankChecker(date));
    	this.subject = new SimpleStringProperty(blankChecker(subject));
    	this.sID = new SimpleStringProperty(sID);
    	this.eID = new SimpleStringProperty(eID);
    	
    }
    
    public void setDate(String word){
    	date.set(blankChecker(word));
    }
        
    public void setSubject(String word){
    	subject.set(blankChecker(word));
    }
    
    public void setsID(String s){
    	sID.set(blankChecker(s));
    }
    
    public void seteID(String e){
    	eID.set(blankChecker(e));
    }
    
	public String getDate() {return date.get();}
	public StringProperty getDateProperty() {return date;}
	public String getSubject() {return subject.get();} 
	public StringProperty getSubjectProperty() {return subject;}
	public String getsID() {return sID.get();} 
	public StringProperty getsIDProperty() {return sID;}
	public String geteID() {return eID.get();} 
	public StringProperty geteIDProperty() {return eID;}
}