package GUI;

import java.sql.SQLException;

import application.Catalog;
import application.Song;
import Data.Item;
import Data.Character;
import Database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class Controller {
	
	private ObservableList<Character> characterData = FXCollections.observableArrayList();
	private ObservableList<Character> itemData = FXCollections.observableArrayList();
	private ObservableList<Character> characterFilter = FXCollections.observableArrayList();
	private ObservableList<Character> itemFilter = FXCollections.observableArrayList();
	
	private Database database = new Database();
	
	private Character selectedCharacter;
	private Item selectedItem;
	
	@FXML
	private SplitPane canvas;
	
	@FXML 
	private TableView<Character> characterTable;
	@FXML
	private TableColumn<Character, String> nameColumn; 
	@FXML
	private TableColumn<Character, String> genderColumn;
	@FXML
	private TableColumn<Character, String> ageColumn;
	@FXML
	private TableColumn<Character, String> raceColumn;
	@FXML
	private TableColumn<Character, String> occupationColumn;
	
	@FXML 
	private TableView<Item> itemTable;
	@FXML
	private TableColumn<Item, String> itemColumn;
	@FXML
	private TableColumn<Item, String> locationColumn;
	@FXML
	private TableColumn<Item, String> ownerColumn;
	
	@FXML
	private TextField nameBox;
	
	@FXML
	private TextField genderBox;
	
	@FXML
	private TextField ageBox;
	
	@FXML
	private TextField raceBox;
	
	@FXML 
	private TextField occupationBox;
	
	@FXML
	private TextField itemBox;
	
	@FXML
	private TextField locationBox;
	
	private String CharacterID = "1";
	
	private String ItemID = "1";
	
	@FXML
	public void initialize() {
		
		nameColumn.setCellValueFactory(
				cellData -> cellData.getValue().getNameProperty());
		genderColumn.setCellValueFactory( 
				cellData -> cellData.getValue().getGenderProperty());
		ageColumn.setCellValueFactory(
				cellData -> cellData.getValue().getAgeProperty());
		raceColumn.setCellValueFactory( 
				cellData -> cellData.getValue().getRaceProperty());
		occupationColumn.setCellValueFactory(
				cellData -> cellData.getValue().getOccupationProperty());

		itemColumn.setCellValueFactory(
				cellData -> cellData.getValue().getNameProperty());
		locationColumn.setCellValueFactory( 
				cellData -> cellData.getValue().getLocationProperty());
		ownerColumn.setCellValueFactory(
				cellData -> cellData.getValue().getOwnerProperty());

		
		canvas.setOnKeyPressed(k -> handlePress(k.getCode()));
		
		characterTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> setSelectedCharacter(newValue));
		
		itemTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> setSelectedItem(newValue));
		
		try {
			database.load_db();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		characterTable.setItems(database.getCharacter_list());
		
		itemTable.setItems(database.getItems_list());
		
		
	}
	
	public void incCharacterID() {
		int charID = Integer.parseInt(CharacterID);
		charID++;
		CharacterID = Integer.toString(charID);
	}
	
	public void incItemID() {
		int itID = Integer.parseInt(ItemID);
		itID++;
		ItemID = Integer.toString(itID);
	}
	
	public void setSelectedCharacter(Character character) {
		if (character != null) {
			selectedCharacter = character;
		}
	}
	
	public void setSelectedItem(Item item) {
		if (item != null) {
			selectedItem = item;
		}
	}
	
	public void handlePress (KeyCode code) {
		
	}
	
	public void addCharacter() {
		if (!nameBox.getText().isEmpty()) {
			Character character = new Character(nameBox.getText(), 
					genderBox.getText(), ageBox.getText(), 
					raceBox.getText(), occupationBox.getText(), CharacterID);
			database.addChar(character);
			incCharacterID();
			nameBox.clear();
			genderBox.clear();
			ageBox.clear();
			raceBox.clear();
			occupationBox.clear();
		}
	}
}
