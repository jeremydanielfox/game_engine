package engine.gameobject.units.slow;

import java.awt.Color;
import java.util.Optional;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.units.Buff;
import engine.gameobject.weapon.Upgrade;

/**
 * Buff that poisons its targets. Inflicts total damage across the entire duration of the buff.
 * 
 * @author Danny Oh and Nathan Prabhu
 *
 */
public class SlowBuff extends Buff implements Slow {

    private int timeIncr;
    private double slowPercent;
    private Optional<SlowBuff> decorated;

    /**
     * Makes a slow buff
     * 
     * @param timeIncr: Time the slow lasts
     * @param slowPercent: slow percentage
     */
    public SlowBuff (double timeIncr, double slowPercent) {
        super(timeIncr);
        this.timeIncr = (int) timeIncr;
        this.slowPercent = slowPercent;
        decorated = Optional.empty();
    }

    @Settable
    public void setTime (double timeIncr) {
        this.timeIncr = (int) timeIncr;
    }

    @Settable
    public void setSlow (double slowPercent) {
        this.slowPercent = slowPercent;
    }

    @Override
    public void apply (GameObject myUnit) {
        float[] hsbvals = new float[3];
        Color.RGBtoHSB(139, 0, 139, hsbvals);
        adjustEffect(myUnit, -hsbvals[0], -hsbvals[1], -hsbvals[2], 0);
        myUnit.getMover().speedBuff(-slowPercent);
    }

    @Override
    public void unapply (GameObject myUnit) {
        float[] hsbvals = new float[3];
        Color.RGBtoHSB(139, 0, 139, hsbvals);
        adjustEffect(myUnit, hsbvals[0], hsbvals[1], hsbvals[2], 0);
        myUnit.getMover().speedBuff(+slowPercent);
    }

    @Override
    public double getSlowPercent () {
        return decorated.map(this::getIncrementedSlow).orElse(slowPercent);
    }

    private double getIncrementedSlow (SlowBuff sublayer) {
        return sublayer.getDuration() + slowPercent;
    }

    @Override
    public int getDuration () {
        return decorated.map(this::getIncrementedDuration).orElse(timeIncr);
    }

    private int getIncrementedDuration (SlowBuff sublayer) {
        return sublayer.getDuration() + timeIncr;
    }

    @Override
    public boolean isStrongerBuff (Buff otherBuff) {
        return otherBuff.timeLeft() <= timeLeft();
    }

    public Buff clone(){
        return new SlowBuff(getDuration(), getSlowPercent());
    }

    @Override
    public void upgrade (Upgrade decorated) {
        this.decorated = Optional.of((SlowBuff) decorated);
    }

}