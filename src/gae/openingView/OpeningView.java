package gae.openingView;

import gae.gameView.GameView;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import View.PopUpScreen;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;
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

    static final String DEFAULT_TYPE_MSG = "NOT SELECTED YET";
    private static final String OPENINGVIEW_CSS = "css/OpeningViewCSS.css";
    private Stage myStage;
    private BorderPane myPane;
    private Scene myScene;
    private List<UIObject> myUIObjects;
    private UIObject dataForm, imagePanel;

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
            PopUpScreen popup = new PopUpScreen();
            popup.makeScreen("Fill out the forms, dummy!", "WILL DO!");
        }

        /*
         * if game type is selected, gray out rest of the options
         */

        /*
         * enable author to proceed if all fields are filled out & game type is selected
         */

    }

    /**
     * indicates whether data form has any empty fields or not
     * 
     * @return
     */
    private boolean fieldsCompleted () {
        DataForm dt = (DataForm) dataForm;
        return dt.filledFields();
    }
}
