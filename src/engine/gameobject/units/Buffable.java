package engine.gameobject.units;

import engine.gameobject.GameObject;

public interface Buffable extends GameObject{
    /**
     * Buff this unit with buff
     * @param buff
     */
    public void addBuff(Buff buff);
}
