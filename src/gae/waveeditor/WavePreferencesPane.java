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

    private static final double DEFAULT_SPACER = 2;
    private static final double DEFAULT_MIN = .1;
    private static final double DEFAULT_MAX = 10;
    private ScrollPane rootNode;
    private Slider waveSpacing, totalWaveSpacing, betweenWaveSpacing;
    private CheckBox randomSpacing;

    public WavePreferencesPane () {
        initialize();
    }

    private void initialize () {
        rootNode = new ScrollPane();
        rootNode.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        rootNode.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

        waveSpacing = createSlider();
        totalWaveSpacing = createSlider();
        betweenWaveSpacing = createSlider();
        randomSpacing = new CheckBox();

        VBox scrollContent = new VBox();
        scrollContent.getChildren().addAll(new Text("Internal Wave Spacing"), waveSpacing,
                                           new Text("Random Wave Spacing"), randomSpacing,
                                           new Text("Random Wave Spacing Total Time"),
                                           totalWaveSpacing, new Text("Time Between Waves"),
                                           betweenWaveSpacing);
        rootNode.setContent(scrollContent);
    }

    private Slider createSlider () {
        Slider slider = new Slider();
        slider.setValue(DEFAULT_SPACER);
        slider.setMin(DEFAULT_MIN);
        slider.setMax(DEFAULT_MAX);
        return slider;
    }

    @Override
    public Node getObject () {
        return rootNode;
    }

    public double getSpacingTime () {
        return waveSpacing.getValue();
    }

    public boolean randomWaveSpacing () {
        return randomSpacing.selectedProperty().get();
    }

    public double getTotalSpacingTime () {
        return totalWaveSpacing.getValue();
    }

    public double getWaveSpacing () {
        return betweenWaveSpacing.getValue();
    }
}
