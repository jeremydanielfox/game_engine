package gae.gameView;

import gae.backend.Placeable;
import java.util.Collection;


public interface EditablesPanel {

    public void initializeEditables (Collection<Placeable> c);

    public void createObject (Placeable e);
}
