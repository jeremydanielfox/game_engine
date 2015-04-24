package engine.game;

import java.util.List;
import View.ButtonWrapper;
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
    private String myGameName;

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
        // TODO make this part of constructor
        myGameName = "GameName";
    }

    @Override
    public void update () {
        myLevelBoard.update();
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
        return myButtons;
    }

    @Override
    public String getGameName () {
        return myGameName;
    }

}
