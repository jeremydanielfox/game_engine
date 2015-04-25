package engine.events;

import View.ViewConcrete2;


public class SecondsToFrames {

    public static int getFramesForSeconds (double seconds) {
        return ViewConcrete2.DEFAULT_FRAMES_SECOND * (int) seconds;
    }

}
