package player.gamePlayer;

import java.util.Arrays;
import java.util.function.Consumer;

import animations.Animator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * PauseScene will be a drop down screen that holds options such as resume, restart, and return to
 * main menu
 *
 * @author Brandon Choi
 *
 */

public class PauseScene implements GameScreen {

    private Stage myStage;
    private Scene myScene, myPrevious;
    private Animator myAnimator;
    private StackPane emptyPane;
    private BorderPane container;
    private Button resume, restart, mainMenu;
    private VBox buttonBox;
    private HBox instructionsBox;
    private Text instructions;

    public PauseScene (Consumer<? extends Object> action, Stage s, Scene previous) {
        myStage = s;
        myAnimator = Animator.getInstance();
        emptyPane = new StackPane();
        container = new BorderPane();
        myScene = new Scene(emptyPane);
        myPrevious = previous;
        resume = new Button("RESUME");
        restart = new Button("RESTART");
        mainMenu = new Button("MAIN MENU");
        buttonBox = new VBox(20);
        instructionsBox = new HBox(10);
        instructionsBox.setAlignment(Pos.CENTER);
        instructions = new Text("Press ESC to exit");
        instructionsBox.getChildren().add(instructions);
        
        buttonBox.getChildren().addAll(resume, restart, mainMenu);
        buttonBox.setAlignment(Pos.CENTER);
        container.setCenter(buttonBox);
        container.setBottom(instructionsBox);

        /* CSS */
        myScene.getStylesheets().add("/css/PauseScreenCSS.css");
        emptyPane.setId("emptyPane");
        container.setId("content");
        instructions.setId("instructions");
        instructionsBox.setId("instructionsBox");
        
        sizeButtons();
        setUpFunctions();
    }

    @Override
    public Scene getScreen () {
        return myScene;
    }
    
    private void sizeButtons() {
        Arrays.asList(resume, restart, mainMenu).forEach(e -> {
            e.setPrefWidth(120);
        });
    }
    
    public void displayPauseScreen() {
        /*
         * TODO write animation for drop down
         */
        emptyPane.getChildren().add(container);
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
            myStage.setScene(opener.getScreen());
        });
    }
}
