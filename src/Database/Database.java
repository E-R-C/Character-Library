package Database;
import Data.Character;
import Data.Item;
import javafx.collections.FXCollections;
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
    public void load_db() throws SQLException, ClassNotFoundException {
        items_list = FXCollections.observableArrayList();
        character_list = FXCollections.observableArrayList();
        filter_items = FXCollections.observableArrayList();
        filter_character = FXCollections.observableArrayList();
        Class.forName("org.sqlite.JDBC");
        why();
        con = DriverManager.getConnection("jdbc:sqlite:story.db");
        stat = con.createStatement();
        String cmd = "CREATE TABLE IF NOT EXISTS CHARACTERS (Name VARCHAR, Gender VARCHAR, Age VARCHAR, Race VARCHAR, Occupation VARCHAR, CharacterID VARCHAR);";
        execute(cmd);
        cmd = "CREATE TABLE IF NOT EXISTS ITEMS (Name VARCHAR, Location VARCHAR, Owner VARCHAR, CharacterID VARCHAR, ItemID VARCHAR);";
        execute(cmd);
        cmd = "create table if not exists EVENTS (Name VARCHAR, Action VARCHAR, Item VARCHAR);";
        execute(cmd);
        populate_lists();
    }
    private void why() throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:story.db");
        stat = con.createStatement();
    }

    public void populate_lists() throws SQLException {
        ResultSet rs = stat.executeQuery( "SELECT * FROM CHARACTERS;" );
        while(rs.next()){
            character_list.add(new Character(rs.getString("Name"), rs.getString("Gender"), rs.getString("Age"), rs.getString("Race"), rs.getString("Occupation"), rs.getString("CharacterID")));
            // rs.getString("Name"), rs.getString("Age"), rs.getString("Gender"), rs.getString("Race"), rs.getString("Occupation"), rs.getString("CharcterID")
            }
        System.out.println(character_list.size());
        rs = stat.executeQuery("SELECT * FROM ITEMS;");
        while(rs.next()){
            items_list.add(new Item(rs.getString("Name"),rs.getString("Location"),rs.getString("Owner"), rs.getString("CharacterID"), rs.getString("ItemID")));
            // rs.getString("Name"),rs.getString("Location"),rs.getString("Owner"), rs.getString("CharacterID"), rs.getString("ItemID")
            }
        System.out.println("items list size: " + items_list.size());
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
        why();
        // instead of item. we could add each of the column inputs. it may be better that way.
        // (Name, Gender, Age, Race, Occupation, CharacterID)
        character_list.add(c);
        String cmd = "INSERT INTO CHARACTERS VALUES ('" + c.getName() + "','"
                + c.getGender() + "','" + c.getAge() + "','" + c.getRace() + "','" + c.getOccupation() + "','" + c.getcID() + "' );";
        execute(cmd);
    }
    public void addItem(Item item) throws SQLException {
        // instead of char. we could add each of the column inputs. it may be better that way.
        items_list.add(item);
        String cmd = "INSERT INTO ITEMS (Name, Location, Owner, CharacterID, ItemID) VALUES ('" +
                item.getName() + "','" + item.getLocation() + "','" + item.getOwner() + "','" + item.getoID() + "','" + item.getiID() + "');";
        execute(cmd);

    }
    public void deleteItem(Item item) throws SQLException {
        String cmd = "DELETE from ITEMS where ItemID=" + item.getiID() + ";";
        items_list.remove(item);
        execute(cmd);
    }
    public void deleteChar(Character chr) throws SQLException {
        String cmd = "DELETE from CHARACTERS where CharacterID=" + chr.getcID() + ";";
        // Update the items that belong to this character
        character_list.remove(chr);
        execute(cmd);

    }
    private Item findItem(String ID){
        Item item = null;
        for (int i = 0; i < items_list.size(); i++) {
            if (ID.equals(items_list.get(i).getiID())) {
                item = items_list.get(i);
                break;
            }
        }
        if (item == null){
            throw new NullPointerException();
        }
        return item;
    }
    private Character findChar(String ID){
        Character charchanging =  null; // NO OTHER OPTION
        for (int i = 0; i < character_list.size(); i++) {
            if (ID.equals(character_list.get(i).getcID())) {
                charchanging = character_list.get(i);
                break;
            }
        }
        if (charchanging == null){
            throw new NullPointerException();
        }
        return charchanging;
    }
    public void queryChar(String Column, String input) throws SQLException {
        filter_character.clear();
        ResultSet rs = stat.executeQuery( "SELECT * FROM CHARACTERS;" );
        int in_len = input.length();

        while (rs.next()){
            System.out.println("Column = " + rs.getString(Column).substring(0,in_len-1));
            System.out.println("input = " + input);
            if (input.length() <= rs.getString(Column).length()){
                if (rs.getString(Column).toLowerCase().substring(0,in_len).equals(input.toLowerCase())){
                    System.out.println(rs.getString("CharacterID"));
                    filter_character.add(findChar(rs.getString("CharacterID")));
                }
            }

        }
    }
    public String getMaxcID() throws SQLException {
        if (character_list.size() > 0){
            ResultSet rs = stat.executeQuery( "SELECT * FROM CHARACTERS ORDER BY CharacterID DESC LIMIT 1;" );
            return rs.getString("CharacterID");
        }
        return "0";
    }
    public String getMaxiID() throws SQLException {
        if (items_list.size() > 0){
            ResultSet rs = stat.executeQuery( "SELECT * FROM ITEMS ORDER BY ItemID DESC LIMIT 1;" );
            return rs.getString("ItemID");
        }
        return "0";
    }
    public void queryitem(String Column, String input) throws SQLException{
        filter_items.clear();
        ResultSet rs = stat.executeQuery("SELECT * FROM ITEMS");
        int in_len = input.length();
        while (rs.next()){
            if (input.length() <= rs.getString(Column).length()){
                System.out.println("Column = " + rs.getString(Column).substring(0,in_len-1));
                System.out.println("input = " + input);
                if (rs.getString(Column).toLowerCase().substring(0, in_len).equals(input.toLowerCase())){
                    System.out.println(rs.getString("ItemID"));
                    filter_items.add(findItem(rs.getString("ItemID")));

                }
            }
        }
    }
    private void execute(String cmd) throws SQLException {
        System.out.println(cmd);
        stat.execute(cmd);
    }
    public ObservableList<Item> getFilter_Items(){return filter_items;}
    public ObservableList<Character> getFilter_Character(){System.out.println(filter_character.toString()); return filter_character;}
    public ObservableList<Item> getItems_list(){return items_list;}
    public ObservableList<Character> getCharacter_list(){return character_list;}



}
