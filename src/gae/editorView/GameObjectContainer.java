package gae.editorView;

import engine.gameobject.PointSimple;
import gae.gridView.ContainerWrapper;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class GameObjectContainer extends VBox implements ContainerWrapper {
    private double width;
    private Scene scene;
    private static final double LABEL_LOCATION_PROPORTIONS = 0.45;

    public GameObjectContainer (double width,
                                double height,
                                Scene scene) {
        setPrefSize(width, height);
        this.width = width;
        this.scene = scene;
        setSpacing(100);
        getChildren().add(getLabel());
    }

    private Label getLabel () {
        Label title = new Label("My Properties");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        title.setTranslateX(width * LABEL_LOCATION_PROPORTIONS);
        return title;
    }

    public void clear () {
        this.getChildren().clear();
        getChildren().add(getLabel());
    }

    /**
     * Important method that checks if the object's coordinate is on the grid
     */
    @Override
    public boolean checkBounds (double x, double y) {
        // Point2D point = this.screenToLocal(x, y);
        // System.out.println("X IS : " + point.getX());
        // System.out.println("Y IS : " + y);
        if (x < 0 || x > getWidth() || y < 0 || y > getHeight()) {
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
