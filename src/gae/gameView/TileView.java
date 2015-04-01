package gae.gameView;

import gameobject.Editable;

public interface TileView {
    public void clear();
    public void setEditable(Editable e);
    public void editEditable();
}
