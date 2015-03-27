package gameView;

import java.util.Collection;

import gameobject.Editable;

public interface EditablesPanel {

    public void initializeEditables(Collection<Editable> c);
    public void createObject(Editable e);   
}
