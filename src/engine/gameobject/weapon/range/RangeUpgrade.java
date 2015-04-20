package engine.gameobject.weapon.range;

import java.util.Optional;
import engine.fieldsetting.Settable;
import engine.gameobject.weapon.Upgrade;


/**
 * Manages a weapon's range. It is both an upgrade and an upgradable via the decorator pattern.
 *
 * @author Nathan Prabhu
 *
 */

public class RangeUpgrade implements Range, Upgrade {

    private double increment;
    private Optional<Range> decorated;

    public RangeUpgrade () {
        this(0);
    }

    public RangeUpgrade (double increment) {
        this.increment = increment;
        decorated = Optional.empty();
    }
    
    @Settable
    public void setIncrement (double increment) {
        this.increment = increment;
    }

    @Override
    public double getRange () {
        return decorated.map(this::getIncrementedRange).orElse(increment);
    }

    private double getIncrementedRange (Range sublayer) {
        return sublayer.getRange() + increment;
    }

    @Override
    public void upgrade (Upgrade decorated) {
        this.decorated = Optional.of((Range) decorated);
    }

}
