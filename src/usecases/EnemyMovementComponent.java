package usecases;

import gameobject.Enemy;
import gameobject.GameObject;
import gameworld.MovementComponent;
import gameworld.PathFinder;

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
