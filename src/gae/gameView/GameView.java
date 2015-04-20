package gae.gameView;

import java.util.EventObject;
import gae.backend.Editable;
//import gae.backend.GameManager;
import gae.openingView.UIMediator;
import gae.openingView.UIObject;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import gae.tabView.CentralTabView;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

/**
 * GameView is the main authoring environment scene in which the author can create new game objects,
 * levels, etc. and eventually export an XML file of the game he or she created
 * 
 * @author Brandon Choi, John Gilhuly
 *
 */

public class GameView implements UIMediator {

    // public EditorView createEditorViewInTab(Editable e);
    // public EditorView createEditorViewinPopup(Editable e);
    // public void createLevel();

    private static final Image CURSOR_GRAPHIC = new Image("/images/swordCursor.jpg");
    private static final String GAMEVIEW_CSS = "css/GameViewCSS.css";
    private BorderPane myUI;
    private CentralTabView myTabs; // replace with main editor
    private Scene myScene;
    //private LibraryView myLibrary;
    private UtilitiesBar utilities;
    private GenericObjectsPane myGenericObjects;
//    private GameManager myGameManager;

    public GameView () {
        myUI = new BorderPane();
        myScene = new Scene(myUI);
        myTabs = new CentralTabView(myScene);
        myScene.getStylesheets().add(GAMEVIEW_CSS);
//        myLibrary = new LibraryView();
        utilities = new UtilitiesBar();
        myGenericObjects = new GenericObjectsPane(myTabs.getConsumer());
//        myLibrary = new LibraryView();
        insertBorders();
//        changeCursor(CURSOR_GRAPHIC);
    }

    @Override
    public Scene getScene () {
        return myScene;
    }
    
    @Override
    public void addUIObject (UIObject object) {
        // TODO Auto-generated method stub
    }

    @Override
    public void handleEvent (UIObject usedObject, EventObject action) {
        // TODO Auto-generated method stub

        myScene.setCursor(new ImageCursor(new Image("/images/swordCursor.jpg")));
        myScene.getStylesheets().add(GAMEVIEW_CSS);
     //   myLibrary = new LibraryView();
        utilities = new UtilitiesBar();
        myUI.setTop(utilities.getUtilitiesBar());
    }

    /**
     * changes the cursor image
     * @param i
     */
    private void changeCursor (Image i) {
        myScene.setCursor(new ImageCursor(i));
    }

    /**
     * inserts the borders of the BorderPane
     */
    private void insertBorders () {
        myUI.setTop(utilities.getUtilitiesBar());
        myUI.setCenter(myTabs.getObject());
        myUI.setRight(myGenericObjects.getObject());    
    }
    
    public void getAddFunction(Editable editable) {
        myTabs.getAddFunction(editable);
    }
}
