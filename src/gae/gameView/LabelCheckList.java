package gae.gameView;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.labels.Type;
import gae.backend.Placeable;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import engine.gameobject.labels.Type;


/**
 * Checklist for types, previously called labels
 * 
 * @author Nina Sun
 */
public class LabelCheckList extends CheckList {

    private ObservableSet<Type> myObjects;
    private Stage myStage;
    private Scene myScene;

    public LabelCheckList (ObservableSet<Type> objects) {
        super();
        myObjects = objects;
        myStage = new Stage();
        myScene = new Scene((Parent) getCheckList());
        
        myObjects.forEach(e -> {
            createCheckOption(new LabelCheckListItem(e));
        });
        
        myObjects.addListener((SetChangeListener.Change<? extends Type> change)->{
            createCheckOption(new LabelCheckListItem(change.getElementAdded()));
            for (CheckListItem key : getMap().keySet()) {
              if (((LabelCheckListItem) key).getLabel().equals(change.getElementRemoved())) {
                  ((Pane) getCheckList()).getChildren().remove(key.getNode());
              }
          }
        });
    }

    /**
     * Called by outside class to display the check list
     */
    public void showCheckList () {
        myStage.setScene(myScene);
        myStage.show();
        myStage.centerOnScreen();
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
