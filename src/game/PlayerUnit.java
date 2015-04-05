package game;

public class PlayerUnit implements Displayable{

    private double myValue;
    private String myLabel;
    
    public PlayerUnit(double value, String label){
        myLabel = label;
        myValue = value;
    }
    
    public void setValue(double value){
        myValue = value;
    }
    
    public void setLabel(String label){
        myLabel = label;
    }
    
    @Override
    public double getValue () {
        return myValue;
    }

    @Override
    public String getLabel () {
        return myLabel;
    }

}
