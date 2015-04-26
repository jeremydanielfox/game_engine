package gae.builder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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
    private HBox field;
    private List<TextField> textFields;

    public FieldMaker (Method m) {
        myMethod = m;
        methodName = extractName(m);
        field = new HBox(15);
        textFields = new ArrayList<>();
        createField(myMethod);
    }

    public Node getField () {
        return field;
    }

    /**
     * Creates field with all setters needed using text boxes
     */
    private void createField (Method m) {
        TextField tf = new TextField();
        Label l = new Label(methodName);
        textFields.add(tf);
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
