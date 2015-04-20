package player.gamePlayer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;

/**
 * PauseScene will be a drop down screen that holds options such as resume, restart, and return to
 * main menu
 * 
 * @author Brandon Choi
 *
 */

public class PauseScene {

    private Scene myScene;
    private BorderPane container;
    private Button resume, restart, mainMenu;
    private EventHandler<ActionEvent> exitPause;

    public PauseScene (EventHandler<ActionEvent> lambda) {
        container = new BorderPane();
        myScene = new Scene(container);
        myScene.getStylesheets().add("/css/GamePlayerCSS.css");
        resume = new Button();
        restart = new Button();
        mainMenu = new Button();
        exitPause = lambda;

        container.setId("pauseContainer");
    }

    private void setUpFunctions () {
        myScene.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ESCAPE)) {
                
            }
        });
    }

    public Scene getScene () {
        return myScene;
    }
}
