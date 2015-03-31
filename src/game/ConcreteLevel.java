package game;

/**
 * This class is in charge of maintaining everything that occurs within one level, such as
 * the list of events/waves to occur (Storyboard object), as well as the background image
 * and the GameWorld for the level.
 * 
 * @author Sierra Smith
 *
 */
public class ConcreteLevel implements Level {

    // note to self: we need to save all of the images out to one directory
    // we should identify images by name and have a constant image path that we pin on before
    // Retrieving image
    private String myImagePath;

    // level also needs a story board and game world

    public ConcreteLevel (String image) {
        myImagePath = image;
    }
    
    public String getLevelBackground(){
        return myImagePath;
    }

    @Override
    public boolean isWon () {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isLost () {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
        
    }

}
