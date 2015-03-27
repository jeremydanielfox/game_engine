package gameView;

import gameobject.Editable;

public interface GameView {
    
    public EditorView createEditorViewInTab(Editable e);
    public EditorView createEditorViewinPopup(Editable e);
    public void createLevel();
}
