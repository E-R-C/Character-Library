package Database;
import Data.Character;
import Data.Circumstance;
import Data.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Created by Eric on 10/27/2015.
 */
public class Database {
    private ObservableList<Item> item_list;
    private ObservableList<Character> character_list;
    private ObservableList<Circumstance> event_list;
    private ObservableList<Item> filter_items;
    private ObservableList<Character> filter_character;
    private ObservableList<Circumstance> filter_Event;
    private Connection con;
    private Statement stat;
    public void load_db() throws SQLException, ClassNotFoundException {
        item_list = FXCollections.observableArrayList();
        character_list = FXCollections.observableArrayList();
        event_list = FXCollections.observableArrayList();
        filter_items = FXCollections.observableArrayList();
        filter_character = FXCollections.observableArrayList();
        filter_Event = FXCollections.observableArrayList();
        Class.forName("org.sqlite.JDBC");
        openConnection();
        con = DriverManager.getConnection("jdbc:sqlite:story.db");
        stat = con.createStatement();
        String cmd = "CREATE TABLE IF NOT EXISTS CHARACTERS (Name VARCHAR, Gender VARCHAR, Age VARCHAR, Race VARCHAR, Occupation VARCHAR, CharacterID VARCHAR);";
        execute(cmd);
        cmd = "CREATE TABLE IF NOT EXISTS ITEMS (Name VARCHAR, Location VARCHAR, Owner VARCHAR, CharacterID VARCHAR, ItemID VARCHAR);";
        execute(cmd);
        cmd = "CREATE TABLE IF NOT EXISTS EVENTS (Name VARCHAR, Date VARCHAR, Subject VARCHAR, CharacterID VARCHAR, EventID VARCHAR);";
        execute(cmd);
        populate_lists();
    }
    private void openConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        con = DriverManager.getConnection("jdbc:sqlite:story.db");
        stat = con.createStatement();
    }
    private void closeConnection() throws SQLException{
        stat.close();
        con.close();
    }

    public void populate_lists() throws SQLException {
        openConnection();
        ResultSet rs = stat.executeQuery( "SELECT * FROM CHARACTERS;" );
        while(rs.next()){
            character_list.add(new Character(rs.getString("Name"), rs.getString("Gender"), rs.getString("Age"), rs.getString("Race"), rs.getString("Occupation"), rs.getString("CharacterID")));
            }
        System.out.println(character_list.size());
        rs = stat.executeQuery("SELECT * FROM ITEMS;");
        while(rs.next()){
            item_list.add(new Item(rs.getString("Name"), rs.getString("Location"), rs.getString("Owner"), rs.getString("CharacterID"), rs.getString("ItemID")));
            }
        rs = stat.executeQuery("SELECT * FROM EVENTS;");
        while(rs.next()){
            event_list.add(new Circumstance(rs.getString("Name"), rs.getString("Date"), rs.getString("Subject"), rs.getString("CharacterID"), rs.getString("EventID")));
        }
        closeConnection();
    }


    public void updateChar(Character c) throws SQLException {
        openConnection();
    	String cmd = "UPDATE CHARACTERS SET Name ='" + c.getName() + "', Age = '" + c.getAge() + "', Gender = '" +
                c.getGender() + "', Race = '" + c.getRace() + "', Occupation = '" + c.getOccupation() +
                "', CharacterID = '" + c.getcID() + "' WHERE CharacterID = '" + c.getcID() + "';";
        execute(cmd);
        closeConnection();
    }
    public void updateItem(Item i) throws SQLException {
        String cmd = "UPDATE ITEMS SET Name = '" + i.getName() + "', Location = '" + i.getLocation() + "', Owner = '" + i.getOwner() + "', CharacterID = '" + i.getoID() + "' WHERE ItemID = '" + i.getiID() + "';";
        execute(cmd);
    }
    public void updateEvent(Circumstance e) throws SQLException{
    	String cmd = "UPDATE EVENTS SET Name ='" + e.getName() + "', Date = '" + e.getDate() + "', Subject = '" + e.getSubject() + "', CharacterID = '" + e.getsID() + "' WHERE EventID = '" + e.geteID() + "';";
        execute(cmd);
    }
    public void addChar(Character c) throws SQLException {
        character_list.add(c);
        String cmd = "INSERT INTO CHARACTERS VALUES ('" + c.getName() + "','"
                + c.getGender() + "','" + c.getAge() + "','" + c.getRace() + "','" + c.getOccupation() + "','" + c.getcID() + "' );";
        execute(cmd);
    }
    public void addItem(Item item) throws SQLException {
        // instead of char. we could add each of the column inputs. it may be better that way.
    	item_list.add(item);
        String cmd = "INSERT INTO ITEMS (Name, Location, Owner, CharacterID, ItemID) VALUES ('" +
                item.getName() + "','" + item.getLocation() + "','" + item.getOwner() + "','" + item.getoID() + "','" + item.getiID() + "');";
        execute(cmd);
    }
    public void addEvent(Circumstance event) throws SQLException{
    	event_list.add(event);
        String cmd = "INSERT INTO EVENTS (Name, Date, Subject, CharacterID, EventID) VALUES ('" +
                event.getName() + "','" + event.getDate() + "','" + event.getSubject() +"','" + event.getsID() + "','" + event.geteID() + "');";
        execute(cmd);
    }
    public void deleteChar(Character chr) throws SQLException {
        String cmd = "DELETE from CHARACTERS where CharacterID=" + chr.getcID() + ";";
        character_list.remove(chr);
        execute(cmd);
    }
    public void deleteItem(Item item) throws SQLException {
        String cmd = "DELETE from ITEMS where ItemID=" + item.getiID() + ";";
        item_list.remove(item);
        execute(cmd);
    }
    public void deleteEvent(Circumstance circ) throws SQLException {
        String cmd = "DELETE from EVENTS where EventID=" + circ.geteID() + ";";
        event_list.remove(circ);
        execute(cmd);
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
    private Item findItem(String ID) {
        Item item = null;
        for (int i = 0; i < item_list.size(); i++) {
            if (ID.equals(item_list.get(i).getiID())) {
                item = item_list.get(i);
                break;
            }
        }
        if (item == null) {
            throw new NullPointerException();
        }
        return item;
    }
    private Circumstance findEvent(String ID){
        Circumstance event = null;
        for (int i = 0; i < event_list.size(); i++){
            if (ID.equals(event_list.get(i).geteID())){
                event = event_list.get(i);
                break;
            }
        }
        if (event == null){
            throw new NullPointerException();
        }
        return event;
    }
    public void queryChar(String Column, String input) throws SQLException {
        openConnection();
        filter_character.clear();
        ResultSet rs = stat.executeQuery( "SELECT * FROM CHARACTERS;" );
        int in_len = input.length();
        while (rs.next()){
            int search_len = in_len -1;
            if (rs.getString(Column).length() <= search_len){
                search_len = rs.getString(Column).length();
            }
            if (input.length() <= rs.getString(Column).length()){
                if (rs.getString(Column).toLowerCase().substring(0,search_len+1).equals(input.toLowerCase())){
                    System.out.println(rs.getString("CharacterID"));
                    filter_character.add(findChar(rs.getString("CharacterID")));
                }
            }

        }
        closeConnection();
    }
    public void queryitem(String Column, String input) throws SQLException{
        openConnection();
        filter_items.clear();
        System.out.println(Column);
        System.out.println(input);
        ResultSet rs = stat.executeQuery("SELECT * FROM ITEMS;");
        int in_len = input.length();
        while (rs.next()){
            int search_len = in_len -1;
            if (rs.getString(Column).length() <= search_len){
                search_len = rs.getString(Column).length();
            }
            if (input.length() <= rs.getString(Column).length()){
                if (rs.getString(Column).toLowerCase().substring(0, search_len+1).equals(input.toLowerCase())){
                    System.out.println(rs.getString("ItemID"));
                    filter_items.add(findItem(rs.getString("ItemID")));
                }
            }
        }
        closeConnection();
    }
    public void queryEvent(String Column, String input) throws SQLException {
    	System.out.println("THis is now LJ's controller not calling it");
        openConnection();
        filter_Event.clear();
        ResultSet rs = stat.executeQuery( "SELECT * FROM EVENTS;" );
        int in_len = input.length();
        System.out.println("Hi");
        while (rs.next()){
        	int search_len = in_len -1;
        	if (rs.getString(Column).length() <= search_len){
        		search_len = rs.getString(Column).length();
        	}
            if (input.length() <= rs.getString(Column).length()){
                if (rs.getString(Column).toLowerCase().substring(0,search_len+1).equals(input.toLowerCase())){
                    System.out.println(rs.getString("EventID"));
                    filter_Event.add(findEvent(rs.getString("EventID")));
                    
                }
            }
        }
        closeConnection();
    }


    public String getMaxcID() throws SQLException {
        openConnection();
        if (character_list.size() > 0){
            ResultSet rs = stat.executeQuery( "SELECT * FROM CHARACTERS ORDER BY CharacterID DESC LIMIT 1;" );
            return rs.getString("CharacterID");
        }
        closeConnection();
        return "0";

    }
    public String getMaxiID() throws SQLException {
        openConnection();
        if (item_list.size() > 0){
            ResultSet rs = stat.executeQuery( "SELECT * FROM ITEMS ORDER BY ItemID DESC LIMIT 1;" );
            return rs.getString("ItemID");
        }
        closeConnection();
        return "0";
    }
    public String getMaxeID() throws SQLException {
        openConnection();
        if (item_list.size() > 0){
            ResultSet rs = stat.executeQuery( "SELECT * FROM EVENTS ORDER BY EventID DESC LIMIT 1;" );
            return rs.getString("EventID");
        }
        closeConnection();
        return "0";
    }


    private void execute(String cmd) throws SQLException {
        openConnection();
        System.out.println(cmd);
        stat.execute(cmd);
        closeConnection();
    }
    public ObservableList<Character> getCharacter_list(){return character_list;}
    public ObservableList<Item> getItem_list(){return item_list;}
    public ObservableList<Circumstance> getEvent_list(){return event_list;}
    public ObservableList<Character> getFilter_Character(){return filter_character;}
    public ObservableList<Item> getFilter_Items(){return filter_items;}
    public ObservableList<Circumstance> getFilter_Event(){return filter_Event;}

}
