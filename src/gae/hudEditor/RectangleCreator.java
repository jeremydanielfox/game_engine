package gae.hudEditor;
import java.util.Arrays;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


/**
 * Class taken from http://stackoverflow.com/questions/26298873/resizable-and-movable-rectangle
 * 
 * Need to improve design
 * 
 * @author !JohnGilhuly
 *
 */

public class RectangleCreator {

    public Rectangle createDraggableRectangle (double x, double y, double size) {
        final double handleRadius = 10;

        Rectangle rect = new Rectangle(x, y, size, size);

        // bottom right resize handle:
        Circle resizeHandleSE = new Circle(handleRadius, Color.GOLD);
        // bind to bottom right corner of Rectangle:
        resizeHandleSE.centerXProperty().bind(rect.xProperty().add(rect.widthProperty()));
        resizeHandleSE.centerYProperty().bind(rect.yProperty().add(rect.heightProperty()));

        // move handle:
        Circle moveHandle = new Circle(handleRadius, Color.GOLD);
        // bind to bottom center of Rectangle:
        moveHandle.centerXProperty().bind(rect.xProperty().add(rect.widthProperty().divide(2)));
        moveHandle.centerYProperty().bind(rect.yProperty().add(rect.heightProperty()));

        // force circles to live in same parent as rectangle:
        standardizeParentNodes(rect, resizeHandleSE, moveHandle);

        Wrapper<Point2D> mouseLocation = new Wrapper<>();

        setUpDragging(resizeHandleSE, mouseLocation);
        setUpDragging(moveHandle, mouseLocation);

        resizeHandleSE.setOnMouseDragged(e ->
                resizeRectangle(handleRadius, rect, mouseLocation, e));

        moveHandle.setOnMouseDragged(e ->
                moveRectangle(handleRadius, rect, mouseLocation, e));
        
        return rect;
    }

    private void moveRectangle (final double handleRadius,
                                Rectangle rect,
                                Wrapper<Point2D> mouseLocation,
                                MouseEvent event) {
        if (mouseLocation.value != null) {
            double deltaX = event.getSceneX() - mouseLocation.value.getX();
            double deltaY = event.getSceneY() - mouseLocation.value.getY();
            double newX = rect.getX() + deltaX;
            double newMaxX = newX + rect.getWidth();
            if (newX >= handleRadius
                && newMaxX <= rect.getParent().getBoundsInLocal().getWidth() - handleRadius) {
                rect.setX(newX);
            }
            double newY = rect.getY() + deltaY;
            double newMaxY = newY + rect.getHeight();
            if (newY >= handleRadius
                && newMaxY <= rect.getParent().getBoundsInLocal().getHeight() - handleRadius) {
                rect.setY(newY);
            }
            mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
        }
    }

    private void resizeRectangle (final double handleRadius,
                                  Rectangle rect,
                                  Wrapper<Point2D> mouseLocation,
                                  MouseEvent event) {
        if (mouseLocation.value != null) {
            double deltaX = event.getSceneX() - mouseLocation.value.getX();
            double deltaY = event.getSceneY() - mouseLocation.value.getY();
            double newMaxX = rect.getX() + rect.getWidth() + deltaX;
            if (newMaxX >= rect.getX()
                && newMaxX <= rect.getParent().getBoundsInLocal().getWidth() - handleRadius) {
                rect.setWidth(rect.getWidth() + deltaX);
            }
            double newMaxY = rect.getY() + rect.getHeight() + deltaY;
            if (newMaxY >= rect.getY()
                && newMaxY <= rect.getParent().getBoundsInLocal().getHeight() - handleRadius) {
                rect.setHeight(rect.getHeight() + deltaY);
            }
            mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
        }
    }

    private void standardizeParentNodes (Rectangle rect, Circle resizeHandleSE, Circle moveHandle) {
        rect.parentProperty().addListener( (obs, oldParent, newParent) -> {
            for (Circle c : Arrays.asList(resizeHandleSE, moveHandle)) {
                Pane currentParent = (Pane) c.getParent();
                if (currentParent != null) {
                    currentParent.getChildren().remove(c);
                }
                ((Pane) newParent).getChildren().add(c);
            }
        });
    }

    private void setUpDragging (Circle circle, Wrapper<Point2D> mouseLocation) {

        circle.setOnDragDetected(event -> {
            circle.getParent().setCursor(Cursor.CLOSED_HAND);
            mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
        });

        circle.setOnMouseReleased(event -> {
            circle.getParent().setCursor(Cursor.DEFAULT);
            mouseLocation.value = null;
        });
    }

    static class Wrapper<T> {
        T value;
    }

}
