package gae.editor;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

/**
 * Represents an editor based on a slider. Could be used for features such as setting movement or
 * firing speed.
 * 
 * @author Brandon Choi
 *
 */

public class SliderEditor implements ComponentEditor {

    private static final double DEFAULT = 0;
    private HBox editBox;
    private Slider slider;
    private Label label;

    /*
     * two constructors: one for when both sides of range are provided and one for just the upper
     * limit
     */

    public SliderEditor (String name, double bottom, double high) {
        editBox = new HBox();
        slider = new Slider(bottom, high, DEFAULT);
        label = new Label(name);
        
        editBox.getChildren().addAll(label, slider);
    }

    public SliderEditor (String name, double high) {
        editBox = new HBox();
        slider = new Slider(DEFAULT, high, DEFAULT);
        label = new Label(name);
        
        editBox.getChildren().addAll(label, slider);
    }

    @Override
    public Node getObject () {
        return editBox;
    }

    @Override
    public void clear () {
        slider.setValue(DEFAULT);
    }

    @Override
    public void defaultField () {
        slider.setValue(DEFAULT);
    }

}
