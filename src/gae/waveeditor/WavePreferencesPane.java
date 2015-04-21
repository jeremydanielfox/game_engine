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
    private double waveSpacing;

    public WavePreferencesPane () {
        waveSpacing = 2; // TODO: Change this to not hardcoded
        initialize();
    }

    private void initialize () {
        rootNode = new ScrollPane();
        rootNode.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        rootNode.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

        Text levelLabel = new Text("Options");
        // TODO: Pull established levels
        ObservableList<String> options =
                FXCollections.observableArrayList(
                                                  "Option 1",
                                                  "Option 2",
                                                  "Option 3"
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

    public double getSpacingTime () {
        return waveSpacing;
    }

}
