package View;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import engine.game.Game;
import engine.game.LevelBoard;
import engine.gameobject.GameObject;


public class ViewConcrete2 implements View, Observer {

    public static final double GAME_WIDTH_TO_HEIGHT = 1.25;

    private Game myGame;
    private Integer myRate = 200;
    private Timeline myAnimation;
    private LevelBoard myLevelBoard;

    // old way
    private Pane myGameWorldPane;
    // private Group myTotalGroup;

    // new way
    private BorderPane myPane;
    
    private VBox vbox=new VBox();

    private double myDisplayWidth;
    private double myDisplayHeight;

    public ViewConcrete2 (Game game, double width, double height) {
        myGame = game;
        myLevelBoard = myGame.getLevelBoard();
        myLevelBoard.addObserver(this);
        //vbox=new VBox();
        // myTotalGroup=group;

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
        HUD headsUp = new HUD(myPane);
        for (Displayable d : myGame.getPlayer().getDisplayables()) {
            headsUp.addPairedDisplay(d);
        }
        vbox.getChildren().add(headsUp.getDisplay());

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
        // myTotalGroup.getChildren().addAll(btn,vbox,btn2);
        vbox.getChildren().addAll(btn, btn2);
        myGame.getButtons().forEach(e->vbox.getChildren().add(e.getButton()));
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
    public void executeFrameActions () {
        // after updating game, how to update after level ends? need to look into checking something
        // like gameEnded()
        // myGame.update();
    }

    @Override
    public void displayShop () {
        // TODO Auto-generated method stub

    }

    private void addInitialObjects () {
        Collection<GameObject> gameObjects = myGame.getLevelBoard().getGameWorld().getGameObjects();
        for (GameObject o : gameObjects) {
            myGameWorldPane.getChildren().add(o.getGraphic().getNode());
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
        
        //Look back at this to think about if statements.
        //Also, alternatively, check if it already exists in gameWorld opposed to just in View.
        if (myLevelBoard.getGameWorld().equals(o)) {
            if (!(arg.equals(null)) && arg instanceof GameObject) {
                Node node=((GameObject) arg).getGraphic().getNode();
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
        image.setFitHeight(myDisplayHeight);
        image.setFitWidth(myDisplayHeight * GAME_WIDTH_TO_HEIGHT);
        myGameWorldPane.getChildren().clear();
        myGameWorldPane.getChildren().add(image);
    }
    

    @Override
    public void pause () {
        myAnimation.pause();
    }

    @Override
    public void play () {
        myAnimation.play();
    }

    public void addButton(Button b, int x,int y) {
        vbox.getChildren().add(b);
    }
    
}
