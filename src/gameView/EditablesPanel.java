package gameView;

import java.util.Collection;
import engine.gameobject.Editable;

public interface EditablesPanel {

    public void initializeEditables(Collection<Editable> c);
    public void createObject(Editable e);   
}
