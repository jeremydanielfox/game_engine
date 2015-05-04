// This entire file is part of my masterpiece.
// JEREMY FOX
package engine.gameobject.units;

public interface BuffComponent {

    public void receiveBuff (Buff buff);

    public void addImmunity (Class<? extends Buff> immunity, UpgradeType buffType);
    
    public void update();

}
