package engine.gameobject.units;

import java.util.Optional;
import engine.fieldsetting.Settable;
import engine.gameobject.weapon.Upgrade;

/**
 * Buff for direct damage.  
 * @author Danny Oh and Nathan Prabhu
 *
 */
public class DirectDamage extends Buff {
    
    private double increment;
    private Optional<DirectDamage> decorated;
    private final static int graphicDuration = 20;
    
    public DirectDamage (double increment) {
        super(graphicDuration);
        this.increment = increment;
        decorated = Optional.empty();
    }
    
    @Settable
    public void setIncrement (double increment) {
        this.increment = increment;
    }

    @Override
    public void apply (BuffableUnit myUnit) {
        myUnit.changeHealth(-1 * getDamage());
        adjustEffect(myUnit, -1, 1, 0, 0);
    }
    
    public double getDamage () {
        return decorated.map(this::getIncrementedDamage).orElse(increment);
    }
    
    private double getIncrementedDamage (DirectDamage sublayer) {
        return sublayer.getDamage() + increment;
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
        return new DirectDamage(getDamage());
    }

    @Override
    public void upgrade (Upgrade decorated) {
        this.decorated = Optional.of((DirectDamage) decorated);
    }

}
