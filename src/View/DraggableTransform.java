package View;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * Utility that allows a node to be draggable
 * @author Nathan Prabhu
 *
 */
public class DraggableTransform {

    private static final boolean dragModeActiveProperty = true;

    public static Node makeDraggable (final Node node) {
        final DragContext dragContext = new DragContext();
        final Group wrapGroup = new Group(node);

        // disable mouse events for all children
        wrapGroup.addEventHandler(MouseEvent.ANY, mouseEvent -> {
            if (dragModeActiveProperty) {
                mouseEvent.consume();
            }
        });

        // remember initial mouse cursor coordinates
        // and node position
        wrapGroup.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            if (dragModeActiveProperty) {

                dragContext.mouseAnchorX = mouseEvent.getX();
                dragContext.mouseAnchorY = mouseEvent.getY();
                dragContext.initialTranslateX = node.getTranslateX();
                dragContext.initialTranslateY = node.getTranslateY();
            }
        });

        // shift node from its initial position by delta
        // calculated from mouse cursor movement
        wrapGroup.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouseEvent -> {
            if (dragModeActiveProperty) {

                node.setTranslateX(dragContext.initialTranslateX
                                   + mouseEvent.getX() -
                                   dragContext.mouseAnchorX);
                node.setTranslateY(dragContext.initialTranslateY
                                   + mouseEvent.getY() -
                                   dragContext.mouseAnchorY);
            }
        });

        return wrapGroup;
    }
    
    public static Node attachToCursor(final Node node){
        final DragContext dragContext = new DragContext();
        final Group wrapGroup = new Group(node);
        
        // disable mouse events for all children
        wrapGroup.addEventHandler(MouseEvent.ANY, mouseEvent -> {
            if (dragModeActiveProperty) {
                mouseEvent.consume();
            }
        });

        // remember initial mouse cursor coordinates
        // and node position
        wrapGroup.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            if (dragModeActiveProperty) {
                dragContext.initialTranslateX = node.getTranslateX();
                dragContext.initialTranslateY = node.getTranslateY();
                dragContext.mouseAnchorX = mouseEvent.getX();
                dragContext.mouseAnchorY = mouseEvent.getY();
                
                node.setTranslateX(dragContext.initialTranslateX
                                   + mouseEvent.getX() -
                                   dragContext.mouseAnchorX);
                node.setTranslateY(dragContext.initialTranslateY
                                   + mouseEvent.getY() -
                                   dragContext.mouseAnchorY);
            }
        });       
        return wrapGroup;      
    }

    private static final class DragContext {
        public double mouseAnchorX;
        public double mouseAnchorY;
        public double initialTranslateX;
        public double initialTranslateY;
    }
}
