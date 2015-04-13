package engine.gameobject.units;

import java.awt.Color;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;

public class PoisonBuff extends Buff{
    
    private double damage;
    
    public PoisonBuff(int duration, int totalDamage){
        super(duration);
        damage = totalDamage;
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
        myUnit.changeHealth(-damage/getDuration());
    }
    
    
    public boolean isStrongerBuff(Buff otherBuff){
        //TODO: You need to compare leftover damage?
        return otherBuff.timeLeft() <= timeLeft();
    }
    
    public Buff clone(){
        return new PoisonBuff(getDuration(), (int) damage);
    }
}
