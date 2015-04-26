package gae.waveeditor;

import gae.openingView.UIObject;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * The preference pane for a given wave
 * 
 * @author JohnGilhuly
 *
 */

public class WavePreferencesPane implements UIObject {

    private ScrollPane rootNode;
    private Slider waveSpacing;
    private CheckBox randomSpacing;

    public WavePreferencesPane () {
        initialize();
    }

    private void initialize () {
        rootNode = new ScrollPane();
        rootNode.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        rootNode.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        
        waveSpacing = new Slider();
        randomSpacing = new CheckBox();
        
        VBox scrollContent = new VBox();
        scrollContent.getChildren().addAll(new Text("Wave Spacing"), waveSpacing, new Text ("Random Wave Spacing"), randomSpacing);
        rootNode.setContent(scrollContent);
    }

    @Override
    public Node getObject () {
        return rootNode;
    }

    public double getSpacingTime () {
        return waveSpacing.getValue();
    }
    
    public boolean randomWaveSpacing() {
        return randomSpacing.selectedProperty().get();
    }

}
