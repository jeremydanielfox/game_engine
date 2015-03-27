package AuthoringEnvironment;

import gameobject.Editable;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This LibraryView class visualizes the back-end Library class.
 * 
 * @author Kei & Nina
 *
 */
public class LibraryView {
	// USE CASE : USER SELECTS ALREADY-CREATED OBJECT FROM A PANE. THE PARAMETER
	// EDITOR PANE SHOWS UP ON THE SIDE.
	// This class is the first class in this use case. This class would check
	// for a click event and then search through the observable list of Editable
	// objects to find the specific one that was clicked.

	BorderPane pane = new BorderPane();
	ComboBox<Editable> box = new ComboBox<>();

	public void setObservableList(ObservableList<Editable> list) {
		box.setItems(list);
		Stage s = new Stage();
		BorderPane pane = new BorderPane();
		Scene scene = new Scene(pane);
		pane.setCenter(box);
		s.setScene(scene);
	}


}
