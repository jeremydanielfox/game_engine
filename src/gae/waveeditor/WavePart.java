package gae.waveeditor;

import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import gae.listView.LibraryData;
import gae.openingView.UIObject;
import java.util.List;
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
    private WaveEnemyTable parent;
    private TextField quantityField;
    private ComboBox<GameObjectSimple> gameObjectSelector;

    public WavePart(WaveEnemyTable parent) {
        this.parent = parent;
        initialize();
    }

    private void initialize () {
        rootNode = new HBox();

        //TODO: make pull from created game objects
        
        LibraryData libData = LibraryData.getInstance();
        ObservableList<GameObjectSimple> enemyList = libData.getGameObjectList();
        
        gameObjectSelector = new ComboBox<GameObjectSimple>(enemyList);
        
        quantityField = new TextField("Enter Quantity");
        quantityField.setEditable(true);
        
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> parent.deleteWavePart(this));
        
        rootNode.getChildren().addAll(new Text("Enemy Type: "), gameObjectSelector, new Text ("Quantity"), quantityField, deleteButton);
        rootNode.setSpacing(5);
        
    }

    @Override
    public Node getObject () {
        return rootNode;
    }

    public int getQuantity () {
        return Integer.parseInt(quantityField.getText());
    }
    
    public GameObject getGameObject() {
        return gameObjectSelector.getSelectionModel().getSelectedItem();
    }
}
