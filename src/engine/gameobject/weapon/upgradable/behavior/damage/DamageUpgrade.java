package engine.gameobject.weapon.upgradable.behavior.damage;

import engine.gameobject.weapon.Upgrade;
import engine.gameobject.weapon.upgradable.Upgradable;
import engine.gameobject.weapon.upgradable.range.Range;


public class DamageUpgrade implements Damage, Upgrade {
    
    private Damage decorated;
    private double increment;

    public DamageUpgrade (double increment, Damage decorated) {
        this.decorated = decorated;
        this.increment = increment;
    }

    @Override
    public double getDamage () {
        return decorated.getDamage() + increment;
    }

    @Override
    public Class<? extends Upgradable> getType () {
        return DamageSimple.class;
    }

    @Override
    public void setDecorated (Upgradable decorated) {
        this.decorated = (Damage) decorated;        
    }

    @Override
    public void setDefault () {
       this.decorated = new DamageSimple(0);
    }

}
