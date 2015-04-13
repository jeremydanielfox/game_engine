package engine.gameobject.units;

import javafx.scene.effect.ColorAdjust;

public class DirectDamage extends Buff{
    int myDamage;
    private final static int graphicDuration = 20;
    
    public DirectDamage (int damage) {
        super(graphicDuration);
        myDamage = damage;
    }

    @Override
    public void apply (BuffableUnit myUnit) {
        myUnit.changeHealth(-1 * myDamage);
        adjustEffect(myUnit, -1, 1, 0, 0);
    }

    @Override
    public void unapply (BuffableUnit myUnit) {
        adjustEffect(myUnit, 1, -1, 0, 0);
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
