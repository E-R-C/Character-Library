package GUI;

import java.awt.Event;
import java.sql.SQLException;
import java.time.LocalDate;

import Data.Item;
import Data.Character;
import Data.Circumstance;
import Database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class Controller {
	
	private Database database = new Database();
	
	private Character selectedCharacter;
	private Item selectedItem;
	private Circumstance selectedEvent;
	
	private State mode = State.NONE;
	
	@FXML
	private SplitPane canvas;
	
	@FXML
	private AnchorPane charCanvas;
	
	@FXML
	private AnchorPane itemCanvas;
	
	@FXML
	private AnchorPane eventCanvas;
	
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
	private TableView<Circumstance> eventTable;
	@FXML
	private TableColumn<Circumstance, String> eventColumn;
	@FXML 
	private TableColumn<Circumstance, String> dateColumn;
	@FXML
	private TableColumn<Circumstance, String> subjectColumn;
	
	@FXML
	private GridPane characterGrid;
	@FXML
	private GridPane itemGrid;
	@FXML
	private GridPane eventGrid;
	
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
	
	@FXML
	private TextField eventBox;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private TextField characterFilter;
	
	@FXML 
	private TextField characterLabel;
	
	@FXML
	private TextField itemFilter;
	
	@FXML
	private TextField itemLabel;
	
	@FXML
	private TextField eventFilter;
	
	@FXML
	private TextField eventLabel;
	
	private String CharacterID;
	
	private String ItemID;
	
	private String EventID;
	
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

		eventColumn.setCellValueFactory(
				cellData -> cellData.getValue().getNameProperty());
		dateColumn.setCellValueFactory(
				cellData -> cellData.getValue().getDateProperty());
		subjectColumn.setCellValueFactory(
				cellData -> cellData.getValue().getSubjectProperty());
		
		canvas.setOnKeyPressed(k -> handlePress(k.getCode()));
		
		charCanvas.setOnKeyPressed(k -> handleCharPress(k.getCode()));

		itemCanvas.setOnKeyPressed(k -> handleItemPress(k.getCode()));
		
		eventCanvas.setOnKeyPressed(k -> handleEventPress(k.getCode()));
		
		characterTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> setSelectedCharacter(newValue));
		
		itemTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> setSelectedItem(newValue));
		
		eventTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> setSelectedEvent(newValue));
		
		try {
			database.load_db();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		characterTable.setItems(database.getCharacter_list());
		
		itemTable.setItems(database.getItem_list());
		
		eventTable.setItems(database.getEvent_list());
		
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
		
		try {
			EventID = database.getMaxeID();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		incCharacterID();
		incItemID();
		incEventID();
		
		clearCharLabels();
		clearItemLabels();
		clearEventLabels();
	}
	
	public void displayCharLabels() {
		if (characterGrid.getChildren().isEmpty()) {
			characterGrid.add(nameBox, 0, 0);
			characterGrid.add(genderBox, 1, 0);
			characterGrid.add(ageBox, 2, 0);
			characterGrid.add(raceBox, 3, 0);
			characterGrid.add(occupationBox, 4, 0);
		}
	}
	
	public void displayItemLabels() {
		if (itemGrid.getChildren().isEmpty()) {
			itemGrid.add(itemBox, 0, 0);
			itemGrid.add(locationBox, 1, 0);
		}
	}
	
	public void displayEventLabels() {
		if (eventGrid.getChildren().isEmpty()) {
			eventGrid.add(eventBox, 0, 0);
			eventGrid.add(datePicker, 1, 0);
		}
	}
	
	public void clearCharLabels() {
		characterGrid.getChildren().clear();
		nameBox.clear();
		genderBox.clear();
		ageBox.clear();
		raceBox.clear();
		occupationBox.clear();
	}
	
	public void clearItemLabels() {
		itemGrid.getChildren().clear();
		itemBox.clear();
		locationBox.clear();
	}
	
	public void clearEventLabels() {
		eventGrid.getChildren().clear();
		eventBox.clear();
		datePicker.setValue(null);
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
	
	public void incEventID() {
		int evID = Integer.parseInt(EventID);
		evID++;
		EventID = Integer.toString(evID);
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
	
	public void setSelectedEvent(Circumstance event) {
		if (event != null) {
			selectedEvent = event;
		}
	}
	
	public void handlePress (KeyCode code) {
		if (code == KeyCode.ESCAPE) {
			escapeAction();
		}
	}
	
	public void handleCharPress(KeyCode code) {
		if (code == KeyCode.ENTER) {
			if (mode == State.ADD_CHAR) {
				addCharacter();
			}			
			if (mode == State.EDIT_CHAR) {
				editCharacter();
			}
		}
	}
	
	public void handleItemPress(KeyCode code) {
		if (code == KeyCode.ENTER) {
			if (mode == State.ADD_ITEM) {
				addItem();
			}			
			if (mode == State.EDIT_ITEM) {
				editItem();
			}
		}
	}		
	
	public void handleEventPress(KeyCode code) {
		if (code == KeyCode.ENTER) {
			if (mode == State.ADD_EVENT) {
				addEvent();
			}			
			if (mode == State.EDIT_EVENT) {
				editEvent();
			}
		}
	}
	
	public void eventBoxFocus() {
		eventBox.requestFocus();
	}
	
	public String parseDatePicker() {
		if (datePicker.getValue() != null) {
			return datePicker.getValue().toString();
		}
		
		else {
			return "Unknown";
		}
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
			mode = State.NONE;
			incCharacterID();
			clearCharLabels();
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
			mode = State.NONE;
			incItemID();
			clearItemLabels();
		}
	}
	
	public void addEvent() {
		if (!eventBox.getText().isEmpty()) {
			Circumstance event = new Circumstance(eventBox.getText(), 
					parseDatePicker(), "None", "0", EventID);
			try {
				database.addEvent(event);
				System.out.println("Event Added");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mode = State.NONE;
			incEventID();
			clearEventLabels();
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
			try {
				database.updateChar(selectedCharacter);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clearCharLabels();
			mode = State.NONE;
		}
	}
	
	public void editItem() {
		if (!itemBox.getText().isEmpty() 
				&& database.getItem_list().contains(selectedItem)) {
			selectedItem.setName(itemBox.getText());
			selectedItem.setLocation(locationBox.getText());
			try {
				database.updateItem(selectedItem);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clearItemLabels();
			mode = State.NONE;
		}
	}
	
	public void editEvent() {
		if (!eventBox.getText().isEmpty() 
				&& database.getEvent_list().contains(selectedEvent)) {
			selectedEvent.setName(eventBox.getText());
			selectedEvent.setDate(parseDatePicker());
			try {
				database.updateEvent(selectedEvent);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clearEventLabels();
			mode = State.NONE;
		}
	}
	
	public void deleteCharacter() {
		if (database.getCharacter_list().contains(selectedCharacter)) {
			try {
				database.deleteChar(selectedCharacter);
				escapeAction();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void deleteItem() {
		if (database.getItem_list().contains(selectedItem)) {
			try {
				database.deleteItem(selectedItem);
				escapeAction();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void deleteEvent() {
		if (database.getEvent_list().contains(selectedEvent)) {
			try {
				database.deleteEvent(selectedEvent);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addCharacterDisplay() {
		clearCharLabels();
		displayCharLabels();
		mode = State.ADD_CHAR;
	}
	
	public void editCharacterDisplay() {
		if (database.getCharacter_list().contains(selectedCharacter)) {
			clearCharLabels();
			displayCharLabels();
			nameBox.setText(selectedCharacter.getName());
			genderBox.setText(selectedCharacter.getGender());
			ageBox.setText(selectedCharacter.getAge());
			raceBox.setText(selectedCharacter.getRace());
			occupationBox.setText(selectedCharacter.getOccupation());
			mode = State.EDIT_CHAR;
		}
	}
	
	public void addItemDisplay() {
		clearItemLabels();
		displayItemLabels();
		mode = State.ADD_ITEM;
	}
	
	public void editItemDisplay() {
		if (database.getItem_list().contains(selectedItem)) {
			clearItemLabels();
			displayItemLabels();
			itemBox.setText(selectedItem.getName());
			locationBox.setText(selectedItem.getLocation());
			mode = State.EDIT_ITEM;
		}
	}
	
	public void addEventDisplay() {
		clearEventLabels();
		displayEventLabels();
		mode = State.ADD_EVENT;
		System.out.println("Event Displayed");
	}
	
	public void editEventDisplay() {
		if (database.getEvent_list().contains(selectedEvent)) {
			clearEventLabels();
			displayEventLabels();
			eventBox.setText(selectedEvent.getName());
			mode = State.EDIT_EVENT;
		}
	}
	
	public void assignOwner() {
		if (database.getCharacter_list().contains(selectedCharacter)
				&& database.getItem_list().contains(selectedItem)) {
			selectedItem.setOwner(selectedCharacter.getName());
			selectedItem.setoID(selectedCharacter.getcID());
			try {
				database.updateItem(selectedItem);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void removeOwner() {
		if (database.getItem_list().contains(selectedItem)) {
			selectedItem.setOwner("None");
			selectedItem.setoID("0");
			try {
				database.updateItem(selectedItem);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void assignSubject() {
		if (database.getCharacter_list().contains(selectedCharacter) 
				&& database.getEvent_list().contains(selectedEvent)) {
			selectedEvent.setSubject(selectedCharacter.getName());
			selectedEvent.setsID(selectedCharacter.getcID());
			try {
				database.updateEvent(selectedEvent);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void removeSubject() {
		if (database.getEvent_list().contains(selectedEvent)) {
			selectedEvent.setSubject("None");
			selectedEvent.setsID("0");
			try {
				database.updateEvent(selectedEvent);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void filterCharacters() {
		if (!characterLabel.getText().isEmpty()
				&& !characterFilter.getText().isEmpty()) {
			try {
				database.queryChar(characterLabel.getText(), 
						characterFilter.getText());
				characterTable.setItems(database.getFilter_Character());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		else {
			characterTable.setItems(database.getCharacter_list());
		}
	}

	public void filterItems() {
		if (!itemLabel.getText().isEmpty()
				&& !itemFilter.getText().isEmpty()) {
			try {
				database.queryitem(itemLabel.getText(), 
						itemFilter.getText());
				itemTable.setItems(database.getFilter_Items());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		else {
			itemTable.setItems(database.getItem_list());
		}		
	}
	
	public void filterEvents() {
		if (!eventLabel.getText().isEmpty()
				&& !eventFilter.getText().isEmpty()) {
			try {
				database.queryEvent(eventLabel.getText(), 
						eventFilter.getText());
				eventTable.setItems(database.getFilter_Event());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else {
			eventTable.setItems(database.getEvent_list());
		}
	}
	
	public void displayInventory() {
		if (database.getCharacter_list().contains(selectedCharacter)) {
			try {
				database.queryitem("CharacterID", selectedCharacter.getcID());
				itemTable.setItems(database.getFilter_Items());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void displayEvents() {
		if (database.getCharacter_list().contains(selectedCharacter)) {
			try {
				database.queryitem("CharacterID", selectedCharacter.getcID());
				eventTable.setItems(database.getFilter_Event());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
		}
	}
	
	public void clearCharFilter() {
		characterTable.setItems(database.getCharacter_list());
	}
	
	public void clearItemFilter() {
		itemTable.setItems(database.getItem_list());
	}
	
	public void clearEventFilter() {
		eventTable.setItems(database.getEvent_list());
	}
	
	public void escapeAction() {
		mode = State.NONE;
		clearCharLabels();
		clearItemLabels();
		clearEventLabels();
	}
	
}
