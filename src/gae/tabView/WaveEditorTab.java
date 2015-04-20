package gae.tabView;

import gae.waveeditor.WaveEditor;
import javafx.scene.control.Tab;


// Potentially combine this with the ShopTab using an abstract class

public class WaveEditorTab {

    private Tab baseNode;
    private WaveEditor wEditor;

    public WaveEditorTab () {
        initialize();
    }

    private void initialize () {
        baseNode = new Tab();
        baseNode.setText("Wave Editor");
        
        wEditor = new WaveEditor();
        baseNode.setContent(wEditor.getObject());
        baseNode.setClosable(false);
    }
    
    public Tab getBaseTabNode () {
        return baseNode;
    }
}