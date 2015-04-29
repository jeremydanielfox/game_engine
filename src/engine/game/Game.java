package engine.game;

import java.util.List;
import View.ButtonWrapper;
import engine.shop.ShopModel;


/**
 * A class can implement the game interface when it can be updated each frame, has a shop and a
 * LevelBoard and a Player, and the ability to support/contain ButtonWrappers.
 *
 * @author Sierra
 *
 */
public interface Game {

    /**
     * Updates everything in the game by one frame.
     */
    public void update ();

    /**
     * Returns the shop for the game.
     *
     * @return
     */
    public ShopModel getShop ();

    /**
     * Returns the level board for the game.
     *
     * @return
     */
    public LevelBoard getLevelBoard ();

    /**
     * Adds the given ButtonWrapper to the game's collection of ButtonWrappers.
     *
     * @param button
     */
    public void addButton (ButtonWrapper button);

    /**
     * Returns a list of the game's ButtonWrappers.
     *
     * @return
     */
    public List<ButtonWrapper> getButtons ();

    /**
     * Returns the game's Player object.
     *
     * @return
     */
    public Player getPlayer ();
    
    /**
     * Sets the game's Player object.
     *
     * @return
     */
    public void setPlayer (Player player);

    /**
     * Returns the string name of the game.
     *
     * @return
     */
    public String getGameName ();

    /**
     * Returns the string instructions of the game.
     * 
     * @return
     */
    public String getInstructions ();

    /**
     * Returns the string description of the game.
     * 
     * @return
     */
    public String getDescription ();
}
