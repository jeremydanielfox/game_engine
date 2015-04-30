package engine.game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import xml.DataManager;
import View.ButtonWrapper;
import engine.fieldsetting.Settable;
import engine.shop.ShopModel;
import engine.shop.ShopModelSimple;


/**
 * @author Jeremy
 * @author Sierra
 * @author Cosette
 *
 */
@Settable
public class ConcreteGame implements Game {

    private static final String DEFAULT_NAME = "GameName";
    private static final String DEFAULT_AUTHOR = "Sierra";
    private static final String DEFAULT_TYPE = "Type";
    private static final String DEFAULT_DESCRIPTION = "This is a fun game.";
    private static final String DEFAULT_INSTRUCTIONS = "Have fun!";
    private static final String DEFAULT_PHANTOM_PATH = "/src/taxes1997.xml";

    private Player myPlayer;
    private LevelBoard myLevelBoard;
    private ShopModel myShop;
    private List<ButtonWrapper> myButtons;
    private String myGameName;
    private String myAuthor;
    private String myType;
    private String myDescription;
    private String myInstructions;

    public ConcreteGame () {
        initialize(new ShopModelSimple(), new Player(), new ConcreteLevelBoard(),
                   new ArrayList<ButtonWrapper>());
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
        myLevelBoard.setShop(myShop);
        myButtons = buttons;
        myGameName = DEFAULT_NAME;
        myAuthor = DEFAULT_AUTHOR;
        myType = DEFAULT_TYPE;
        myDescription = DEFAULT_DESCRIPTION;
        myInstructions = DEFAULT_INSTRUCTIONS;
    }

    @Settable
    public void setName (String name) {
        myGameName = name;
    }

    @Settable
    public void setAuthor (String author) {
        myAuthor = author;
    }

    public String getAuthor () {
        return myAuthor;
    }

    @Settable
    public void setDescription (String description) {
        myDescription = description;
    }

    public String getDescription () {
        System.out.println("Getting description: " + myDescription);
        return myDescription;
    }

    @Settable
    public void setType (String type) {
        myType = type;
    }

    public String getType () {
        return myType;
    }

    @Settable
    public void setInstructions (String instruct) {
        myInstructions = instruct;
    }

    public String getInstructions () {
        return myInstructions;
    }

    @Override
    public void update () {
        myLevelBoard.update();
    }

    @Override
    public LevelBoard getLevelBoard () {
        return myLevelBoard;
    }

    @Override
    public void addButton (ButtonWrapper button) {
        myButtons.add(button);
    }

    @Override
    public ShopModel getShop () {
        return myShop;
    }

    @Override
    @Settable
    public void setPlayer (Player player) {
        myPlayer = player;
    }

    @Override
    public Player getPlayer () {
        return myPlayer;
    }

    @Settable
    public void setLevelBoard (LevelBoard levelBoard) {
        myLevelBoard = levelBoard;
        myLevelBoard.setShop(myShop);
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

    @Settable
    public void setShop (ShopModel shop) {
        myShop = shop;
        // TODO get rid of shop in the game
        myLevelBoard.setShop(shop);
    }

    @Override
    public Game cloneGame () throws IOException {
        File temp = File.createTempFile(DEFAULT_PHANTOM_PATH, ".xml" );
        String absolutePath = temp.getAbsolutePath();
        DataManager.writeToXML(this, absolutePath);
        return DataManager.readFromXML(this.getClass(), absolutePath);
    }
}
