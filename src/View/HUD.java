package View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import engine.shop.ItemGraphic;
import engine.shop.TransitionGameObject;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * This class acts as a heads up display, which contains player stats and the shop for the game.
 * It can display "displayable" objects as well as any other nodes.
 * 
 * @author Sierra Smith and Cosette Goldstein
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

    private List<ButtonWrapper> myButtonWrapperList;

    public HUD (BorderPane pane) {
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
        System.out.println("stats:" + myStatsDisplay.getWidth());
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
        System.out.println("pane:" + myPane.getWidth());
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
        Text value = new Text(d.getValue() + "");
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
                
                System.out.println(e.getButton().getText()+" Button Enabled");
            }
                else {
                    e.getButton().setDisable(true);
                    System.out.println(e.getButton().getText()+" Button Disabled");
                }
            });
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
        shopDisplay.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        // shopDisplay.setMaxWidth(SHOP_WIDTH+200);
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
                TransitionGameObject transitionTower = new TransitionGameObject(item.getTower());
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
                ViewUtilities
                        .bindCursor(node, myPane.getScene().getRoot(), initial, KeyCode.ESCAPE);
        myPane.getChildren().add(bindedTower);
    }

}
