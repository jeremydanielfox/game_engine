package gae.tabView;

import gae.gridView.LevelView;
import gae.listView.LibraryData;
import gae.openingView.UIObject;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;


public class CentralTabView implements UIObject {
    private VBox baseNode;
    private TabPane tabView;
    private int levelCount;
    private Scene scene;
    private HudEditorTab hudTab;
    private LevelView levelView;
    private LibraryData libraryData;

    public CentralTabView (Scene sceneIn) {
        scene = sceneIn;
        initialize();
    }

    private void initialize () {
        libraryData = LibraryData.getInstance();
        levelCount = 1;

        baseNode = new VBox();
        tabView = new TabPane();
        // refactor this code
        ShopTab shopTab = new ShopTab();
        hudTab = new HudEditorTab(null);
        GameObjectEditorTab gameObjectTab = new GameObjectEditorTab(scene, getConsumer(), getBiconsumer());

        tabView.getTabs().addAll(shopTab.getBaseTabNode(), hudTab.getBaseTabNode(),
                                 gameObjectTab.getBaseTabNode());

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

    public Consumer<Object> getConsumer () {
        return e -> libraryData.addGameObjectToList(e);
    }

    public BiConsumer<Class<?>, Object> getBiconsumer () {
        BiConsumer<Class<?>, Object> biConsumer = (klass, o) -> {
            libraryData.addCreatedObjectToList(klass, o);
        };
        return biConsumer;
    }
}
