package engine.gameobject.weapon.upgradable.firingrate;

import java.util.Optional;
import engine.gameobject.weapon.Upgrade;


public class FiringRateUpgrade implements FiringRate {

    private double increment;
    private Optional<FiringRate> decorated;

    public FiringRateUpgrade () {
        this(0);
    }

    public FiringRateUpgrade (double increment) {
        this.increment = increment;
        this.decorated = Optional.empty();
    }

    @Override
    public double getRate () {
        return decorated.map(this::getIncrementedRate).orElse(increment);
    }

    private double getIncrementedRate (FiringRate decorated) {
        return decorated.getRate() + increment;
    }

    @Override
    public Class<? extends Upgrade> getType () {
        return FiringRate.class;
    }

    @Override
    public void setDecorated (Upgrade decorated) {
        this.decorated = Optional.of((FiringRate) decorated);
    }

    @Override
    public void setDefault () {
        this.decorated = Optional.of(new FiringRateUpgrade());
    }

}
