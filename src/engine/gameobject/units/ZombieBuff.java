package engine.gameobject.units;

import java.awt.Color;
import engine.gameobject.GameObject;
import engine.gameobject.weapon.Upgrade;
import engine.gameobject.labels.SimpleType;
import engine.gameobject.labels.Type;
/**
 * Buff that changes the target's type
 * @author myungoh
 *
 */
public class ZombieBuff extends Buff{

    private Type myType;
    
    public ZombieBuff () {
        super(0);
        myType = new SimpleType();
    }

    public ZombieBuff (Type type){
        this();
        myType = type;
    }
    
    public void setType(Type type){
        myType = type;
    }
    
    @Override
    public void upgrade (Upgrade decorated) {
        //You can't upgrade ZombieBuff?
    }

    @Override
    public void apply (GameObject myUnit) {
        myUnit.setType(myType);
        float[] hsbvals = new float[3];
        Color.RGBtoHSB(46, 139, 87, hsbvals);
        adjustEffect(myUnit, -hsbvals[0], -hsbvals[1], -hsbvals[2], 0);
    }

    @Override
    public void unapply (GameObject myUnit) {
        //Do nothing
    }

    @Override
    public boolean isStrongerBuff (Buff otherBuff) {
        return true;//ZombieBuffs override each other every time
    }

    @Override
    public Buff clone () {
        return new ZombieBuff(myType);
    }

}
