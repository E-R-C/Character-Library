package Tests;
import Data.Character;

/**
 * Created by Eric on 11/9/2015.
 */
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataTests {

    @Test
    public void createCharacter(){
        Character c1 = new Character("Name","Gender","Age","Race","Occupation","cID");
        assertEquals("Name",c1.getName());
        c1.setName("");
        assertEquals("Unknown",c1.getName());
        assertEquals("Gender", c1.getGender());
        assertEquals("Age",c1.getAge());
        assertEquals("Race",c1.getRace());
        assertEquals("Occupation",c1.getOccupation());
        assertEquals("cID",c1.getcID());

    }


}
