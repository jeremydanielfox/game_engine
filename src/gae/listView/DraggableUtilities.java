package gae.listView;

import engine.gameobject.PointSimple;
import exception.ObjectOutOfBoundsException;
import gae.backend.Placeable;
import gae.editor.ObjectComponentEditor;
import gae.editorView.DragIntoRectangle;
import gae.editorView.DraggableFields;
import gae.editorView.DraggableItem;
import gae.gridView.ContainerWrapper;
import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import View.ViewUtil;


public class DraggableUtilities {
    /**
     * places the node on the location clicked. When the location is clicked, a new instance of the
     * object is added to a list and a MovableImage is placed on the location.
     *
     * @param me: MouseEvent to bind the cursor to the EditableNode
     * @param editablenode: The EditableNode
     * @param node: Node that will be clicked on
     * @param instanceList: List of Editables
     * @param wrapper: The object which determines the bounds of where the object can be placed
     * @param root: where the Image will be placed
     */
    public static void makeEditablePlaceable (MouseEvent me,
                                              Authorable authorable,
                                              Node node,
                                              ObservableList<Authorable> instanceList,
                                              ContainerWrapper wrapper,
                                              Group root) {
        Placeable editable = (Placeable) authorable;
        ImageView transitionImage = editable.getImageView();
        // TODO: implement popup error when overlapping - collision detection
        Node binder =
                ViewUtil.bindCursor(transitionImage,
                                    node,
                                    ViewUtil
                                            .getMouseLocation(me, transitionImage),
                                    KeyCode.Q, false);
        binder.setOnMouseClicked(ev -> {
            Point2D current =
                    binder.localToParent(new Point2D(binder.getTranslateX(), binder
                            .getTranslateY()));
            Double currentX = current.getX();
            Double currentY = current.getY();
            if (wrapper.checkBounds(currentX, currentY)) {
                throw new ObjectOutOfBoundsException();
            }

            Placeable newEditable = editable.makeNewInstance();
            instanceList.add(newEditable);
            PointSimple relativeLocation = wrapper.convertCoordinates(currentX, currentY);
            newEditable.setLocation(relativeLocation);
            MovableImage edimage =
                    new MovableImage(editable.getImageView(), newEditable, wrapper);
            newEditable.setMovableImage(edimage);
            edimage.relocate(currentX, currentY);
            root.getChildren().add(edimage);
        });
        root.getChildren().add(binder);
    }

    /**
     * places the node on the location clicked. When the location is clicked, a new instance of the
     * object is added to a list and a MovableImage is placed on the location.
     *
     * @param me: MouseEvent to bind the cursor to the EditableNode
     * @param editablenode: The EditableNode
     * @param node: Node that will be clicked on
     * @param instanceList: List of Editables
     * @param wrapper: The object which determines the bounds of where the object can be placed
     * @param root: where the Image will be placed
     */
    public static void makeObjectPlaceable (MouseEvent me,
                                            DraggableItem placeable,
                                            Node node,
                                            ObservableList<Object> instanceList,
                                            ContainerWrapper wrapper,
                                            Group root,
                                            ObjectComponentEditor editor,
                                            BooleanProperty unclicked) {
        // TODO: figure out how to clone
        Node binder =
                ViewUtil.bindCursor(placeable,
                                    node,
                                    ViewUtil
                                            .getMouseLocation(me, placeable),
                                    KeyCode.Q, false);
        binder.setOnMouseClicked(ev -> {
            DraggableItem clone = placeable.getNewInstance();
            Point2D current =
                    binder.localToParent(new Point2D(binder.getTranslateX(), binder
                            .getTranslateY()));
            Double currentX = current.getX();
            Double currentY = current.getY();
            if (wrapper.checkBounds(currentX, currentY)) {
                throw new ObjectOutOfBoundsException();
            }
//            event.handle(ev);
            // PointSimple relativeLocation = wrapper.convertCoordinates(currentX, currentY);
            //
            // placeable.setTranslateX(relativeLocation.getX());
            // placeable.setTranslateY(relativeLocation.getY());
            editor.setObject(placeable.getDraggedObject());
            clone.setTranslateX(currentX);
            clone.setTranslateY(currentY);
            root.getChildren().add(clone);
            ViewUtil.unbindCursor(node, placeable);
            unclicked.setValue(false);
        });
        root.getChildren().add(binder);
    }
}
