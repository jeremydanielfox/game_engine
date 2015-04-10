package gae.editor;

import java.util.ArrayList;
import java.util.List;
import gae.openingView.UIObject;
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

    public SimpleEditor (Class<?> c) {
        simpleEditor = new VBox(30);
        createEditor(c);
    }

    @Override
    public Node getObject () {
        return simpleEditor;
    }

    /**
     * creates the simple editor by iterating through all the methods and extracting the necessary
     * fields
     * @param c 
     */
    private void createEditor (Class<?> c) {
        Label title = new Label(c.getSimpleName());
        simpleEditor.getChildren().add(title);
        TreeNode root = getMethodsTree(c, null);
        ArrayList<Node> editors = new ArrayList<Node>();
        for (TreeNode subNode : root.getChildren()) {
            loadArrayWithEditors(subNode, editors);
        }
        simpleEditor.getChildren().addAll(editors);
    }

    private void loadArrayWithEditors (TreeNode root, ArrayList<Node> editors) {
        if (root.getNumChildren() == 0 && root.getInputType() != "null") {
            ComponentEditor component = getInstanceFromName(String.format("%s%s", CLASS_PATH, root.getInputType()));
            component.setName(Editor.getPropertyName(root.getMethod().getName()));
            editors.add(component.getObject());
        }
        else if (root.getMethod() != null){
            Class<?> klass = (Class<?>) root.getMethod().getGenericParameterTypes()[0];
            if (getPropertiesMap().containsKey(klass.getName()))
                try {
                    String newName = getPropertiesMap().get(klass.getName()).get(0);
                    klass = Class.forName(newName);
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            SimpleEditor simple = new SimpleEditor(klass);
            editors.add(simple.getObject());
        }
    }
    
    private ComponentEditor getInstanceFromName(String name) {
        Class<?> c = null;
        ComponentEditor component = null;
        try {
            c = Class.forName(name);
            component = (ComponentEditor) c.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
        } catch (InstantiationException ie) {
            ie.printStackTrace();
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
}
