package gae.listView;

import java.util.List;
import java.util.Map;
import gae.backend.Editable;
import View.ViewUtilities;
import exception.ObjectOutOfBoundsException;
import gae.backend.Editable;
import gae.gridView.ContainerWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;


/**
 * Abstract class to be extended to all Editable object's lists
 * 
 * @author Kei & Nina
 *
 */
public abstract class PaneList {
    public static final int THUMBNAIL_SIZE = 20;

    // private List<EditableImage> imageList;
    /**
     * Initializes the specific pane class
     * 
     * @param root
     * @param node
     * @param scene
     * @param wrapper
     * @return
     */
    public abstract TitledPane initialize (Group root,
                                           Node node,
                                           Scene scene,
                                           ContainerWrapper wrapper,
                                           ObservableList<EditableNode> observableList);

    public abstract void removeRoot ();

    public abstract void addRoot ();

    /**
     * method created to make a simple TitledPane with a text
     * 
     * @param text
     * @return
     */
    protected TitledPane getTitledPane (String text) {
        TitledPane pane = new TitledPane();
        pane.setText(text);
        pane.setTextFill(Color.RED);
        return pane;
    }

    /**
     * sets up the Accordion given the pane. The pane in this case is a generic object (Tower,
     * Enemy, etc) and the Accordion keeps track of created objects (FireTower, IceEnemy, etc.)
     * 
     * @param pane
     * @return
     */
    protected ObservableList<TitledPane> setAccordion (TitledPane pane) {
        Accordion accordion = new Accordion();
        pane.setContent(accordion);
        return accordion.getPanes();
    }

    protected void setUpObservableList (ObservableList<TitledPane> paneList,
                                        ObservableList<EditableNode> observableList,
                                        String type,
                                        Map<EditableNode, ObservableList<Editable>> map,
                                        Group root,
                                        Node node,
                                        Scene scene,
                                        ContainerWrapper wrapper) {

        for (EditableNode previousNode : observableList) {
            setUpNewInstanceList(paneList, previousNode, map, root, node, scene, wrapper, type);
        }
        observableList.addListener(new ListChangeListener<EditableNode>() {
            public void onChanged (javafx.collections.ListChangeListener.Change<? extends EditableNode> change) {
                while (change.next()) {
                    if (change.wasAdded()) { // if an editablenode was added
                        EditableNode added = (EditableNode) change.getAddedSubList().get(0);
                        setUpNewInstanceList(paneList, added, map, root, node, scene, wrapper, type);
                    }
                }
            }
        });

    }

    private void setUpNewInstanceList (ObservableList<TitledPane> paneList,
                                       EditableNode editableNode,
                                       Map<EditableNode, ObservableList<Editable>> map,
                                       Group root,
                                       Node node,
                                       Scene scene,
                                       ContainerWrapper wrapper,
                                       String type) {
        if (editableNode.getType().equals(type)) {
            ObservableList<Editable> instanceList =
                    FXCollections.observableArrayList();
            map.put(editableNode, instanceList);
            TitledPane newPane =
                    setTitledPaneClick(editableNode, instanceList, root, node, scene,
                                       wrapper);
            paneList.add(newPane);
        }
    }

    /**
     * sets up the clicking of the TitledPane such that one can instantiate an object by right
     * clicking the object. Also deals with binding the image to the cursor
     * 
     * @param node
     * @param root
     * @param pane
     * @param scene
     * @param wrapper
     * @return
     */
    protected TitledPane setTitledPaneClick (EditableNode node,
                                             ObservableList<Editable> instanceList,
                                             Group root,
                                             Node pane,
                                             Scene scene,
                                             ContainerWrapper wrapper) {
        TitledPane newPane = new TitledPane();
        newPane.setText(node.getName());
        newPane.setContent(ListViewUtilities.createList(instanceList, scene));
        newPane.setOnMousePressed(me -> {
            if (me.isSecondaryButtonDown()) {
                ContextMenu contextmenu = new ContextMenu();
                MenuItem item = new MenuItem("New");
                item.setOnAction(ae -> {
                    ImageView transitionImage = node.getImageView();
                    // TODO: implement popup error when overlapping - collision detection
                    Node binder =
                            ViewUtilities.bindCursor(transitionImage,
                                                     pane,
                                                     ViewUtilities
                                                             .getMouseLocation(me, transitionImage),
                                                     KeyCode.ESCAPE);
                    makeNodePlaceable(binder, node, root, wrapper, instanceList);
                    root.getChildren().add(binder);

                });
                contextmenu.getItems().add(item);
                contextmenu.show(newPane, me.getSceneX(), me.getSceneY());

            }
        });
        return newPane;
    }

    /**
     * places the node on the location clicked. When the location is clicked, a new instance of the
     * object is added to a list and a MovableImage is placed on the location.
     * 
     * @param binder
     * @param node
     * @param root
     * @param wrapper
     */
    private void makeNodePlaceable (Node binder,
                                    EditableNode node,
                                    Group root,
                                    ContainerWrapper wrapper, ObservableList<Editable> instanceList) {
        binder.setOnMouseClicked(ev -> {
            Point2D current =
                    binder.localToParent(new Point2D(binder.getTranslateX(), binder
                            .getTranslateY()));
            Double currentX = current.getX();
            Double currentY = current.getY();
            if (wrapper.checkBounds(currentX, currentY))
                throw new ObjectOutOfBoundsException();

            Editable newEditable = node.makeNewInstance();
            instanceList.add(newEditable);
            newEditable.setLocation(currentX, currentY);
            MovableImage edimage = new MovableImage(node.getImageView(), newEditable, wrapper);
            newEditable.setMovableImage(edimage);
            edimage.relocate(currentX, currentY);
            // for (EditableImage image : imageList) {
            // if (edimage.checkIntersect(image)) {
            // System.out.println("intersecting");
            // }
            // }
            root.getChildren().add(edimage);
            // imageList.add(edimage);

            // for (Editable editables : editableList) {
            // System.out.println("X IS : " + editables.getLocation().getX());
            // System.out.println("Y IS : " + editables.getLocation().getY());
            // System.out.println();
            // }

        });
    }

}
