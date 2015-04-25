package gae.gameView;

import engine.gameobject.Graphic;
import gae.backend.Placeable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


/**
 * Creates a checklist item that holds a placeable to be added to a ShopCheckList
 * 
 * @author Nina Sun
 */
public class ShopCheckListItem implements CheckListItem {
    private Placeable placeable;
    private CheckBox checkbox;

    public ShopCheckListItem (Placeable obj) {
        placeable = obj;
        checkbox = new CheckBox();
    }

    /**
     * Returns the node to be added to the checklist, showing a thumbnail, name and checkbox
     * 
     * @author Nina Sun
     */
    public Node getNode () {
        HBox node = new HBox(10);
        Graphic graphic = placeable.getTag().getGraphic().clone();
        graphic.setHeight(50);
        Node image = graphic.getResizedGraphic(1);
        Label label = new Label(placeable.getName());
        node.getChildren().addAll(image, label, checkbox);
        return node;
    }

    public CheckBox getCheckBox () {
        return checkbox;
    }

    /**
     * Returns placeable that the item holds
     * 
     * @return list
     */
    public Placeable getPlaceable () {
        return placeable;
    }

}
