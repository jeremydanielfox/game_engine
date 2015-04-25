package gae.editorView;

import java.util.ArrayList;
import java.util.Map;
import gae.editor.EditingParser;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;


public class GenericObjectList {
    private static final String PROPERTY_FILE_PATH = "engine.fieldsetting.implementing_classes";
    private ObservableList<Object> createdSpecificObjects;
    private Node node;
    private Group root;
    private Scene scene;
    private Class<?> klass;
    private EventHandler<? super MouseEvent> popNewEditor;
    private Map<String, ArrayList<String>> interfaceToClassMap;

    public GenericObjectList (Class<?> klass,
                              Node node,
                              Group root,
                              Scene scene,
                              ObjectComponentEditor editor) {
        popNewEditor = editor.popNewEditor();
        this.node = node;
        this.root = root;
        this.scene = scene;
        this.klass = klass;
        interfaceToClassMap = EditingParser.getInterfaceClasses(PROPERTY_FILE_PATH);
        createdSpecificObjects = LibraryData.getInstance().getObservableList(klass);
    }

    public TitledPane getTitledPane () {
        TitledPane titledPane = new TitledPane();
        String classType = getType();
        titledPane.setText(classType);
        titledPane.setContent(ListViewUtilities.createGenericList(createdSpecificObjects));
        DraggableItem draggable = new DraggableItem(classType, popNewEditor);
        titledPane.setOnMouseClicked(me -> {
            // if (me.isSecondaryButtonDown()) {
                ContextMenu contextmenu = new ContextMenu();
                MenuItem item = new MenuItem("New");
                item.setOnAction(ae -> {
                    DraggableUtilities.makeObjectPlaceable(me, draggable.getNewInstance(),
                                                           node,
                                                           createdSpecificObjects,
                                                           (ContainerWrapper) node, root);
                });

                contextmenu.getItems().add(item);
                contextmenu.show(titledPane, me.getSceneX(), me.getSceneY());
                // }
            });
        return titledPane;
    }

    private String getType () {
        for (String interfaceName : interfaceToClassMap.keySet()) {
            if (klass.getName().equals(interfaceToClassMap.get(interfaceName).get(0))) {
                String[] parts = interfaceName.split("\\.");
                String[] importantFieldName = parts[parts.length - 1].split("(?=\\p{Upper})");
                return importantFieldName[importantFieldName.length - 1];
            }
        }
        return klass.getSimpleName();
    }
}
