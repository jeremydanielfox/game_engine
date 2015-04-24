package gae.backend;

import java.util.ArrayList;
import java.util.List;


/**
 * LabelNode represents a single node in the LabelTree
 *
 * @author Brandon Choi
 *
 */
public class LabelNode {

    private List<LabelNode> children;
    private String label;
    private LabelNode myParent;

    public LabelNode (String s) {
        children = new ArrayList<>();
        label = s;
    }

    /**
     * allows user to reset the label
     *
     * @param change
     */
    public void setLabel (String change) {
        label = change;
    }

    /**
     * allows user to add a new child to TreeNode
     *
     * @param n
     */
    public void addChild (LabelNode n) {
        children.add(n);
        n.setParent(this);
    }

    /**
     * sets the parent node. This is called on the child node whenever addChild() is performed.
     *
     * @param n
     */
    public void setParent (LabelNode n) {
        myParent = n;
    }

    /**
     * The three methods below are standard get methods to pull information when traversing through
     * a LabelTree
     *
     * @return
     */

    public List<LabelNode> getChildren () {
        return children;
    }

    public String getLabel () {
        return label;
    }

    public LabelNode getParent () {
        return myParent;
    }
}
