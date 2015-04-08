package engine.gameobject.weapon.upgradable.range;

import engine.gameobject.weapon.Upgrade;
import engine.gameobject.weapon.upgradable.Upgradable;


public class RangeUpgrade implements Range, Upgrade {

    private Range decorated;
    private double increment;

    public RangeUpgrade (double increment) {
        this.increment = increment;
    }

    @Override
    public double getRange () {
        return decorated.getRange() + increment;
    }

    @Override
    public Class<? extends Upgradable> getType () {
        return Range.class;
    }

    @Override
    public void setDecorated (Upgradable decorated) {
        this.decorated = (Range) decorated;
    }
    
    @Override 
    public void setDefault () {
        this.decorated = new RangeSimple(0);
    }

}
