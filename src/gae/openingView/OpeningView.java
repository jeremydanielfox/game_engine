package gae.openingView;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * OpeningView is the overarching class for the first scene the author sees. 
 * Allows the author to provide basic information about the game and select game type.
 * 
 * @author Brandon Choi
 *
 */

public class OpeningView implements UIMediator {
    
    private static final String OPENINGVIEW_CSS = "css/OpeningViewCSS.css"; 
    private BorderPane myPane;
    private Scene myScene;
    private List<UIObject> myUIObjects;
    private UIObject dataForm, imagePanel;

    public OpeningView (Stage myStage) {
        myPane = new BorderPane();
        myScene = new Scene(myPane);
        myScene.getStylesheets().add(OPENINGVIEW_CSS);
        myUIObjects = new ArrayList<>();
        imagePanel = new ImagePanel();
        dataForm = new DataForm();
        insertBorders();
    }

    private void insertBorders () {
        myPane.setRight(imagePanel.getObject());
        myPane.setLeft(dataForm.getObject());
    }
    
    /**
     * returns a scene for the stage to display
     * @return
     */
    public Scene getScene() {
        return myScene;
    }
    
    @Override
    public void addUIObject (UIObject object) {
        myUIObjects.add(object);
    }

    @Override
    public void handleEvent (UIObject usedObject, ActionEvent action) {
        // TODO Auto-generated method stub
        
    }
}
