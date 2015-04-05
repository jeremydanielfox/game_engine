package engine.gameobject;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import engine.gameobject.weapon.WeaponSimple;
import engine.gameobject.weapon.Weapon;


/**
 * 
 * @author Jeremy
 *
 *         This Bean class for GameObjectSimple will let the Game authoring environment create a
 *         GameObjectSimple
 *         through reflection without violating encapsulation.
 */
public class GameObjectSimpleBean {
    private GameObjectSimple myGameObjectSimple;
    private Node myNode;
    private String myImagePath;
    private String myLabel;
    private PointSimple myPoint;
    private Health myHealth;
    private Mover myMover;
    private Weapon myWeapon;
    private Graphic myGraphic;

    public GameObjectSimpleBean () {
        myGameObjectSimple = new GameObjectSimple();
        myNode = new ImageView();
        myImagePath = new String();
        myLabel = new String();
        myPoint = new PointSimple(0, 0);
        myHealth = new HealthSimple();
        myMover = new MoverPath(null, 0);
        myWeapon = new WeaponSimple(0, 0, null, null);
        myGraphic = new Graphic(0, 0, null);
    }

    public Node getNode () {
        return myNode;
    }

    public void setNode (Node node) {
        myNode = node;
    }

    public String getImagePath () {
        return myImagePath;
    }

    public void setImagePath (String imgpath) {
        myImagePath = imgpath;
    }

    public String getLabel () {
        return myLabel;
    }

    public void setLabel (String label) {
        myLabel = label;
    }

    public PointSimple getPointSimple () {
        return myPoint;
    }

    public void setPointSimple (PointSimple point) {
        myPoint = point;
    }

    public Health getHealth () {
        return myHealth;
    }

    public void setHealth (Health health) {
        myHealth = health;
    }

    public Mover getMover () {
        return myMover;
    }

    public void setMover (Mover mover) {
        myMover = mover;
    }

    public Weapon getWeapon () {
        return myWeapon;
    }

    public void setWeapon (Weapon weapon) {
        myWeapon = weapon;
    }

    public Graphic getGraphic () {
        return myGraphic;
    }

    public void setGraphic (Graphic graphic) {
        myGraphic = graphic;
    }

}
