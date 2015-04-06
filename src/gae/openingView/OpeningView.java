package gae.openingView;

import gae.gameView.GameView;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javafx.scene.Scene;
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

    private static final String OPENINGVIEW_CSS = "css/OpeningViewCSS.css";
    private Stage myStage;
    private BorderPane myPane;
    private Scene myScene;
    private List<UIObject> myUIObjects;
    private UIObject dataForm, imagePanel;

    public OpeningView (Stage stage) {
        myStage = stage;
        myPane = new BorderPane();
        myScene = new Scene(myPane);
        myScene.getStylesheets().add(OPENINGVIEW_CSS);
        myUIObjects = new ArrayList<>();
        imagePanel = new ImagePanel(this);
        dataForm = new DataForm(this);
        insertBorders();
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

        // changes the scene to display the GameView if button in DataForm has been pressed
        if (usedObject.equals(dataForm) && action instanceof MouseEvent) {
            UIMediator author = new GameView();
            myScene = author.getScene();
            myStage.setScene(myScene);
        }

        // if game type is selected, gray out rest of the options

        // enable author to proceed if all fields are filled out & game type is selected

    }
}
