package gae.editor;

import gae.editorView.PopUpEditorView;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Eric Saba
 *
 *         This ComponentEditor is used in the Editor when a @settable method takes an
 *         Engine-defined object as a parameter.
 *         It presents an "Add existing" object button and a "create new" object button.
 */
public class ObjectComponentEditor extends ComponentEditor {
    private Button myAddExistingButton;
    private Button myCreateNewButton;
    private Object myObject;
    private Class<?> clazz;
    private BiConsumer<Class<?>, Object> biConsumer;

    public ObjectComponentEditor (Class<?> klass, BiConsumer<Class<?>, Object> biconsumer) {
        createObjectClass(klass, biconsumer);
        myAddExistingButton = new Button("Add Existing");
        myCreateNewButton = new Button("Create New");
        // myCreateNewButton.setOnMouseClicked(popNewEditor());
        // TODO: setup lambda's for these buttons
        getEditBox().getChildren().addAll(getLabel(), myAddExistingButton, myCreateNewButton);
    }
    
    private void createObjectClass(Class<?> klass, BiConsumer<Class<?>, Object> biconsumer) {
        clazz = klass;
        biConsumer = biconsumer;
        try {
            myObject = Class.forName(klass.getName()).newInstance();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
            System.out.println(klass.getName() + " defaulted to first concrete class.");
            try {
                myObject = EditingParser.getConcreteClassFromMap(klass).newInstance();
            }
            catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                myObject = null;
            }
//            e1.printStackTrace();
        }
    }

    public void setObject (Object obj) {
        myObject = obj;
    }

    public BiConsumer<Class<?>, Object> getBiConsumer () {
        return biConsumer;
    }

    public Class<?> getObjectClass () {
        return myObject.getClass();
    }
    
    /**
     * The instantiated object's class and the variable clazz can be different classes if ObjectComponentEditor is 
     * instantiated with an interface class.
     * 
     * @return interface if any
     */
    public Class<?> getInterfaceClass() {
        return clazz;
    }

    public void popNewEditor (int index) {
        // Consumer<Object> setObjectConsumer = o -> setObject(o);
        Consumer<Object> setObjectConsumer = o -> clear();
        // GenericObjectsPane.newCustomObject(clazz, "yo", setObjectConsumer);
        new PopUpEditorView(setObjectConsumer, biConsumer, myObject.getClass(), index);
    }

    @Override
    public Object createObject (Class<?> c) {
        return c.cast(myObject);
    }

    @Override
    void clear () {
        // TODO Auto-generated method stub

    }

    @Override
    void defaultField () {
        // TODO Auto-generated method stub

    }

}
