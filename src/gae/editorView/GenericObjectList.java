package gae.editorView;

import java.util.ArrayList;
import java.util.Map;
import gae.editor.EditingParser;
import gae.editor.ObjectComponentEditor;
import gae.gridView.ContainerWrapper;
import gae.listView.DraggableUtilities;
import gae.listView.LibraryData;
import gae.listView.ListViewUtilities;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;


public class GenericObjectList {
    private static final String PROPERTY_FILE_PATH = "engine.fieldsetting.implementing_classes";
    private ObservableList<Object> createdSpecificObjects;
    private Node node;
    private Group root;
    private Class<?> klass;
    private ObjectComponentEditor objectEditor;
    private ContainerWrapper wrapper;
    private Map<String, ArrayList<String>> interfaceToClassMap;
    private int count;

    public GenericObjectList (ObjectComponentEditor editor,
                              Node node,
                              ContainerWrapper wrapper,
                              Group root) {
        objectEditor = editor;
        this.node = node;
        this.root = root;
        this.wrapper = wrapper;
        this.klass = editor.getObjectClass();
        interfaceToClassMap = EditingParser.getInterfaceClasses(PROPERTY_FILE_PATH);
        createdSpecificObjects = LibraryData.getInstance().getObservableList(klass);
    }

    public TitledPane getTitledPane () {
        TitledPane titledPane = new TitledPane();
        String classType = getType();
        titledPane.setText(classType);
        titledPane.setContent(setList(classType));
        titledPane.setOnMousePressed(me -> {
            if (me.isSecondaryButtonDown()) {
                ContextMenu contextmenu = new ContextMenu();
                MenuItem item = new MenuItem("New");
                item.setOnAction(ae -> {
                    if (klass.getSimpleName().equals("Collider")) {
                        new ColliderEditorOpener(objectEditor.getBiConsumer(), klass, interfaceToClassMap);
                    }
                        else {
                            objectEditor.popNewEditor(classType + " " + count++);
                        }
                    });
                contextmenu.getItems().add(item);
                contextmenu.show(titledPane, me.getSceneX(), me.getSceneY());
            }
        });
        return titledPane;
    }

    private ListView<?> setList (String classType) {
        ListView<?> list = ListViewUtilities.createGenericList(createdSpecificObjects, classType);
        BooleanProperty unclicked = new SimpleBooleanProperty(false);
        list.setOnMouseClicked(me -> {
            if (me.getClickCount() == 2 && !unclicked.get()) {
                unclicked.set(true);
                DraggableItem draggable =
                        new DraggableItem(list.getSelectionModel().getSelectedItem(), klass,
                                          classType);
                DraggableUtilities.makeObjectPlaceable(me, draggable, node,
                                                       createdSpecificObjects, wrapper, root,
                                                       objectEditor, unclicked);
            }
        });
        return list;
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
