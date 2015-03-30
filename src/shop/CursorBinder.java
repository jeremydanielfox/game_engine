package shop;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

/**
 * Decorator intended to allow a node to be attached to the cursor (and thus move with it).
 * @author Nathan Prabhu
 *
 */

public class CursorBinder extends Parent {
    
    private Node node;
    
    public CursorBinder(Node node){
        this.node = node;
        execute(node);
    }

    private final BooleanProperty dragModeActiveProperty =
            new SimpleBooleanProperty(this, "dragModeActive", true);

    public void execute (Node node) {
        final DragContext dragContext = new DragContext();
        final Group wrapGroup = new Group(node);
        
        node.addEventHandler(MouseEvent.ANY, mouseEvent -> {
            if (dragModeActiveProperty.get()) {
                mouseEvent.consume();
            }
        });

        // remember initial mouse cursor coordinates
        // and node position
//        node.setOnMousePressed(mouseEvent -> {
//            dragModeActiveProperty.set(!dragModeActiveProperty.get());
//            node.relocate(mouseEvent.getX(), mouseEvent.getY());
//            //node.setTranslateX(mouseEvent.getX());
//            dragContext.mouseAnchorX = mouseEvent.getX();
//            dragContext.mouseAnchorY = mouseEvent.getY();
//            
//            dragContext.initialTranslateX = node.getTranslateX();
//            dragContext.initialTranslateY = node.getTranslateY();
//        });
        
        node.setOnMouseMoved(mouseEvent -> {
            if (dragModeActiveProperty.get()) {
                node.setTranslateX(dragContext.initialTranslateX
                                   + mouseEvent.getX() -
                                   dragContext.mouseAnchorX);
                node.setTranslateY(dragContext.initialTranslateY
                                   + mouseEvent.getY() -
                                   dragContext.mouseAnchorY);
            }
        });

    }
    
    public void doOnMousePressed(MouseEvent mouseEvent){
        dragModeActiveProperty.set(!dragModeActiveProperty.get());
        node.relocate(mouseEvent.getX(), mouseEvent.getY());
        System.out.println(String.format("Mouse Coords: (%f, %f)", mouseEvent.getX(), mouseEvent.getY()));
        System.out.println(String.format("Node Coords: (%f, %f)", node.getLayoutX(), node.getLayoutY()));
        getChildren().add(node);
        //node.setTranslateX(mouseEvent.getX());
//        dragContext.mouseAnchorX = mouseEvent.getX();
//        dragContext.mouseAnchorY = mouseEvent.getY();
//        
//        dragContext.initialTranslateX = node.getTranslateX();
//        dragContext.initialTranslateY = node.getTranslateY();
    }

    private static final class DragContext {
        public double mouseAnchorX;
        public double mouseAnchorY;
        public double initialTranslateX;
        public double initialTranslateY;
    }
}
