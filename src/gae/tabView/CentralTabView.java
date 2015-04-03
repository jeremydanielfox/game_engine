package gae.tabView;

import gae.gridView.TempScene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class CentralTabView {
    private VBox baseNode;
    private TabPane tabView;
    private int levelCount;
    
    public CentralTabView() {
        initialize();
    }

    private void initialize () {
        levelCount = 1;
        
        baseNode = new VBox();
        
        tabView = new TabPane();
        ShopTab shopTab = new ShopTab();
        tabView.getTabs().add(shopTab.getBaseTabNode());
        
        Button newLevel = new Button ("Add Level");
        newLevel.setOnAction(e -> createNewLevel());
        
        baseNode.getChildren().addAll(newLevel, tabView);
    }
    
    private void createNewLevel () {
        TempScene tempScene = new TempScene();
        LevelPreferencesTab levelPrefs = new LevelPreferencesTab();
        LevelTabSet newLevel = new LevelTabSet(tempScene.getStack(), levelPrefs.getStack());
        
        Tab newTab = new Tab("Level:" + levelCount++);
        newTab.setContent(newLevel.getBaseNode());
        newTab.setClosable(false);
        tabView.getTabs().add(newTab);
    }

    public Node getBaseNode() {
        return baseNode;
    }
}
