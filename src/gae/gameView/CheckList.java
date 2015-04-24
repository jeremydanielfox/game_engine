package gae.gameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
<<<<<<< HEAD
import java.util.Map;
import engine.gameobject.GameObject;
import engine.gameobject.Graphic;
=======
>>>>>>> d7e5488c3a9d55c05610a2d255bb60d993598bff
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import engine.gameobject.GameObject;


/**
 * List of options that can be checked to indicate selection
 *
 * @author Brandon Choi
 *
 */

public class CheckList {

    private VBox checkList;
    private List<? extends GameObject> myObjects;
    private Map<GameObject, Boolean> myMap;

    public CheckList (List<? extends GameObject> objects) {
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
     * Called by outside class to display the check list
     */
    public void showCheckList () {
        Stage temp = new Stage();
        Scene scene = new Scene(checkList);
        temp.setScene(scene);
        temp.show();
        temp.centerOnScreen();
    }

    /*
     * TODO: differentiate between using a Label object in JavaFX or a label as a type?
     */

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
    
    
