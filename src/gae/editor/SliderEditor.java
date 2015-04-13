package gae.editor;

import javafx.scene.control.Slider;

/**
 * Represents an editor based on a slider. Could be used for features such as setting movement or
 * firing speed.
 * 
 * @author Brandon Choi
 *
 */

public class SliderEditor extends ComponentEditor {

    private static final double DEFAULT = 0;
    private Slider slider;

    /*
     * Temp blank constructor. TODO: make range settable
     */
    public SliderEditor() {
        super();
        slider = new Slider(0, 10, DEFAULT);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);   
        getEditBox().getChildren().addAll(getLabel(), slider);
    }
/*
    public SliderEditor (String name, double bottom, double high) {
        slider = new Slider(bottom, high, DEFAULT);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);   
        getEditBox().getChildren().addAll(getLabel(), slider);
    }

    public SliderEditor (String name, double high) {
        slider = new Slider(DEFAULT, high, DEFAULT);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        getEditBox().getChildren().addAll(getLabel(), slider);
    }
*/
    @Override
    public void clear () {
        slider.setValue(DEFAULT);
    }

    @Override
    public void defaultField () {
        slider.setValue(DEFAULT);
    }
    @Override
    public Object createObject (Class<?> c) {
        return slider.getValue();
    }

}
