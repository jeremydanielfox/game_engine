package engine.gameobject.units;

public interface Buffable {

    /**
     * Buff this unit with buff
     * 
     * @param buff
     */

    public void receiveBuff(Buff buff);
    
    /**
     * Add an immunity to this unit
     * @param buff
     */
    public void addImmunity(Buff buff);

}