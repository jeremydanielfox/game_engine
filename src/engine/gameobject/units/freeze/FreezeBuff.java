package engine.gameobject.units.freeze;

import java.util.Optional;
import engine.fieldsetting.Settable;
import engine.gameobject.units.Buff;
import engine.gameobject.units.BuffType;
import engine.gameobject.weapon.Upgrade;
import engine.gameobject.BasicMover;
import engine.gameobject.GameObject;

/**
 * Buff that freezes, or temporarily paralyzes, its targets.
 * @author Danny Oh and Nathan Prabhu
 *
 */
public class FreezeBuff extends Buff implements Freeze{
    
    private int increment;
    private BuffType type;
    private Optional<Freeze> decorated;

    public FreezeBuff(int increment){
        super(increment);
        this.increment = increment;
        this.type = BuffType.COLLISION;
        decorated = Optional.empty();
    }

    @Settable
    public void setIncrement (int increment) {
        this.increment = increment;
    }
    
    @Settable
    public void setBuffType (BuffType type){
        this.type = type;
    }
    
    public void apply(GameObject myUnit){
        ((BasicMover) myUnit.getMover()).setFreeze(true);
        adjustEffect(myUnit, .66, .5, .5, 0);
    }
    
    @Override
    public int getDuration() {
        return decorated.map(this::getIncrementedDuration).orElse(increment);
    }
    
    private int getIncrementedDuration (Freeze sublayer) {
        return sublayer.getDuration() + increment;
    }
    
    public void unapply(GameObject myUnit){
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
        this.decorated = Optional.of((Freeze) decorated);
    }

    @Override
    public BuffType getBuffType () {
        return type;
    }
}
