package gae.editorView;

import gae.gridView.ContainerWrapper;
import gae.listView.DraggableUtilities;
import gae.listView.LibraryData;
import gae.listView.ListViewUtilities;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;


public class GenericObjectList {
    private ObservableList<Object> createdSpecificObjects;
    private Node node;
    private Group root;
    private Scene scene;

    public GenericObjectList (Class<?> klass, Node node, Group root, Scene scene) {
        this.node = node;
        this.root = root;
        this.scene = scene;
        createdSpecificObjects = LibraryData.getInstance().getObservableList(klass);
    }

    public TitledPane getTitledPane (String classType) {
        TitledPane titledPane = new TitledPane();
        titledPane.setText(classType);
        titledPane.setContent(ListViewUtilities.createGenericList(createdSpecificObjects));
        Label label = new Label("Create New " + classType);
        titledPane.setOnMouseClicked(me -> {
            if (me.isSecondaryButtonDown()) {
            }
            ContextMenu contextmenu = new ContextMenu();
            MenuItem item = new MenuItem("New");
            item.setOnAction(ae -> {
                DraggableUtilities.makeEditablePlaceable(me, label, node, createdSpecificObjects,
                                                         (ContainerWrapper) node, root);
            });

            contextmenu.getItems().add(item);
            contextmenu.show(titledPane, me.getSceneX(), me.getSceneY());

        });
        return titledPane;
    }
}
