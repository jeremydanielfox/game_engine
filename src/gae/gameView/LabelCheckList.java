package gae.gameView;

import java.util.List;
import engine.gameobject.labels.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LabelCheckList extends CheckList {

    private List<Label> myObjects;

    public LabelCheckList (List<Label> objects) {
        super();
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

}
