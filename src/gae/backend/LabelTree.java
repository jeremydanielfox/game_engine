package gae.backend;

import java.util.ArrayList;
import java.util.List;


/**
 * Tree meant to represent the hierarchies of labels on game objects. This will be used to control
 * the interactions within the game and will be utilized when setting the interaction table.
 *
 * @author Brandon Choi
 *
 */

public class LabelTree {

    /*
     * There can be multiple roots.
     *
     * Case: Projectile, Tower, and Enemy can all be separate labels. Each of these could have their
     * own hierarchies and thus each one is a root node in its own tree. But all these roots make
     * the entire LabelTree.
     */
    private List<LabelNode> roots;

    public LabelTree () {
        roots = new ArrayList<>();
    }

    /**
     * adds a new root node
     *
     * @param n
     */
    public void addRoot (LabelNode n) {
        roots.add(n);
    }

    /**
     * by returning all the roots, the caller now has access to iterate through each of the trees
     *
     * @return
     */
    public List<LabelNode> getRoots () {
        return roots;
    }
}
