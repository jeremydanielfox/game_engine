package gae.waveeditor;

import engine.events.GameObjectQueue;
import gae.openingView.UIObject;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class WaveEnemyTable implements UIObject {
    private ScrollPane rootNode;
    private WavePreferencesPane myPreferences;
    
    private GameObjectQueue myEnemies;
    
    public WaveEnemyTable() {
        initialize();
    }

    private void initialize () {
        myPreferences = new WavePreferencesPane();
        rootNode = new ScrollPane();
    }

    @Override
    public Node getObject () {
        return rootNode;
    }

    public UIObject getPreferencesPane () {
        return myPreferences;
    }

}
