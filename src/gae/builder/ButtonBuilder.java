package gae.builder;

import engine.fieldsetting.Settable;
import engine.fieldsetting.Triggerable;
import engine.game.StoryBoard;
import engine.goals.Goal;
import gae.editor.EditingParser;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import View.ButtonWrapper;

/**
 * Main class used to construct a button for the game player to use
 *
 * TODO: implement Builder interface?
 *
 * @author Brandon Choi
 *
 */

public class ButtonBuilder extends Application {

    private static final String DROPDOWN_DEFAULT = "Select action here";
    private static final String DEFAULT_NAME_FIELD = "Enter Button name here";

    private VBox container;
    private EditingParser parser;
    private ButtonData buttonData;
    private Map<Method, String> settableMap;
    private Map<Method, String> triggerableMap;
    private Button create;

    private TextField buttonName;
    private ComboBox<String> action, enableCondition;

    public ButtonBuilder () {
        container = new VBox(15);
        buttonData = new ButtonData();
        settableMap = new HashMap<>();
        triggerableMap = new HashMap<>();
        create = new Button("CREATE");

        buttonName = new TextField();
        action = new ComboBox<>();
        enableCondition = new ComboBox<>();

        createMap(settableMap, ButtonWrapper.class, Settable.class);
        createMap(triggerableMap, StoryBoard.class, Triggerable.class);
        createFields();
        container.getChildren().add(create);
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
        EditingParser.getMethodsWithAnnotation(klass, annotation).forEach(e -> {
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
        int index = 0;
        if (method.contains("set"))
            index = method.indexOf("set") + "set".length();
        
        return method.substring(index);
    }

    /**
     * fills the creator with fields
     */
    private void createFields () {
        settableMap.keySet().forEach(e -> {
            createFieldSetter(settableMap.get(e), Arrays.asList(e.getParameterTypes()));
        });
        fillComboBoxes();
    }

    /**
     * creates the appropriate field setter based on the parameter type of the method that is passed
     * in
     */
    @SuppressWarnings("rawtypes")
    private void createFieldSetter (String methodName, List<Class> parameterTypes) {
        HBox fieldSetter = new HBox(15);
        Label label = new Label(methodName);
        parameterTypes.forEach(e -> {
            Node n = null;
            /*
             * if field requires a String, then use text field
             */
            if (e.getName().equals(String.class.getName())) {
                n = buttonName;
            }

            /*
             * if field requires a Consumer, then use drop down of all options for them to use
             */
            else if (e.getName().equals(Consumer.class.getName())) {
                n = action;
            }

            /*
             * if field requires a Goal, then use drop down of all goals created & give them option
             * to create new Goal
             */
            else if (e.getName().equals(Goal.class.getName())) {
                n = enableCondition;
            }
            ;

            if (n != null)
                fieldSetter.getChildren().addAll(label, n);
        });
        container.getChildren().addAll(fieldSetter);
    }

    /**
     * fills drop downs with appropriate choices based on reflection of annotations
     */
    private void fillComboBoxes () {
        triggerableMap.keySet().forEach(e -> {
            
        });
    }

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage arg0) throws Exception {
        Stage myStage = new Stage();
        Scene scene = new Scene(container, 600, 600);
        myStage.setScene(scene);
        myStage.show();
        myStage.centerOnScreen();
    }
}
