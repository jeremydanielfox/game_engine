package gae.listView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import View.ImageUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import engine.gameobject.Editable;


/**
 * A TreeNode class for handling the Editables
 * 
 * @author Kei Yoshikoshi
 *
 */
public class EditableNode {
    private String myName;
    private String myType;
    private Editable editable;
    private ObservableList<Editable> myChildren;
    private ListView listView;

    public EditableNode (Editable editable) {
        myName = editable.getName();
        myType = editable.getType();
        myChildren = FXCollections.observableArrayList();
        this.editable = editable;
        /*
         * Get ListView from Nina's class --> each EditableNode will have an editable object + it's
         * listView
         */
        listView = null;
    }

    public ListView getListView () {
        return listView;
    }

    public void clearChildren () {
        myChildren = FXCollections.observableArrayList();
    }

    public Editable makeNewInstance () {
        Editable copy = (Editable) DeepCopy.copy(editable);
        myChildren.add(copy);
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
        return ImageUtilities.changeImageSize(new ImageView(new Image(getClass()
                .getResourceAsStream(editable.getImage()))), 75, 75);
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

}
