package gae.gridView;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * The view of the tile and its properties
 *
 * @author Kei & Nina
 *
 */
public class TileView extends Group {
    private TileData data;
    private Rectangle rect;
    private BooleanProperty walkableProperty = new SimpleBooleanProperty();
    private String color;

    public TileView (double size, TileData data, String color) {
        this.data = data;
        this.color = color;
        walkableProperty.bind(data.getWalkableProperty());
        rect = new Rectangle(size, size, Color.TRANSPARENT);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(1);
        getChildren().addAll(rect);
        setUp(rect);
    }

    private void setUp (Rectangle rect) {
        setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                data.changeState();
            }
        });
        setProperty(walkableProperty);
    }

    private void setProperty (BooleanProperty property) {
        property.addListener( (observable, oldValue, newValue) -> {
            if (!newValue) {
                rect.setFill(Color.web(color, .5));
            }
                else {
                    rect.setFill(Color.TRANSPARENT);
                }
            });
    }

    public void handleSelected (Node node, boolean walkable) {
        if (getBoundsInParent().intersects(node.getBoundsInParent())) {
            data.setWalkable(walkable);
        }
    }

    public TileData getData () {
        return data;
    }
}
