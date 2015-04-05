package AuthoringEnvironment;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import engine.gameobject.Editable;

public class EditorView {
	// This property and the Library's property would be bound
	ObjectProperty<Editable> property = new SimpleObjectProperty<>();

	// The EditorView had already been instantiated by when the program was
	// started. The setEditor() class will be called every time the property
	// above is changed, which means some other object was selected.
	public void setEditor() {
		EditorData editor = new EditorData(property.get());
		editor.showFields();
	}

	public void setGUI() {
		// this method will create in the GUI the textboxes and corresponding
		// fields, taken from the editor class
	}

	public static void main(String[] args) {
		ConcreteTower ct = new ConcreteTower(null, null, null);
		EditorView ev = new EditorView();
		ev.property.set(ct);
		ev.setEditor();
	}
}
