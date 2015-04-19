package engine.gameobject.labels;

import java.util.Collection;

public class SimpleLabel implements Label{

    private Label superLabel;
    private String myName;
    public SimpleLabel(){
        superLabel = new LabelBase();
        myName = "";
    }
    
    public SimpleLabel(String name){
        myName = name;
        superLabel = new LabelBase();
    }
    
    public SimpleLabel(String name, Label parent){
        myName = name;
        superLabel = parent;
    }
    
    public void setName(String name){
        myName = name;
    }
    
    public void setSuperLabel(Label parent){
        superLabel = parent;
    }
    public Label getSuperLabel(){
        return superLabel;
    }
    
    public String getName(){
        return myName;
    }
    
    //TODO: equals method needs work... possibly should go all the way down to baselabel
    @Override
    public boolean equals(Object other){
        if (other instanceof Label){
            if (((Label) other).getName().equals(myName)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        return myName.hashCode();
    }
}
