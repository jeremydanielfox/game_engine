package gae.gameView;

import engine.gameobject.labels.Type;
import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


/**
 * CheckListItem that is created with a game engine label to be added to a LabelCheckList
 *
 * @author Nina Sun
 */
public class LabelCheckListItem implements CheckListItem {
    private Type label;
    private CheckBox checkbox;

    public LabelCheckListItem (Type obj) {
        label = obj;
        checkbox = new CheckBox();
    }

    /**
     * Returns an HBox with the name of the label and a checkbox
     * 
     * @return hbox
     */
    @Override
    public Node getNode () {
        HBox node = new HBox(10);
        Text text = new Text(label.getName());
        node.getChildren().addAll(text, checkbox);
        return node;
    }

    @Override
    public BooleanProperty getSelectedProperty () {
        return checkbox.selectedProperty();
    }

    /**
     * Returns the label that the item holds
     * 
     * @return label
     */
    public Type getLabel () {
        return label;
    }

}
