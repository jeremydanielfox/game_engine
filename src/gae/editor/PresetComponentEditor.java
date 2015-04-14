package gae.editor;

import javafx.scene.control.Label;

public class PresetComponentEditor extends ComponentEditor{
    private Label objectType;
    
    public PresetComponentEditor () {
        super();
        setName("Object type: ");
        objectType = new Label();
        getEditBox().getChildren().addAll(getLabel(), objectType);
    }

    @Override
    public Object createObject (Class<?> c) {
        return objectType.getText();
    }

    @Override
    void clear () {
        // TODO Auto-generated method stub
        
    }

    @Override
    void defaultField () {
        // TODO Auto-generated method stub
        
    }
    
    public void setObjectType(String type) {
        objectType.setText(type);
    }

}
