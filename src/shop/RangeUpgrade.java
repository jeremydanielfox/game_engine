package shop;

import gameobject.Weapon;

public class RangeUpgrade implements Upgrade {
    
    private double value;

    public RangeUpgrade(double value){
        this.value = value;
    }

    @Override
    public void apply (Weapon weapon) {
        weapon.addRange(value);
    }

    
}
