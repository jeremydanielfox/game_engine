package gae.tabView;

import gae.hudEditor.HudEditor;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;

/**
 * Hud Editor Tab
 * @author JohnGilhuly
 *
 */

public class HudEditorTab {

    private Tab baseNode;
    private HudEditor hEditor;

    public HudEditorTab (Image backgroundImage) {
        initialize(backgroundImage);
    }

    private void initialize (Image backgroundImage) {
        baseNode = new Tab();
        baseNode.setText("HUD Editor");

        hEditor = new HudEditor();
        baseNode.setContent(hEditor.getObject());
        baseNode.setClosable(false);
    }

    public void setBackgroundImage (Image backgroundImage) {
        hEditor.setBackgroundImage(backgroundImage);
    }

    public Tab getBaseTabNode () {
        return baseNode;
    }
}
