package View;

/**
 * This interface should be used by anything that is "playable," meaning that it can be either
 * playing or paused/stopped/not playing, such as the Timeline in the view for the engine.
 * 
 * @author Cosette
 * @author Sierra
 *
 */
public interface Playable {

    public abstract boolean isPlaying ();

}
