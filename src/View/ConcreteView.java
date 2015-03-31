package View;

import java.util.Observable;
import java.util.Observer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
    private Integer myRate;
    private Timeline myAnimation;
    private LevelBoard myLevelBoard;
    
    public ConcreteView(Game game) {
        myGame=game;
        myLevelBoard=myGame.getLevelBoard();
    }
    
    //look into removing Drawer
    @Override
    public void initializeGameWorld (Drawer drawer) {
        // TODO Auto-generated method stub
        buildTimeline();
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
