package gae.gameView;

import java.util.Arrays;
import java.util.List;

import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    public VBox getCheckList () {
        return checkList;
    }

    /**
     * create one check option based on object given
     * 
     * @param s
     */
    private void createCheckOption (GameObject o) {
        HBox checkOption = new HBox(10);
        CheckBox checker = new CheckBox("s");
        checkOption.getChildren().add(checker);
        checkList.getChildren().add(checkOption);
    }
}
