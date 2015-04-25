package gae.tabView;

import engine.fieldsetting.Settable;
import engine.game.Game;
import engine.game.Level;
import engine.game.StoryBoard;
import gae.editor.EditingParser;
import gae.gridView.LevelView;
import gae.listView.LibraryData;
import gae.openingView.UIObject;
import gae.waveeditor.WaveEditor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
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
    private Game game;

    public CentralTabView (Scene sceneIn, Game gameIn) {
        scene = sceneIn;
        game = gameIn;
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
        Level levelData;
        StoryBoard sb = new StoryBoard();
        List<Method> levelMethods;

        try {
            levelData =
                    (Level) Class.forName(EditingParser
                                  .getInterfaceClasses("engine.fieldsetting.implementing_classes")
                                  .get("Level").get(0)).newInstance();
            
            levelMethods = EditingParser.getMethodsWithAnnotation(Class.forName(levelData.getClass().getName()), Settable.class);
            
            for (Method m : levelMethods) {
                if (m.getName().equals("setStoryBoard")) {
                    m.invoke(levelData, sb);
                }
            }   
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        game.getLevelBoard().addLevel(levelData);
        levelView = new LevelView();
        LevelPreferencesTab levelPrefs = new LevelPreferencesTab();
        WaveEditor waves = new WaveEditor(sb);
        LevelTabSet newLevel =
                new LevelTabSet(levelView.getBorder(scene), levelPrefs.getStack(),
                                waves.getObject());
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
