package gae.editorView;

import javafx.stage.Stage;


public class WeaponEditorOpener extends EditorOpener {
    private static final String TITLE = "Weapon Editor";

    @Override
    public void initialize () {
        // TODO Auto-generated method stub
        Stage stage = new Stage();
        stage.show();
        stage.setTitle(TITLE);
        // implement Weapon Editor
    }

}
