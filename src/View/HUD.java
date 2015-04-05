package View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import shop.ItemGraphic;
import shop.TransitionTower;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


/**
 * This class acts as a heads up display, which contains player stats and the shop for the game.
 * It can display "displayable" objects as well as any other nodes.
 * 
 * @author Sierra Smith
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

    public HUD(BorderPane pane){
        initialize(pane);
    }
    
    public HUD (BorderPane pane,Displayable ... displays) {
        initialize(pane);
        for (Displayable d : displays) {
            addPairedDisplay(d);
        }
    }
    
    private void initialize(BorderPane pane){
        myStatsDisplay = new VBox();
        myWholeDisplay = new VBox();
        myWholeDisplay.getChildren().add(myStatsDisplay);
        myDisplayFields = new HashMap<>();
        myPane=pane;
        makeShop();
    }

    public void addPairedDisplay (Displayable d) {
        d.addObserver(this);
        HBox newBox = new HBox(TEXT_SPACING);
        Text label = new Text(d.getLabel());
        Text value = new Text(d.getValue() + "");
        newBox.getChildren().addAll(label, value);
        myDisplayFields.put(d, value);
        myStatsDisplay.getChildren().add(newBox);
    }

    public VBox getDisplay () {
        return myWholeDisplay;
    }

    @Override
    public void update (Observable o, Object arg) {
        for (Displayable d : myDisplayFields.keySet()) {
            if (d.equals(o)) {
                myDisplayFields.get(d).setText(d.getValue() + "");
                break;
            }
        }
    }
    
    private void makeShop () {
        FlowPane shopDisplay = new FlowPane();
        shopDisplay.setHgap(5);
        shopDisplay.setVgap(5);
        //pane.setRight(shopDisplay);
        //shopDisplay.setMaxWidth(SHOP_WIDTH);
        shopDisplay.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        addIcons(shopDisplay);
        myWholeDisplay.getChildren().add(shopDisplay);
    }
    
    private void addIcons (FlowPane shopDisplay) {
        Map<String, String> shopImages = new HashMap<String, String>();
        String[] iconImages = new String[] { "/images/Bloons_DartMonkeyIcon.jpg",
                                            "/images/Bloons_TackShooterIcon.jpg" };
        String[] towerImages = new String[] { "/images/Bloons_DartMonkey.png",
                                             "/images/Bloons_TackShooter.png" };
        for (int i = 0; i < iconImages.length; i++) {
            shopImages.put(iconImages[i], towerImages[i]);
        }

        List<Node> items = new ArrayList<Node>();
        for (int i = 0; i < ITEM_COUNT / iconImages.length; i++) {
            shopImages.forEach( (icon, tower) -> {
                ItemGraphic item = new ItemGraphic(icon, tower);
                TransitionTower transitionTower = new TransitionTower(item.getTower());
                Node towerNode = transitionTower.getView();
                item.setOnMouseClicked(mouseEvent -> {
                    addTransitionTower(ViewUtilities.getMouseLocation(mouseEvent, towerNode),
                                       towerNode);
                });
                items.add(item);
            });
        }
        shopDisplay.getChildren().addAll(items);
    }
    
    private void addTransitionTower (Point2D initial, Node node) {
        Node bindedTower =
                ViewUtilities.bindCursor(node, myPane.getScene(), initial, KeyCode.ESCAPE);
        myPane.getChildren().add(bindedTower);
    }

}
