package View;

import java.util.Observable;
import java.util.Observer;
import engine.game.Drawer;
import engine.game.Game;
import engine.game.LevelBoard;
import gae.gameView.Main;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * 
 * @authors Sierra Smith, Cosette Goldstein
 *
 */

public class ConcreteView implements View, Observer {

    private Game myGame;
    private Integer myRate=200;
    private Timeline myAnimation;
    private LevelBoard myLevelBoard;
    private Group myGameWorldGroup;
    private Group myTotalGroup;
    
    private double myDisplayWidth;
    private double myDisplayHeight;
    
    public ConcreteView(Game game,Group group,double width,double height) {
        myGame=game;
        myLevelBoard=myGame.getLevelBoard();
        myLevelBoard.addObserver(this);
        myTotalGroup=group;
        myGameWorldGroup=new Group();
        myTotalGroup.getChildren().add(myGameWorldGroup);
        myDisplayWidth=width;
        myDisplayHeight=height;
    }
    
    //look into removing Drawer
    @Override
    public void initializeGameWorld () {
        //System.out.println("initializing world");
        ImageView image = new ImageView(myLevelBoard.getCurrentLevelMap());
        image.setPreserveRatio(true);
        image.setFitHeight(myDisplayHeight);
        myGameWorldGroup.getChildren().add(image);
        HeadsUpDisplay headsUp=new HeadsUpDisplay(myGame.getPlayer());
        HBox hbox=headsUp.makeDisplay();
        hbox.setLayoutY(hbox.getLayoutX()+500);
        buildTimeline();
        //for testing purposes:
        PopUpScreen popup=new PopUpScreen();
        popup.makeScreen("Begin Level 1", "Start"); // these should be from resource files
        Button btn=new Button("Dec");
        btn.setOnAction(e->myGame.getPlayer().changeHealth(-100));
        Button btn2=new Button("Inc");
        btn2.setTranslateX(btn2.getLayoutX()+200);
        btn2.setOnAction(e->myGame.getPlayer().increaseScore(100));//.changeScore(100));
        myTotalGroup.getChildren().addAll(btn,hbox,btn2);
        play();
    }

    @Override
    public void buildTimeline () {
        KeyFrame frame = makeKeyFrame(60);
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Animation.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
    }
    
    private KeyFrame makeKeyFrame(int frameRate) {
        return new KeyFrame(Duration.millis(myRate / frameRate),
                            e->executeFrameActions());
    }

    @Override
    public void executeFrameActions () {
        //after updating game, how to update after level ends? need to look into checking something like gameEnded()
        myGame.update();
        //System.out.println("Game loop running");
    }

    @Override
    public void displayShop () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update (Observable o, Object arg) {
        System.out.println("in update method");
        if (myLevelBoard.equals(o)) {
            pause();
            System.out.println("calling game over check");
            if (myLevelBoard.gameOver()) {
                //note: display game over screen
                PopUpScreen gameOver=new PopUpScreen();
                gameOver.makeScreen("GAME OVER", "Play Again");
                //ideally gamePlayer/observers should be notified here
                
            }
            else {
                myLevelBoard.startNextLevel();
                //display new background
                //display new sprites
                //popup window
                //then after closing popup window, play();
                ImageView image = new ImageView(myLevelBoard.getCurrentLevelMap());
                image.setPreserveRatio(true);
                image.setFitHeight(myDisplayHeight);
                myGameWorldGroup.getChildren().clear();
                myGameWorldGroup.getChildren().add(image);
                //display new sprites
                play();
            }
        }
    }

    
    public void test() {
        pause();
        myLevelBoard.startNextLevel();
        //display new background
        //display new sprites
        //popup window
        //then after closing popup window, play();
        ImageView image = new ImageView(myLevelBoard.getCurrentLevelMap());
        image.setPreserveRatio(true);
        image.setFitHeight(myDisplayHeight);
        myGameWorldGroup.getChildren().clear();
        myGameWorldGroup.getChildren().add(image);
        //display new sprites
        play();
    }
    
    @Override
    public void pause () {
        myAnimation.pause();
    }

    @Override
    public void play () {
        myAnimation.play();
    }
    
}
