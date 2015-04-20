package engine.gameobject.units.poison;

import engine.gameobject.weapon.Upgrade;

public interface Poison extends Upgrade {

    public int getDuration ();
    
    public double getDamage ();
}
