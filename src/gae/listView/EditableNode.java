package gae.listView;

import java.util.Iterator;
import engine.gameobject.PointSimple;
import View.ImageUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import gae.backend.Editable;
import gae.openingView.UIObject;


/**
 * When all the stats are set, consider deleting this class!!
 * 
 * @author Kei
 *
 */
public class EditableNode implements UIObject {
    private String myName;
    private String myType;
    private Editable editable;
    private int myID = 0;
    private ObservableList<Editable> myChildren;

    public EditableNode (Editable editable) {
        myName = editable.getName();
        myType = editable.getType();
        myChildren = FXCollections.observableArrayList();
        this.editable = editable;
    }

    public void clearChildren () {
        myChildren = FXCollections.observableArrayList();
    }

    public Editable makeNewInstance () {
        Editable copy = (Editable) DeepCopy.copy(editable);
        copy.setID(myID);
        myID++;
        // myChildren.add(copy);
        return copy;
    }

    public void remove (Editable editable) {
        Iterator<Editable> iter = myChildren.iterator();
        while (iter.hasNext()) {
            if (iter.next().equals(editable)) {
                iter.remove();
            }
        }
    }

    public ImageView getImageView () {
        return ImageUtilities.changeImageSize(new ImageView(new Image(editable.getImagePath())),
                                              75, 75);
    }

    public int getWidth () {
        return editable.getWidth();
    }

    public int getHeight () {
        return editable.getHeight();
    }

    public String getImagePath () {
        return editable.getImagePath();
    }

    public String getName () {
        return myName;
    }

    public String getType () {
        return myType;
    }

    public ObservableList<Editable> getChildrenList () {
        return myChildren;
    }

    public PointSimple getLocation () {
        return editable.getLocation();
    }

    @Override
    public boolean equals (Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        return true;
    }

    @Override
    public Node getObject () {
        // TODO Auto-generated method stub
        return null;
    }

}
