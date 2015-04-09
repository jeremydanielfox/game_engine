package gae.gameView;

import java.util.Collection;

import gae.backend.Editable;

public interface EditablesPanel {

    public void initializeEditables(Collection<Editable> c);
    public void createObject(Editable e);   
}
