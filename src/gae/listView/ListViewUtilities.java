package gae.listView;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import gae.backend.Editable;


/**
 * ListView utility class made to create lists and cells with icons
 * 
 * @author Kei and Nina
 *
 */
public class ListViewUtilities {

    public static final int THUMBNAIL_SIZE = 20;

    /**
     * able to create a cell with a label and icon
     * 
     * @param edit
     * @return
     */
    public static Node createCellContentWithIcon (Editable edit) {
        HBox content = new HBox();
        ImageView image = new ImageView(edit.getImagePath());

        image.setFitHeight(THUMBNAIL_SIZE);
        image.setPreserveRatio(true);
        content.getChildren().addAll(image, new Label(edit.getName() + edit.getID()));
        return content;
    }

    /**
     * creates a ListView given an observable list of Editables, with specific properties, such as
     * deleting objects and highlighting selected objects
     * 
     * @param editables
     * @param scene
     * @return
     */
    public static Node createList (ObservableList<Editable> editables, Scene scene) {
        ListView<Editable> list = new ListView<>();
        list.setPrefWidth(200);
        list.setItems(editables);
        list.setOnMousePressed(e -> {
            if (e.getClickCount() == 1) {
                Editable selected = list.getSelectionModel().getSelectedItem();
                selected.getMovableImage().selectEditableImage();
                scene.setOnKeyPressed(keyEvent -> {
                    if (keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
                        editables.remove(selected);
                        selected.getMovableImage().deleteImage();
                    }
                });
                for (Editable editable : list.getItems()) {
                    if (editable != selected)
                        editable.getMovableImage().unselectEditableImage();
                }
            }
                else if (e.getClickCount() == 2) {
                    list.getSelectionModel().getSelectedItem().getMovableImage()
                            .unselectEditableImage();

                    list.getSelectionModel().clearSelection();
                }

            });
        list.setCellFactory((myList) -> {
            return new ListCell<Editable>() {
                    @Override
                    protected void updateItem (Editable edit, boolean bln) {
                        super.updateItem(edit, bln);
                        if (bln) {
                            setText(null);
                            setGraphic(null);
                        }
                        else if (edit != null) {
                            setGraphic(ListViewUtilities.createCellContentWithIcon(edit));
                        }
                    }
                };
        });

        return list;
    }
}
