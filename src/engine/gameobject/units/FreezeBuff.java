package engine.gameobject.units;

import java.util.Optional;
import engine.gameobject.weapon.Upgrade;

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
    
    public void apply(BuffableUnit myUnit){
        myUnit.getMover().setFreeze(true);
        adjustEffect(myUnit, .66, .5, .5, 0);
    }
    
    @Override
    public int getDuration() {
        return decorated.map(this::getIncrementedDuration).orElse(increment);
    }
    
    private int getIncrementedDuration (FreezeBuff sublayer) {
        return sublayer.getDuration() + increment;
    }
    
    public void unapply(BuffableUnit myUnit){
        myUnit.getMover().setFreeze(false);
        adjustEffect(myUnit, -.66, -.5, -.5, 0);
    }
    
    public boolean isStrongerBuff(Buff otherBuff){
        return otherBuff.timeLeft() <= timeLeft();
    }
    
    public Buff clone(){
        return new FreezeBuff(getDuration());
    }

    @Override
    public Class<? extends Upgrade> getType () {
        return this.getClass();
    }

    @Override
    public void setDecorated (Upgrade decorated) {
        this.decorated = Optional.of((FreezeBuff) decorated);
    }

    @Override
    public void setDefault () {
        this.decorated = Optional.of(new FreezeBuff(0));        
    }
}
