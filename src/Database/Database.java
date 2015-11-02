package Database;
import Data.Character;
import Data.Item;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Eric on 10/27/2015.
 */
public class Database {
    private ObservableList<Item> items_list;
    private ObservableList<Character> character_list;
    private Connection con;
    private Statement stat;
    public void load_db() throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:story");
        stat = con.createStatement();
        String cmd = "create table if not exists CHARACTERS (Name VARCHAR, Age VARCHAR, Gender VARCHAR, Race VARCHAR, Occupation VARCHAR, CharacterID VARCHAR);";
        execute(cmd);
        cmd = "create table if not exists ITEMS (Name VARCHAR, Location VARCHAR, Owner VARCHAR, CharacterID VARCHAR, ItemID VARCHAR);";
        execute(cmd);
        cmd = "create table if not exists EVENTS (Name VARCHAR, Action VARCHAR, Item VARCHAR);";
        execute(cmd);
        populate_lists();
    }
    public void populate_lists(){
        // TODO

    }

    public Character findChar(int ID){
        Character charchanging = new Character();
        for (int i = 0; i < character_list.size(); i++) {
            if (ID == character_list.get(i).getID()) {
                charchanging = character_list.get(i);
                break;
            }
        }
        return charchanging;
    }
    public Item findItem(int ID){
        // Should this throw something if it can't find it? -E.H.
        Item item = new Item();
        for (int i = 0; i < items_list.size(); i++) {
            if (ID == items_list.get(i).getID()) {
                item = items_list.get(i);
                break;
            }
        }
        return item;
    }

    public void writeCharCell(int ID, String column, String new_val) throws SQLException {
        String cmd = "UPDATE CHARACTERS SET column='" + new_val + "' WHERE CharacterID = " + ID + ";"; // Insert Code Here
        Character charchanging = findChar(ID);
        charchanging.update(column, new_val);
        execute(cmd);
    }
    public void writeItemCell(int ID, String column, String new_val) throws SQLException {
        String cmd = "UPDATE ITEMS SET column='" + new_val + "' WHERE ItemID = " + ID + ";";
        Item item = findItem(ID);
        item.update(column, new_val);
        execute(cmd);
    }
    public void addChar(String Name, String Age, String Gender,String Race, String Occupation, String Character_ID ) throws SQLException {
        // instead of item. we could add each of the column inputs. it may be better that way.
        Character nChar = new Character(); // pass in the new arguments
        character_list.add(nChar);
        String cmd = "INSERT INTO CHARACTERS (Name, Age, Gender, Race, Occupation, CharacterID) VALUES (" + Name + ","
                + Age + "," + Gender + "," + Race + "," + Occupation + "," + Character_ID + " );";
        execute(cmd);
    }
    public void addItem(String Name, String Location, String Owner, String CharacterID, String ItemID) throws SQLException {
        // instead of char. we could add each of the column inputs. it may be better that way.
        Item nItem = new Item();
        items_list.add(nItem);
        String cmd = "INSERT INTO ITEMS (Name, Location, Owner, CharacterID, ItemID) VALUES (" +
                Name + "," + Location + "," + Owner + "," + CharacterID + "," + ItemID + ");";
        execute(cmd);

    }
    public void deleteItem(int ID) throws SQLException {
        String cmd = "DELETE from CHARACTERS where ID=" + ID + ";";
        Item item = findItem(ID);
        items_list.remove(item);
        execute(cmd);
    }
    public void deleteChar(int ID) throws SQLException {
        String cmd = "DELETE from ITEMS where ID=" + ID + ";";
        // Update the items that belong to this character
        Character chr = findChar(ID);
        character_list.remove(chr);
        execute(cmd);

    }

    public void filterItem() throws SQLException {
        String cmd = "";
        execute(cmd);
    }
    private void execute(String cmd) throws SQLException {
        stat.execute(cmd);
    }

    public ObservableList<Item> getItems_list(){return items_list;}
    public ObservableList<Character> getCharacter_list(){return character_list;}



}
