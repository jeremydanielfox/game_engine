package gae.gridView;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * The rectangle that will appear upon dragging the mouse to select an area, then show a menu
 * upon completion of the drag
 * 
 * @author Nina
 *
 */
public class SelectionRectangle extends Rectangle {
    private ContextMenu contextmenu;
    private boolean showMenu;
    private TileContainer parent;

    /**
     * 
     * @param parent node that hosts selection rectangle
     * @param context menu that shows up upon completion of drag
     */
    public SelectionRectangle (TileContainer node, ContextMenu cm) {
        parent = node;
        contextmenu = cm;
        this.setFill(Color.web("blue", 0.1));
        this.setStroke(Color.BLUE);
        this.setVisible(false);
        setMouseEvents();
    }

    /**
     * Sets up mouse handling for drag
     */
    private void setMouseEvents () {
        DoubleProperty rectinitX = new SimpleDoubleProperty();
        DoubleProperty rectinitY = new SimpleDoubleProperty();
        DoubleProperty rectX = new SimpleDoubleProperty();
        DoubleProperty rectY = new SimpleDoubleProperty();

        this.layoutXProperty().bind(findTranslation(rectinitX, rectX));
        this.layoutYProperty().bind(findTranslation(rectinitY, rectY));
        this.widthProperty().bind(correctNegativeProperty(rectinitX, rectX));
        this.heightProperty().bind(correctNegativeProperty(rectinitY, rectY));

        parent.setOnMousePressed(e -> {
            this.setX(e.getX());
            this.setY(e.getY());
            rectinitX.set(e.getX());
            rectinitY.set(e.getY());
        });

        parent.setOnMouseDragged(e -> {
            setVisible(true);
            double width = parent.getGridWidthProperty().get();
            double height = parent.getGridHeightProperty().get();
            rectX.set((e.getX() > 0 && e.getX() < width) ? e.getX() : width *
                                                                      Math.round(e.getX() / width));
            rectY.set((e.getY() > 0 && e.getY() < height) ? e.getY()
                                                         : height * Math.round(e.getY() / height));
            showMenu = true;
        });
        parent.setOnMouseReleased(e -> {
            contextmenu.hide();
            if (showMenu) {
                contextmenu.show(this, e.getScreenX(), e.getScreenY());
                showMenu = false;
            }
        });
    }

    /**
     * Corrects for the anchor point of JavaFX node when using the mouse to select upper left of
     * initial point
     * 
     * @param starting location
     * @param ending location
     */
    private DoubleBinding findTranslation (DoubleProperty start, DoubleProperty end) {
        DoubleBinding onlyNegative = new DoubleBinding() {
            {
                super.bind(start, end);
            }

            @Override
            protected double computeValue () {
                double diff = end.get() - start.get();
                return diff > 0 ? 0 : diff;
            }
        };
        return onlyNegative;
    }

    /**
     * Finds absolute value of the difference of two properties to set the height and width
     * properties
     * 
     * @param starting location
     * @param ending location
     */

    private DoubleBinding correctNegativeProperty (DoubleProperty start, DoubleProperty end) {
        DoubleBinding absDifference = new DoubleBinding() {
            {
                super.bind(start, end);
            }

            @Override
            protected double computeValue () {
                return Math.abs(end.get() - start.get());
            }
        };
        return absDifference;
    }
}
