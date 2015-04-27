package gae.gameView;

import engine.fieldsetting.Settable;
import engine.game.Game;
import engine.game.LevelBoard;
import engine.shop.ShopModelSimple;
import gae.editor.EditingParser;
// import gae.backend.GameManager;
import gae.openingView.UIMediator;
import gae.openingView.UIObject;
import gae.tabView.CentralTabView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EventObject;
import java.util.Map;
import javafx.scene.ImageCursor;
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
    private CentralTabView myTabs;
    private Scene myScene;
    // private LibraryView myLibrary;
    private UtilitiesBar utilities;
    private Map<String, String> openingViewData;

    private Game myGame;

    // private GameManager myGameManager;

    public GameView (Map<String, String> dataResults) {
        myUI = new BorderPane();
        myScene = new Scene(myUI);
        myGame = createGameWithLevelBoard();
        
        if (dataResults != null) {
            openingViewData = dataResults;
            myTabs = new CentralTabView(myScene, myGame, openingViewData.get("Game Type"));
        }
        else {
            myTabs = new CentralTabView(myScene, myGame, null);
        }

        myScene.getStylesheets().add(GAMEVIEW_CSS);
        // myLibrary = new LibraryView();
        utilities = new UtilitiesBar(myGame);
        // myLibrary = new LibraryView();
        insertBorders();
        // changeCursor(CURSOR_GRAPHIC);
    }

    private Game createGameWithLevelBoard () {
        Game g = null;
        try {
            g =
                    (Game) Class.forName(EditingParser
                                         .getInterfaceClasses("engine.fieldsetting.implementing_classes")
                                         .get("Game").get(0)).newInstance();

            LevelBoard levelBoard = (LevelBoard) Class.forName(EditingParser
                                                               .getInterfaceClasses("engine.fieldsetting.implementing_classes")
                                                               .get("LevelBoard").get(0)).newInstance();

            for (Method m : EditingParser.getMethodsWithAnnotation(Class.forName(g.getClass().getName()), Settable.class)) {
                if (m.getName().equals("setLevelBoard")) {
                    m.invoke(g, levelBoard);
                }
            } 
        }
        catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }

        if (g == null) {
            Exception e = new NullPointerException();
            e.printStackTrace();
        }

        return g;
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
        // myLibrary = new LibraryView();
        utilities = new UtilitiesBar(myGame);
        myUI.setTop(utilities.getUtilitiesBar());
    }

    /**
     * changes the cursor image
     * 
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
        //        myUI.setRight(myGenericObjects.getObject());
    }
}
