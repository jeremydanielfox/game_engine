package engine.gameobject;

import engine.gameobject.weapon.Weapon;
import engine.grid.GridCell;

/**
 * 
 * @author Jeremy
 *
 */
public interface GameObject extends Movable, Health {
    // public void updateGraphics ();//cannot implement yet
    public void setWeapon (Weapon weapon);

    public Weapon getWeapon ();

    public String getLabel ();

    public GameObject clone ();

    public PointSimple getPoint ();
    
    public GridCell getGridDimensions();

}
