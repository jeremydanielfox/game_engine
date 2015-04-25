package musician;

import engine.interactions.Interaction;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.media.Media;

/**
 * Interface whose implementors will enable music & sound effects to be played when using any of our
 * project components
 * 
 * @author Brandon Choi
 *
 */
public interface Musician {
    /**
     * Adds a sound effect m whenever node one and two interact via interaction i
     * 
     * @param one
     * @param i
     * @param two
     * @param m
     */
    void addSoundEffect (Node one, Interaction i, Node two, Music m);

    /**
     * Adds a background song to scene
     * 
     * @param scene
     * @param m
     */
    void addBackgroundMusic (Scene scene, Music m);

    /**
     * Restarts the music or sounds from the beginning
     */
    void restartMusic (Object o);

    /**
     * Clears all sounds a certain node possesses
     * 
     * @param n
     */
    void clearMusic (Object o);

    /**
     * Mutes the musician
     * 
     * @param s
     */
    void mute ();
    
    /**
     * plays media file associated with object
     */
    void playAudio(Object o);
    
    /**
     * pauses media file currently being played
     */
    void pauseAudio();
}
