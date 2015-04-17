package engine.gameobject.units;

import java.util.Optional;
import engine.gameobject.weapon.Upgrade;
import engine.gameobject.BasicMover;

/**
 * Buff that freezes, or temporarily paralyzes, its targets.
 * @author Danny Oh and Nathan Prabhu
 *
 */
public class FreezeBuff extends Buff{
    
    private int increment;
    private Optional<FreezeBuff> decorated;

    public FreezeBuff(int increment){
        super(increment);
        this.increment = increment;
        decorated = Optional.empty();
    }
    
    public void apply(BuffTracker myUnit){
        ((BasicMover) myUnit.getMover()).setFreeze(true);
        adjustEffect(myUnit, .66, .5, .5, 0);
    }
    
    @Override
    public int getDuration() {
        return decorated.map(this::getIncrementedDuration).orElse(increment);
    }
    
    private int getIncrementedDuration (FreezeBuff sublayer) {
        return sublayer.getDuration() + increment;
    }
    
    public void unapply(BuffTracker myUnit){
        ((BasicMover) myUnit.getMover()).setFreeze(false);
        adjustEffect(myUnit, -.66, -.5, -.5, 0);
    }
    
    public boolean isStrongerBuff(Buff otherBuff){
        return otherBuff.timeLeft() <= timeLeft();
    }
    
    public Buff clone(){
        return new FreezeBuff(getDuration());
    }

    @Override
    public void upgrade (Upgrade decorated) {
        this.decorated = Optional.of((FreezeBuff) decorated);
    }
}
