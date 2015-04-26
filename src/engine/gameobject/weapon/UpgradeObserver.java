package engine.gameobject.weapon;

import javafx.beans.property.DoubleProperty;
import engine.observable.Observable;
import engine.observable.Observer;

public class UpgradeObserver implements Observer{

    private Runnable updater;

    public UpgradeObserver (Runnable updateRange){
        this.updater = updateRange;
        update();
    }

    @Override
    public void update () {
        updater.run();
    }

}
