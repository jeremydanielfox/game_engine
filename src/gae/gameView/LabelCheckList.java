package gae.gameView;

import java.util.ArrayList;
import java.util.List;

import engine.gameobject.labels.Type;
import gae.backend.Placeable;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import engine.gameobject.labels.Type;


/**
 * Checklist for labels
 * 
 * @author Nina Sun
 */
public class LabelCheckList extends CheckList {

    private ObservableList<Type> myObjects;
    private Stage mytemp;
    private Scene myScene;

    public LabelCheckList (ObservableList<Type> objects) {
        super();
        myObjects = objects;
        mytemp = new Stage();
        myScene = new Scene((Parent) getCheckList());
        myObjects = (ObservableList<Type>) (ObservableList<?>) objects;
        
        myObjects.forEach(e -> {
            createCheckOption(new LabelCheckListItem(e));
        });
        
        myObjects.addListener( (ListChangeListener.Change<? extends Type> change) -> {
            while (change.next()) {
                change.getAddedSubList().stream()
                        .forEach(e -> {
                            createCheckOption(new LabelCheckListItem(e));
                        });
                change.getRemoved().stream().forEach(e -> {
                    for (CheckListItem key : getMap().keySet()) {
                        if (((LabelCheckListItem) key).getLabel().equals(e)) {
                            ((Pane) getCheckList()).getChildren().remove(key.getNode());
                        }
                    }
                });
            }
        });
    }

    /**
     * Called by outside class to display the check list
     */
    public void showCheckList () {
        mytemp.setScene(myScene);
        mytemp.show();
        mytemp.centerOnScreen();
    }

    /**
     * Returns the list of labels that has been selected in the checklist
     *
     * @return list of labels
     */
    public List<Type> getSelectedLabels () {
        List<Type> list = new ArrayList<>();
        getSelectedItems().stream().forEach(e -> list.add(((LabelCheckListItem) e).getLabel()));
        return list;

    }

}
