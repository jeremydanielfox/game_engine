package engine.events;

import gameworld.GameWorld;


public class ConstantSpacingWave extends Wave {

    private int myFramesBetweenEnemies;
    private int myFramesSinceLastRelease;

    public ConstantSpacingWave (double secBetweenObjects, GameObjectQueue objects, GameWorld world) {
        super(objects, world);
        myFramesBetweenEnemies = SecondsToFrames.getFramesForSeconds(secBetweenObjects);
        myFramesSinceLastRelease = 0;
    }

    @Override
    public boolean update () {
        if (!canStart()) {
            return true;
        }
        if (myFramesSinceLastRelease == myFramesBetweenEnemies) {
            // release enemy
//            if (!releaseObject()) {
//                return false;
//            }
            releaseObject();
            
            // reset frame counter
            myFramesSinceLastRelease = 0;
        }
        else {
            myFramesSinceLastRelease++;
        }
        return super.update();
        
    }
}
