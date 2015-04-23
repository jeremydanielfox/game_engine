package musician;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import engine.interactions.Interaction;

/**
 * Concrete musician that can be accessed by any classes in the project to add music to their scene,
 * actions, etc.
 * 
 * @author Brandon Choi
 *
 */

public class MusicianSimple implements Musician {

    /*
     * Map that keeps track of the music associated with a specific Object
     */
    private Map<Object, Music> myMusic;
    
    public MusicianSimple() {
        myMusic = new HashMap<>();
    }

    @Override
    public void addSoundEffect (Node one, Interaction i, Node two, Media m) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addBackgroundMusic (Scene scene, Media m) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void restartMusic () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void clearSounds (Node n) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void clearBackgroundMusic (Scene s) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mute (Scene s) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void playAudio () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pauseAudio () {
        // TODO Auto-generated method stub
        
    }

}
