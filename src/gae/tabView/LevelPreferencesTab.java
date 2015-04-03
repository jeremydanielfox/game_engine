package gae.tabView;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;


public class LevelPreferencesTab {
    private StackPane baseNode;

    public LevelPreferencesTab () {
        initialize();
    }

    private void initialize () {
        baseNode = new StackPane();

        // Initialize Level Preferences Here
        baseNode.getChildren().add(new Label("Insert Level Preferences Here"));
    }

    public StackPane getStack () {
        return baseNode;
    }
}
