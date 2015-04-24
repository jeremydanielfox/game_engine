package gae.gameView;

import gae.backend.Editable;
import java.util.Collection;


public interface EditablesPanel {

    public void initializeEditables (Collection<Editable> c);

    public void createObject (Editable e);
}
