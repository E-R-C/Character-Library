package Tests;

import Data.Character;
import Data.Item;
import Data.Circumstance;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
/*
 * Created by Eric on 11/9/2015.
 * Added to by Devin on 11/10/2015
 */

public class DataTests {

    @Test
    public void createCharacter(){
        Character c1 = new Character("Name","Gender","Age","Race","Occupation","cID");
        assertEquals("Name",c1.getName());
        c1.setName("");
        assertEquals("Unknown",c1.getName());
        assertEquals("Gender", c1.getGender());
        c1.setGender("");
        assertEquals("Unknown", c1.getGender());
        assertEquals("Age",c1.getAge());
        c1.setAge("");
        assertEquals("Unknown", c1.getAge());
        assertEquals("Race",c1.getRace());
        c1.setRace("");
        assertEquals("Unknown", c1.getRace());
        assertEquals("Occupation",c1.getOccupation());
        c1.setOccupation("");
        assertEquals("Unknown", c1.getOccupation());
        assertEquals("cID",c1.getcID());
        c1.setcID("");
        assertEquals("Unknown", c1.getcID());
    }
    
    @Test
    public void createItem(){
    	Item i1 = new Item("Sword", "Location", "Jared", "oID", "iID");
    	assertEquals("Sword", i1.getName());
    	i1.setName("");
    	assertEquals("Unknown", i1.getName());
    	assertEquals("Location", i1.getLocation());
    	i1.setLocation("");
    	assertEquals("Unknown", i1.getLocation());
    	assertEquals("Jared", i1.getOwner());
    	i1.setOwner("");
    	assertEquals("Unknown", i1.getOwner());
    	assertEquals("oID", i1.getoID());
    	i1.setoID("");
    	assertEquals("Unknown", i1.getoID());
    	assertEquals("iID", i1.getiID());
    	i1.setiID("");
    	assertEquals("Unknown", i1.getiID());
    }
    
    @Test
    public void createCircumstance(){
    	Circumstance e1 = new Circumstance("name", "date", "subject", "sID", "eID");
    	assertEquals("Sword", e1.getName());
    	e1.setName("");
    	assertEquals("Unknown", e1.getName());
    	assertEquals("Location", e1.getDate());
    	e1.setDate("");
    	assertEquals("Unknown", e1.getDate());
    	assertEquals("Jared", e1.getSubject());
    	e1.setSubject("");
    	assertEquals("Unknown", e1.getSubject());
    	assertEquals("oID", e1.getsID());
    	e1.setsID("");
    	assertEquals("Unknown", e1.getsID());
    	assertEquals("iID", e1.geteID());
    	e1.seteID("");
    	assertEquals("Unknown", e1.geteID());
    }
}
