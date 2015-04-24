package musician;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import engine.interactions.Interaction;

/**
 * Concrete musician that can be accessed by any classes in the project to add music to their scene,
 * actions, etc. Implements a Singleton model so that one musician handles all the sound.
 * 
 * @author Brandon Choi
 *
 */

public class MusicianSimple implements Musician {

    private static final int MUTED = 0;

    /*
     * Map that keeps track of the music associated with a specific Object
     */
    private static MusicianSimple instance;
    private Map<Object, Music> myMusic;

    /*
     * try & catch blocks implemented for methods with MediaPlayer in case it isn't instantiated yet
     */
    MediaPlayer myMusician;

    public MusicianSimple () {
        myMusic = new HashMap<>();
    }

    /**
     * for Singleton design pattern. Ensures there is ever only one MusicianSimple.
     * 
     * @return
     */
    public static synchronized MusicianSimple getInstance () {
        if (instance == null)
            instance = new MusicianSimple();
        return instance;
    }

    @Override
    public void addSoundEffect (Node one, Interaction i, Node two, Music m) {
        myMusic.put(i, m);
    }

    @Override
    public void addBackgroundMusic (Scene scene, Music m) {
        myMusic.put(scene, m);
    }

    @Override
    public void restartMusic (Object o) {
        try {
            pauseAudio();
            playAudio(o);
        }
        catch (Exception e) {
            /* does nothing if myMusician is not initialized*/
        }
    }

    @Override
    public void clearMusic (Object o) {
        myMusic.replace(o, myMusic.get(o), null);
    }

    @Override
    public void mute () {
        try {
            myMusician.setVolume(MUTED);
        }
        catch (Exception e) {
            /* does nothing if myMusician is not initialized*/
        }
    }

    @Override
    public void playAudio (Object o) {
        Media music = myMusic.get(o).getMusic();
        myMusician = new MediaPlayer(music);
        myMusician.play();
    }

    @Override
    public void pauseAudio () {
        try {
            myMusician.pause();
        }
        catch (Exception e) {
            /* does nothing if myMusician is not initialized*/
        }
    }
}
