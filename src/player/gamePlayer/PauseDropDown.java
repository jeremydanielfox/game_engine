package player.gamePlayer;

import java.util.Arrays;
import java.util.function.Consumer;

import animations.Animator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Pause scene will be a new stage that pops up and automatically pauses the game going on
 *
 * @author Brandon Choi
 *
 */

public class PauseDropDown implements GameScene {

    private Stage mainStage, myStage;
    private Scene myScene;
    private BorderPane myContainer;
    private Button resume, mainMenu;
    private VBox buttonBox;
    private HBox instructionsBox;
    private Text instructions;
    private Consumer<? extends Object> resumer;

    public PauseDropDown (Consumer<? extends Object> lambda, Stage s) {
        mainStage = s;
        myStage = new Stage ();
        myContainer = new BorderPane();
        myScene = new Scene(myContainer);
        resumer = lambda;
        resume = new Button("RESUME");
        mainMenu = new Button("MAIN MENU");
        buttonBox = new VBox(20);
        instructionsBox = new HBox(10);
        instructionsBox.setAlignment(Pos.CENTER);
        instructions = new Text("Press ESC to exit");
        instructionsBox.getChildren().add(instructions);

        buttonBox.getChildren().addAll(resume, mainMenu);
        buttonBox.setAlignment(Pos.CENTER);
        myContainer.setCenter(buttonBox);
        myContainer.setBottom(instructionsBox);

        setUpCSS();
        sizeButtons();
        setUpFunctions();
    }

    @Override
    public Scene getScene () {
        return myScene;
    }

    private void setUpCSS () {
        myScene.getStylesheets().add("/css/PauseScreenCSS.css");
        myContainer.setId("content");
        instructions.setId("instructions");
        instructionsBox.setId("instructionsBox");
    }

    private void sizeButtons () {
        Arrays.asList(resume, mainMenu).forEach(e -> {
            e.setPrefWidth(120);
        });
    }

    public void displayPauseScreen () {;
        myStage.setScene(myScene);
        myStage.show();
    }

    /**
     * Sets up functionalities for all the clicks
     */
    private void setUpFunctions () {
        myScene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode().equals(KeyCode.ESCAPE)) {
                resume();
            }
        });
        
        resume.setOnMouseClicked(e -> {
            resume();
        });

        mainMenu.setOnMouseClicked(e -> {
            PlayerOpener opener = new PlayerOpener(mainStage);
            mainStage.setScene(opener.getScene());
            myStage.close();
        });
    }

    private void resume () {
        myStage.close();
        resumer.accept(null);
    }
}
