package View;

import java.util.function.Consumer;
import javafx.scene.control.Button;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import engine.fieldsetting.Settable;
import engine.goals.Goal;
import engine.goals.NullGoal;


@Settable
public class ButtonWrapper {

    @XStreamOmitField
    private transient Button myButton;
    private Goal myEnableCondition;
    private String myLabel;
    private Consumer<? extends Object> myAction;
    private Goal myGoal;

    public ButtonWrapper () {
        initializeFields("", e -> doNothing(), new NullGoal());
    }

    public ButtonWrapper (String l, Consumer<? extends Object> action, Goal enable) {
        myLabel = l;
        myAction = action;
        myGoal = enable;
    }

    private void initializeFields (String label, Consumer<? extends Object> action, Goal enable) {
        myButton = new Button(label);
        myButton.setOnAction(e -> action.accept(null));
        myEnableCondition = enable;
    }

    private void doNothing () {

    }

    // TODO: make initialize fields work
    public Button getButton () {
        if (myButton == null) {
            initializeFields(myLabel, myAction, myGoal);
        }
        return myButton;
    }

    public boolean isEnabled () {
        if (myEnableCondition == null) {
            initializeFields(myLabel, myAction, myGoal);
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
