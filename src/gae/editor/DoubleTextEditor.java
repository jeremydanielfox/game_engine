package gae.editor;

import gae.gridView.DoubleNumberTextField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;


/**
 * Represents an editor based on a slider. Could be used for features such as setting movement or
 * firing speed.
 *
 * @author Brandon Choi
 *
 */

public class DoubleTextEditor extends ComponentEditor {

    private static final double DEFAULT = 0;
    private TextField doubleTextField;

    /*
     * Temp blank constructor. TODO: make range settable
     */
    public DoubleTextEditor () {
        super();
        doubleTextField = new DoubleNumberTextField();
        getEditBox().getChildren().addAll(getLabel(), doubleTextField);
    }

    /*
     * public SliderEditor (String name, double bottom, double high) {
     * slider = new Slider(bottom, high, DEFAULT);
     * slider.setShowTickLabels(true);
     * slider.setShowTickMarks(true);
     * getEditBox().getChildren().addAll(getLabel(), slider);
     * }
     * 
     * public SliderEditor (String name, double high) {
     * slider = new Slider(DEFAULT, high, DEFAULT);
     * slider.setShowTickLabels(true);
     * slider.setShowTickMarks(true);
     * getEditBox().getChildren().addAll(getLabel(), slider);
     * }
     */
    @Override
    public void clear () {
        doubleTextField.clear();
    }

    @Override
    public void defaultField () {
        doubleTextField.setText(Double.toHexString(DEFAULT));
    }

    @Override
    public Object createObject (Class<?> c) {
        return Double.parseDouble(doubleTextField.getText());
    }

}
