package gae.gameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import engine.gameobject.GameObject;
import engine.gameobject.Graphic;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * List of options that can be checked to indicate selection
 * 
 * @author Brandon Choi
 *
 */

public class CheckList {

    private VBox checkList;
    private List<GameObject> myObjects;
    private Map<GameObject, Boolean> myMap;

    public CheckList (List<GameObject> objects) {
        checkList = new VBox(15);
        myObjects = objects;
        myMap=new HashMap<>();
        myObjects.forEach(e -> {
            createCheckOption(e);
        });
    }

    public Node getCheckList () {
        return checkList;
    }

    /**
     * create one check option based on object given
     * 
     * @param s
     */
    private void createCheckOption (GameObject o) {
        HBox checkOption = new HBox(10);
        Graphic graphic=o.getTag().getGraphic().clone();
        graphic.setHeight(50);
        Node image=graphic.getResizedGraphic(1);
        Label label = new Label( o.getTag().getName());
        CheckBox checker = new CheckBox();
        checkOption.getChildren().addAll(image, label, checker);
        myMap.put(o, checker.isSelected());
        checker.selectedProperty().addListener((obs, old, newVal)->{
             myMap.put(o, newVal);
        });
        checkList.getChildren().add(checkOption);
    }
    
    public List<GameObject> getSelected(){
        List<GameObject> selected=new ArrayList<>();
        for(GameObject key: myMap.keySet()){
            if(myMap.get(key)) selected.add(key);
        }
        return selected;
    }
}
    
    
