package engine.game;

import java.util.ArrayList;
import java.util.List;
import View.ButtonWrapper;
import View.Displayable;
import engine.fieldsetting.Settable;
import engine.shop.ShopModel;


/**
 * @author Jeremy
 * @author Sierra
 * @author Cosette
 *
 */
@Settable
public class ConcreteGame implements Game {

    private Player myPlayer;
    private LevelBoard myLevelBoard;
    private ShopModel myShop;
    private List<ButtonWrapper> myButtons;

    public ConcreteGame () {
        // initialize(new Player(), new ConcreteLevelBoard(), new ArrayList<ButtonWrapper>());
    }

    public ConcreteGame (ShopModel shop,
                         Player player,
                         LevelBoard level,
                         List<ButtonWrapper> buttons) {
        initialize(shop, player, level, buttons);
    }

    public void initialize (ShopModel shop,
                            Player player,
                            LevelBoard level,
                            List<ButtonWrapper> buttons) {
        myShop = shop;
        myPlayer = player;
        myLevelBoard = level;
        myButtons = buttons;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
        myLevelBoard.update();
    }

    public void init () {

    }

    public LevelBoard getLevelBoard () {
        return myLevelBoard;
    }

    public void addButton (ButtonWrapper button) {
        myButtons.add(button);
    }

    @Override
    public ShopModel getShop () {
        return myShop;
    }

    @Override
    public Player getPlayer () {
        // TODO Auto-generated method stub
        return myPlayer;
    }

    @Settable
    public void setPlayer (Player player) {
        myPlayer = player;
    }

    @Settable
    public void setLevelBoard (LevelBoard levelBoard) {
        myLevelBoard = levelBoard;
    }

    @Settable
    public void setButtonList (List<ButtonWrapper> list) {
        myButtons = list;
    }

    @Override
    public List<ButtonWrapper> getButtons () {
        // TODO Auto-generated method stub
        return myButtons;
    }

}
