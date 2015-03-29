package View;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import game.Drawer;
import game.Game;

public class ConcreteView implements View {

    private Game myGame;
    private Integer myRate;
    private Timeline myAnimation;
    
    public ConcreteView(Game game) {
        myGame=game;
    }
    
    
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
        myAnimation.play();
        
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
    
}
