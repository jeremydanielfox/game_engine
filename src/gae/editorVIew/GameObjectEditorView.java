package gae.editorVIew;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import gae.openingView.UIObject;


public class GameObjectEditorView implements UIObject {
    public GameObjectEditorView () {
        BorderPane border = new BorderPane();
    }

    private ScrollPane setUpScroll () {
        ScrollPane scroll = new ScrollPane();
        return scroll;
    }

    @Override
    public Node getObject () {
        // TODO Auto-generated method stub
        return null;
    }

}
