package engine.endconditions;

import java.util.ArrayList;
import java.util.Collection;
import engine.observable.Observable;
import engine.observable.Observer;


/**
 *
 * @author Jeremy
 *
 */
public class EndCondition implements Observable {
    Collection<Observer> myObservers = new ArrayList<Observer>();

    @Override
    public void addObserver (Observer observer) {
        myObservers.add(observer);
    }

    @Override
    public void removeObserver (Observer observer) {
        if (myObservers.contains(observer)) {
            myObservers.remove(observer);
        }
    }

    @Override
    public void notifyObservers () {
        myObservers.forEach(o -> o.update());

    }

    public void satisfy () {
        notifyObservers();
    }

}
