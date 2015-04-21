package gae.gameView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.fieldsetting.Settable;
import engine.fieldsetting.Triggerable;
import engine.game.StoryBoard;
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

public class ButtonCreator extends Application {

    private static final String DROPDOWN_DEFAULT = "Select action here";
    private static final String DEFAULT_NAME_FIELD = "Enter Button name here";

    private VBox container;
    private EditingParser parser;
    private Map<Method, String> settableMap;
    private Map<Method, String> triggerableMap;

    public ButtonCreator () {
        container = new VBox();
        settableMap = new HashMap<>();
        triggerableMap = new HashMap<>();
        createMaps();
    }

    /**
     * fills settableMap and triggerableMap with the keys as the methods and the values as the
     * method property name
     */
    private void createMaps () {
        /*
         * settableMap
         */
        parser.getMethodsWithAnnotation(ButtonWrapper.class, Settable.class).forEach(e -> {
            settableMap.put(e, extractName(e.getName()));
        });

        /*
         * triggerableMap
         */
        parser.getMethodsWithAnnotation(StoryBoard.class, Triggerable.class).forEach(e -> {
            triggerableMap.put(e, extractName(e.getName()));
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
    }

    /**
     * fills the creator with fields
     */
    private void createFields () {

    }

    private HBox createFieldContainer (String label) {
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
