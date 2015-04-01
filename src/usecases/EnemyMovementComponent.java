package usecases;

import engine.gameobject.Enemy;
import engine.gameobject.GameObject;
import engine.pathfinding.PathFinder;
import gameworld.MovementComponent;

public class EnemyMovementComponent implements MovementComponent {
    
    private PathFinder pathfinder;

    public EnemyMovementComponent(PathFinder pathfinder){
        this.pathfinder = pathfinder;
    }

    @Override
    public void update (GameObject object) {
        pathfinder.getNextLocation((Enemy) object, object.getLocation());
    }

}
