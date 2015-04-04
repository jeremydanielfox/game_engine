package gae.gameView;

import gae.frontend.UtilitiesBar;
import gae.openingView.OpeningView;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TabPane;

//import gameobject.Editable;

public class GameView {
    
//    public EditorView createEditorViewInTab(Editable e);
//    public EditorView createEditorViewinPopup(Editable e);
//    public void createLevel();
    
    private static final String GAMEVIEW_CSS = "css/GameViewCSS.css";
    
    private BorderPane myUI;
    private TabPane myTabs; //replace with main editor
    private Scene myScene;
    private LibraryView myLibrary;
    private UtilitiesBar utilities;
    
    public GameView () {
        initialize();
    }
    
    public Scene getScene() {
        return myScene;
    }

    private void initialize () {
        myUI = new BorderPane();
        myUI.setPrefWidth(Main.SCREEN_WIDTH);
        myUI.setPrefHeight(Main.SCREEN_HEIGHT);
        myTabs = new TabPane();
        myUI.setCenter(myTabs);
        myScene = new Scene(myUI);
        myScene.setCursor(new ImageCursor(new Image("/images/swordCursor.jpg")));
        myScene.getStylesheets().add(GAMEVIEW_CSS);
        myLibrary = new LibraryView();
        utilities = new UtilitiesBar();
        myUI.setTop(utilities.getUtilitiesBar());
    }
}
