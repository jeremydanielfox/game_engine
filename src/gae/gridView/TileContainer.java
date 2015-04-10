package gae.gridView;

import java.util.ArrayList;
import java.util.List;
import exception.ObjectOutOfBoundsException;
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
public class TileContainer extends Region implements ContainerWrapper {
    public static final double SCREEN_HEIGHT =
            Screen.getPrimary().getVisualBounds().getHeight() - 150;
    public static final double SCREEN_WIDTH =
            Screen.getPrimary().getVisualBounds().getWidth() - 120;
    private BorderPane border;
    private List<TileView> tileList = new ArrayList<>();
    private Rectangle selectionRect = getNewRectangle();
    private ContextMenu contextmenu;
    private boolean showMenu;

    public TileContainer (int size, BorderPane border) {
        this.border = border;
        addTiles(size);
        addSelectionBox();
        this.getChildren().add(selectionRect);
    }

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

    public boolean checkBounds (double x, double y) {
        Point2D point = this.screenToLocal(x, y);
        if (point.getX() < 0 || point.getX() > this.getWidth() || y < 0 ||
            y > this.getHeight()) {
            return true;
        }
        return false;
    }

    @Override
    public Point2D convertCoordinates (double x, double y) {
        Point2D point = this.screenToLocal(x, y);
        return new Point2D(point.getX(), y);
    }

    private void addSelectionBox () {
        DoubleProperty rectinitX = new SimpleDoubleProperty();
        DoubleProperty rectinitY = new SimpleDoubleProperty();
        DoubleProperty rectX = new SimpleDoubleProperty();
        DoubleProperty rectY = new SimpleDoubleProperty();
        contextmenu = createContextMenu();
        selectionRect.widthProperty().bind(rectX.subtract(rectinitX));
        selectionRect.heightProperty().bind(rectY.subtract(rectinitY));
        this.setOnMousePressed(e -> {
            selectionRect.setX(e.getX());
            selectionRect.setY(e.getY());
            rectinitX.set(e.getX());
            rectinitY.set(e.getY());
            contextmenu = createContextMenu();
        });
        this.setOnMouseDragged(e -> {
            selectionRect.setVisible(true);
            rectX.set(e.getX());
            rectY.set(e.getY());
            showMenu = true;
        });
        this.setOnMouseReleased(e -> {
            if (showMenu) {
                contextmenu.show(this, e.getScreenX(), e.getScreenY());
                showMenu = false;
            }
        });
    }

    private Rectangle getNewRectangle () {
        Rectangle rect = new Rectangle();
        rect.setFill(Color.web("blue", 0.1));
        rect.setStroke(Color.BLUE);
        return rect;
    }

    private ContextMenu createContextMenu () {
        ContextMenu cmenu = new ContextMenu();
        MenuItem walkable = new MenuItem("Make Walkable");
        walkable.setOnAction(e -> {
            tileList.stream().forEach(tile -> tile.handleSelected(selectionRect, true));
            selectionRect.setVisible(false);
            ;
        });
        MenuItem unwalkable = new MenuItem("Make Unwalkable");
        unwalkable.setOnAction(e -> {
            tileList.stream().forEach(tile -> tile.handleSelected(selectionRect, false));
            selectionRect.setVisible(false);
        });
        cmenu.getItems().addAll(walkable, unwalkable);
        return cmenu;

    }

}
