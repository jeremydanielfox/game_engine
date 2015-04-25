package gae.gameView;

import engine.gameobject.Graphic;
import gae.backend.Placeable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * Uses builder pattern to create customizable checklistitems with only desired information from the
 * placeable
 *
 * @author Nina Sun
 *
 */
public class PlaceableCheckListItem implements CheckListItem {
    private String name;
    private Graphic image;
    private String description;
    private Double value;
    private Placeable placeable;

    @Override
    public Node getNode () {
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Label(name), new Text(description),
                                  new Text("Price: " + value));
        hbox.getChildren().addAll(image.getNode(), vbox);
        return null;
    }

    public PlaceableCheckListItem (Builder builder) {
        name = builder.name;
        image = builder.image;
        description = builder.description;
        value = builder.value;
        placeable = builder.placeable;
    }

    public static class Builder {
        private String name = null;
        private Graphic image = null;
        private String description = null;
        private Double value = null;
        private Placeable placeable = null;

        public Builder setName (String input) {
            name = input;
            return this;
        }

        public Builder setImage (Graphic graphic) {
            image = graphic;
            return this;
        }

        public Builder setDescription (String desc) {
            description = desc;
            return this;
        }

        public Builder setValue (double price) {
            value = price;
            return this;
        }
    }

}
