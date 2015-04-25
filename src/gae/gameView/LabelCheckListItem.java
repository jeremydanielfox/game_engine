package gae.gameView;

import engine.gameobject.labels.Label;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class LabelCheckListItem implements CheckListItem {
    private Label label;
    private CheckBox checkbox;

    public LabelCheckListItem (Label obj) {
        label = obj;
        checkbox = new CheckBox();
    }

    @Override
    public Node getNode () {
        HBox node = new HBox(10);
        Text text = new Text(label.getName());
        node.getChildren().addAll(text, checkbox);
        return node;
    }

    @Override
    public CheckBox getCheckBox () {
        return checkbox;
    }
    
    public Label getLabel () {
        return label;
    }

}
