package engine.gameobject.weapon.firingrate;

import java.util.Optional;
import engine.fieldsetting.Settable;
import engine.gameobject.units.BuffType;
import engine.gameobject.weapon.Upgrade;


/**
 * Manages a weapon's firing rate. It is both an upgrade and an upgradable via the decorator
 * pattern.
 * 
 * 
 * @author Nathan Prabhu
 *
 */

public class FiringRateUpgrade implements FiringRate, Upgrade {

    private double increment;
    private Optional<FiringRate> decorated;

    public FiringRateUpgrade () {
        this(0);
    }

    public FiringRateUpgrade (double increment) {
        this.increment = increment;
        decorated = Optional.empty();
    }
    
    @Settable
    public void setIncrement (double increment) {
        this.increment = increment;
    }

    @Override
    public double getRate () {
        return decorated.map(this::getIncrementedRate).orElse(increment);
    }

    private double getIncrementedRate (FiringRate sublayer) {
        return sublayer.getRate() + increment;
    }


    @Override
    public void upgrade (Upgrade decorated) {
        this.decorated = Optional.of((FiringRate) decorated);
    }
}
