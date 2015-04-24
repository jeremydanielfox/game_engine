package gae.editorView;

import engine.gameobject.PointSimple;
import gae.gridView.ContainerWrapper;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class GameObjectContainer extends VBox implements ContainerWrapper {
    private double width;
    private String[] settable = { "Weapon", "Health", "Mover" };
    private List<DragIntoRectangle> rectangleList;
    private Scene scene;

    public GameObjectContainer (double width, double height, Scene scene) {
        rectangleList = new ArrayList<>();
        setPrefSize(width, height);
        this.width = width;
        this.scene = scene;
        setSpacing(100);
        getChildren().add(getLabel());
        getChildren().add(addRectangles());
    }

    private Label getLabel () {
        Label title = new Label("My Properties");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        title.setTranslateX(width / 2);
        return title;
    }

    private HBox addRectangles () {
        HBox hbox = new HBox();
        setSpacing(width / 10);
        System.out.println("total width is : " + width);
        for (int i = 1; i <= 3; i++) {
            DragIntoRectangle rect = new DragIntoRectangle(width, settable[i - 1], scene);
            rect.setTranslateX((3 * i - 2) * width / 10);
            hbox.getChildren().add(rect);
            rectangleList.add(rect);
        }
        return hbox;
    }

    public List<DragIntoRectangle> getRectangles () {
        return rectangleList;
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
}
