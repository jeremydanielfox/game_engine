package engine.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import engine.fieldsetting.Settable;
import gameworld.GameWorld;


/**
 * This class implements a wave that releases enemies randomly over a given period of time.
 * 
 * @author Cosette Goldstein
 * @author Sierra Smith
 *
 */
@Settable
public class RandomSpanWave extends Wave {

    private static final double DEFAULT_SPAN = 10;

    private int myFrameDuration;
    private int myFramesSinceStart;
    private List<Integer> myReleaseTimes;

    public RandomSpanWave () {
        super();
        initialize(DEFAULT_SPAN, new ConcreteQueue());
    }

    public RandomSpanWave (double spanSeconds, GameObjectQueue objects, GameWorld world) {
        super(objects, world);
        initialize(spanSeconds, objects);
    }

    public RandomSpanWave (double startTime,
                           double spanSeconds,
                           GameObjectQueue objects,
                           GameWorld world) {
        super(startTime, objects, world);
        initialize(spanSeconds, objects);
    }

    private void initialize (double spanSeconds, GameObjectQueue objects) {
        myFrameDuration = SecondsToFrames.getFramesForSeconds(spanSeconds);
        myFramesSinceStart = 0;
        generateReleaseTimes(objects);
    }

    @Settable
    public void setGameObjectQueue (GameObjectQueue objects) {
        super.setObjectQueue(objects);
    }

    @Settable
    public void setWorld (GameWorld w) {
        super.setGameWorld(w);
    }

    @Settable
    public void setDurationSecs (double seconds) {
        myFrameDuration = SecondsToFrames.getFramesForSeconds(seconds);
    }

    /**
     * Releases an enemy if the frame count was designated as a release frame
     */
    @Override
    public boolean update () {
        if (!canStart()) {
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
     * 
     * @param objects
     */

    private void generateReleaseTimes (GameObjectQueue objects) {
        myReleaseTimes = new ArrayList<Integer>();
        Random generator = new Random();
        int num = objects.getObjectCount();
        for (int i = 0; i < num; i++) {
            myReleaseTimes.add(generator.nextInt(myFrameDuration));
        }
    }

    @Override
    public void onCompleteAction () {
        // TODO Auto-generated method stub

    }

}
