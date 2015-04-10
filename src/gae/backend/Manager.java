package gae.backend;

import gae.editor.EditorView;
import javafx.stage.Stage;


public class Manager {
    Stage s;

    public Manager () {
    }

    public void init (Stage s) {
        this.s = s;
        LibraryData data = new LibraryData();
        data.addToList(new TempTower());
        data.addToList(new TempTower());
        // DummyLibraryView view = new DummyLibraryView(s, data.getMap());
        TempTower tower = new TempTower();
        data.addToList(tower);
        // EditorView view = new EditorView(tower, s);
        // view.setGUI();

    }
}
