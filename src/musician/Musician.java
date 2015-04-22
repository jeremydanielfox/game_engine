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
    void addSoundEffect (Node one, Interaction i, Node two, Media m);

    /**
     * Adds a background song to scene
     * 
     * @param scene
     * @param m
     */
    void addBackgroundMusic (Scene scene, Media m);

    /**
     * Restarts the music or sounds from the beginning
     */
    void restartMusic ();

    /**
     * Clears all sounds a certain node possesses
     * 
     * @param n
     */
    void clearSounds (Node n);

    /**
     * Clears the song in a scene
     * 
     * @param s
     */
    void clearMusic (Scene s);

    /**
     * Mutes the entire scene and all sounds within
     * 
     * @param s
     */
    void mute (Scene s);
    
    /**
     * plays media file
     */
    void play();
    
    /**
     * pauses media file
     */
    void pause();
}
