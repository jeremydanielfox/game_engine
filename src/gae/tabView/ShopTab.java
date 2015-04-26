package gae.tabView;

import gae.shopEditor.ShopEditor;
import javafx.scene.control.Tab;

/**
 * Shop editor tab
 * @author JohnGilhuly
 *
 */

public class ShopTab {
    private Tab baseNode;

    public ShopTab () {
        initialize();
    }

    private void initialize () {
        baseNode = new Tab();
        baseNode.setText("Shop");

        ShopEditor editor=new ShopEditor();
        baseNode.setContent(editor.getObject());
        baseNode.setClosable(false);
    }

    public Tab getBaseTabNode () {
        return baseNode;
    }
}
