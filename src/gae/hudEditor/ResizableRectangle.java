package gae.hudEditor;

import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ResizableRectangle {
    private Rectangle baseNode;
    
    public ResizableRectangle() {
        initialize();
    }
    
    private void initialize () {
        final double handleRadius = 10 ;

        baseNode = new Rectangle();
        
        // bottom right resize handle:
        Circle moveHandle = new Circle(handleRadius, Color.GOLD);
        // bind to bottom right corner of Rectangle:
        moveHandle.centerXProperty().bind(baseNode.xProperty().add(baseNode.widthProperty()));
        moveHandle.centerYProperty().bind(baseNode.yProperty().add(baseNode.heightProperty()));
        
        // force circles to live in same parent as rectangle:
        baseNode.parentProperty().addListener((obs, oldParent, newParent) -> {
                Pane currentParent = (Pane)moveHandle.getParent();
                if (currentParent != null) {
                    currentParent.getChildren().remove(moveHandle);
                }
                ((Pane)newParent).getChildren().add(moveHandle);
        });
        
        Wrapper<Point2D> mouseLocation = new Wrapper<>();
        setUpDragging(moveHandle, mouseLocation) ;
        
//        resizeHandle.setOnMouseDragged(event -> {
//            if (mouseLocation.value != null) {
//                double deltaX = event.getSceneX() - mouseLocation.value.getX();
//                double deltaY = event.getSceneY() - mouseLocation.value.getY();
//                double newMaxX = baseNode.getX() + baseNode.getWidth() + deltaX ;
//                if (newMaxX >= baseNode.getX() 
//                        && newMaxX <= baseNode.getParent().getBoundsInLocal().getWidth() - handleRadius) {
//                    baseNode.setWidth(baseNode.getWidth() + deltaX);
//                }
//                double newMaxY = baseNode.getY() + baseNode.getHeight() + deltaY ;
//                if (newMaxY >= baseNode.getY() 
//                        && newMaxY <= baseNode.getParent().getBoundsInLocal().getHeight() - handleRadius) {
//                    baseNode.setHeight(baseNode.getHeight() + deltaY);
//                }
//                mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
//            }
//        });
        
        moveHandle.setOnMouseDragged(event -> {
            if (mouseLocation.value != null) {
                double deltaX = event.getSceneX() - mouseLocation.value.getX();
                double deltaY = event.getSceneY() - mouseLocation.value.getY();
                double newX = baseNode.getX() + deltaX ;
                double newMaxX = newX + baseNode.getWidth();
                if (newX >= handleRadius 
                        && newMaxX <= baseNode.getParent().getBoundsInLocal().getWidth() - handleRadius) {
                    baseNode.setX(newX);
                }
                double newY = baseNode.getY() + deltaY ;
                double newMaxY = newY + baseNode.getHeight();
                if (newY >= handleRadius 
                        && newMaxY <= baseNode.getParent().getBoundsInLocal().getHeight() - handleRadius) {
                    baseNode.setY(newY);
                }
                mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
            }

        });
    }

    private void setUpDragging(Circle circle, Wrapper<Point2D> mouseLocation) {

        circle.setOnDragDetected(event -> {
            circle.getParent().setCursor(Cursor.CLOSED_HAND);
            mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
        });

        circle.setOnMouseReleased(event -> {
            circle.getParent().setCursor(Cursor.DEFAULT);
            mouseLocation.value = null ;
        });
    }

    static class Wrapper<T> { T value ; }

    public Rectangle getRectangle () {
        return baseNode;
    }
}
