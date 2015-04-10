package engine.gameobject.weapon.upgradable.firingrate;

import java.util.Optional;
import engine.gameobject.weapon.Upgrade;


public class FiringRateUpgrade implements FiringRate {

    private double increment;
    private Optional<FiringRate> decorated;

    public FiringRateUpgrade () {
        setNullDecorated();
        increment = 0;
    }

    public FiringRateUpgrade (double increment) {
        setNullDecorated();
        this.increment = increment;
    }

    private void setNullDecorated () {
        /*
         * commented out because it was giving NullPointerException when instantiating GameObject in
         * GAE
         */
        // FiringRate nullVal = null;
        // decorated = Optional.of(nullVal);
    }

    @Override
    public double getRate () {
        return decorated.map(FiringRate::getRate).orElse(increment);
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
        this.decorated = Optional.of(new FiringRateUpgrade(0));
    }

}
