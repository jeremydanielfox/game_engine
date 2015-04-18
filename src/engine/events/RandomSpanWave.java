package engine.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import gameworld.GameWorld;

/**
 * 
 * @author Cosette Goldstein
 * @author Sierra Smith
 *
 */

public class RandomSpanWave extends Wave {

    private int myFrameDuration;
    private int myFramesSinceStart;
    private List<Integer> myReleaseTimes;

    public RandomSpanWave (double startTime, double spanSeconds, GameObjectQueue objects, GameWorld world) {
        super(startTime, objects, world);
        myFrameDuration = SecondsToFrames.getFramesForSeconds(spanSeconds);
        myFramesSinceStart = 0;
        generateReleaseTimes(objects);
    }
    /**
     * Releases an enemy if the frame count was designated as a release frame 
     */
    @Override
    public boolean update () {
        if(!canStart()) {
            incFrameCount();
            return true;
        }
        
        while (myReleaseTimes.contains(myFramesSinceStart)) {
            releaseObject();
            myReleaseTimes.remove((Integer) myFramesSinceStart);
        }
        
        myFramesSinceStart++;
        return super.update();
    }

    /**
     * This method generates a frame count at which each enemy is released during the wave
     * @param objects
     */
    
    private void generateReleaseTimes (GameObjectQueue objects) {
        myReleaseTimes=new ArrayList<Integer>();
        Random generator=new Random();
        int num=objects.getObjectCount();
        for (int i=0;i<num;i++) {
            myReleaseTimes.add(generator.nextInt(myFrameDuration));
        }
    }
    
    @Override
    public void onCompleteAction () {
        // TODO Auto-generated method stub
        
    }

}
