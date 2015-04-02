package engine.gameobject;

import java.util.Collection;


/**
 * 
 * @author Jeremy
 *
 */
public interface Weapon {
    public double getRange ();

    public double getFiringRate ();

    public Collection<GameObject> getProjectiles (); // <-- rework weapons/projectiles?
}
