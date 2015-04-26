package engine.game;

import View.Displayable;
import engine.fieldsetting.Settable;

/**
 * 
 * @author Sierra
 *
 */
@Settable
public class PlayerUnit extends Displayable {

    private double myValue;
    private String myLabel;

    public PlayerUnit () {
        myValue = 0;
        myLabel = "";
    }

    public PlayerUnit (double startValue, String label) {
        myLabel = label;
        myValue = startValue;
    }

    public void setValue (double value) {
        myValue = value;
    }

    @Settable
    public void setLabel (String label) {
        myLabel = label;
    }

    public void changeValue (double toAdd) {
        myValue += toAdd;
        updateObservers();
    }

    public double getValue () {
        return myValue;
    }

    @Override
    public String getStringValue () {
        return myValue + "";
    }

    @Override
    public String getLabel () {
        return myLabel;
    }

    private void updateObservers () {
        setChanged();
        notifyObservers();
    }

    @Settable
    public void setStartingValue (double value) {
        myValue = value;
    }
}
