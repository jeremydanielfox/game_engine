package engine.gameobject;

/**
 * 
 * @author Jeremy
 *
 */
public interface GameObject extends Movable, Health {
    // public void updateGraphics ();//cannot implement yet
    public void setWeapon (Weapon weapon);

    public Weapon getWeapons ();

    public String getLabel ();

    public GameObject clone ();

    public Pointlike getPoint ();

}
