package engine.gameobject.weapon.firingstrategy;

import engine.gameobject.GameObject;
import engine.gameobject.units.Buff;
import engine.gameobject.units.Buffable;

public interface Buffer extends GameObject{

    /**
     * Apply buffs to target
     * @param target
     */
    public void impartBuffs(Buffable target);

    /**
     * Add newBuff to the list of buffs that the buffer is supposed to impart
     * @param newBuff
     */
    public void addCollisionBehavior (Buff newBuff);
}
