package engine.gameobject.upgrade;

import engine.gameobject.weapon.Weapon;

public class DamageUpgrade implements Upgrade {
    private double value;
    
    public DamageUpgrade(double value){
        this.value = value;
    }

    @Override
    public void apply (Weapon weapon) {
        //weapon.getProjectile().addDamage(value);
    }

}
