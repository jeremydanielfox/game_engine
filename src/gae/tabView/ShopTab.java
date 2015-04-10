package gae.tabView;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;


public class ShopTab {
    private Tab baseNode;

    public ShopTab () {
        initialize();
    }

    private void initialize () {
        baseNode = new Tab();
        baseNode.setText("Shop");

        baseNode.setContent(new Label("Shop Tab"));
        baseNode.setClosable(false);
    }

    public Tab getBaseTabNode () {
        return baseNode;
    }
}
