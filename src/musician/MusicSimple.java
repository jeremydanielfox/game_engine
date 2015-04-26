package musician;

import javafx.scene.media.Media;

/**
 * Concrete version of the Music interface
 *
 * @author Brandon Choi
 *
 */

public class MusicSimple implements Music {

    private Media music;
    
    public MusicSimple(Media m) {
        music = m;
    }
    
    @Override
    public Media getMusic () {
        return music;
    }
    
    @Override
    public String getPath () {
        return music.getSource();
    }

    @Override
    public void replace (Media m) {
        music = m;
    }
}
