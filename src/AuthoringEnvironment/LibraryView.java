package AuthoringEnvironment;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.Editable;
import javafx.scene.control.Button;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

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

	private BorderPane pane = new BorderPane();
	private ComboBox<Editable> box = new ComboBox<>();

	private Stage s;

	public LibraryView(Stage s) {
		this.s = s;
	}

	public void setObservableList(ObservableList<Editable> list) {
		
		
		System.out.println(box.getOnMouseClicked());
		BorderPane pane = new BorderPane();
		Scene scene = new Scene(pane);
		pane.setCenter(box);
		s.setScene(scene);
	}

	private HBox temp() {
		HBox hbox = new HBox();
		Button a = new Button("A");
		Button b = new Button("B");
		hbox.getChildren().addAll(a, b);
		return hbox;
	}

}
