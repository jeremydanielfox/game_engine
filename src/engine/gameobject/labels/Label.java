package engine.gameobject.labels;

import java.util.Collection;

public interface Label {

    public String getLabel();
    
    public boolean hasSubLabel();
    
    public Collection<Label> getSubLabels();
    
    public boolean hasSuperLabel();
    
    public Label getSuperLabel();
    
    public void addSubLabel(Label label);
    
}
