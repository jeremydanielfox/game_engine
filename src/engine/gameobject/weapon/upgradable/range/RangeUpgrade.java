package engine.gameobject.weapon.upgradable.range;

import java.util.Optional;
import engine.gameobject.weapon.Upgrade;


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
    public Class<? extends Upgrade> getType () {
        return Range.class;
    }

    @Override
    public void setDecorated (Upgrade decorated) {
        this.decorated = Optional.of((Range) decorated);
    }
    
    @Override 
    public void setDefault () {
        this.decorated = Optional.of(new RangeUpgrade(0));
    }

}
