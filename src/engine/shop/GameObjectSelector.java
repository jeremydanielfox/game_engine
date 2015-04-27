package engine.shop;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;


public class GameObjectSelector {

    private Node prev = null;
    private ObjectProperty<GameObject> current = new SimpleObjectProperty<GameObject>();
    private Pane worldPane;

    private Consumer<GameObject> displayUpgrades;
    private Runnable removeSelf;

    private static final List<KeyCode> DESELECTION_KEYS = Arrays
            .asList(new KeyCode[] { KeyCode.ESCAPE });

    public GameObjectSelector (Consumer<GameObject> displayUpgrades, Runnable removeSelf, Pane worldPane) {
        
        this.worldPane = worldPane;
        this.removeSelf = removeSelf;
        this.displayUpgrades = displayUpgrades;

        initialize();

    }

    private void initialize () {
        current.addListener( (obs, old, cur) -> {
            removeSelf.run();
            select(null);
            if (prev !=null){
                worldPane.getChildren().remove(prev);
            }

            
            if (cur != null) {
                Node rangeDisp = placeRangeDisplay(cur);
                prev = rangeDisp;
                displayUpgrades.accept(cur);
                worldPane.setOnKeyPressed(event -> {
                    if (DESELECTION_KEYS.contains(event.getCode())) {
                        removeSelf.run();
                        worldPane.getChildren().remove(rangeDisp);
                        worldPane.setOnKeyPressed(null);
                    }
                });
            }
        });
    }
    
    private Node placeRangeDisplay(GameObject current){
        Node rangeDisp = current.getRangeDisplay().getNode();
        PointSimple point = current.getPoint();

        rangeDisp.relocate(point.getX(), point.getY());
        worldPane.getChildren().add(rangeDisp);
        return rangeDisp;
    }

    public void select (GameObject obj) {
        current.set(obj);
    }

}
