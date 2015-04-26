package musician;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
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
    private boolean muted;
    private double savedVolume;
    private Media rayGunSound, laserSound, gunSound;
    private MediaPlayer rayGun, laser, gun;

    /*
     * try & catch blocks implemented for methods with MediaPlayer in case it isn't instantiated yet
     */
    private MediaPlayer myMusician;

    private MusicianSimple () {
        myMusic = new HashMap<>();
        muted = false;
        savedVolume = 0;
        
        rayGunSound = new Media("src/musician/raygun.wav");
        laserSound = new Media("/src/musician/laser.wav");
        gunSound = new Media("/src/musician/gun.wav");
        
        rayGun = new MediaPlayer(rayGunSound);
        laser = new MediaPlayer(laserSound);
        gun = new MediaPlayer(gunSound);
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
    public void addBackgroundMusic (Stage s, Scene scene, Music m) {
        myMusic.put(scene, m);
        setUpMute(scene);
    }

    /**
     * Sets up muting on the scene through CTRL + M. Disables typing when control is down so that
     * typing m isn't displayed.
     * 
     * @param scene
     */
    private void setUpMute (Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (e -> {
            if (e.isControlDown()) {
                scene.setOnKeyTyped(null);
                if (e.getCode().equals(KeyCode.M)) {

                    if (muted) {
                        myMusician.setVolume(savedVolume);
                    }
                    else {
                        mute();
                    }
                    muted = !muted;
                }
            }
        }));
    }

    @Override
    public void restartMusic (Object o) {
        try {
            pauseAudio();
            playAudio(o);
        }
        catch (Exception e) {
            /* does nothing if myMusician is not initialized */
        }
    }

    @Override
    public void clearMusic (Object o) {
        pauseAudio();
        myMusic.replace(o, myMusic.get(o), null);
    }

    @Override
    public void mute () {
        try {
            savedVolume = myMusician.getVolume();
            myMusician.setVolume(MUTED);
        }
        catch (Exception e) {
            /* does nothing if myMusician is not initialized */
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
            /* does nothing if myMusician is not initialized */
        }
    }

    /**
     * Preset sounds that can are also settable and thus customizable
     */

    public void rayGun () {
        rayGun.play();
    }

    public void setRayGun (Music m) {
        rayGunSound = m.getMusic();
    }

    public void laser () {
        laser.play();
    }

    public void setLaser (Music m) {
        laserSound = m.getMusic();
    }

    public void gun () {
        gun.play();
    }

    public void setGun (Music m) {
        gunSound = m.getMusic();
    }
}
