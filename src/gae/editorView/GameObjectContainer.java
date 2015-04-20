package gae.editorView;

import engine.gameobject.PointSimple;
import gae.gridView.ContainerWrapper;
import javafx.geometry.Point2D;
import javafx.scene.control.ScrollPane;


public class GameObjectContainer extends ScrollPane implements ContainerWrapper {
    public GameObjectContainer () {
        
    }

    /**
     * Important method that checks if the object's coordinate is on the grid
     */
    public boolean checkBounds (double x, double y) {
        Point2D point = this.screenToLocal(x, y);
        System.out.println("X IS : " + point.getX());
        System.out.println("Y IS : " + y);
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
}
