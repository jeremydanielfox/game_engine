package gae.editor;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

public abstract class PropertyEditor {
    
    private HBox editorBox;
    private String label;
    
    public PropertyEditor (String s) {
        label = s;
    }
    
    public Node getPropertyEditor() {
        return editorBox;
    }
}
