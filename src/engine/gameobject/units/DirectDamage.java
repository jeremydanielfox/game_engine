package engine.gameobject.units;

import javafx.scene.effect.ColorAdjust;

public class DirectDamage extends Buff{
    int myDamage;
    public DirectDamage (int damage) {
        super(20);
        myDamage = damage;
    }

    @Override
    public void apply (BuffableUnit myUnit) {
        myUnit.changeHealth(-1 * myDamage);
        adjustEffect(myUnit, 0, 1, .5, .5);
    }

    @Override
    public void unapply (BuffableUnit myUnit) {
        adjustEffect(myUnit, 0, -1, -.5, -.5);
    }

    /*
     * You always want the new damage to apply, so return true
     */
    @Override
    public boolean isStrongerBuff (Buff otherBuff) {
        return true;
    }
    
    public Buff clone(){
        return new DirectDamage(myDamage);
    }

}
