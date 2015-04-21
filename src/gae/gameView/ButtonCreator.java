package gae.gameView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import engine.fieldsetting.Settable;
import engine.fieldsetting.Triggerable;
import View.ButtonWrapper;
import gae.editor.EditingParser;
import gae.editor.Editor;
import gae.openingView.OpeningView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class used to construct a button for the game player to use
 * 
 * @author Brandon Choi
 *
 */

public class ButtonCreator extends Application{
    
    private static final String DROPDOWN_DEFAULT = "Select action here";
    private static final String DEFAULT_NAME_FIELD = "Enter Button name here";
    
    private VBox container;
    private EditingParser parser;
    private List<Method> settableList;
    private List<Method> triggerableList;
    private List<String> methodNames;
    
    public ButtonCreator() {
        container = new VBox();
        settableList = parser.getMethodsWithSetterAnnotation(ButtonWrapper.class, Settable.class);
        triggerableList = parser.getMethodsWithSetterAnnotation(ButtonWrapper.class, Triggerable.class);
        methodNames = new ArrayList<>();
        getMethodNames();
        createFields();
    }

    private void getMethodNames () {
        settableList.forEach(e -> {
            String s = extractName(e.getName());
            methodNames.add(s);
        });
    }
    
    /**
     * takes away the "set" from the method name
     * @param method
     * @return
     */
    private String extractName(String method) {
        return method.substring(3);
    }

    /**
     * fills the creator with fields
     */
    private void createFields() {
        
    }
    
    private HBox createFieldContainer(String label) {
        HBox field = new HBox(15);
        
        
        return field;
    }
    
    
    
    
    
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage arg0) throws Exception {
        Stage stage = new Stage();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();
    } 
}
