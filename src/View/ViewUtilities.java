package View;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;


public class ViewUtilities {

    private static Point2D previous;

    /**
     * Binds the cursor to the node. Binding will be disabled, and the node will be removed from the
     * scene, when the appropriate key is pressed.
     * 
     * @param node Node to be bound
     * @param scene Scene of the node
     * @param initial Initial position
     * @param key Disabling key
     * @return
     */
    public static Node bindCursor (Node node, Node scene, Point2D initial, KeyCode key) {
        final Group wrapGroup = new Group(node);
        wrapGroup.relocate(initial.getX(), initial.getY());
        scene.setOnMouseMoved(mouseEvent -> {
            Point2D current = getMouseLocation(mouseEvent, node);
            wrapGroup.relocate(current.getX(), current.getY());

            previous = current;
        });

        // TODO: figure out why keyPressed caller must be scene, and not pane or node
        // EDIT: I changed this to node and it worked perfectly for me! 
        scene.getScene().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == key) {
                unbindCursor(scene);
                wrapGroup.setVisible(false);
                // ((Group) node.getParent()).getChildren().remove(node);
            }
        });

        return wrapGroup;
    }
    
    public static void unbindCursor(Node scene) {
        scene.setOnMouseMoved(mouseEvent -> {
        });
    }

    /**
     * Determines the normalized ratio (0 to 1) of width and height within a parent
     * container from local pixel coordinates
     * 
     * @param mouseEvent Triggering MouseEvent
     * @param parent Container
     * @return
     */
    public static Point2D normalizePixels (MouseEvent mouseEvent, Parent parent) {
        return normalizePixels(mouseEvent.getX(), mouseEvent.getY(), parent);
    }

    /**
     * Determines the normalized ratio (0 to 1) of width and height within a parent
     * container from local pixel coordinates
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param parent Container
     * @return
     */
    public static Point2D normalizePixels (double x, double y, Parent parent) {
        return new Point2D(x / parent.getBoundsInLocal().getWidth(),
                           y / parent.getBoundsInLocal().getHeight());
    }

    /**
     * Returns the global (scene-wide) mouseLocation triggered by a MouseEvent
     * on a node
     * 
     * @param mouseEvent MouseEvent
     * @param node Node
     * @return
     */
    public static Point2D getMouseLocation (MouseEvent mouseEvent, Node node) {
        return new Point2D(mouseEvent.getX() + getCenterOffSetX(node),
                           mouseEvent.getY() + getCenterOffSetY(node));
    }
    
    /**
     * Used to find the center of a node. Gets the X offset.
     * 
     * @param node Node
     * @return
     */
    public static double getCenterOffSetX (Node node) {
        return -node.getBoundsInLocal().getWidth() / 2;
    }

    /**
     * Used to find the center of a node. Gets the X offset.
     * 
     * @param node Node
     * @return
     */
    public static double getCenterOffSetY (Node node) {
        return -node.getBoundsInLocal().getHeight() / 2;
    }
}
