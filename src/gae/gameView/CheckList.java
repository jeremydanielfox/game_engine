package gae.gameView;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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

    public CheckList (List<? extends GameObject> objects) {
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
        Label label = (Label) o.getLabel();
        CheckBox checker = new CheckBox();
        checkOption.getChildren().add(checker);
        checkList.getChildren().addAll(label, checkOption);
    }
}
