package engine.gameobject.units;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;

public class FreezeBuff extends Buff{
    
    private Effect returnEffect;
    
    public FreezeBuff(int duration){
        super(duration);
    }
    
    public void apply(BuffableUnit myUnit){
        myUnit.getMover().setFreeze(true);
        returnEffect = myUnit.getGraphic().getNode().getEffect();
        myUnit.getGraphic().getNode().setEffect(new ColorAdjust(.66, .5, .5, .5));
    }
    
    public void unapply(BuffableUnit myUnit){
        myUnit.getMover().setFreeze(false);
        myUnit.getGraphic().getNode().setEffect(returnEffect);
    }
    
    public boolean isStrongerBuff(Buff otherBuff){
        return otherBuff.timeLeft() <= timeLeft();
    }

}
