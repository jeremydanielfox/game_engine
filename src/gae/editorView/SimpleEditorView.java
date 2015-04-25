package gae.editorView;

import gae.editor.ComponentEditor;
import gae.openingView.UIObject;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class SimpleEditorView extends VBox implements UIObject {
    private VBox vbox;

    public SimpleEditorView (ObservableList<ComponentEditor> list) {
        vbox = new VBox();
        for (ComponentEditor node : list) {
            this.getChildren().add(node.getObject());
        }
        list.addListener( (ListChangeListener.Change<? extends ComponentEditor> change) -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    ComponentEditor added = change.getAddedSubList().get(0);
                    this.getChildren().add(added.getObject());
                }
                if (change.wasRemoved()) {
                    this.getChildren().clear();
                }
            }
        });
    }

    @Override
    public Node getObject () {
        // TODO Auto-generated method stub
        return vbox;
    }
}
