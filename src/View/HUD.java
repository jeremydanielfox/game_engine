package View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import engine.gameobject.test.TestTower;
import engine.shop.ShopModel;
import engine.shop.ShopView;
import gameworld.GameWorld;


/**
 * This class acts as a heads up display, which contains player stats and the shop for the game.
 * It can display "displayable" objects as well as any other nodes.
 *
 * @author Sierra Smith
 * @author Cosette Goldstein
 *
 */
public class HUD implements Observer {

    // note: this should change with screen size, fix it later
    private static final double TEXT_SPACING = 10;
    private final static int SHOP_WIDTH = 160;
    private final static int ITEM_COUNT = 12;

    private Map<Displayable, Text> myDisplayFields;
    private VBox myStatsDisplay;
    private VBox myWholeDisplay;
    private BorderPane myPane;
    private HBox myDefaultButtonDisplay;
    private ShopModel shop;
    private GameWorld world;
    
    private List<ButtonWrapper> myButtonWrapperList;

    public HUD (GameWorld world, BorderPane pane, ShopModel shop) {
        this.world = world;
        this.shop = shop;
        initialize(pane);
    }

    public HUD (BorderPane pane, Displayable ... displays) {
        initialize(pane);
        for (Displayable d : displays) {
            addPairedDisplay(d);
        }
    }

    private void initialize (BorderPane pane) {
        myStatsDisplay = new VBox(10);
        myWholeDisplay = new VBox(10);
        myWholeDisplay.setAlignment(Pos.CENTER);
        myStatsDisplay.setAlignment(Pos.CENTER_RIGHT);
        myWholeDisplay.setAlignment(Pos.CENTER);
        myWholeDisplay.getChildren().add(myStatsDisplay);
        myDisplayFields = new HashMap<>();
        myPane = pane;
        makeShop();
        makeDefaultButtonDisplay();
        myWholeDisplay.setAlignment(Pos.CENTER);
        myButtonWrapperList = new ArrayList<>();
    }

    private void makeDefaultButtonDisplay () {
        myDefaultButtonDisplay = new HBox();
        myDefaultButtonDisplay.setAlignment(Pos.CENTER);
        myWholeDisplay.getChildren().add(myDefaultButtonDisplay);
    }

    public void addButton (ButtonWrapper button) {
        myButtonWrapperList.add(button);
        myDefaultButtonDisplay.getChildren().add(button.getButton());
    }

    public void addPairedDisplay (Displayable d) {
        d.addObserver(this);
        HBox newBox = new HBox(TEXT_SPACING);
        Text label = new Text(d.getLabel());
        formatText(label, 30);
        Text value = new Text(d.getStringValue());
        formatText(value, 30);
        newBox.getChildren().addAll(label, value);
        newBox.setAlignment(Pos.CENTER);
        myDisplayFields.put(d, value);
        myStatsDisplay.getChildren().add(newBox);
        myStatsDisplay.setAlignment(Pos.CENTER);
    }

    private void formatText (Text t, int fontSize) {
        t.setFont(Font.font("Verdana", fontSize));
        t.setFill(Color.BLUEVIOLET);
    }

    public VBox getDisplay () {
        return myWholeDisplay;
    }

    public void update () {
        myButtonWrapperList.forEach(e -> {
            if (e.isEnabled()) {
                e.getButton().setDisable(false);
            }
            else {
                e.getButton().setDisable(true);

                }
        });
    }

    @Override
    public void update (Observable o, Object arg) {
        for (Displayable d : myDisplayFields.keySet()) {
            if (d.equals(o)) {
                myDisplayFields.get(d).setText(d.getStringValue());
                break;
            }
        }
    }

    private void makeShop () {
        //TESTING purposes:
        shop.addPurchasable(new TestTower(0,0,0));
        myWholeDisplay.getChildren().add(new ShopView(world, shop, myPane));
    }
}
