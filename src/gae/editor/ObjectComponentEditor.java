package gae.editor;

import gae.editorView.PopUpEditorView;
import gae.gameView.GenericObjectsPane;
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

    public ObjectComponentEditor (Class<?> klass) {
        clazz = klass;
        try {
            myObject = Class.forName(klass.getName()).newInstance();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
            System.out.println("error: " + klass.getName());
            myObject = null;
            e1.printStackTrace();
        }
        myAddExistingButton = new Button("Add Existing");
        myCreateNewButton = new Button("Create New");
        myCreateNewButton.setOnMouseClicked(popNewEditor());
        // TODO: setup lambda's for these buttons
        getEditBox().getChildren().addAll(getLabel(), myAddExistingButton, myCreateNewButton);
    }

    private void setObject (Object obj) {
        myObject = obj;
    }
    
    public Class<?> getObjectClass() {
        return clazz;
    }
    
    public EventHandler<? super MouseEvent> popNewEditor() {
        return e -> {
            Consumer<Object> setObjectConsumer = o -> setObject(o);
            new PopUpEditorView(setObjectConsumer, clazz);
        };
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
