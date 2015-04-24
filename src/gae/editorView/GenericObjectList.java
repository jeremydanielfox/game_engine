package gae.editorView;

import gae.editor.ObjectComponentEditor;
import gae.gridView.ContainerWrapper;
import gae.listView.DraggableUtilities;
import gae.listView.LibraryData;
import gae.listView.ListViewUtilities;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


public class GenericObjectList {
    private ObservableList<Object> createdSpecificObjects;
    private Node node;
    private Group root;
    private Scene scene;
    private Class<?> klass;
    private EventHandler<? super MouseEvent> popNewEditor;

    public GenericObjectList (Class<?> klass, Node node, Group root, Scene scene, ObjectComponentEditor editor) {
        popNewEditor = editor.popNewEditor();
        this.node = node;
        this.root = root;
        this.scene = scene;
        this.klass = klass;
        createdSpecificObjects = LibraryData.getInstance().getObservableList(klass);
    }

    public TitledPane getTitledPane () {
        TitledPane titledPane = new TitledPane();
        String classType = klass.getSimpleName();
        titledPane.setText(classType);
        titledPane.setContent(ListViewUtilities.createGenericList(createdSpecificObjects));
        HBox hbox = new HBox();
        hbox.getChildren().add(new Label("Create New " + classType));
        titledPane.setOnMouseClicked(me -> {
            if (me.isSecondaryButtonDown()) {
            }
            ContextMenu contextmenu = new ContextMenu();
            MenuItem item = new MenuItem("New");
            item.setOnAction(ae -> {
                DraggableUtilities.makeObjectPlaceable(me, hbox, node, createdSpecificObjects,
                                                       (ContainerWrapper) node, root, popNewEditor);
            });

            contextmenu.getItems().add(item);
            contextmenu.show(titledPane, me.getSceneX(), me.getSceneY());

        });
        return titledPane;
    }
}
