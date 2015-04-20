package gae.tabView;

import java.util.function.Consumer;
import gae.backend.Editable;
import gae.gridView.LevelView;
import gae.openingView.UIObject;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;


public class CentralTabView implements UIObject{
    private VBox baseNode;
    private TabPane tabView;
    private int levelCount;
    private Scene scene;
    private HudEditorTab hudTab;
    private LevelView levelView;

    public CentralTabView (Scene sceneIn) {
        scene = sceneIn;
        initialize();
    }

    private void initialize () {
        levelCount = 1;

        baseNode = new VBox();
        tabView = new TabPane();
        ShopTab shopTab = new ShopTab();
        hudTab = new HudEditorTab(null);
        tabView.getTabs().addAll(shopTab.getBaseTabNode(), hudTab.getBaseTabNode());

        Button newLevel = new Button("Add Level");
        newLevel.setOnAction(e -> createNewLevel());
        baseNode.getChildren().addAll(newLevel, tabView);
    }

    private void createNewLevel () {
        levelView = new LevelView();
        LevelPreferencesTab levelPrefs = new LevelPreferencesTab();
        LevelTabSet newLevel =
                new LevelTabSet(levelView.getBorder(scene), levelPrefs.getStack());
        Tab newTab = new Tab("Level:" + levelCount++);
        newTab.setContent(newLevel.getBaseNode());
        newTab.setClosable(false);
        tabView.getTabs().add(newTab);
        hudTab.setBackgroundImage(levelView.getBackgroundImage());
    }

    @Override
    public Node getObject () {
        return baseNode;
    }
    
    public void getAddFunction(Editable editable) {
        levelView.getAddFunction(editable);
    }

    public Consumer<Object> getConsumer () {
        //TODO Initially create a levelView so the consumer can be passed to the GameView
        //return levelView.getConsumer();
        return null;
    }
}
