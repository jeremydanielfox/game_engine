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
     * returns the Media file
     * @return
     */
    Media getMusic();
    
    /**
     * gets path of the media file
     * @return
     */
    String getPath();
    
    /**
     * replaces media file with new m
     * 
     * @param m
     */
    void replace (Media m);
}
