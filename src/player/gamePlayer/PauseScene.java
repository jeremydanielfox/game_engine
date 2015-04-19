package player.gamePlayer;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class PauseScene {
    
    private Scene myScene;
    private BorderPane container;
    private Button pause, restart, mainMenu;
    
    public PauseScene() {
        container = new BorderPane();
        myScene = new Scene(container);
        myScene.getStylesheets().add("/css/GamePlayerCSS.css");
        pause = new Button();
        restart = new Button();
        mainMenu = new Button();
    }
    
    public Scene getScene() {
        return myScene;
    }

}
