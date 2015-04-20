package gae.waveeditor;

import gae.openingView.UIObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class WavePreferencesPane implements UIObject {

    private ScrollPane rootNode;

    public WavePreferencesPane () {
        initialize();
    }

    private void initialize () {
        rootNode = new ScrollPane();
        rootNode.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        rootNode.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

        Text levelLabel = new Text("Level");
        // TODO: Pull established levels
        ObservableList<String> options =
                FXCollections.observableArrayList(
                                                  "Level 1",
                                                  "Level 2",
                                                  "Level 3"
                        );
        ComboBox<String> levelSelector = new ComboBox<String>(options);
        
        VBox scrollContent = new VBox();
        scrollContent.getChildren().addAll(levelLabel, levelSelector);
        rootNode.setContent(scrollContent);
    }

    @Override
    public Node getObject () {
        return rootNode;
    }

}
