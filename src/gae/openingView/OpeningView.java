package gae.openingView;

import gae.gameView.GameView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import animations.Animator;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * OpeningView is the overarching class for the first scene the author sees. Allows the author to
 * provide basic information about the game and select game type.
 * 
 * @author Brandon Choi
 *
 */

public class OpeningView implements UIMediator {

    static final Animator myAnimator = new Animator();
    static final String DEFAULT_TYPE_MSG = "Choose from right ->";
    private static final String OPENINGVIEW_CSS = "css/OpeningViewCSS.css";
    private Stage myStage;
    private BorderPane myPane;
    private Scene myScene;
    private List<UIObject> myUIObjects;
    private UIObject dataForm, imagePanel;
    private Map<String, String> dataResults;

    private SimpleStringProperty gameSelected;

    public OpeningView (Stage stage) {
        myStage = stage;
        myPane = new BorderPane();
        myScene = new Scene(myPane);
        myScene.getStylesheets().add(OPENINGVIEW_CSS);
        myUIObjects = new ArrayList<>();
        gameSelected = new SimpleStringProperty();
        gameSelected.set(DEFAULT_TYPE_MSG);
        imagePanel = new ImagePanel(this, gameSelected);
        dataForm = new DataForm(this, gameSelected);
        dataResults = new HashMap<>();
        insertBorders();

        /*
         * to make it easier for us to test!
         */
        myScene.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ESCAPE)) {
                UIMediator author = new GameView();
                myScene = author.getScene();
                myStage.setScene(myScene);
            }
        });
    }

    /**
     * sets up the different borders of the BorderPane
     */
    private void insertBorders () {
        myPane.setRight(imagePanel.getObject());
        myPane.setLeft(dataForm.getObject());
    }

    @Override
    public Scene getScene () {
        return myScene;
    }

    @Override
    public void addUIObject (UIObject object) {
        myUIObjects.add(object);
    }

    @Override
    public void handleEvent (UIObject usedObject, EventObject action) {

        /*
         * changes the scene to display the GameView if button in DataForm has been pressed
         */
        if (fieldsCompleted()) {
            if (usedObject.equals(dataForm) && action instanceof MouseEvent) {
                UIMediator author = new GameView();
                myScene = author.getScene();
                myStage.setScene(myScene);
            }
        }
        else {
            myAnimator.shake(dataForm.getObject());
        }
    }


    /**
     * indicates whether data form has any empty fields or not
     * 
     * @return
     */
    private boolean fieldsCompleted () {
        DataForm dt = (DataForm) dataForm;
        extractFields(dt);
        return dt.filledFields();
    }
    
    /**
     * extracts text from data form into map via reflection on the method name
     * 
     * @param data
     */
    private void extractFields(DataForm data) {
        Arrays.asList("Author", "Description", "Game Type", "Instructions", "Title").forEach(e -> {
            String methodName = "get" + e.replaceAll(" ", "");
            try {
                Method method = data.getClass().getDeclaredMethod(methodName);
                dataResults.put(e, method.invoke(data).toString());
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
