package gae.editor;

import gae.openingView.UIObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private VBox simpleEditor;
    private List<ComponentEditor> editFields;
    private TreeNode root;
    private HashMap<Edits, TreeNode> nodeMap;
    private ArrayList<ComponentEditor> simpleFields;
    private ArrayList<ObjectComponentEditor> objectFields;

    public SimpleEditor (Class<?> c) {
        Label title = new Label(c.getSimpleName());
        createEditor(c, title);
    }

    public SimpleEditor (Class<?> c, String objectName) {
        Label title = new Label(objectName);
        createEditor(c, title);
    }

    @Override
    public Object createObject (Class<?> c) {
        c = getConcreteClassFromMap(c);
        Object obj = getInstanceFromName(c.getName());
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
        return simpleEditor;
    }

    /**
     * creates the simple editor by iterating through all the methods and extracting the necessary
     * fields
     * 
     * @param c
     */
    private void createEditor (Class<?> c, Label title) {
        simpleFields = new ArrayList<ComponentEditor>();
        objectFields = new ArrayList<ObjectComponentEditor>();
        nodeMap = new HashMap<Edits, TreeNode>();
        simpleEditor = new VBox(30);
        simpleEditor.getChildren().add(title);
        root = getMethodsTree(c, null);
        ArrayList<Node> editors = new ArrayList<Node>();
        for (TreeNode subNode : root.getChildren()) {
            loadArrayWithEditors(subNode, editors);
        }
        simpleEditor.getChildren().addAll(editors);
    }

    private void loadArrayWithEditors (TreeNode root, ArrayList<Node> editors) {
        ComponentEditor component;
        if (root.getInputType().equals("ObjectComponentEditor")) {
            Class<?> klass = (Class<?>) root.getMethod().getGenericParameterTypes()[0];
            klass = getConcreteClassFromMap(klass);
            component = new ObjectComponentEditor(klass);
            objectFields.add((ObjectComponentEditor) component);
        }
        else {
            component =
                    (ComponentEditor) getInstanceFromName(String.format("%s%s", CLASS_PATH,
                                                                        root.getInputType()));
            simpleFields.add(component);
        }
        component.setName(root.getMethod().getName());
        editors.add(component.getObject());
        nodeMap.put(component, root);
    }

    /**
     * Checks to see if the input klass is an interface by looking in the properties map.
     *
     * @param klass the class to check.
     * @return the conrete class from the map or if the input klass is not in the map, the input
     */
    private Class<?> getConcreteClassFromMap (Class<?> klass) {
        if (getPropertiesMap().containsKey(klass.getName())) {
            try {
                String newName = getPropertiesMap().get(klass.getName()).get(0);
                return Class.forName(newName);
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return klass;
    }

    private Object getInstanceFromName (String name) {
        Class<?> c = null;
        Object component = null;
        try {
            c = Class.forName(name);
            component = c.newInstance();
        }
        catch (ClassNotFoundException e) {
            // e.printStackTrace();
        }
        catch (IllegalAccessException iae) {
            // iae.printStackTrace();
        }
        catch (InstantiationException ie) {
            // ie.printStackTrace();
        }

        return component;
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
    
    public ArrayList<ComponentEditor> getSimpleComponentEditors() {return simpleFields;}
    
    public ArrayList<ObjectComponentEditor> getObjectComponentEditors() {return objectFields;}
}
