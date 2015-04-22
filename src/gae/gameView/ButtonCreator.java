package gae.gameView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import engine.fieldsetting.Settable;
import engine.fieldsetting.Triggerable;
import engine.game.StoryBoard;
import engine.goals.Goal;
import View.ButtonWrapper;
import gae.editor.EditingParser;
import gae.editor.Editor;
import gae.openingView.OpeningView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

public class ButtonCreator extends Application {

    private static final String DROPDOWN_DEFAULT = "Select action here";
    private static final String DEFAULT_NAME_FIELD = "Enter Button name here";

    private VBox container;
    private EditingParser parser;
    private Map<Method, String> settableMap;
    private Map<Method, String> triggerableMap;

    public ButtonCreator () {
        container = new VBox(15);
        settableMap = new HashMap<>();
        triggerableMap = new HashMap<>();
        createMap(settableMap, ButtonWrapper.class, Settable.class);
        createMap(triggerableMap, StoryBoard.class, Triggerable.class);
        createFields();
    }

    public VBox getContainer () {
        return container;
    }

    /**
     * fills settableMap and triggerableMap with the keys as the methods and the values as the
     * method property name
     */
    @SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
    private void createMap (Map m, Class klass, Class annotation) {
        parser.getMethodsWithAnnotation(klass, annotation).forEach(e -> {
            m.put(e, extractName(e.getName()));
        });
    }

    /**
     * takes away the "set" from the method name
     * 
     * @param method
     * @return
     */
    private String extractName (String method) {
        return method.substring(3);
        /*
         * how to split the capital letters with space?
         */
    }

    /**
     * fills the creator with fields
     */
    private void createFields () {
        settableMap.keySet().forEach(e -> {
            createFieldSetter(settableMap.get(e), Arrays.asList(e.getParameterTypes()));
        });
    }

    /**
     * creates the appropriate field setter based on the parameter type of the method that is passed
     * in
     */
    @SuppressWarnings("rawtypes")
    private void createFieldSetter (String methodName, List<Class> parameterTypes) {
        HBox fieldSetter = new HBox(15);
        Label label = new Label(methodName);
        fieldSetter.getChildren().add(label);

        parameterTypes.forEach(e -> {
            Node field = null;

            /*
             * if field requires a String, then create a text field
             */
            if (e.isInstance(String.class)) {
                field = new TextField();
            }

            /*
             * if field requires a Consumer, then create drop down of all options for them to use
             */
            else if (e.isInstance(Consumer.class)) {
                field = getPossibleActions();
            }

            /*
             * if field requires a Goal, then create a drop down of all goals created & give them
             * option to create new Goal
             */
            else if (e.isInstance(Goal.class)) {
                field = getTriggerables();
            }

            fieldSetter.getChildren().add(field);
        });

        container.getChildren().add(fieldSetter);
    }

    /**
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    private ComboBox getPossibleActions () {
        /*
         * TODO: How to extract possible actions?
         */
        return null;
    }

    /**
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    private ComboBox getTriggerables () {
        /*
         * TODO: Currently only one triggerable...?
         */
        return null;
    }

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage arg0) throws Exception {
        Stage stage = new Stage();
        ButtonCreator bc = new ButtonCreator();
        Scene scene = new Scene(bc.getContainer(), 500, 500);
        stage.setScene(scene);
        stage.show();
    }
}
