package engine.gameobject.units;

public class FreezeBuff extends Buff{
    
    public FreezeBuff(int duration){
        super(duration);
    }
    
    public void apply(BuffableUnit myUnit){
        myUnit.getMover().setFreeze(true);
    }
    
    public void unapply(BuffableUnit myUnit){
        myUnit.getMover().setFreeze(false);
    }
    
    public boolean isStrongerBuff(Buff otherBuff){
        return !(otherBuff.timeLeft() >= timeLeft());
    }

}
