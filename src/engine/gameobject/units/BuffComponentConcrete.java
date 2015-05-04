// This entire file is part of my masterpiece.
// JEREMY FOX
package engine.gameobject.units;

import engine.gameobject.GameObject;


public class BuffComponentConcrete implements BuffComponent {
    private BuffTracker myBuffs;
    private GameObject myGameObject;

    public BuffComponentConcrete (GameObject go) {
        myGameObject = go;
    }

    @Override
    public void receiveBuff (Buff buff) {
        myBuffs.receiveBuff(buff, myGameObject);
    }

    @Override
    public void addImmunity (Class<? extends Buff> immunity, UpgradeType buffType) {
        myBuffs.addImmunity(immunity, buffType);
    }

    @Override
    public void update () {
        
    }

}
