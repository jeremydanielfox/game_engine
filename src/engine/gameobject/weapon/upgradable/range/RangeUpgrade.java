package engine.gameobject.weapon.upgradable.range;

import java.util.Optional;
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

    @Override
    public double getRange () {
        return decorated.map(this::getIncrementedRange).orElse(increment);
    }

    private double getIncrementedRange (Range sublayer) {
        return sublayer.getRange() + increment;
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
        this.decorated = Optional.of(new RangeUpgrade());
    }

}
