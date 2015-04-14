package engine.gameobject.units;

import java.awt.Color;
import java.util.Optional;
import engine.gameobject.weapon.Upgrade;

public class PoisonBuff extends Buff{
    
    private int timeIncr;
    private double damageIncr;
    private Optional<PoisonBuff> decorated;
    
    public PoisonBuff(int timeIncr, double damageIncr){
        super(timeIncr);
        this.timeIncr = timeIncr;
        this.damageIncr = damageIncr;
    }
    
    public void apply(BuffableUnit myUnit){
        float[] hsbvals = new float[3];
        Color.RGBtoHSB(139, 0, 139, hsbvals);
        adjustEffect(myUnit, -hsbvals[0], -hsbvals[1], -hsbvals[2], 0);
    }
    
    public void unapply(BuffableUnit myUnit){
        float[] hsbvals = new float[3];
        Color.RGBtoHSB(139, 0, 139, hsbvals);
        adjustEffect(myUnit, hsbvals[0], hsbvals[1], hsbvals[2], 0);
    }
    protected void changeOverTime(BuffableUnit myUnit){
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
    public Class<? extends Upgrade> getType () {
        return this.getClass();
    }

    @Override
    public void setDecorated (Upgrade decorated) {
        this.decorated = Optional.of((PoisonBuff) decorated);        
    }

    @Override
    public void setDefault () {
        this.decorated = Optional.of(new PoisonBuff(0,0));        
    }
}
