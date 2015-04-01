package shop;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;


/**
 * Static utility that allows a node to be attached to the cursor (and thus move with it).
 * 
 * @author Nathan Prabhu
 *
 */

public class CursorBinder {

    private static double previousX = 0;
    private static double previousY = 0;

    /**
     * Binds the cursor to the node. Binding will be disabled, and the node will be removed from the
     * scene, when the appropriate key is pressed.
     * 
     * @param node Node to be bound
     * @param scene Scene of the node
     * @param key Disabling key
     * @return
     */
    public static Node bindCursor (Node node, Scene scene, KeyCode key) {
        final Group wrapGroup = new Group(node);

        scene.setOnMouseMoved(mouseEvent -> {
            double currentX = mouseEvent.getSceneX() + CenterOffset.getX(node);
            double currentY = mouseEvent.getSceneY() + CenterOffset.getY(node);

            wrapGroup.relocate(currentX, currentY);

            previousX = currentX;
            previousY = currentY;
        });

        // TODO: figure out why keyPressed caller must be scene, and not pane or node
        // AND why node not deleted if deleted while on flowpane
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == key) {
                System.out.println("triggered");
                scene.setOnMouseMoved(mouseEvent -> {
                });
                ((Group) node.getParent()).getChildren().remove(node);
            }
        });

        return wrapGroup;
    }
}
