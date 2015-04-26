package gae.editor;

import gae.openingView.UIObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


/**
 * SimpleEditor is a component of the ObjectEditor and will be represented on the top right corner
 * when an object is pressed. It will have all the basic properties of any game object such as its
 * name.
 *
 * @author Brandon Choi
 * @author Eric Saba
 *
 */

public class SimpleEditor extends Editor implements UIObject {

    private static final String CLASS_PATH = "gae.editor.";

    private VBox simpleEditorVBox;
    private List<ComponentEditor> editFields;
    private TreeNode root;
    private HashMap<Edits, TreeNode> nodeMap;
    private ArrayList<ComponentEditor> simpleFields;
    private ArrayList<ObjectComponentEditor> objectFields;
    private BiConsumer<Class<?>, Object> biConsumer;

    public SimpleEditor (Class<?> c, BiConsumer<Class<?>, Object> biconsumer) {
        Label title = new Label(c.getSimpleName());
        createEditor(c, title, biconsumer);
    }

    public SimpleEditor (Class<?> c, BiConsumer<Class<?>, Object> biconsumer, String objectName) {
        Label title = new Label(objectName);
        createEditor(c, title, biconsumer);
    }

    @Override
    public Object createObject (Class<?> c) {
        c = EditingParser.getConcreteClassFromMap(c);
        Object obj = EditingParser.getInstanceFromName(c.getName());
        for (Edits edits : nodeMap.keySet()) {
            Method method = nodeMap.get(edits).getMethod();
            Class<?> paramClass = method.getParameterTypes()[0];
            Object subObject = edits.createObject(paramClass);
            try {
                method.invoke(obj, subObject);
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    @Override
    public Node getObject () {
        return simpleEditorVBox;
    }

    /**
     * creates the simple editor by iterating through all the methods and extracting the necessary
     * fields
     * 
     * @param c
     * @param biconsumer2 
     */
    private void createEditor (Class<?> c, Label title, BiConsumer<Class<?>, Object> biconsumer) {
        biConsumer = biconsumer;
        objectFields = new ArrayList<ObjectComponentEditor>();
        simpleFields = new ArrayList<ComponentEditor>();
        nodeMap = new HashMap<Edits, TreeNode>();
        simpleEditorVBox = new VBox(30);
        simpleEditorVBox.getChildren().add(title);
        root = getMethodsTree(c, null);
        ArrayList<Node> editors = new ArrayList<Node>();
        for (TreeNode subNode : root.getChildren()) {
            loadArrayWithEditors(subNode, editors);
        }
        simpleEditorVBox.getChildren().addAll(editors);
    }

    private void loadArrayWithEditors (TreeNode root, ArrayList<Node> editors) {
        ComponentEditor component;
        if (root.getInputType().equals("ObjectComponentEditor")) {
            Class<?> klass = (Class<?>) root.getMethod().getGenericParameterTypes()[0];
            klass = EditingParser.getConcreteClassFromMap(klass);
            component = new ObjectComponentEditor(klass, biConsumer);
            objectFields.add((ObjectComponentEditor) component);
        }
        else {
            component =
                    (ComponentEditor) EditingParser.getInstanceFromName(String.format("%s%s", CLASS_PATH,
                                                                        root.getInputType()));
            simpleFields.add(component);
            editors.add(component.getObject());
        }
//        editors.add(component.getObject());
        component.setName(root.getMethod().getName());
        nodeMap.put(component, root);
    }

    @Override
    void setDefaults () {
        editFields.forEach(e -> e.defaultField());
    }

    @Override
    void clearValues () {
        editFields.forEach(e -> e.clear());
    }

    protected TreeNode getTreeNode () {
        return root;
    }

    public ArrayList<ComponentEditor> getSimpleComponentEditors () {
        return simpleFields;
    }

    public ArrayList<ObjectComponentEditor> getObjectComponentEditors () {
        return objectFields;
    }
}
