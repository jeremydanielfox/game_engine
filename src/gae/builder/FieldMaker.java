package gae.builder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Represents a single field maker given a method. Will be used by builder to recursively set up
 * fields for user to set.
 * 
 * @author Brandon Choi
 *
 */
public class FieldMaker {

    private Method myMethod;
    private String methodName;
    private String propertyName;
    private HBox field;
    private Map<TextField, SimpleStringProperty> textFields;

    public FieldMaker (Method m) {
        myMethod = m;
        methodName = m.getName();
        propertyName = extractName(m);
        field = new HBox(15);
        textFields = new HashMap<>();
        createField(myMethod);
    }

    public Node getField () {
        return field;
    }
    
    public Map<TextField, SimpleStringProperty> getInputMap() {
        return textFields;
    }
    
    public String getMethodName() {
        return methodName;
    }
    
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Creates field with all setters needed using text boxes
     */
    private void createField (Method m) {
        TextField tf = new TextField();
        SimpleStringProperty s = new SimpleStringProperty("");
        s.bind(tf.textProperty());
        Label l = new Label(propertyName);
        textFields.put(tf, s);
        l.setPrefWidth(100);
        tf.setPrefWidth(150);
        field.getChildren().addAll(l, tf);
    }

    /**
     * extracts name of property from method by substringing after "set"
     * 
     * @param m
     * @return
     */
    private String extractName (Method m) {
        String s = m.getName();
        int i = s.indexOf("set");
        return m.getName().substring(i + "set".length());
    }
}
