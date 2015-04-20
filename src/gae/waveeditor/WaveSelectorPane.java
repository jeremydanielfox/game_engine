package gae.waveeditor;

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
        makeNewWave();
        makeNewWave();
        makeNewWave();

        ListView<WaveEnemyTable> listView = new ListView<WaveEnemyTable>(waves);
        listView.setPrefSize(200, 250);
        listView.setEditable(true);

        listView.setItems(waves);
        listView.setOnMouseClicked(e -> parent.newWaveSelected(listView.getSelectionModel().getSelectedItem()));
        
        Button newWaveButton = new Button ("New Wave");
        newWaveButton.setOnAction(e -> makeNewWave());
        
        VBox scrollContents = new VBox();
        scrollContents.getChildren().addAll(listView, newWaveButton);
        
        rootNode.addEventHandler(KeyEvent.KEY_PRESSED, e -> checkKeyPressed(e, listView));
        rootNode.setContent(scrollContents);
    }

    private void checkKeyPressed (KeyEvent e, ListView<WaveEnemyTable> listView) {
        System.out.println("Check");
        if (e.getCode().equals(KeyCode.DELETE)) {
            waves.remove((listView.getSelectionModel().getSelectedItem()));
        }
    }

    private void makeNewWave () {
        waves.add(new WaveEnemyTable(waves.size()+1));
    }

    @Override
    public Node getObject () {
        return rootNode;
    }

}
