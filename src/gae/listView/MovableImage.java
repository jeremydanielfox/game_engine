package gae.listView;

import gae.backend.Editable;
import View.ViewUtilities;
import engine.gameobject.PointSimple;
import exception.ObjectOutOfBoundsException;
import gae.gridView.ContainerWrapper;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;


/**
 * Any placed instances of an object is a MovableImage (stored in EditableNode)
 * 
 * @author Kei
 *
 */
public class MovableImage extends Region {
    private final Group wrapGroup;
    private Editable editable;
    private ContainerWrapper wrapper;
    private double startX;
    private double startY;
    private boolean selected;

    public MovableImage (ImageView placedEditableImage, Editable editable, ContainerWrapper wrapper) {
        this.editable = editable;
        this.wrapper = wrapper;
        wrapGroup = new Group(placedEditableImage);
        this.getChildren().add(wrapGroup);
        enableDrag();
    }

    /**
     * enables dragging of the object, while checking for out of bounds errors and only allowing it
     * to move if selected at the LibraryView
     */
    private void enableDrag () {
        setOnMousePressed(e -> {
            Point2D current = ViewUtilities.getMouseLocation(e, wrapGroup);
            startX = current.getX();
            startY = current.getY();
        });
        setOnMouseDragged(e -> {
            // TODO: fix glitch where I drag select other towers not selected
            if (selected) {
                Point2D current = ViewUtilities.getMouseLocation(e, wrapGroup);
                double newX = current.getX();
                double newY = current.getY();
                Point2D abs = localToParent(new Point2D(newX, newY));
                if (abs.getX() > 0 && abs.getY() < getScene().getWidth()) {
                    wrapGroup.setTranslateX(newX);
                }
                if (abs.getY() > 0 && abs.getY() < getScene().getHeight()) {
                    wrapGroup.setTranslateY(newY);
                }
                editable.setLocation(new PointSimple(abs.getX(), abs.getY()));
                setOnMouseReleased(location -> {
                    if (wrapper.checkBounds(abs.getX(), abs.getY())) {
                        wrapGroup.setTranslateX(startX);
                        wrapGroup.setTranslateY(startY);
                        throw new ObjectOutOfBoundsException();
                    }
                });
            }
        });

    }

    public boolean checkIntersect (Node object) {
        return this.getBoundsInParent().intersects(object.getBoundsInParent());
    }

    public void selectEditableImage () {
        selected = true;
        wrapGroup.setEffect(new Glow(1));
    }

    public void unselectEditableImage () {
        selected = false;
        wrapGroup.setEffect(null);
    }

    public void deleteImage () {
        this.getChildren().remove(wrapGroup);
    }
}
