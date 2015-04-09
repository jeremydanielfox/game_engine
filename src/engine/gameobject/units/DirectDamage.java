package engine.gameobject.units;

public class DirectDamage extends Buff{
    int myDamage;
    public DirectDamage (int damage) {
        super(0);
        myDamage = damage;
    }

    @Override
    public void apply (BuffableUnit myUnit) {
        myUnit.changeHealth(-1 * myDamage);
        
    }

    @Override
    public void unapply (BuffableUnit myUnit) {
        
    }

    /*
     * You always want the new damage to apply, so return false
     */
    @Override
    public boolean isStrongerBuff (Buff otherBuff) {
        return false;
    }

}
