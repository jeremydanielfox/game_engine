package engine.game;

import View.Displayable;
import engine.fieldsetting.Settable;


@Settable
public class PlayerUnit extends Displayable {

    private double myValue;
    private String myLabel;
    
    public PlayerUnit() {
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

    @Override
    public double getValue () {
        return myValue;
    }

    @Override
    public String getLabel () {
        return myLabel;
    }

    public void updateObservers () {
        setChanged();
        notifyObservers();
    }
    
    @Settable
    public void setStartingValue(double value) {
        myValue = value;
    }
}
