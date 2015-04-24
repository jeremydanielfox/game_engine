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
    MediaPlayer myMusician;

    public MusicianSimple () {
        myMusic = new HashMap<>();
        myMusician = new MediaPlayer(null);
        myMusician.setCycleCount(MediaPlayer.INDEFINITE);
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
    public void addSoundEffect (Node one, Interaction i, Node two, Media m) {   
        SoundEffectSimple ses = new SoundEffectSimple(one, i, two, m);
        
    }

    @Override
    public void addBackgroundMusic (Scene scene, Media m) {
        myMusic.put(scene, new MusicSimple(m));
    }

    @Override
    public void restartMusic (Object o) {
        myMusician.pause();
        playAudio(o);
    }

    @Override
    public void clearMusic(Object o) {
        myMusic.replace(o, myMusic.get(o), null);
    }

    @Override
    public void mute () {
        myMusician.setVolume(MUTED);
    }

    @Override
    public void playAudio (Object o) {
        Media music = myMusic.get(o).getMusic();
        myMusician.play();
    }

    @Override
    public void pauseAudio (Object o) {
        Media music = myMusic.get(o).getMusic();
        myMusician.pause();
    }
}
