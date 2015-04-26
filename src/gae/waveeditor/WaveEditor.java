package gae.waveeditor;

import engine.game.StoryBoard;
import gae.openingView.UIObject;
import gameworld.GameWorld;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * Class to edit the composition of waves for a given level
 * @author John Gilhuly
 *
 */

public class WaveEditor implements UIObject {
    
    private BorderPane rootNode;
    private StoryBoard myStoryBoard;
    private GameWorld myGameWorld;

    public WaveEditor(StoryBoard storyBoardIn, GameWorld gameWorldIn) {
        myStoryBoard = storyBoardIn;
        myGameWorld = gameWorldIn;
        initialize();
    }

    private void initialize () {
        rootNode = new BorderPane();
        WaveSelectorPane waveSelectorPane = new WaveSelectorPane(this);
        
        rootNode.setLeft(waveSelectorPane.getObject());
    }
    
    public void newWaveSelected (WaveEnemyTable newWET) {
        rootNode.setCenter(newWET.getObject());
        rootNode.setRight(newWET.getPreferencesPane().getObject());
    }

    @Override
    public Node getObject () {
        return rootNode;
    }

    public StoryBoard getStoryboard () {
        return myStoryBoard;
    }
    
    public GameWorld getGameWorld() {
        return myGameWorld;
    }
}