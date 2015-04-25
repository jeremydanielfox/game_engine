package gae.waveeditor;

import engine.events.ConcreteQueue;
import engine.events.ConstantSpacingWave;
import engine.events.GameObjectQueue;
import engine.events.Wave;
import gae.openingView.UIObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class WaveSelectorPane implements UIObject {
    
    private ScrollPane rootNode;
    private ObservableList<WaveEnemyTable> waves;
    private WaveEditor parent;
    
    public WaveSelectorPane (WaveEditor parent) {
        this.parent = parent;
        initialize();
    }
    
    private void initialize () {
        rootNode = new ScrollPane();
        rootNode.setHbarPolicy(ScrollBarPolicy.NEVER);
        rootNode.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
                
        waves = FXCollections.observableArrayList();

        ListView<WaveEnemyTable> listView = new ListView<WaveEnemyTable>(waves);
        listView.setPrefSize(200, 250);
        listView.setEditable(true);

        listView.setItems(waves);
        listView.setOnMouseClicked(e -> parent.newWaveSelected(listView.getSelectionModel().getSelectedItem()));
        
        Button newWaveButton = new Button ("New Wave");
        newWaveButton.setOnAction(e -> makeNewWave());
        
        Button saveButton = new Button ("Save Waves");
        saveButton.setOnAction(e -> saveWaves());
        
        VBox scrollContents = new VBox();
        scrollContents.getChildren().addAll(listView, newWaveButton, saveButton);
        scrollContents.setSpacing(5);
        
        rootNode.setOnKeyPressed(e -> checkKeyPressed(e, listView));
        rootNode.setContent(scrollContents);
    }

    private void checkKeyPressed (KeyEvent e, ListView<WaveEnemyTable> listView) {
        if (e.getCode() == KeyCode.BACK_SPACE) {
            waves.remove((listView.getSelectionModel().getSelectedItem()));
        }
    }

    private void makeNewWave () {
        waves.add(new WaveEnemyTable(waves.size()+1));
    }
    
    private void saveWaves () {
        for (WaveEnemyTable wave : waves) {            
            GameObjectQueue wQueue = new ConcreteQueue(wave.getEnemiesAsList());
            Wave newWave = new ConstantSpacingWave(wave.getPreferencesPane().getSpacingTime(), wQueue, null); //Last argument is a GameWorld object
            parent.getStoryboard().addEvent(newWave);
        }        
        
        System.out.println("check");
  }

    @Override
    public Node getObject () {
        return rootNode;
    }

}
