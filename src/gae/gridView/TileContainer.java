package gae.gridView;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Region;
import engine.gameobject.PointSimple;


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
    private List<TileView> tileList = new ArrayList<>();
    private SelectionRectangle selectionRect;
    private DoubleProperty gridWidthProperty = new SimpleDoubleProperty(623);
    private DoubleProperty gridHeightProperty = new SimpleDoubleProperty(623);
    private String selectionColor;

    public TileContainer (ObjectProperty<Dimension> sizeProp, Scene scene, String color) {
        selectionColor = color;
        double maxHeight = scene.getHeight() - TAB_HEIGHT;
        addTiles(sizeProp.get(), maxHeight);
        sizeProp.addListener( (observable, oldVal, newVal) -> {
            removeTiles();
            addTiles(newVal, maxHeight);
        });
        selectionRect = new SelectionRectangle(this, createContextMenu());
        getChildren().add(selectionRect);
        scene.heightProperty().addListener( (p, o, n) -> {
            if (tileList != null) {
                for (TileView view : tileList) {
                    getChildren().remove(view);
                }
            }
            gridHeightProperty.set(scene.getHeight() - TAB_HEIGHT);
            addTiles(sizeProp.getValue(), gridHeightProperty.get());
        });
    }

    /**
     * Adds the tiles and binds the height to the pane's height.
     * Currently hard-coded to a square but should be able to change.
     *
     * @param size
     */
    private void addTiles (Dimension size, double height) {
        double length =
                size.getWidth() < size.getHeight() ? height / size.getHeight() : height /
                                                                                 size.getWidth();
        for (int i = 0; i < size.getHeight(); i++) {
            for (int j = 0; j < size.getWidth(); j++) {
                TileView tileView = new TileView(length, new TileData(i, j), selectionColor);
                tileView.setLayoutX(i * length);
                tileView.setLayoutY(j * length);
                getChildren().add(tileView);
                tileList.add(tileView);
                gridWidthProperty.set(i * length + length);
                gridHeightProperty.set(j * length + length);
            }

        }
    }

    /**
     * Important method that checks if the object's coordinate is on the grid
     */
    @Override
    public boolean checkBounds (double x, double y) {
        Point2D point = this.screenToLocal(x, y);
        System.out.println("X IS : " + point.getX());
        System.out.println("Y IS : " + y);
        if (point.getX() < 0 || point.getX() > getWidth() || y < 0 ||
            y > getHeight()) {
            return true;
        }
        return false;
    }

    /**
     * Important method that converts other coordinate systems to that relative to the grid
     */
    @Override
    public PointSimple convertCoordinates (double x, double y) {
        Point2D point = this.screenToLocal(x, y);
        return new PointSimple(point.getX(), y);
    }

    public DoubleProperty getGridHeightProperty () {
        // return new SimpleDoubleProperty(maxHeight);
        return gridHeightProperty;
    }

    public DoubleProperty getGridWidthProperty () {
        // return new SimpleDoubleProperty(maxHeight);
        return gridWidthProperty;
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

    private void removeTiles () {
        if (tileList != null) {
            for (TileView view : tileList) {
                getChildren().remove(view);
            }
        }
    }

    public List<Point> getUnwalkableTiles () {
        List<Point> unwalkableTiles = new ArrayList<>();
        tileList.stream().forEach(tile -> {
            if (!tile.getData().getWalkableProperty().get()) {
                unwalkableTiles.add(tile.getData().getGridLocation());
            }
        });
        return unwalkableTiles;
    }

}
