package player.gamePlayer;

import java.util.Arrays;
import gae.gameView.Main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
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
        playerScene.getStylesheets().add("/css/GamePlayerCSS.css");
        options = new HBox(50);
        options.setId("optionBox");
        header = new Text(headerText);
        header.setId("playerHeader");
        gameSelector = new GameSelector(playerScene);
        setUpButtons();
        setUpBorderPane();
        createOptions();
    }

    private void createOptions () {
        options.getChildren().addAll(loadB, playB);
    }

    /**
     * sets up functionalities of buttons on the UI
     */
    private void setUpButtons () {
        loadB = new Button("LOAD GAME");
        loadB.setOnMousePressed(e -> {

        });
        playB = new Button("PLAY");
        playB.setOnMousePressed(e -> {

        });

        Arrays.asList(loadB, playB).forEach(e -> {
            e.setId("playerButton");
        });
    }

    /**
     * sets up different border areas of the border pane
     */
    private void setUpBorderPane () {
        view.setCenter(gameSelector.getChooser());
        view.setTop(header);
        view.setBottom(options);
    }

    public Scene getPlayer () {
        return playerScene;
    }

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
