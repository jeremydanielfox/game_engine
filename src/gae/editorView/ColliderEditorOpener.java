package gae.editorView;

import java.util.List;
import gae.editor.ComponentEditor;
import gae.editor.SimpleEditor;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class ColliderEditorOpener extends EditorOpener {
    private static final String TITLE = "Collider Editor";
    private SimpleEditor editor;

    public ColliderEditorOpener (SimpleEditor editor) {
        super();
        this.editor = editor;
    }

    @Override
    public Parent setUpParent () {
        BorderPane border = new BorderPane();
        List<ComponentEditor> simpleList = editor.getSimpleComponentEditors();
        SimpleEditorView simpleEditorView = new SimpleEditorView(simpleList);
        VBox top = (VBox) simpleEditorView.getObject();
        border.setTop(top);
        return border;
    }

    @Override
    public String getTitle () {
        // TODO Auto-generated method stub
        return TITLE;
    }

}
