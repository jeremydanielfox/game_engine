package usecases.usecase_GAE1;

import engine.gameobject.Editable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class EditorView {
	// This property and the Library's property would be bound
	ObjectProperty<Editable> property = new SimpleObjectProperty<>();

	// The EditorView had already been instantiated by when the program was
	// started. The setEditor() class will be called every time the property
	// above is changed, which means some other object was selected.
	public void setEditor() {
		Editor editor = new Editor(property.get());
		editor.showFields();
	}

	public void setGUI() {
		// this method will create in the GUI the textboxes and corresponding
		// fields, taken from the editor class
	}
}
