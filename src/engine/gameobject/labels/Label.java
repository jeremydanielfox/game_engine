package engine.gameobject.labels;

import java.util.Collection;

public interface Label {

    public void setName(String name);
    
    public String getName();
    
    public Label getSuperLabel();
    
    public void setSuperLabel(Label parent);
    
    public Label clone();
    /*public String getLabel();
    
    public boolean hasSubLabel();
    
    public Collection<Label> getSubLabels();
    
    public boolean hasSuperLabel();
    
    public Label getSuperLabel();
    
    public void addSubLabel(Label label);
    
    public void removeSubLabel(Label label);*/
    
}
