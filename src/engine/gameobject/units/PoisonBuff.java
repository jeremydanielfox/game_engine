package engine.gameobject.units;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;

public class PoisonBuff extends Buff{
    
    private int damage;
    public PoisonBuff(int duration, int tickDamage){
        super(duration);
        damage = tickDamage;
    }
    
    public void apply(BuffableUnit myUnit){
        
    }
    
    protected void changeOverTime(BuffableUnit myUnit){
        myUnit.changeHealth(-damage);
    }
    
    public void unapply(BuffableUnit myUnit){

    }
    
    public boolean isStrongerBuff(Buff otherBuff){
        return otherBuff.timeLeft() <= timeLeft();
    }
    
    public Buff clone(){
        return new FreezeBuff(getDuration());
    }
}
