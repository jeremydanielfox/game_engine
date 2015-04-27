package View;

import java.util.function.Consumer;
import javafx.scene.control.Button;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import engine.fieldsetting.Settable;
import engine.goals.Goal;
import engine.goals.NullGoal;


/**
 * This class contains information needed to create, enable, and set up the click action for a
 * button. It was created to allow a game designer to add a custom button to the game and use a goal
 * to determine when it should be enabled and give it a lambda function to execute on click. It
 * assumes that anything passed into it's constructor and set methods is not null, and it depends on
 * the Goal interface to act as the enable condition for the button.
 * 
 * @author Sierra
 * @author Cosette
 *
 */
@Settable
public class ButtonWrapper {

    private static final String DEFAULT_TEXT = "";

//    @XStreamOmitField
//    private transient Button myButton;
    private Goal myEnableCondition;
    private String myLabel;
    private Consumer<? extends Object> myAction;
    @XStreamOmitField
    private transient Button myButton;

    public ButtonWrapper () {
        initializeFields(DEFAULT_TEXT, e -> doNothing(), new NullGoal());
    }

    public ButtonWrapper (String l, Consumer<? extends Object> action, Goal enable) {
        initializeFields(l, action, enable);
    }

    /**
     * Takes in the information needed to construct a button and initializes everything.
     * 
     * @param label
     * @param action
     * @param enable
     */
    private void initializeFields (String label, Consumer<? extends Object> action, Goal enable) {
        myButton = new Button(label);
        myButton.setOnAction(e -> action.accept(null));
        myEnableCondition = enable;
    }

    /**
     * This method is empty and is used as the default button action in the empty button wrapper
     * constructor so that a button whose action is uninitialized will do nothing but will not cause
     * a null pointer exception.
     */
    private void doNothing () {

    }

    /**
     * Initializes the button if it is currently null and returns the JavaFX button.
     * 
     * @return the actual JavaFX Button object
     */
    public Button getButton () {
        if (myButton == null) {
            initializeFields(myLabel, myAction, myEnableCondition);
        }
        return myButton;
    }

    /**
     * Returns true is the enable condition (goal) is satisfied, otherwise false.
     * 
     * @return a boolean that indicates if the button should be enabled
     */
    public boolean isEnabled () {
        if (myEnableCondition == null) {
            initializeFields(myLabel, myAction, myEnableCondition);
        }
        return myEnableCondition.isSatisfied();
    }

    @Settable
    public void setLabel (String s) {
        myButton.setText(s);
    }

    @Settable
    public void setAction (Consumer<? extends Object> action) {
        myButton.setOnAction(e -> action.accept(null));
    }

    @Settable
    public void setEnableCondition (Goal enable) {
        myEnableCondition = enable;
    }

}
