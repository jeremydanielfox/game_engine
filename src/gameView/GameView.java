package gameView;

import engine.gameobject.Editable;
import gae.frontend.EditorView;

public interface GameView {
    
    public EditorView createEditorViewInTab(Editable e);
    public EditorView createEditorViewinPopup(Editable e);
    public void createLevel();
}
