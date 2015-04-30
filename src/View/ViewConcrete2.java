package View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import player.gamePlayer.GameOverScreen;
import player.gamePlayer.GamePlayerScreen;
import player.gamePlayer.PauseDropDown;
import voogasalad.util.highscore.HighScoreController;
import voogasalad.util.highscore.HighScoreException;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.util.Duration;
import engine.game.Game;
import engine.game.LevelBoard;
import engine.gameobject.GameObject;
import engine.goals.CanDecSpeedGoal;
import engine.goals.CanIncSpeedGoal;
import engine.goals.IsPlayingGoal;
import engine.goals.NotPlayingGoal;


public class ViewConcrete2 implements EngineView, Observer, ChangeableSpeed, Playable {

    private static final int DEFAULT_FRAME_RATE = 60;
    public static final int MAX_FRAME_RATE = 200;
    public static final int MIN_FRAME_RATE = 500;
    public static final double WORLD_WIDTH = 623;
    public static final double WORLD_HEIGHT = 623;
    // public static final int MAX_FRAME_RATE = 500;
    // public static final int MIN_FRAME_RATE = 1000;
    public static final int DEFAULT_FRAMES_SECOND =
            (int) ((double) 1 / ((double) ((double) MIN_FRAME_RATE / (double) DEFAULT_FRAME_RATE) / 1000));

    private List<Node> hack = new ArrayList<Node>();
    private Game myGame;
    private Integer myRate = MIN_FRAME_RATE;
    private Timeline myAnimation;
    private LevelBoard myLevelBoard;
    private Pane myGameWorldPane;
    private BorderPane myPane;
    private VBox vbox = new VBox();
    private List<ButtonWrapper> myButtonList;
    private double myDisplayWidth;
    private double myDisplayHeight;
    private HUD myHeadsUp;
    private GameOverScreen myEndScreen;
    private PauseDropDown pauseScreen;

    public ViewConcrete2 (Game game,
                          double stageWidth,
                          double stageHeight, GameOverScreen screen, PauseDropDown pauser) {
        myGame = game;
        myLevelBoard = myGame.getLevelBoard();
        myLevelBoard.addObserver(this);
        myDisplayWidth = stageWidth;
        myDisplayHeight = stageHeight;
        myEndScreen=screen;
        pauseScreen = pauser;
    }
    
    public ViewConcrete2 (Game game,
                          double stageWidth,
                          double stageHeight) {
        myGame = game;
        myLevelBoard = myGame.getLevelBoard();
        myLevelBoard.addObserver(this);
        myDisplayWidth = stageWidth;
        myDisplayHeight = stageHeight;
        
    }

    @Override
    public Node initializeView () {
        myPane = new BorderPane();
        myGameWorldPane = new Pane();
        
        myPane.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode().equals(KeyCode.P)){
                this.pause();
                pauseScreen.displayPauseScreen();
            }
        });
        
        myGameWorldPane.setMaxWidth(myDisplayHeight);
        myPane.setCenter(myGameWorldPane);
        initializeGameWorld();
        // vbox.setFocusTraversable(false);
        return myPane;
    }

    @Override
    public void initializeGameWorld () {
        setCurrentBackground();
        myHeadsUp = new HUD(myPane, myGame.getShop());
        addControlButtons();
        for (Displayable d : myGame.getPlayer().getDisplayables()) {
            myHeadsUp.addStatsDisplay(d);
        }
        addLevelDisplays();
        vbox.getChildren().add(myHeadsUp.getDisplay());

        addInitialObjects();

        myPane.setRight(vbox);
        buildTimeline();
        
        Button btn = new Button("Dec");
        btn.setOnAction(e -> myGame.getPlayer().changeHealth(-100));
        Button btn2 = new Button("Inc");
        btn2.setTranslateX(btn2.getLayoutX());
        btn2.setOnAction(e -> myGame.getPlayer().changeScore(100));
        vbox.getChildren().addAll(btn, btn2);
        myButtonList = myGame.getButtons();
        myButtonList.forEach(e -> vbox.getChildren().add(e.getButton()));
        play();
    }

    private void addLevelDisplays () {
        for (Displayable d : myGame.getLevelBoard().getCurrentLevel().getDisplayables()) {
            myHeadsUp.addLevelDisplay(d);
        }
    }

    private void buildTimeline () {
        KeyFrame frame = makeKeyFrame(DEFAULT_FRAME_RATE);
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Animation.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
    }

    private KeyFrame makeKeyFrame (int frameRate) {
        return new KeyFrame(Duration.millis(myRate / frameRate),
                            e -> executeFrameActions());
        // return new KeyFrame(Duration.millis(myRate / frameRate),
        // e -> executeFrameActions());
    }

    @Override
    public void toggleRate () {
        // TODO
        Animation.Status previousStatus = myAnimation.getStatus();
        myAnimation.stop();
        // changeFramesPerSecondValue(speedChangeMultiplier);
        if (myRate <= MAX_FRAME_RATE) {
            myRate = MIN_FRAME_RATE;
        }
        else {
            myRate = MAX_FRAME_RATE;
        }
        buildTimeline();
        restoreLastAnimationStatus(previousStatus);
    }

    @Override
    public boolean canIncSpeed () {
        return myRate >= MIN_FRAME_RATE;
    }

    @Override
    public boolean canDecSpeed () {
        return myRate <= MAX_FRAME_RATE;
    }

    private void restoreLastAnimationStatus (Animation.Status prevStatus) {
        myHeadsUp.update();
        if (prevStatus.equals(Animation.Status.RUNNING)) {
            play();
        }
    }

    private void executeFrameActions () {
        // after updating game, how to update after level ends? need to look into checking something
        // like gameEnded()
        myGame.update();
        addInitialObjects();
        myButtonList.forEach(e -> {
            if (e.isEnabled()) {
                e.getButton().setDisable(false);
            }
                else {
                    e.getButton().setDisable(true);
                }
            });
        myHeadsUp.update();
    }

    private void addInitialObjects () {// This is actually a rendering method now.
        Collection<GameObject> gameObjects = myGame.getLevelBoard().getGameWorld().getGameObjects();
        for (Node n : hack) {
            myGameWorldPane.getChildren().remove(n);
        }
        List<Node> buffer = new ArrayList<Node>();
        for (GameObject o : gameObjects) {
            if (hack.contains(o.getGraphic().getNode()))//If this object existed last frame, add. If first frame, don't add.
                myGameWorldPane.getChildren().add(o.getGraphic().getNode());
            buffer.add(o.getGraphic().getNode());
        }
        hack.clear();
        buffer.forEach(b -> hack.add(b));
    }

    @Override
    public void update (Observable o, Object arg) {
        System.out.println("being notified that a level has ended.");
        if (myLevelBoard.equals(o)) {
            pause();
            if (myLevelBoard.gameOver()) {
                HighScoreController scores = HighScoreController.getController();
                // TODO eliminate magic values
                try {
                    scores.setValue(myGame.getGameName(), 0, "Score", myGame.getPlayer().getScore());
                    scores.saveInstanceScoreData(myGame.getGameName(), 0, myGame.getPlayer()
                            .getName());
                }
                catch (HighScoreException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println("Issue saving when game ends");
                }
                if (myLevelBoard.isLost())
                    myEndScreen.setResultsText("YOU LOST");
                else if (myLevelBoard.isWon())
                    myEndScreen.setResultsText("YOU WON");
                myEndScreen.setScene();
            }
            else {
                myLevelBoard.startNextLevel();
                System.out.println("changing to level 2");
                myHeadsUp.clearLevelDisplay();
                addLevelDisplays();
                setCurrentBackground();
                play();
            }
        }

        // Look back at this to think about if statements.
        // Also, alternatively, check if it already exists in gameWorld opposed to just in View.
        if (myLevelBoard.getGameWorld().equals(o)) {
            if (!(arg.equals(null)) && arg instanceof GameObject) {
                Node node = ((GameObject) arg).getGraphic().getNode();
                if (myGameWorldPane.getChildren().contains(node)) {
                    myGameWorldPane.getChildren().remove(node);
                }
                else {
                    myGameWorldPane.getChildren().add(node);
                }
            }
        }
    }

    private void setCurrentBackground () {
        ImageView image = new ImageView(myLevelBoard.getCurrentLevelMap());
        // image.setFitHeight(myDisplayHeight);
        // image.setFitWidth(myDisplayHeight * GAME_WIDTH_TO_HEIGHT);
        /*
         * I changed this to 600 because that's the size of the container - the relative thing works
         * I think - Kei
         */
        image.setFitHeight(WORLD_HEIGHT);
        image.setFitWidth(WORLD_WIDTH);
        myGameWorldPane.getChildren().clear();
        myGameWorldPane.getChildren().add(image);
    }

    private void addControlButtons () {
        myHeadsUp.addButton(new ButtonWrapper("Play", e -> play(), new NotPlayingGoal(this)));
        myHeadsUp.addButton(new ButtonWrapper("Pause", e -> pause(), new IsPlayingGoal(this)));
        myHeadsUp
                .addButton(new ButtonWrapper("Slow", e -> toggleRate(), new CanDecSpeedGoal(this)));
        myHeadsUp
                .addButton(new ButtonWrapper("Fast", e -> toggleRate(), new CanIncSpeedGoal(this)));
    }

    public static double getWorldHeight () {
        return WORLD_HEIGHT;
    }

    public static double getWorldWidth () {
        return WORLD_WIDTH;
    }

    @Override
    public void pause () {
        myAnimation.pause();
        myHeadsUp.update();
    }

    @Override
    public void play () {
        myAnimation.play();
        myHeadsUp.update();
    }

    @Override
    public boolean isPlaying () {
        return myAnimation.getStatus().equals(Status.RUNNING);
    }
}
