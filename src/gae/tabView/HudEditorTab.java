package gae.tabView;

import gae.hudEditor.HudEditor;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;


// Potentially combine this with the ShopTab using an abstract class

public class HudEditorTab {

    private Tab baseNode;

    public HudEditorTab (Image backgroundImage) {
        initialize(backgroundImage);
    }

    private void initialize (Image backgroundImage) {
        baseNode = new Tab();
        baseNode.setText("HUD Editor");

        HudEditor hEditor = new HudEditor(backgroundImage);
        baseNode.setContent(hEditor.getObject());
        baseNode.setClosable(false);
    }

    public Tab getBaseTabNode () {
        return baseNode;
    }
}
