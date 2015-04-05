package View;

import java.util.Observable;

public abstract class Displayable extends Observable {

    public abstract double getValue();
    public abstract String getLabel();
    
}
