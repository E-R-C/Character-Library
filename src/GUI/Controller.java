package GUI;

import java.sql.SQLException;

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
	
	private String CharacterID;
	
	private String ItemID;
	
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
		
		try {
			CharacterID = database.getMaxcID();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ItemID = database.getMaxiID();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		incCharacterID();
		incItemID();
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
			try {
				database.addChar(character);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			incCharacterID();
			nameBox.clear();
			genderBox.clear();
			ageBox.clear();
			raceBox.clear();
			occupationBox.clear();
		}
	}
	
	public void addItem() {
		if (!itemBox.getText().isEmpty()) {
			Item item = new Item(itemBox.getText(), locationBox.getText(), "None", 
					"0", ItemID);
			try {
				database.addItem(item);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			incItemID();
			itemBox.clear();
			locationBox.clear();
		}
	}
	
	public void editCharacter() {
		if (!nameBox.getText().isEmpty() 
				&& database.getCharacter_list().contains(selectedCharacter)) {
			selectedCharacter.setName(nameBox.getText());
			selectedCharacter.setGender(genderBox.getText());
			selectedCharacter.setAge(ageBox.getText());
			selectedCharacter.setRace(raceBox.getText());
			selectedCharacter.setOccupation(occupationBox.getText());
		}
	}
	
	public void editItem() {
		if (!itemBox.getText().isEmpty() 
				&& database.getItems_list().contains(selectedItem)) {
			selectedItem.setName(itemBox.getText());
			selectedItem.setLocation(locationBox.getText());
		}
	}
	
	public void deleteCharacter() {
		if (database.getCharacter_list().contains(selectedCharacter)) {
			try {
				database.deleteChar(selectedCharacter);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void deleteItem() {
		if (database.getItems_list().contains(selectedItem)) {
			try {
				database.deleteItem(selectedItem);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void assignOwner() {
		if (database.getCharacter_list().contains(selectedCharacter)
				&& database.getItems_list().contains(selectedItem)) {
			selectedItem.setOwner(selectedCharacter.getName());
			selectedItem.setoID(selectedCharacter.getcID());
		}
	}
	
}
