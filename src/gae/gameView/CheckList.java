package gae.gameView;

import java.util.List;

import engine.gameobject.GameObject;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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

    public CheckList (List<GameObject> objects) {
        checkList = new VBox(15);
        myObjects = objects;
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
        Label label = (Label) o.getLabel();
        CheckBox checker = new CheckBox();
        checkOption.getChildren().add(checker);
        checkList.getChildren().addAll(label,checkOption);
    }
}
