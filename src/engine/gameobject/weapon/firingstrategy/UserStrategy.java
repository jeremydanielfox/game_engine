package engine.gameobject.weapon.firingstrategy;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.Graphic;
import engine.gameobject.PointSimple;
import gameworld.ObjectCollection;
//TODO: THIS IS DUPLICATED AS FUCK FROM MOVERUSER AND THE IF STATEMENT TREE HAS TO BE REFACTORED AS SHIT.
public class UserStrategy extends BasicStrategy {

    private boolean pressedRecently = false;
    private KeyCode myKey;
    private Graphic myGraphic;
    private PointSimple myDirection;
    @XStreamOmitField
    private transient Node myNode;
    
    public UserStrategy(){
        myKey = KeyCode.SPACE;
        myGraphic = new Graphic();
        myDirection = new PointSimple(0, 0);
    }
    
    public UserStrategy(KeyCode myKey){
        this.myKey = myKey;
    }
    
    private void initializeNode (Graphic graphic) {
        myNode = graphic.getNode();
        myNode.setFocusTraversable(true);
        myNode.addEventHandler(KeyEvent.KEY_PRESSED, e -> handleKeyInput(e));
    }
    
    @Settable
    public void setKey(KeyCode myKey){
        this.myKey = myKey;
    }
    
    @Settable
    public void setGraphic (Graphic graphic) {
        myGraphic = graphic;
    }
    
    @Override
    public void execute (ObjectCollection world,
                         GameObject target,
                         PointSimple location,
                         GameObject prototype) {
        if (myNode == null)
            initializeNode(myGraphic);
        if (pressedRecently){
            PointSimple newLocation = location.add(myDirection.multiply(10));
            GameObject newProjectile = makeProjectile(location, newLocation, prototype);
            world.addObject(newProjectile);
            pressedRecently = false;
        }
    }

    private void handleKeyInput (KeyEvent e) {
        KeyCode keyCode = e.getCode();
        if (keyCode == KeyCode.D)
            myDirection = new PointSimple(1, .01);
        else if (keyCode == KeyCode.A)
            myDirection = new PointSimple(-1, .01);
        else if (keyCode == KeyCode.W)
            myDirection = new PointSimple(.01, -1);
        else if (keyCode == KeyCode.S)
            myDirection = new PointSimple(.01, 1);
        else if (keyCode == myKey){
            pressedRecently = true;
        }
    }
}
