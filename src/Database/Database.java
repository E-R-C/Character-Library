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
        String cmd = "create table if not exists CHARACTERS (Name VARCHAR, Age VARCHAR, Gender VARCHAR, Race VARCHAR, Occupation VARCHAR, CharacterID VARCHAR)";
        execute(cmd);
        cmd = "create table if not exists ITEMS (Name VARCHAR, Location VARCHAR, Owner VARCHAR, CharacterID VARCHAR, ItemID VARCHAR)";
        execute(cmd);
    }
    public void populate_lists(){}
    public void writeCharCell(int ID, String column, String new_val) throws SQLException {
        Character charchanging;
        charchanging = new Character();
        // this does not need to be here, needs to throw null pointer error?

        String cmd = "UPDATE CHARACTERS SET column='" + new_val + "' WHERE something='some value';"; // Insert Code Here
        for (int i = 0; i < character_list.size(); i++) {
            if (ID == character_list.get(i).getID()) {
                charchanging = character_list.get(i);
                break;
            }
        }
        charchanging.update(column, new_val);
        execute(cmd);
    }
    public void writeItemCell(int ID, String column, String new_val) throws SQLException {
        Item item;
        item = new Item();
        // this does not need to be here, needs to throw null pointer error?

        String cmd = "UPDATE ITEMS SET column='" + new_val + "' WHERE something='some value';";
        for (int i = 0; i < items_list.size(); i++) {
            if (ID == items_list.get(i).getID()) {
                item = items_list.get(i);
                break;
            }
        }
        item.update(column, new_val);
        execute(cmd);
    }
    public void addItem(Item item){ // instead of item. we could add each of the column inputs. it may be better that way.

    }
    public void addChar(Character character){// instead of char. we could add each of the column inputs. it may be better that way.

    }
    public void deleteItem(int ID) throws SQLException {
        String cmd = "DELETE from CHARACTERS where ID=" + ID + ";";
        Item item = new Item();
        for (int i = 0; i < items_list.size(); i++) {
            if (ID == items_list.get(i).getID()) {
                item = items_list.get(i);
                break;
            }
        }
        items_list.remove(item);
        execute(cmd);
    }
    public void deleteChar(int ID) throws SQLException {
        Character chr;
        chr = new Character();
        String cmd = "DELETE from ITEMS where ID=" + ID + ";";
        for (int i = 0; i < character_list.size(); i++) {
            if (ID == character_list.get(i).getID()) {
                chr = character_list.get(i);
                break;
            }
        }
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
