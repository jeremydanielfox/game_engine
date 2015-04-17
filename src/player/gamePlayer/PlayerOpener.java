package player.gamePlayer;

import java.io.File;
import java.util.Arrays;

import gae.gameView.Main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Opens the game player. It will ideally have a few pre-authored games for the user to play but
 * also allow the user to upload new games he or she created as well.
 * 
 * @author Brandon Choi
 *
 */
public class PlayerOpener extends Application {

    private static final String headerText = "Select a Game";

    private Scene playerScene;
    private BorderPane view;
    private HBox options;
    private Text header;
    private Button loadB, playB;
    private GameSelector gameSelector;

    public PlayerOpener () {
        view = new BorderPane();
        playerScene = new Scene(view);
        options = new HBox(50);
        header = new Text(headerText);
        gameSelector = new GameSelector(playerScene);

        playerScene.getStylesheets().add("/css/GamePlayerCSS.css");
        options.setId("optionBox");
        header.setId("playerHeader");

        setUpBorderPane();
    }

    /**
     * sets up functionalities of buttons on the UI
     */
    private void setUpButtons () {
        loadB = new Button("LOAD GAME");
        loadB.setOnMousePressed(e -> {
            openFileChooser();
        });
        playB = new Button("PLAY");
        playB.setOnMousePressed(e -> {
            /*
             * change scene to next player scene
             */
        });

        Arrays.asList(loadB, playB).forEach(e -> {
            e.setId("playerButton");
        });

        createOptions();
    }

    private void openFileChooser () {
        FileChooser fc = new FileChooser();
        Stage fileStage = new Stage();
        File chosen = fc.showOpenDialog(fileStage);
    }

    /**
     * adds buttons to options box
     */
    private void createOptions () {
        options.getChildren().addAll(loadB, playB);
    }

    /**
     * sets up different border areas of the border pane
     */
    private void setUpBorderPane () {
        view.setCenter(gameSelector.getChooser());
        view.setTop(header);
        setUpButtons();
        view.setBottom(options);
    }

    /**
     * returns the Player scene
     * 
     * @return
     */
    public Scene getPlayer () {
        return playerScene;
    }

    /*
     * Main to start the Player
     */

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage arg0) throws Exception {
        Stage myStage = new Stage();
        myStage.setWidth(Main.SCREEN_WIDTH);
        myStage.setHeight(Main.SCREEN_HEIGHT);
        myStage.setScene(playerScene);
        myStage.show();
    }
}
