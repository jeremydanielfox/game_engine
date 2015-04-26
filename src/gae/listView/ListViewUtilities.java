package gae.listView;

import gae.backend.Placeable;
import gae.editorView.DraggableFields;
import gae.editorView.GameObjectInformation;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;


/**
 * ListView utility class made to create lists and cells with icons
 *
 * @author Kei and Nina
 *
 */
public class ListViewUtilities {

    public static final int THUMBNAIL_SIZE_HORIZONTAL = 20;
    public static final int THUMBNAIL_SIZE_VERTICAL = 200;

    /**
     * able to create a cell with a label and icon
     *
     * @param edit
     * @return
     */
    public static Node createCellContentWithIcon (Authorable authorable) {
        if (authorable.getType().equals("Image")) { // image Case
            DraggableFields container = (DraggableFields) authorable;
            ImageView image = container.getImageView();
            image.setFitHeight(THUMBNAIL_SIZE_VERTICAL);
            image.setPreserveRatio(true);
            return image;
        }
        else { // otherwise
            HBox content = new HBox();
            ImageView image = new ImageView(authorable.getImagePath());
            image.setFitHeight(THUMBNAIL_SIZE_HORIZONTAL);
            image.setPreserveRatio(true);
            content.getChildren().addAll(image,
                                         new Label(authorable.getName() + authorable.getID()));
            return content;
        }
    }

    public static void setEditableSelection (ListView<Authorable> list,
                                             ObservableList<Authorable> authorables,
                                             Scene scene) {
        try {
            list.setOnMousePressed(e -> {
                if (e.getClickCount() == 1) {
                    Placeable selected = (Placeable) list.getSelectionModel().getSelectedItem();
                    selected.getMovableImage().selectEditableImage();
                    scene.setOnKeyPressed(keyEvent -> {
                        if (keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
                            authorables.remove(selected);
                            selected.getMovableImage().deleteImage();
                        }
                    });
                    for (Authorable authorable : list.getItems()) {
                        Placeable editable = (Placeable) authorable;
                        if (editable != selected)
                            editable.getMovableImage().unselectEditableImage();
                    }
                }
                    else if (e.getClickCount() == 2) {
                        Placeable selected = (Placeable) list.getSelectionModel().getSelectedItem();
                        selected.getMovableImage().unselectEditableImage();
                        list.getSelectionModel().clearSelection();
                    }
                });
        }
        catch (NullPointerException e) {
        }
    }

    /**
     * creates a ListView given an observable list of Editables, with specific properties, such as
     * deleting objects and highlighting selected objects
     *
     * @param editables
     * @param scene
     * @return
     */
    public static ListView<Authorable> createList (ObservableList<Authorable> authorables,
                                                   Scene scene,
                                                   String type) {
        ListView<Authorable> list = listCreatingHelper(authorables);
        if (type.equals("Editable")) {
            setEditableSelection(list, authorables, scene);
        }
        return list;
    }

    /**
     * creates a ListView given an observable list of Authorables
     *
     * @param editables
     * @param scene
     * @return
     */
    public static ListView<Authorable> listCreatingHelper (ObservableList<Authorable> authorables) {

        ListView<Authorable> list = new ListView<>();
        list.setPrefWidth(200);
        list.setItems(authorables);

        list.setCellFactory( (myList) -> {
            return new ListCell<Authorable>() {
                @Override
                protected void updateItem (Authorable object, boolean bln) {
                    super.updateItem(object, bln);
                    if (bln) {
                        setText(null);
                        setGraphic(null);
                    }
                    else if (object != null) {
                        setGraphic(ListViewUtilities.createCellContentWithIcon(object));
                    }
                }
            };
        });

        return list;
    }

    /**
     * creates a ListView given an observable list of Objects
     *
     * @param editables
     * @param scene
     * @return
     */
    public static ListView<?> createGenericList (ObservableList<Object> objects,
                                                 String classType) {
        ListView<Object> list = new ListView<>();
        list.setPrefWidth(200);
        list.setItems(objects);

        list.setCellFactory( (myList) -> {
            return new ListCell<Object>() {
                @Override
                protected void updateItem (Object object, boolean bln) {
                    super.updateItem(object, bln);
                    if (bln) {
                        setText(null);
                        setGraphic(null);
                    }
                    else if (object != null) {
                        setText(GameObjectInformation.getInstance().getTitle(object));
                        // setGraphic(ListViewUtilities.createCellContentWithIcon(object));
                    }
                }
            };
        });

        return list;
    }
}
