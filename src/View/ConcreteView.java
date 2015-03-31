package View;

import java.util.Observable;
import java.util.Observer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import game.Drawer;
import game.Game;
import game.LevelBoard;

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
    private Group myGroup;
    
    public ConcreteView(Game game,Group group) {
        myGame=game;
        myLevelBoard=myGame.getLevelBoard();
        myGroup=group;
    }
    
    //look into removing Drawer
    @Override
    public void initializeGameWorld (Drawer drawer) {
        ImageView image = new ImageView(myLevelBoard.getCurrentLevelMap());
        image.setPreserveRatio(true);
        image.setFitWidth(500);
        myGroup.getChildren().add(image);
        PopUpScreen popup=new PopUpScreen();
        popup.makeScreen("Begin Level 1", "Start"); // these should be from resource files
        buildTimeline();
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
    }

    @Override
    public void displayShop () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update (Observable o, Object arg) {
        if (myLevelBoard.equals(o)) {
            pause();
            if (myLevelBoard.gameOver()) {
                //note: display game over screen
                //ideally gamePlayer/observers should be notified here
            }
            else {
                myLevelBoard.startNextLevel();
                //display new background
                //display new sprites
                //popup window
                //then after closing popup window, play();
            }
        }
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
