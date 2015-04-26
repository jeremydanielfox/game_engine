package engine.gameobject.units;

public interface Buffable {

    /**
     * Buff this unit with buff
     * 
     * @param buff
     */

    public void receiveBuff(Buff buff);
    
   /**
    * Make immune to certain type of buff
    * @param immunity class of added immunity
    * @param buffType specific bufftype (BuffType.NULL if regardless of bufftype)
    */
    public void addImmunity(Class<? extends Buff> immunity, UpgradeType buffType);

}