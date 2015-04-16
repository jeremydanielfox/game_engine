package gae.listView;

import gae.backend.Editable;
import gae.gridView.ContainerWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;


/**
 * A subclass to keep track of Enemy GameObjects that are made through the Editor.
 * 
 * @author Kei
 *
 */
public class EnemyPaneList extends PaneList {
    private Map<EditableNode, ObservableList<Editable>> instancesEditableNodeMap;
    private Group root;
    private boolean added;
    private StackPane stack;
    private boolean initialized;

    public EnemyPaneList () {
        instancesEditableNodeMap = new HashMap<>();
    }

    @Override
    public TitledPane initialize (Group root,
                                  Node node,
                                  Scene scene,
                                  ContainerWrapper wrapper,
                                  ObservableList<EditableNode> observableList) {
        this.root = root;
        root.setManaged(false);
        this.stack = (StackPane) node;
        TitledPane pane = getTitledPane("Enemy");
        ObservableList<TitledPane> enemyPaneList = setAccordion(pane);
        for (EditableNode previousNode : observableList) {
            if (previousNode.getType().equals("Enemy")) {
                ObservableList<Editable> instanceList =
                        FXCollections.observableArrayList();
                instancesEditableNodeMap.put(previousNode, instanceList);
                TitledPane newPane =
                        setTitledPaneClick(previousNode, instanceList, root, node, scene,
                                           wrapper);
                enemyPaneList.add(newPane);

            }
        }
        observableList.addListener(new ListChangeListener<EditableNode>() {
            public void onChanged (javafx.collections.ListChangeListener.Change<? extends EditableNode> change) {
                while (change.next()) {
                    if (change.wasAdded()) { // if an editablenode was added
                        EditableNode added = (EditableNode) change.getAddedSubList().get(0);
                        if (added.getType().equals("Enemy")) {
                            ObservableList<Editable> instanceList =
                                    FXCollections.observableArrayList();
                            instancesEditableNodeMap.put(added, instanceList);
                            TitledPane newPane =
                                    setTitledPaneClick(added, instanceList, root, node, scene,
                                                       wrapper);
                            enemyPaneList.add(newPane);
                            initialized = true;
                        }
                    }
                }
            }
        });

        return pane;
    }

    @Override
    public void removeRoot () {
        if (initialized) {
            System.out.println("removing enemy root");
            // does not work if path is added first before towers are added
            stack.getChildren().remove(root);
            added = true;
        }
    }

    @Override
    public void addRoot () {
        if (added && initialized) {
            System.out.println("adding enemy root");
            stack.getChildren().add(root);
            added = false;
        }
    }

}
