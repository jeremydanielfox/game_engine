package musician;

import javafx.scene.media.Media;


/**
 * Interface that represents any sounds or music that can be used by a Musician
 *
 * @author Brandon Choi
 *
 */
public interface Music {
    /**
     * replaces media file with new m
     *
     * @param m
     */
    void replace (Media m);

    /**
     * mutes the media file
     */
    void mute ();

    /**
     * restarts the media file
     */
    void restart ();

    /**
     * plays media file
     */
    void play ();

    /**
     * pauses media file
     */
    void pause ();
}
