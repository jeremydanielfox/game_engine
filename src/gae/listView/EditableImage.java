package gae.listView;


import exception.ObjectOutOfBoundsException;
import gae.backend.Editable;
import gae.gridView.ContainerWrapper;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import View.ViewUtilities;


public class EditableImage extends Region {
    private final Group wrapGroup;
    private Editable editable;
    private ContainerWrapper wrapper;
    private double startX;
    private double startY;

    public EditableImage (ImageView placedEditableImage, Editable editable, ContainerWrapper wrapper) {
        this.editable = editable;
        this.wrapper = wrapper;
        wrapGroup = new Group(placedEditableImage);
        this.getChildren().add(wrapGroup);
        enableDrag();
    }

    private void enableDrag () {
        setOnMousePressed(e -> {
            Point2D current = ViewUtilities.getMouseLocation(e, wrapGroup);
            startX = current.getX();
            startY = current.getY();
        });
        setOnMouseDragged(e -> {
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
            editable.setLocation(abs.getX(), abs.getY());
            setOnMouseReleased(location -> {
                if (wrapper.checkBounds(abs.getX(), abs.getY())) {
                    wrapGroup.setTranslateX(startX);
                    wrapGroup.setTranslateY(startY);
                    throw new ObjectOutOfBoundsException();
                }
            });
        });

    }

    public boolean checkIntersect (Node object) {
        return this.getBoundsInParent().intersects(object.getBoundsInParent());
    }

    public void selectEditableImage () {
        wrapGroup.setEffect(new Glow(1));
    }

    public void unselectEditableImage () {
        wrapGroup.setEffect(null);
    }

    public void deleteImage () {
        this.getChildren().remove(wrapGroup);
    }
}
