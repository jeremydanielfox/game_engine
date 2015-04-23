package gae.tabView;

import engine.game.Level;
import engine.game.StoryBoard;
import gae.backend.Editable;
import gae.editor.EditingParser;
import gae.gridView.LevelView;
import gae.openingView.UIObject;
import gae.waveeditor.WaveEditor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        Level levelData;
        StoryBoard sb = new StoryBoard();
        List<Method> levelMethods;

        try {
//            levelData =
//                    (Level) Class.forName(EditingParser
//                                  .getInterfaceClasses("engine.fieldsetting.implementing_classes")
//                                  .get("engine.game.Level").get(0)).newInstance();
            
            Map<String, ArrayList<String>> temp = EditingParser
                                  .getInterfaceClasses("engine.fieldsetting.implementing_classes");
            
            System.out.println(temp.get("Level"));
            
            System.out.println(temp.get("Level").get(0));
            
            Class<?> c = Class.forName(temp.get("Level").get(0));
            System.out.println(c.getName());
            
//            levelMethods = EditingParser.getMethodsWithSetterAnnotation(Class.forName(levelData.getClass().getName()));
            
//            for (Method m : levelMethods) {
//                if (m.getName().equals("setStoryBoard")) {
//                    m.invoke(levelData, sb);
//                }
//            }
        }
//        catch (InstantiationException e) {
//            e.printStackTrace();
//        }
//        catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
//        catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        

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

    public void getAddFunction (Editable editable) {
        levelView.getAddFunction(editable);
    }
}
