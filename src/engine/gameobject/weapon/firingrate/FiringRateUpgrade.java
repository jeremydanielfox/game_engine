package engine.gameobject.weapon.firingrate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import engine.fieldsetting.Settable;
import engine.gameobject.weapon.Upgrade;
import engine.gameobject.weapon.range.RangeUpgrade;
import engine.observable.Observer;


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
    private List<Observer> observers = new ArrayList<>();

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
    
    @Override
    public Upgrade clone () {
        return new FiringRateUpgrade(increment);
    }
    
    @Override
    public void addObserver (Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver (Observer observer) {
        // TODO Auto-generated method stub   
    }

    @Override
    public void notifyObservers () {
        observers.forEach(obs -> obs.update());
    }
}
