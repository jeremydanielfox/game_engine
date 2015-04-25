package gae.editorView;

import java.util.List;
import gae.editor.ComponentEditor;
import gae.openingView.UIObject;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class SimpleEditorView implements UIObject {
    private VBox vbox;

    public SimpleEditorView (List<ComponentEditor> list) {
        vbox = new VBox();
        System.out.println(list);
        for (ComponentEditor editor : list) {
            vbox.getChildren().add(editor.getObject());
        }
    }

    @Override
    public Node getObject () {
        // TODO Auto-generated method stub
        return vbox;
    }
}
