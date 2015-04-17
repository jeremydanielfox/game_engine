package gae.gridView;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.PointSimple;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
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
    private static final int TAB_HEIGHT = 128;
    private BorderPane border;
    private List<TileView> tileList = new ArrayList<>();
    private SelectionRectangle selectionRect;
    double height;

    public TileContainer (int size, Scene scene, BorderPane border) {
        this.border = border;
        height = scene.getHeight() - TAB_HEIGHT;
        System.out.println("HEIGHT IS :" + height);
        addTiles(size, height);
        selectionRect = new SelectionRectangle(this, createContextMenu());
        this.getChildren().add(selectionRect);
        scene.heightProperty().addListener( (p, o, n) -> {
            if (tileList != null) {
                for (TileView view : tileList) {
                    this.getChildren().remove(view);
                }
            }
            height = scene.getHeight() - TAB_HEIGHT;
            addTiles(size, height);
        });
    }

    /**
     * Adds the tiles and binds the height to the pane's height.
     * Currently hard-coded to a square but should be able to change.
     * 
     * @param size
     */
    private void addTiles (int size, double height) {
        double length = height / size;
        for (double i = 0; Math.round(i) < height; i += height / size) {
            for (double j = 0; Math.round(j) < height; j += height / size) {
                TileView tileView =
                        new TileView(length, new TileData(i / length, j / length));
                tileView.setLayoutX(i);
                tileView.setLayoutY(j);
                this.getChildren().add(tileView);
                tileList.add(tileView);
            }
        }
//        this.prefHeightProperty().bind(border.prefHeightProperty());
        // this.setMaxWidth(SCREEN_HEIGHT);
        // this.setMaxHeight(SCREEN_HEIGHT);

    }

    /**
     * Important method that checks if the object's coordinate is on the grid
     */
    public boolean checkBounds (double x, double y) {
        Point2D point = this.screenToLocal(x, y);
        System.out.println("THE WIDTH IS : " + this.getWidth());
        System.out.println("THE HEIGHT IS : " + this.getHeight());
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
        System.out.println("X IS : " + point.getX());
        System.out.println("Y IS : " + y);
        return new PointSimple(point.getX(), y);
    }

    public DoubleProperty getGridHeightProperty () {
        return new SimpleDoubleProperty(height);
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
