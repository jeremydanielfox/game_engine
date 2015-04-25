package View;

import java.util.Observable;


/**
 * A class can implement this interface when it has something that can be displayed via a String
 * label and a String value.
 * 
 * @author Sierra
 * @author Cosette
 *
 */
public abstract class Displayable extends Observable {

    /**
     * Returns the String that is to be considered the "value" of the displayable object.
     * 
     * @return the string value
     */
    public abstract String getStringValue ();

    /**
     * Returns the String that represents the label for the item/value being displayed.
     * 
     * @return the String label
     */
    public abstract String getLabel ();

}
