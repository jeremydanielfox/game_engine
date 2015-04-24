package View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import engine.game.Game;
import engine.game.LevelBoard;
import engine.gameobject.GameObject;
import engine.goals.CanDecSpeedGoal;
import engine.goals.CanIncSpeedGoal;
import engine.goals.IsPlayingGoal;
import engine.goals.NotPlayingGoal;


public class ViewConcrete2 implements EngineView, Observer, ChangeableSpeed, Playable {

    public static final double GAME_WIDTH_TO_HEIGHT = 1;
    public static final int MAX_FRAME_RATE = 200;
    public static final int MIN_FRAME_RATE = 500;
    // TODO need to update this to be a function of min frame rate
    public static final int DEFAULT_FRAMES_SECOND = 60;

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

    public ViewConcrete2 (Game game, double width, double height) {
        myGame = game;
        myLevelBoard = myGame.getLevelBoard();
        myLevelBoard.addObserver(this);
        myDisplayWidth = width;
        myDisplayHeight = height;
    }

    @Override
    public Node initializeView () {
        myPane = new BorderPane();
        myGameWorldPane = new Pane();
        myGameWorldPane.setMaxWidth(myDisplayHeight * GAME_WIDTH_TO_HEIGHT);
        myPane.setCenter(myGameWorldPane);
        initializeGameWorld();
        return myPane;
    }

    @Override
    public void initializeGameWorld () {
        setCurrentBackground();
        myHeadsUp = new HUD(myPane, myGame.getShop());
        addControlButtons();
        for (Displayable d : myGame.getPlayer().getDisplayables()) {
            myHeadsUp.addPairedDisplay(d);
        }
        for (Displayable d : myGame.getLevelBoard().getCurrentLevel().getDisplayables()) {
            myHeadsUp.addPairedDisplay(d);
        }
        vbox.getChildren().add(myHeadsUp.getDisplay());

        addInitialObjects();

        myPane.setRight(vbox);
        buildTimeline();
        // for testing purposes:
        PopUpScreen popup = new PopUpScreen();
        popup.makeScreen("Begin Level 1", "Start"); // these should be from resource files
        Button btn = new Button("Dec");
        btn.setOnAction(e -> myGame.getPlayer().changeHealth(-100));
        Button btn2 = new Button("Inc");
        btn2.setTranslateX(btn2.getLayoutX());
        btn2.setOnAction(e -> myGame.getPlayer().changeScore(100));// .changeScore(100));
        vbox.getChildren().addAll(btn, btn2);
        myButtonList = myGame.getButtons();
        myButtonList.forEach(e -> vbox.getChildren().add(e.getButton()));
        play();
    }

    @Override
    public void buildTimeline () {
        KeyFrame frame = makeKeyFrame(60);
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Animation.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
    }

    private KeyFrame makeKeyFrame (int frameRate) {
        return new KeyFrame(Duration.millis(myRate / frameRate),
                            e -> executeFrameActions());
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

    @Override
    public void executeFrameActions () {
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
        hack.clear();
        for (GameObject o : gameObjects) {
            myGameWorldPane.getChildren().add(o.getGraphic().getNode());
            hack.add(o.getGraphic().getNode());
        }
    }

    @Override
    public void update (Observable o, Object arg) {
        if (myLevelBoard.equals(o)) {
            pause();
            if (myLevelBoard.gameOver()) {
                // note: display game over screen
                PopUpScreen gameOver = new PopUpScreen();
                gameOver.makeScreen("GAME OVER", "Play Again");
                // ideally gamePlayer/observers should be notified here

            }
            else {
                myLevelBoard.startNextLevel();
                // display new background
                // display new sprites
                // popup window
                // then after closing popup window, play();
                setCurrentBackground();
                // display new sprites
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
        image.setFitHeight(600);
        image.setFitWidth(600);
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
    public void addButton (Button b, int x, int y) {
        vbox.getChildren().add(b);
    }

    @Override
    public boolean isPlaying () {
        return myAnimation.getStatus().equals(Status.RUNNING);
    }

}
