package engine.gameobject.weapon.firingstrategy;

import engine.gameobject.weapon.Weapon;

public class DirectAttack implements FiringStrategy {

    @Override
    public void fire (Weapon weapon) {
        // Directly use weapon on intended target (will be used by projectiles)
    }

}
