package engine.gameobject.units.poison;

import java.awt.Color;
import java.util.Optional;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.units.Buff;
import engine.gameobject.units.BuffType;
import engine.gameobject.weapon.Upgrade;


/**
 * Buff that poisons its targets. Inflicts total damage across the entire duration of the buff.
 * 
 * @author Danny Oh and Nathan Prabhu
 *
 */
public class PoisonBuff extends Buff implements Poison {

    private int timeIncr;
    private double damageIncr;
    private Optional<PoisonBuff> decorated;
    private BuffType type;

    public PoisonBuff () {
        super(0);
        this.timeIncr = 0;
        this.damageIncr = 0;
        decorated = Optional.empty();
    }

    /**
     * Makes a poison buff
     * 
     * @param timeIncr: Time the damage occurs over
     * @param damageIncr: Total damage
     */
    public PoisonBuff (int timeIncr, double damageIncr) {
        super(timeIncr);
        this.timeIncr = timeIncr;
        this.damageIncr = damageIncr;
        decorated = Optional.empty();
    }

    @Settable
    public void setBuffType (BuffType type) {
        this.type = type;
    }

    @Settable
    public void setTime (int timeIncr) {
        this.timeIncr = timeIncr;
    }

    @Settable
    public void setDamage (double damageIncr) {
        this.damageIncr = damageIncr;
    }

    @Override
    public void apply (GameObject myUnit) {
        float[] hsbvals = new float[3];
        Color.RGBtoHSB(139, 0, 139, hsbvals);
        adjustEffect(myUnit, -hsbvals[0], -hsbvals[1], -hsbvals[2], 0);
    }

    @Override
    public void unapply (GameObject myUnit) {
        float[] hsbvals = new float[3];
        Color.RGBtoHSB(139, 0, 139, hsbvals);
        adjustEffect(myUnit, hsbvals[0], hsbvals[1], hsbvals[2], 0);
    }

    @Override
    protected void changeOverTime (GameObject myUnit) {
        myUnit.changeHealth(-getDamage() / getDuration());
    }

    @Override
    public double getDamage () {
        return decorated.map(this::getIncrementedDamage).orElse(damageIncr);
    }

    private double getIncrementedDamage (PoisonBuff sublayer) {
        return sublayer.getDuration() + damageIncr;
    }

    @Override
    public int getDuration () {
        return decorated.map(this::getIncrementedDuration).orElse(timeIncr);
    }

    private int getIncrementedDuration (PoisonBuff sublayer) {
        return sublayer.getDuration() + timeIncr;
    }

    @Override
    public boolean isStrongerBuff (Buff otherBuff) {
        // TODO: You need to compare leftover damage?
        return otherBuff.timeLeft() <= timeLeft();
    }

    public Buff clone () {
        return new PoisonBuff(getDuration(), getDamage());
    }

    @Override
    public void upgrade (Upgrade decorated) {
        this.decorated = Optional.of((PoisonBuff) decorated);
    }

    @Override
    public BuffType getBuffType () {
        return type;
    }
}
