package gae.builder;

import View.ButtonWrapper;

/**
 * Represents the data back end of ButtonBuilder and utilizes the Builder/BuildObjectData interface
 * system
 * 
 * @author Brandon Choi
 *
 */
public class ButtonData implements BuildObjectData {

    ButtonWrapper myButton;
    
    public ButtonData () {
        myButton = new ButtonWrapper();
    }

    @Override
    public void fillProperties () {
        
    }

    @Override
    public Object getBuiltObject () {
        return myButton;
    }

}
