package gae.gridView;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.PointSimple;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;


/*
 * https://www.youtube.com/watch?v=ag8U6sUptsY
 * Want to base it on this ^^^^^
 */
/**
 * Creates the tile objects and gives it the ability to see if other objects are on the grid. Also
 * able to select multiple tiles at once.
 * 
 * @author Kei & Nina
 *
 */
public class TileContainer extends Region implements ContainerWrapper {
    public static final double SCREEN_HEIGHT =
            Screen.getPrimary().getVisualBounds().getHeight() - 150;
    public static final double SCREEN_WIDTH =
            Screen.getPrimary().getVisualBounds().getWidth() - 120;
    private BorderPane border;
    private List<TileView> tileList = new ArrayList<>();
    private SelectionRectangle selectionRect;
    private DoubleProperty gridWidth = new SimpleDoubleProperty(SCREEN_HEIGHT);
    private DoubleProperty gridHeight = new SimpleDoubleProperty(SCREEN_HEIGHT);

    public TileContainer (int size, BorderPane border) {
        this.border = border;
        addTiles(size);
        selectionRect = new SelectionRectangle(this, createContextMenu());
        this.getChildren().add(selectionRect);
    }

    /**
     * Adds the tiles and binds the height to the pane's height.
     * Currently hard-coded to a square but should be able to change.
     * 
     * @param size
     */
    private void addTiles (int size) {
        double length = SCREEN_HEIGHT / size;
        for (double i = 0; Math.round(i) < SCREEN_HEIGHT; i += SCREEN_HEIGHT / size) {
            for (double j = 0; Math.round(j) < SCREEN_HEIGHT; j += SCREEN_HEIGHT / size) {
                TileView tileView =
                        new TileView(length, new TileData(i / length, j / length));
                tileView.setLayoutX(i);
                tileView.setLayoutY(j);
                this.getChildren().add(tileView);
                tileList.add(tileView);
            }
        }
        border.prefHeightProperty().bind(this.heightProperty());
        // this.setMaxWidth(SCREEN_HEIGHT);
        // this.setMaxHeight(SCREEN_HEIGHT);

    }

    /**
     * Important method that checks if the object's coordinate is on the grid
     */
    public boolean checkBounds (double x, double y) {
        Point2D point = this.screenToLocal(x, y);
        if (point.getX() < 0 || point.getX() > this.getWidth() || y < 0 ||
            y > this.getHeight()) {
            return true;
        }
        return false;
    }

    /**
     * Important method that converts other coordinate systems to that relative to the grid
     */
    public PointSimple convertCoordinates (double x, double y) {
        Point2D point = this.screenToLocal(x, y);
        return new PointSimple(point.getX(), y);
    }

    public DoubleProperty getGridWidthProperty () {
        return gridWidth;
    }

    public DoubleProperty getGridHeightProperty () {
        return gridHeight;
    }

    /**
     * Creates context menu at the end of a drag selection to set walkable property
     * 
     * @return context menu
     */
    private ContextMenu createContextMenu () {
        ContextMenu cmenu = new ContextMenu();
        cmenu.getItems().addAll(createMenuItem("Make Walkable", true),
                                createMenuItem("Make Unwalkable", false));
        return cmenu;
    }

    /**
     * Creates menu items to set whether a tile is walkable
     * 
     * @return menu item
     */
    private MenuItem createMenuItem (String name, boolean walkable) {
        MenuItem item = new MenuItem(name);
        item.setOnAction(e -> {
            tileList.stream().forEach(tile -> tile.handleSelected(selectionRect, walkable));
            selectionRect.setVisible(false);
        });
        return item;
    }

}
