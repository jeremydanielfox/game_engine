package engine.gameobject.weapon.range;

import javafx.beans.property.DoubleProperty;
import engine.gameobject.weapon.UpgradeSet;
import engine.observable.Observer;

public class RangeObserver implements Observer{
    
    private DoubleProperty rangeProp;
    private UpgradeSet upgradables;
    private Range myRange;
    
    public RangeObserver(DoubleProperty rangeProp, UpgradeSet upg, Range range){
        this.rangeProp = rangeProp;
        upgradables = upg;
        myRange = range;
    }

    @Override
    public void update () {
        myRange = ((Range) upgradables.get(myRange));
        rangeProp.set(myRange.getRange());
    }

}
