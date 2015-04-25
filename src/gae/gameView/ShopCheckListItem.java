package gae.gameView;

import engine.gameobject.Graphic;
import gae.backend.Placeable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class ShopCheckListItem implements CheckListItem {
    private Placeable placeable;
    private CheckBox checkbox;

    public ShopCheckListItem (Placeable obj) {
        placeable = obj;
        checkbox = new CheckBox();
    }

    public Node getNode () {
        HBox node = new HBox(10);
        Graphic graphic = placeable.getTag().getGraphic().clone();
        graphic.setHeight(50);
        Node image = graphic.getResizedGraphic(1);
        Label label = new Label(placeable.getName());
        node.getChildren().addAll(image, label, checkbox);
        return node;
    }

    public Placeable getPlaceable () {
        return placeable;
    }

    public CheckBox getCheckBox () {
        return checkbox;
    }

}
