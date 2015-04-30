package gae.tabView;

import engine.shop.ShopModel;
import gae.shopEditor.ShopEditor;
import javafx.scene.control.Tab;


/**
 * Shop editor tab
 * 
 * @author JohnGilhuly
 *
 */

public class ShopTab implements ITab {
    private Tab baseNode;
    private ShopEditor editor;

    public ShopTab () {
        initialize();
    }

    private void initialize () {
        baseNode = new Tab();
        baseNode.setText("Shop");

        editor = new ShopEditor();
        baseNode.setContent(editor.getObject());
        baseNode.setClosable(false);
    }

    @Override
    public Tab getBaseTabNode () {
        return baseNode;
    }

    /**
     * Used to connect the shop and game
     * 
     * @return
     */
    public ShopModel getShop () {
        return editor.getShop();
    }
}
