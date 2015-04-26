package gae.gameView;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.labels.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Checklist for labels
 * 
 * @author Nina Sun
 */
public class LabelCheckList extends CheckList {

    private List<Label> myObjects;

    public LabelCheckList (List<Label> objects) {
        super();
        myObjects = objects;
        myObjects.forEach(e -> {
            createCheckOption(new LabelCheckListItem(e));
        });
    }

    /**
     * Called by outside class to display the check list
     */
    public void showCheckList () {
        Stage temp = new Stage();
        Scene scene = new Scene((Parent) getCheckList());
        temp.setScene(scene);
        temp.show();
        temp.centerOnScreen();
    }

    /**
     * Returns the list of labels that has been selected in the checklist
     *
     * @return list of labels
     */
    public List<Label> getSelectedLabels () {
        List<Label> list = new ArrayList<>();
        getSelectedItems().stream().forEach(e -> list.add(((LabelCheckListItem) e).getLabel()));
        return list;

    }

}
