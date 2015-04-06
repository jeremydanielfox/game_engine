package engine.gameobject.weapon;

import engine.gameobject.weapon.upgradable.Upgradable;


/**
 * An Upgrade applies some change to an Upgradable.
 * 
 * @author Nathan Prabhu
 *
 */
public interface Upgrade {

    /**
     * 
     * @return Type of Upgradable this Upgrade can be applied to.
     */
    public Class<? extends Upgradable> getType ();

    /**
     * 
     * @param decorated Upgradable to be decorated
     */
    public void setDecorated (Upgradable decorated);

    /**
     * sets Upgradable to a default value. Enables Upgrades if a weapon doesn't contain the existing
     * Upgradable.
     */
    public void setDefault ();

}
