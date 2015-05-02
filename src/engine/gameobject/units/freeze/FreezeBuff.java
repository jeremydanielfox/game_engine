package engine.gameobject.units.freeze;

import java.util.Optional;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.movers.BasicMover;
import engine.gameobject.units.Buff;
import engine.gameobject.units.UpgradeType;
import engine.gameobject.weapon.Upgrade;


/**
 * Buff that freezes, or temporarily paralyzes, its targets.
 * 
 * @author Danny Oh and Nathan Prabhu
 *
 */
@Settable
public class FreezeBuff extends Buff implements Freeze {

    private int increment;
    private UpgradeType type;
    private Optional<Freeze> decorated;

    public FreezeBuff () {
        super(0);
        this.increment = 0;
        decorated = Optional.empty();
    }

    public FreezeBuff (double increment) {
        super(increment);
        this.increment = (int) increment;
        setBuffType(UpgradeType.COLLISION);
        decorated = Optional.empty();
    }

    /**
     * Sets duration to increment
     * @param increment
     */
    @Settable
    public void setIncrement (double increment) {
        this.increment = (int) increment;
    }

//    @Settable
    public void setBuffType (UpgradeType type) {
        this.type = type;
    }

    @Override
    public void apply (GameObject myUnit) {
        ((BasicMover) myUnit.getMover()).setFreeze(true);
        adjustEffect(myUnit, .66, .5, .5, 0);
    }

    @Override
    public int getDuration () {
        return decorated.map(this::getIncrementedDuration).orElse(increment);
    }

    private int getIncrementedDuration (Freeze sublayer) {
        return sublayer.getDuration() + increment;
    }

    @Override
    public void unapply (GameObject myUnit) {
        ((BasicMover) myUnit.getMover()).setFreeze(false);
        adjustEffect(myUnit, -.66, -.5, -.5, 0);
    }

    @Override
    public boolean isStrongerBuff (Buff otherBuff) {
        return otherBuff.timeLeft() <= timeLeft();
    }

    @Override
    public Buff clone () {
        return new FreezeBuff(getDuration());
    }

    @Override
    public void upgrade (Upgrade decorated) {
        this.decorated = Optional.of((Freeze) decorated);
    }

    @Override
    public UpgradeType getType () {
        return type;
    }
}
