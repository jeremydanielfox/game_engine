package engine.gameobject.weapon.upgradetree.upgradebundle;

/**
 * Marker interface that allows access to methods in UpgradeBundle AND Buildable. Motivation for
 * this interface came from separating dangerous Buildable methods from UpgradeBundle's interface.
 * 
 * @author Nathan Prabhu
 *
 */
public interface BuildableBundle extends UpgradeBundle, Buildable {

}
