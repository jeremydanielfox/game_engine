package engine.gameobject;


import engine.grid.GridCell;
import gameobject.Weapon;
import javafx.scene.Node;

/**
 * 
 * @author Jeremy
 *
 */
public interface GameObject extends Movable, Health {
    // public void updateGraphics ();//cannot implement yet
    /**
     * Adds a weapon to the GameObject's Collection of Weapons
     */
    public void addWeapon (Weapon weapon);


    /**
     * Returns the GameObject's Collection of Weapons
     */
  
    public Weapon getWeapon ();


    /**
     * Labels allow the GameEngine to differentiate
     * 
     * @return: A string
     */
    public String getLabel ();

    public GameObject clone ();

    
    public Node getGraphic();

    public PointSimple getPoint ();
    
    public GridCell getGridDimensions();


}
