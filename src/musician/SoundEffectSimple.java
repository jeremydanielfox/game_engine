package musician;

import engine.interactions.Interaction;
import javafx.scene.Node;
import javafx.scene.media.Media;

/**
 * Represents a concerete Music interface. Differes from MusicSimple because a sound effect is
 * caused by interactions between two nodes.
 * 
 * @author Brandon Choi
 *
 */
public class SoundEffectSimple implements Music {

    private Node actor, acted;
    private Interaction action;
    private Media sound;
    
    
    public SoundEffectSimple (Node one, Interaction i, Node two, Media m) {
        actor = one;
        acted = two;
        action = i;
        sound = m;
    }

    @Override
    public Media getMusic () {
        return sound;
    }
    
    @Override
    public String getPath () {
        return sound.getSource();
    }
    
    @Override
    public void replace (Media m) {
        sound = m;
    }
}
