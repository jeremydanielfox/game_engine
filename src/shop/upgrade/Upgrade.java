package shop.upgrade;

import gameobject.Weapon;

/**
 * One-time upgrades used to enhance a weapon's properties. Examples are range and firing rate.
 * @author Nathan Prabhu and Tom Puglisi
 *
 */
public interface Upgrade {

    public void apply (Weapon weapon);
}
