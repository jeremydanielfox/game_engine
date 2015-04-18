package gae.waveeditor;

import engine.events.ConstantSpacingWave;
import engine.events.Wave;
import gae.openingView.UIObject;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

public class WaveSelectorPane implements UIObject {
    
    private ScrollPane rootNode;
    private List<Wave> waves;
    
    public WaveSelectorPane () {
        initialize();
    }
    
    private void initialize () {
        rootNode = new ScrollPane();
        rootNode.setHbarPolicy(ScrollBarPolicy.NEVER);
        rootNode.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
                
        ObservableList<Wave> data = FXCollections.observableArrayList();

        ListView<Wave> listView = new ListView<Wave>(data);
        listView.setPrefSize(200, 250);
        listView.setEditable(true);

        data.addAll(waves);

        listView.setItems(data);
//        listView.setOnMouseClicked(e -> cellClicked(e, listView.getSelectionModel().getSelectedItem()));
        
        Button newWaveButton = new Button ("New Wave");
        newWaveButton.setOnAction(e -> makeNewWave(e));
    }

    private void makeNewWave (ActionEvent e) {
        waves.add(new ConstantSpacingWave());
    }

    @Override
    public Node getObject () {
        return rootNode;
    }

}
