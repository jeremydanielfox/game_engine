package gae.tabView;

import engine.fieldsetting.Settable;
import engine.game.Game;
import engine.game.Level;
import engine.game.Player;
import engine.game.StoryBoard;
import engine.shop.ShopModel;
import gae.builder.PlayerBuilder;
import gae.editor.EditingParser;
import gae.gameView.InteractionTable;
import gae.gameWorld.FixedGameWorldFactory;
import gae.gameWorld.FreeGameWorldFactory;
import gae.gameWorld.GameWorldFactory;
import gae.gridView.LevelView;
import gae.listView.LibraryData;
import gae.openingView.UIObject;
import gae.waveeditor.WaveEditor;
import gameworld.FreeWorld;
import gameworld.GameWorld;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Central container for the central tab view in the gae editor
 * 
 * @author JohnGilhuly
 *
 */

public class CentralTabView implements UIObject {
    private VBox baseNode;
    private TabPane tabView;
    private int levelCount;
    private Scene scene;
    private ITab hudTab;
    private ITab shopTab;
    private ITab gameObjectTab;
    private LevelView levelView;
    private LibraryData libraryData;
    private Game game;
    private GameWorldFactory gameWorldFactory;
    private boolean editorInstantiated;

    public CentralTabView (Scene sceneIn, Game gameIn, String gameTypeIn) {
        scene = sceneIn;
        game = gameIn;
        initialize(gameTypeIn);
    }

    private void initialize (String gameTypeIn) {
        libraryData = LibraryData.getInstance();
        levelCount = 1;

        baseNode = new VBox();
        tabView = new TabPane();
        // refactor this code
        shopTab = new ShopTab();
        hudTab = new HudEditorTab(null);
        gameObjectTab = new GameObjectEditorTab(scene, getConsumer(), getBiconsumer());

        tabView.getTabs().addAll(shopTab.getBaseTabNode(), hudTab.getBaseTabNode());

        Button newLevel = new Button("Add Level");
        newLevel.setOnAction(e -> {
            if (!editorInstantiated) {
                GameObjectEditorTab gameObjectTab =
                        new GameObjectEditorTab(scene, getConsumer(), getBiconsumer());
                tabView.getTabs().add(gameObjectTab.getBaseTabNode());
                editorInstantiated = true;
            }
            createNewLevel();
        });
        baseNode.getChildren().addAll(newLevel, tabView);
        gameWorldFactory = createGameWorldFactory(gameTypeIn);

        try {
            setUpShopAndLinkToGame();
            setUpPlayerAndLinkToGame();
        }
        catch (ClassNotFoundException |
               IllegalAccessException |
               IllegalArgumentException |
               InvocationTargetException e1) {
            e1.printStackTrace();
        }
    }

    private GameWorldFactory createGameWorldFactory (String gameTypeIn) {
        if (gameTypeIn != null && gameTypeIn.equals("Free World")) {
            return new FreeGameWorldFactory();
        }
        else {
            return new FixedGameWorldFactory();
        }
    }

    private void setUpShopAndLinkToGame () throws ClassNotFoundException, IllegalAccessException,
                                          IllegalArgumentException, InvocationTargetException {

        ShopModel shopModel = ((ShopTab) shopTab).getShop();

        for (Method m : EditingParser.getMethodsWithAnnotation(Class.forName(game.getClass()
                .getName()), Settable.class)) {
            if (m.getName().equals("setShop")) {
                m.invoke(game, shopModel);
            }
        }
    }

    @SuppressWarnings("unused")
    private void setUpPlayerAndLinkToGame ()
        throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException,
        InvocationTargetException {
        
        PlayerBuilder playerBuilder = new PlayerBuilder();
        Player myPlayer = (Player) playerBuilder.getData().getBuiltObject();

        for (Method m : EditingParser.getMethodsWithAnnotation(Class.forName(game.getClass()
                .getName()), Settable.class)) {
            if (m.getName().equals("setPlayer")) {
                m.invoke(game, myPlayer);
            }
        }
    }

    private void createNewLevel () {
        levelView = new LevelView();
        Pane levelViewPane = levelView.getBorder(scene);
        gameWorldFactory.bindGridSize(levelView.getGridDimensionProperty());
        GameWorld nextWorld = gameWorldFactory.createGameWorld();
        if (nextWorld instanceof FreeWorld) {
            FreeWorld game = (FreeWorld) nextWorld;
            LibraryData.getInstance().addFreeWorldPath(game.getPath());
        }
        WaveEditor waves = createLevelAndWaveObject(gameWorldFactory.createGameWorld());
        InteractionTable iTable = new InteractionTable();

        LevelTabSet newLevel = new LevelTabSet(levelViewPane, waves.getObject(), iTable.getTable());

        Tab newTab = new Tab("Level:" + levelCount++);
        newTab.setContent(newLevel.getBaseNode());
        newTab.setClosable(false);
        tabView.getTabs().add(newTab);
        ((HudEditorTab) hudTab).setBackgroundImage(levelView.getBackgroundImage());
    }

    private WaveEditor createLevelAndWaveObject (GameWorld nextWorld) {
        Level levelData = null;
        StoryBoard sb = new StoryBoard();
        List<Method> levelMethods;

        try {
            levelData = (Level) Class
                    .forName(EditingParser
                                     .getInterfaceClasses("engine.fieldsetting.implementing_classes")
                                     .get("Level").get(0)).newInstance();

            levelMethods = EditingParser.getMethodsWithAnnotation(Class.forName(levelData
                    .getClass().getName()), Settable.class);

            for (Method m : levelMethods) {
                checkAndInvokeMethods(nextWorld, levelData, sb, m);
            }
        }
        catch (InstantiationException |
               IllegalAccessException |
               InvocationTargetException |
               ClassNotFoundException e) {
            e.printStackTrace();
        }

        game.getLevelBoard().addLevel(levelData);
        return new WaveEditor(sb, gameWorldFactory.createGameWorld());
    }

    private void checkAndInvokeMethods (GameWorld nextWorld,
                                        Level levelData,
                                        StoryBoard sb,
                                        Method m)
        throws IllegalAccessException, InvocationTargetException {
        if (m.getName().equals("setStoryBoard")) {
            m.invoke(levelData, sb);
        }
        else if (m.getName().equals("setGameWorld")) {
            m.invoke(levelData, nextWorld);
        }
        else if (m.getName().equals("setImagePath")) {
            m.invoke(levelData, levelView.getBackgroundImagePath());
        }
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
