package gae.waveeditor;

import engine.events.GameObjectQueue;
import gae.openingView.UIObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class WavePart implements UIObject {

    private HBox rootNode;
    private GameObjectQueue myWaveQueue;
    private WaveEnemyTable parent;

    public WavePart(WaveEnemyTable parent) {
        this.parent = parent;
        initialize();
    }

    private void initialize () {
        rootNode = new HBox();

        //TODO: make pull from created game objects
        ObservableList<String> options = 
                FXCollections.observableArrayList(
                                                  "Enemy 1",
                                                  "Enemy 2",
                                                  "Enemy 3"
                        );
        ComboBox<String> comboBox = new ComboBox<String>(options);
        
        TextField quantityField = new TextField("Enter Quantity");
        quantityField.setEditable(true);
        
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> parent.deleteWavePart(this));
        
        rootNode.getChildren().addAll(new Text("Enemy Type: "), comboBox, new Text ("Quantity"), quantityField, deleteButton);
        rootNode.setSpacing(5);
    }

    @Override
    public Node getObject () {
        return rootNode;
    }
}
