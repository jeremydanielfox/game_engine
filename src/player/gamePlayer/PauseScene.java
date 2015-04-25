package player.gamePlayer;

import java.util.function.Consumer;

import animations.Animator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * PauseScene will be a drop down screen that holds options such as resume, restart, and return to
 * main menu
 *
 * @author Brandon Choi
 *
 */

public class PauseScene implements GameScene {

    private Stage myStage;
    private Scene myScene;
    private Animator myAnimator;
    private BorderPane container;
    private Button resume, restart, mainMenu;

    public PauseScene (Consumer<? extends Object> action, Stage s) {
        myStage = s;
        myAnimator = Animator.getInstance();
        container = new BorderPane();
        myScene = new Scene(container);
        resume = new Button();
        restart = new Button();
        mainMenu = new Button();
        //exitPause = lambda;

        myScene.getStylesheets().add("/css/GamePlayerCSS.css");
        container.setId("pauseContainer");
    }

    @Override
    public Scene getScene () {
        return myScene;
    }
    
    public void displayPauseScreen() {
        System.out.println("hello");
        myAnimator.dropDown(container);
    }

    private void setUpFunctions () {
        myScene.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ESCAPE)) {
                /*
                 * return to previous Scene as the dominant screen
                 */
            }
        });

        resume.setOnMouseClicked(e -> {

        });

        restart.setOnMouseClicked(e -> {

        });

        mainMenu.setOnMouseClicked(e -> {
            PlayerOpener opener = new PlayerOpener(myStage);
            
        });
    }
}
