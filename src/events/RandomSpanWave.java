package events;

import gameworld.GameWorld;


public class RandomSpanWave extends Wave {

    private int myFrameDuration;
    private int myFramesSinceStart;

    public RandomSpanWave (double spanSeconds, GameObjectQueue objects, GameWorld world) {
        super(objects, world);
        myFrameDuration = SecondsToFrames.getFramesForSeconds(spanSeconds);
        myFramesSinceStart = 0;
    }

    @Override
    public boolean update (int frameCount) {
        if(!canStart(frameCount)) {
            return true;
        }
        // decide if it's time to release an enemy
        // need to know the number of enemies total...?
        // preset the enemy release over the period in the constructor?

        myFramesSinceStart++;
        return true;
    }

}
