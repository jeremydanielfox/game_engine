package gae.waveeditor;

import engine.game.StoryBoard;
import gae.openingView.UIObject;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class WaveEditor implements UIObject {
    
    private BorderPane rootNode;
    private StoryBoard myStoryBoard;

    public WaveEditor(StoryBoard sb) {
        myStoryBoard = sb;
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
}
