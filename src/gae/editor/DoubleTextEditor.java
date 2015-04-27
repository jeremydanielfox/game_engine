package gae.editor;

import gae.gridView.DoubleNumberTextField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;


/**
 * Represents an editor based on a slider. Could be used for features such as setting movement or
 * firing speed.
 *
 * @author Kei Yoshikoshi
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
        doubleTextField.setPromptText("Input Double Value");
        getEditBox().getChildren().addAll(getLabel(), doubleTextField);
    }

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
        if (doubleTextField.getText().equals("")) {
            return 0.0;
        }
        return Double.parseDouble(doubleTextField.getText());
    }

}
