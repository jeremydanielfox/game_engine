package gae.backend;

import engine.gameobject.Editable;
import gae.gridView.Pair;
import gae.listView.EditableImage;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


public class TileData implements Editable {
    // bound to TileView's coordinates
    ObjectProperty<Integer> x_coor = new SimpleObjectProperty<>();
    ObjectProperty<Integer> y_coor = new SimpleObjectProperty<>();

    @Override
    public void edit () {
    }

    public void setEditable (Editable editable) {

    }

    public Editable getEditable () {
        return null;
    }

    @Override
    public String getName () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getType () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getImagePath () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLocation (double x, double y) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Pair getLocation () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setEditableImage (EditableImage image) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public EditableImage getEditableImage () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setID (int id) {
        // TODO Auto-generated method stub
        
    }
}
