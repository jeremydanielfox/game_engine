package engine.gameobject.units;

import engine.gameobject.GameObject;
import engine.gameobject.weapon.Upgrade;
import engine.gameobject.weapon.Weapon;

public class WeaponBuff extends Buff{

    private Weapon myWeapon;
    public WeaponBuff (Weapon toSet) {
        super(0);
        myWeapon = toSet;
    }

    @Override
    public void upgrade (Upgrade decorated) {
        //Can't upgrade weapon buff
    }

    @Override
    public void apply (GameObject myUnit) {
        myUnit.setWeapon(myWeapon.clone());
    }

    @Override
    public void unapply (GameObject myUnit) {
        //Do nothing;
    }

    @Override
    public boolean isStrongerBuff (Buff otherBuff) {
        return true;
    }

    @Override
    public Buff clone () {
        return new WeaponBuff(myWeapon);
    }

}
