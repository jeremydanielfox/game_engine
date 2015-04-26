package gae.builder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import engine.fieldsetting.Settable;
import engine.game.Player;
import gae.editor.EditingParser;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayerBuilder extends Application {

    private PlayerData playerData;
    private VBox builder;
    private Button createButton;
    private EditingParser parser;
    private List<Method> settables;
    private List<FieldMaker> fields;

    @SuppressWarnings("static-access")
    public PlayerBuilder () {
        playerData = new PlayerData();
        builder = new VBox(15);
        createButton = new Button("CREATE");
        parser = new EditingParser();
        settables = parser.getMethodsWithAnnotation(Player.class, Settable.class);
        fields = new ArrayList<>();
        populateFields(settables);
        setUpCreateButton();
    }

    /**
     * Creates field with all setters needed. Uses recursion to check if parameters of the method
     * need to be built as well.
     */
    @SuppressWarnings("static-access")
    private void populateFields (List<Method> methods) {
        System.out.println(methods.size());
        methods.forEach(e -> {
            Arrays.asList(e.getParameterTypes()).forEach(f -> {
                if (f.isPrimitive() || f.equals(String.class)) {
                    FieldMaker fm = new FieldMaker(e);
                    fields.add(fm);
                    builder.getChildren().add(fm.getField());
                }
                else {
                    try {
                        populateFields(parser.getMethodsWithAnnotation(Class.forName(f.getName()), Settable.class));
                    }
                    catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        });
    }

    /**
     * when create button is clicked, all inputed data is sent to PlayerData
     */
    private void setUpCreateButton () {
        builder.getChildren().add(createButton);
        createButton.setAlignment(Pos.CENTER);
        createButton.setOnMouseClicked(e -> {
            /* modifty player data */
        });
    }

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage arg0) throws Exception {
        Stage myStage = new Stage();
        Scene s = new Scene(builder, 400, 400);
        myStage.setScene(s);
        myStage.show();
        myStage.centerOnScreen();
    }
}
