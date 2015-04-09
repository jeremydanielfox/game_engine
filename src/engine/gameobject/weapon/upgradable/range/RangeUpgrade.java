package engine.gameobject.weapon.upgradable.range;

import java.util.Optional;
import engine.gameobject.weapon.Upgrade;
import engine.gameobject.weapon.upgradable.Upgradable;
import engine.gameobject.weapon.upgradable.behavior.damage.Damage;
import engine.gameobject.weapon.upgradable.behavior.damage.DamageUpgrade;


/**
 * Manages a weapon's range. It is both an upgrade and an upgradable via the decorator pattern.
 * 
 * 
 * @author Nathan Prabhu
 *
 */

public class RangeUpgrade implements Range, Upgrade {

    private double increment;
    private Optional<Range> decorated;
    
    public RangeUpgrade() {
        increment = 0;
    }

    public RangeUpgrade (double increment) {
        this.increment = increment;
    }

    @Override
    public double getRange () {
        return decorated.map(Range::getRange).orElse(increment);
    }

    @Override
    public Class<? extends Upgradable> getType () {
        return Range.class;
    }

    @Override
    public void setDecorated (Upgradable decorated) {
        this.decorated = Optional.of((Range) decorated);
    }
    
    @Override 
    public void setDefault () {
        this.decorated = Optional.of(new RangeUpgrade(0));
    }

}
