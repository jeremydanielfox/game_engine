package gae.builder;

import java.lang.reflect.Method;

import javafx.scene.Node;
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

    public FieldMaker (Method m) {
        myMethod = m;
        methodName = extractName(m);
        field = new HBox(15);
        createField();
    }

    public Node getField () {
        return field;
    }

    /**
     * Creates field with all setters needed. Uses recursion to check if parameters of the method
     * need to be built as well.
     */
    private void createField () {
        
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
        return m.getName().substring(i);
    }
}
