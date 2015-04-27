package gae.builder;

import java.lang.reflect.Method;
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
    private TextField textField;

    public FieldMaker (Method m) {
        myMethod = m;
        methodName = m.getName();
        propertyName = extractName(m);
        field = new HBox(15);
        textField = new TextField();
        createField(myMethod);
    }

    public Node getField () {
        return field;
    }
    
    public TextField getInput() {
        return textField;
    }
    
    public String getMethodName() {
        return methodName;
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public Method getMethod() {
        return myMethod;
    }

    /**
     * Creates field with all setters needed using text boxes
     */
    private void createField (Method m) {
        textField = new TextField();
        Label l = new Label(propertyName);
        l.setPrefWidth(100);
        textField.setPrefWidth(150);
        field.getChildren().addAll(l, textField);
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
