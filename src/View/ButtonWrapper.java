package View;

import java.util.function.Consumer;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import engine.fieldsetting.Settable;
import engine.goals.Goal;
import javafx.scene.control.Button;
import engine.goals.NullGoal;

@Settable
public class ButtonWrapper {

    @XStreamOmitField
    private transient Button myButton;
    private Goal myEnableCondition;
    
    public ButtonWrapper(){
        initializeFields("", e -> this.doNothing(), new NullGoal());
    }
    
    public ButtonWrapper(String l, Consumer<? extends Object> action, Goal enable) {
        initializeFields(l, action, enable);
    }
    
    private void initializeFields(String label, Consumer<? extends Object> action, Goal enable){
        myButton=new Button(label);
        myButton.setOnAction(e -> action.accept(null));
        myEnableCondition = enable;
    }
    
    private void doNothing(){
        
    }
    
    public Button getButton(){
        return myButton;
    }
    
    public boolean isEnabled(){
        return myEnableCondition.isSatisfied();
    }
    
    @Settable
    public void setLabel(String s){
        myButton.setText(s);
    }
    
    @Settable
    public void setAction(Consumer<? extends Object> action){
        myButton.setOnAction( e -> action.accept(null));
    }
    
    @Settable
    public void setEnableCondition(Goal enable){
        myEnableCondition = enable;
    }
    
}
