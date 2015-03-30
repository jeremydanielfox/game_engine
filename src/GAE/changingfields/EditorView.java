package GAE.changingfields;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gameobject.Editable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditorView {
	// This property and the Library's property would be bound
	private Editable editable;
	private HashMap<String, String[]> fieldMap;
	private Stage s;

	public EditorView(Editable editable, Stage s) {
		this.editable = editable;
		fieldMap = ResourceBundleUtil.useResourceBundle();
		this.s = s;
		printFields();
	}

	public void setGUI() {
		List<String> labelList = new ArrayList<>();
		List<TextField> textfieldList = new ArrayList<>();
		for (Field f : getFields()) {
			labelList.add(f.getName());
		}
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20, 0, 20, 20));
		gridPane.setHgap(7);
		gridPane.setVgap(7);

		for (int i = 0; i < labelList.size(); i++) {
			TextField field = new TextField();
			gridPane.add(new Label(labelList.get(i)), 0, i);
			gridPane.add(field, 1, i);
			textfieldList.add(field);
		}
		Button change = new Button("Change");
		change.setOnAction(e -> changeFields(textfieldList));
		gridPane.add(change, 1, labelList.size());
		Scene scene = new Scene(gridPane);
		s.setTitle("Hello World!");
		s.setScene(scene);
		s.show();
	}

	private void changeFields(List<TextField> textfieldList) {
		for (int i = 0; i < textfieldList.size(); i++) {
			try {
				getFields().get(i).set(editable,
						Integer.parseInt(textfieldList.get(i).getText()));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		printFields();
	}

	private List<Field> getFields() {
		Class<?> editableClass = editable.getClass();
		List<Field> list = new ArrayList<>();
		for (String fieldName : fieldMap.get(editable.getClass()
				.getSimpleName())) {
			try {
				Field field = editableClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				// Object value = field.get(editable);
				list.add(field);
			} catch (NoSuchFieldException | SecurityException
					| IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	private void printFields() {
		Class<?> editableClass = editable.getClass();
		for (String fieldName : fieldMap.get(editable.getClass()
				.getSimpleName())) {
			try {
				Field field = editableClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				Object value = field.get(editable);
				System.out.println("Field name is: " + field.getName());
				System.out.println("Value is: " + value);
			} catch (NoSuchFieldException | SecurityException
					| IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	// public void setGUI() {
	// // this method will create in the GUI the textboxes and corresponding
	// // fields, taken from the editor class
	// ListView<String> simpleList = new ListView<>(
	// FXCollections.observableArrayList("Item1", "Item2", "Item3",
	// "Item4"));
	// simpleList.setEditable(true);
	//
	// simpleList.setCellFactory(TextFieldListCell.forListView());
	//
	// simpleList
	// .setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
	// @Override
	// public void handle(ListView.EditEvent<String> t) {
	// simpleList.getItems()
	// .set(t.getIndex(), t.getNewValue());
	// System.out.println("setOnEditCommit");
	// }
	//
	// });
	//
	// simpleList
	// .setOnEditCancel(new EventHandler<ListView.EditEvent<String>>() {
	// @Override
	// public void handle(ListView.EditEvent<String> t) {
	// System.out.println("setOnEditCancel");
	// }
	// });
	//
	// BorderPane root = BorderPaneBuilder.create().center(simpleList).build();
	// Scene scene = new Scene(root, 300, 250);
	//
	// s.setTitle("Hello World!");
	// s.setScene(scene);
	// s.show();
	// }

}
