package engine.gameobject.weapon.upgradetree.upgradebundle;

import engine.gameobject.weapon.upgradetree.UpgradeTree;


/**
 * Set of methods used to build and traverse an UpgradeTree
 * 
 * @author Nathan Prabhu
 *
 */
public interface Buildable {

    /**
     *
     * @return child of UpgradeBundle. Will return null if no children.
     */
    public BuildableBundle getNext ();

    /**
     * Adds children object. To be called during tree-building.
     */
    public void addChild (Buildable child);

    /**
     * Mark this upgrade as the last child in an UpgradeTree.
     *
     * @return
     */
    public void markFinalUpgrade ();

    /**
     * sets the appropriate parent. Caution: should only be used with single trees.
     * 
     * @param tree
     */
    public void setParent (UpgradeTree tree);
}
