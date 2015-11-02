package Database;
import Data.Character;
import Data.Item;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Created by Eric on 10/27/2015.
 */
public class Database {
    private ObservableList<Item> items_list;
    private ObservableList<Character> character_list;
    private ObservableList<Item> filter_items;
    private ObservableList<Character> filter_character;

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
    public void populate_lists() throws SQLException {
        ResultSet rs = stat.executeQuery( "SELECT * FROM CHARACTERS;" );
        while(rs.next()){
            character_list.add(new Character(rs.getString("Name"), rs.getString("Gender"), rs.getString("Age"), rs.getString("Race"), rs.getString("Occupation"), rs.getString("CharacterID")));
            // rs.getString("Name"), rs.getString("Age"), rs.getString("Gender"), rs.getString("Race"), rs.getString("Occupation"), rs.getString("CharcterID")
            }
        rs = stat.executeQuery("SELECT * FROM ITEMS;");
        while(rs.next()){
            items_list.add(new Item(rs.getString("Name"),rs.getString("Location"),rs.getString("Owner"), rs.getString("CharacterID"), rs.getString("ItemID")));
            // rs.getString("Name"),rs.getString("Location"),rs.getString("Owner"), rs.getString("CharacterID"), rs.getString("ItemID")
            }
        }


    public void updateChar(Character c) throws SQLException {
        // Name VARCHAR, Age VARCHAR, Gender VARCHAR, Race VARCHAR, Occupation VARCHAR, CharacterID VARCHAR
        String cmd = "UPDATE CHARACTERS SET Name =" + c.getName() + ", Age = " + c.getAge() + ", Gender = " +
                c.getGender() + ", Race = " + c.getRace() + ", Occupation = " + c.getOccupation() +
                ", CharacterID = " + c.getcID() + ", WHERE ItemID = " + c.getcID() + ";";
        execute(cmd);
    }
    public void updateItem(Item i) throws SQLException {
        String cmd = "UPDATE ITEMS SET Name =" + i.getName() + ", Location = " + i.getLocation() + ", Owner = " + i.getOwner() + ", CharacterID = " + i.getoID() + ", WHERE ItemID = " + i.getiID() + ";";
        execute(cmd);
    }
    public void addChar(Character c) throws SQLException {
        // instead of item. we could add each of the column inputs. it may be better that way.
        character_list.add(c);
        String cmd = "INSERT INTO CHARACTERS (Name, Gender, Age, Race, Occupation, CharacterID) VALUES (" + c.getName() + ","
                + c.getGender() + "," + c.getAge() + "," + c.getRace() + "," + c.getOccupation() + "," + c.getcID() + " );";
        execute(cmd);
    }
    public void addItem(Item item) throws SQLException {
        // instead of char. we could add each of the column inputs. it may be better that way.
        items_list.add(item);
        String cmd = "INSERT INTO ITEMS (Name, Location, Owner, CharacterID, ItemID) VALUES (" +
                item.getName() + "," + item.getLocation() + "," + item.getOwner() + "," + item.getoID() + "," + item.getiID() + ");";
        execute(cmd);

    }
    public void deleteItem(Item item) throws SQLException {
        String cmd = "DELETE from CHARACTERS where ItemID=" + item.getiID() + ";";
        items_list.remove(item);
        execute(cmd);
    }
    public void deleteChar(Character chr) throws SQLException {
        String cmd = "DELETE from ITEMS where ID=" + chr.getcID() + ";";
        // Update the items that belong to this character
        character_list.remove(chr);
        execute(cmd);

    }

    private Character findChar(String ID){
        Character charchanging =  null; // NO OTHER OPTION
        for (int i = 0; i < character_list.size(); i++) {
            if (ID == character_list.get(i).getcID()) {
                if (ID == character_list.get(i).getcID()) {
                    charchanging = character_list.get(i);
                    break;
                }
            }
        }
        return charchanging;
    }
    public void queryChar(String Column, String input) throws SQLException {
        ResultSet rs = stat.executeQuery( "SELECT * FROM CHARACTERS;" );
        int in_len = input.length();
        while (rs.next()){
            if (input.length() <= rs.getString(Column).length()){
                if (rs.getString(Column).toLowerCase().substring(0,in_len-1).equals(input.toLowerCase().substring(0,in_len-1))){
                    filter_character.add(findChar(rs.getString("CharacterID")));
                }
            }

        }
    }
    public int getMaxcID() throws SQLException {
        ResultSet rs = stat.executeQuery( "SELECT * FROM CHARACTERS;" );
        return rs.getFetchSize();
    }
    public int getMaxiID() throws SQLException {
        ResultSet rs = stat.executeQuery( "SELECT * FROM CHARACTERS;" );
        return rs.getFetchSize();
    }
    public void queryitem(String Column, String input) throws SQLException{

    }
    private void execute(String cmd) throws SQLException {
        stat.execute(cmd);
    }
    public ObservableList<Item> getFilter_Items(){return filter_items;}
    public ObservableList<Character> getFilter_Character(){return filter_character;}
    public ObservableList<Item> getItems_list(){return items_list;}
    public ObservableList<Character> getCharacter_list(){return character_list;}



}
