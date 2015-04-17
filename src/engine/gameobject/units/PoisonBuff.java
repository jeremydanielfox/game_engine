package engine.gameobject.units;

import java.awt.Color;
import java.util.Optional;
import engine.gameobject.GameObject;
import engine.gameobject.weapon.Upgrade;

/**
 * Buff that poisons its targets. Inflicts total damage across the entire duration of the buff. 
 * @author Danny Oh and Nathan Prabhu
 *
 */
public class PoisonBuff extends Buff{
    
    private int timeIncr;
    private double damageIncr;
    private Optional<PoisonBuff> decorated;
    
    public PoisonBuff(int timeIncr, double damageIncr){
        super(timeIncr);
        this.timeIncr = timeIncr;
        this.damageIncr = damageIncr;
        decorated = Optional.empty();
    }
    
    public void apply(GameObject myUnit){
        float[] hsbvals = new float[3];
        Color.RGBtoHSB(139, 0, 139, hsbvals);
        adjustEffect(myUnit, -hsbvals[0], -hsbvals[1], -hsbvals[2], 0);
    }
    
    public void unapply(GameObject myUnit){
        float[] hsbvals = new float[3];
        Color.RGBtoHSB(139, 0, 139, hsbvals);
        adjustEffect(myUnit, hsbvals[0], hsbvals[1], hsbvals[2], 0);
    }
    
    @Override
    protected void changeOverTime(GameObject myUnit){
        myUnit.changeHealth(-getDamage()/getDuration());
    }
    
    private double getDamage () {
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

    public boolean isStrongerBuff(Buff otherBuff){
        //TODO: You need to compare leftover damage?
        return otherBuff.timeLeft() <= timeLeft();
    }
    
    public Buff clone(){
        return new PoisonBuff(getDuration(), (int) getDamage());
    }

    @Override
    public void upgrade (Upgrade decorated) {
        this.decorated = Optional.of((PoisonBuff) decorated);        
    }
}
