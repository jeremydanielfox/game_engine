package gae.waveeditor;

import engine.gameobject.GameObject;
import gae.openingView.UIObject;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * The holder of wave parts which comprise a wave
 * @author JohnGilhuly
 *
 */

public class WaveEnemyTable implements UIObject {
    private ScrollPane rootNode;
    private WavePreferencesPane myPreferences;
    private String myText;
    private VBox wavePartsBox;
    private ObservableList<WavePart> myWaveParts;
    
    public WaveEnemyTable(int number) {
        myText = "Wave " + number;
        initialize();
    }

    private void initialize () {
        myPreferences = new WavePreferencesPane();
        rootNode = new ScrollPane();
        myWaveParts = FXCollections.observableArrayList();
        
        Button newWavePart = new Button("New Wave Part");
        newWavePart.setOnAction(e -> makeNewWavePart());
        
        wavePartsBox = new VBox();
        wavePartsBox.setSpacing(10);
        updateWavePartsBox();
        
        VBox scrollContents = new VBox();
        scrollContents.getChildren().addAll(wavePartsBox, newWavePart);
        scrollContents.setSpacing(10);
        
        rootNode.setContent(scrollContents);
    }

    private void updateWavePartsBox () {
        wavePartsBox.getChildren().clear();
        
        for (WavePart wp : myWaveParts) {
            wavePartsBox.getChildren().add(wp.getObject());
        }
    }

    private void makeNewWavePart () {
        myWaveParts.add(new WavePart(this));
        updateWavePartsBox();
    }

    @Override
    public Node getObject () {
        return rootNode;
    }

    public WavePreferencesPane getPreferencesPane () {
        return myPreferences;
    }
    
    @Override
    public String toString() {
        return myText;
    }

    public void deleteWavePart (WavePart wavePart) {
        myWaveParts.remove(wavePart);
        updateWavePartsBox();
    }

    public List<GameObject> getEnemiesAsList () {
        List<GameObject> enemies = new ArrayList<GameObject>();
        for (WavePart wp: myWaveParts) {
            for (int i = 0; i < wp.getQuantity(); i++) {                
                enemies.add(wp.getGameObject());
            }
        }
        return enemies;
    }

}
