package engine.gameobject.weapon;



// TODO: experiment with generics here
/**
 * **
 * <p>
 * One-time upgrades used to enhance a weapon's properties or existing behaviors. Examples are
 * increasing range and firing rate for the weapon, or adding larger damage or longer freeze time
 * for damage and freeze behaviors, respectively.
 * </p>
 * 
 * <p>
 * An Upgrade applies some change to another Upgrade. It will act as the decorator and the decorated
 * in the decorater pattern
 * <p>
 * 
 * @author Nathan Prabhu
 *
 */
public interface Upgrade {

    /**
     * 
     * @return Type of Upgrade this Upgrade can be applied to. Examples are Damage and Range.
     */
    public Class<? extends Upgrade> getType ();

    /**
     * 
     * @param decorated Upgradable to be decorated
     */
    public void setDecorated (Upgrade decorated);

    /**
     * sets Upgradable to a default value. Enables Upgrades if a weapon doesn't contain the existing
     * Upgradable.
     */
    public void setDefault ();

}
