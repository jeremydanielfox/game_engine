package engine.events;

import engine.fieldsetting.Settable;
import gameworld.GameWorld;

/**
 * 
 * @author Sierra Smith
 * @author Cosette Goldstein
 *
 */

@Settable
public class ConstantSpacingWave extends Wave {

    private static final double DEFAULT_SEC_BETWEEN_OBJS = 2;
    
    private int myFramesBetweenEnemies;
    private int myFramesSinceLastRelease;

    public ConstantSpacingWave(){
        super();
        initializeVars(DEFAULT_SEC_BETWEEN_OBJS);
    }
    
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
    
    @Settable
    public void setSecBtwnEnemies(double seconds){
        myFramesBetweenEnemies = SecondsToFrames.getFramesForSeconds(seconds);
    }

    private void initializeVars (double seconds) {
        myFramesBetweenEnemies = SecondsToFrames.getFramesForSeconds(seconds);
        myFramesSinceLastRelease = 0;
    }

    @Override
    public boolean update () {
        if (!canStart()) {
            incFrameCount();
            return true;
        }
        if (myFramesSinceLastRelease == myFramesBetweenEnemies) {
            releaseObject();
            myFramesSinceLastRelease = 0;
        }
        else {
            myFramesSinceLastRelease++;
        }
        return super.update();

    }

//    @Override
//    public void onCompleteAction () {
//        // TODO Auto-generated method stub
//        
//    }
}
