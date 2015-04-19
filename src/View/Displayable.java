package View;

import java.util.Observable;

public abstract class Displayable extends Observable {

    public abstract String getStringValue();
    public abstract String getLabel();
    
}
