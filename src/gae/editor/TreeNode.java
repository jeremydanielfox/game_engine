package gae.editor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Eric Saba
 *
 * A data node to create a tree of ComponentEditors for all editing capabilities.
 */
public class TreeNode {
    private ArrayList<TreeNode> myTreeNodes;
    private Method myMethod;
    private String myInputType;

    public TreeNode (Method method, String inputType) {
        myMethod = method;
        myInputType = inputType;
        myTreeNodes = new ArrayList<TreeNode>();
    }

    public Method getMethod () {
        return myMethod;
    }

    public String getInputType () {
        return myInputType;
    }

    public void addToNodes (TreeNode treeNode) {
        myTreeNodes.add(treeNode);
    }

    /**
     * added to provide outside class to access children nodes to iterate through
     * @ author Brandon
     * 
     * @return
     */
    public List<TreeNode> getChildren () {
        return myTreeNodes;
    }

    public int getNumChildren () {
        return myTreeNodes.size();
    }
}
