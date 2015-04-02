package shop;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;


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
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == key) {
                scene.setOnMouseMoved(mouseEvent -> {
                });
                wrapGroup.setVisible(false);
                //((Group) node.getParent()).getChildren().remove(node);
            }
        });

        return wrapGroup;
    }
    
    private static void redrawScene(Node node){
        List<Node> children = node.getScene().getRoot().getChildrenUnmodifiable();
        Pane pane = (Pane) node.getScene().getRoot();
        pane.getChildren().removeAll(children);
        pane.getChildren().addAll(children);
    }
}
