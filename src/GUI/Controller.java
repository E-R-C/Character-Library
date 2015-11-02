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
}
