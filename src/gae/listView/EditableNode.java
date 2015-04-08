package gae.listView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import engine.gameobject.Editable;


/**
 * A TreeNode class for handling the Editables
 * 
 * @author Kei Yoshikoshi
 *
 */
public class EditableNode extends Region {
    private String myName;
    private String myType;
    private Editable editable;
    private List<Editable> myChildren;
    private ListView listView;

    public EditableNode (Editable editable) {
        myName = editable.getName();
        myType = editable.getType();
        myChildren = new ArrayList<>();
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
        myChildren = new ArrayList<>();
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

    public String getName () {
        return myName;
    }

    public String getType () {
        return myType;
    }

    public List<Editable> getChildrenList () {
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
