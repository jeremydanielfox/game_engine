package player.gamePlayer;

import View.EngineView;
import View.GameWriter;
import View.GameWriterConcrete2;
import View.ViewConcrete2;
import voogasalad.util.highscore.HighScoreController;
import voogasalad.util.highscore.HighScoreException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import engine.game.Game;
import engine.game.Player;
import gae.gameView.Main;
import gameworld.GameWorld;

/**
 * 
 * @author goldstein
 *
 */
public class GamePlayerScreen implements GameScene {

    private static final String USER_NAME_PROMPT_TEXT = "ENTER A NAME";

    private VBox myVbox;
    private Game myGame;
    private Stage myStage;
    private EngineView myGameView;
    private PauseScene pauseScreen;
    private BorderPane myPane;
    private String myPlayerName; // myPlayerName holds the name of the user to later be put into
                                 // high scores.
    private Scene myPreviousScene;
    private Scene myScene;

    public GamePlayerScreen (Stage s, Scene previousScene) {
        myStage = s;
        myStage.setResizable(false);

        myVbox = new VBox(30);

        pauseScreen = new PauseScene(e -> resumeGame(), myStage, null);
        myGame = loadGame();
        myPlayerName = "";
        myPreviousScene = previousScene;
    }

    /**
     * Creates the whole scene with image and information of game.
     * 
     * @return scene with borderpane
     */
    public Scene makeScene () {
        myPane = new BorderPane();
        myPane.setPadding(new Insets(0, 40, 0, 0));
        myScene = new Scene(myPane);
        makeSideBar();
        myVbox.setAlignment(Pos.CENTER);
        myPane.setRight(myVbox);

        VBox gameTypeImageVBox = new VBox();
        // this image below needs to be the image that was selected when the play button was pushed
        // from the previous scene
        ImageView image = new ImageView("images/Park_Path.png");
        image.setPreserveRatio(true);
        image.setFitHeight(Main.SCREEN_HEIGHT - 100);
        gameTypeImageVBox.getChildren().addAll(image);
        gameTypeImageVBox.setAlignment(Pos.CENTER);
        myPane.setLeft(gameTypeImageVBox);
        return myScene;
    }

    /**
     * @return myPlayerName user input of name to begin game
     */
    public String getPlayerName () {
        return myPlayerName;
    }

    /**
     * Creates initialized View for the game.
     * 
     * @returns a node to add to the scene
     */
    public Node makeDemoGameView () {

        myGameView = new ViewConcrete2(myGame, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT,new GameOverScreen(myStage,myScene,myPreviousScene,myGame));
        Node node = myGameView.initializeView();
        return node;

    }

    /**
     * Creates display of information taken from the Game.
     */
    public void makeSideBar () {

        addDetails("Name", myGame.getGameName());
        addDetails("Description", myGame.getDescription());
        addDetails("Instructions", myGame.getInstructions());

        Button scoreBtn = new Button("View high scores");
        scoreBtn.setOnAction(e -> displayScores());

        Button playBtn = new Button("PLAY");
        playBtn.setOnAction(e -> openMainMenu());

        Button backBtn = new Button("BACK");
        backBtn.setOnAction(e -> changeScene(myPreviousScene));

        myVbox.getChildren().addAll(scoreBtn, playBtn, backBtn);
        myVbox.setAlignment(Pos.CENTER);
    }

    /**
     * Resumes game after pause.
     */
    private void resumeGame () {

    }

    /**
     * Creates a label and text for adding information to the sidebar.
     * 
     * @param label
     * @param text
     */
    private void addDetails (String label, String text) {
        VBox insideBox = new VBox(10);
        insideBox.setMaxWidth(400);
        Label labelText = new Label(label);
        Label description = new Label(text);

        labelText.setWrapText(true);
        description.setWrapText(true);

        description.setAlignment(Pos.CENTER);
        insideBox.getChildren().addAll(labelText, description);
        insideBox.setAlignment(Pos.CENTER);
        myVbox.getChildren().addAll(insideBox);
    }

    /**
     * Displays high scores using HighScoreController from the high score utility.
     */
    public void displayScores () {
        HighScoreController scores = HighScoreController.getController();
        try {
            scores.displayHighScores(myGame.getGameName(), "Score:", 500, 500);
        }
        catch (HighScoreException e) {
            // TODO Auto-generated catch block
            System.out.println("Issue with displaying high scores.");
            e.printStackTrace();
        }
    }

    /**
     * Changes scene displayed in stage.
     * (useful mainly for back button when changing back to myPreviousScene)
     * 
     * @param sceneToChange
     */
    private void changeScene (Scene sceneToChange) {
        myStage.setScene(sceneToChange);
//         GameOverScreen gameover=new GameOverScreen(myStage,myScene,myPreviousScene,myGame);
  //       myStage.setScene(gameover.getScene());
    }

    /**
     * PopUp with name entry field to start game.
     */
    private void openMainMenu () {
        VBox vBox = new VBox();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(vBox, 0, 0);
        Scene popUpScene = new Scene(grid);
        Stage popUpStage = new Stage();

        Text enterNameText = new Text("Enter your name:");
        TextField enterName = new TextField();
        Button btn = new Button("Play Game!");
        btn.setOnAction(e -> handleNameButtonAction(popUpStage, enterName));

        vBox.getChildren().addAll(enterNameText, enterName, btn);
        vBox.setAlignment(Pos.CENTER);
        popUpStage.setScene(popUpScene);
        popUpStage.show();
    }

    /**
     * Checks to make sure that user inputs name in order to start game.
     * 
     * @param popUpStage to close after inputting name
     * @param enterName textfield to get user inputted name
     */
    private void handleNameButtonAction (Stage popUpStage, TextField enterName) {
        if (!enterName.getText().isEmpty() && enterName.getText().trim() != null) {
            myPlayerName = enterName.getText();
            myGame.getPlayer().setName(myPlayerName);
            popUpStage.close();
            startGame();
        }
        else {
            enterName.setPromptText(USER_NAME_PROMPT_TEXT);
        }
    }

    /**
     * Sets new scene to stage to start the game.
     */
    private void startGame () {
        Group root = new Group();
        root.getChildren().add(makeDemoGameView());
        myScene = new Scene(root);
        myStage.setScene(myScene);
    }

    /**
     * Creates game by loading it in with the GameWriter.
     * 
     * @return
     */
    private Game loadGame () {
        GameWriterConcrete2 gw = new GameWriterConcrete2();
        GameWorld world = gw.makeWorld();
        Player player = gw.makePlayer();
        return gw.makeGame(player, world, gw.makeShop(player, world));
    }

    /**
     * Returns the current scene.
     */
    @Override
    public Scene getScene () {
        makeScene();
        return myScene;
    }
}
