package engine.events;

import engine.fieldsetting.Settable;
import gameworld.GameWorld;


@Settable
public class ConstantSpacingWave extends Wave {

    private int myFramesBetweenEnemies;
    private int myFramesSinceLastRelease;

    public ConstantSpacingWave (double secBetweenObjects, GameObjectQueue objects, GameWorld world) {
        super(objects, world);
        initializeVars(secBetweenObjects);
    }

    public ConstantSpacingWave (double startTime,
                                double secBetweenObjects,
                                GameObjectQueue objects,
                                GameWorld world) {
        super(startTime, objects, world);
        initializeVars(secBetweenObjects);
    }

    private void initializeVars (double seconds) {
        myFramesBetweenEnemies = SecondsToFrames.getFramesForSeconds(seconds);
        myFramesSinceLastRelease = 0;
    }

    @Override
    public boolean update () {
        // System.out.println("Updating timed event");
        if (!canStart()) {
            // System.out.println("Updating timed event: Event can't start.");
            // System.out.println("Current frame: " + myFramesSinceLastRelease + ". Target: " +
            // myFramesBetweenEnemies);
            incFrameCount();
            return true;
        }
        if (myFramesSinceLastRelease == myFramesBetweenEnemies) {
            // release enemy
            // if (!releaseObject()) {
            // return false;
            // }
            releaseObject();
            // System.out.println("Releasing object.");

            // reset frame counter
            myFramesSinceLastRelease = 0;
        }
        else {
            myFramesSinceLastRelease++;
            // System.out.println("Increasing frame since release.");
            // System.out.println("Current frame: " + myFramesSinceLastRelease + ". Target: " +
            // myFramesBetweenEnemies);
        }
        return super.update();

    }
}
