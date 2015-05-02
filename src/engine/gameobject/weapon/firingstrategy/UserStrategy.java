package engine.gameobject.weapon.firingstrategy;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.PointSimple;
import engine.gameobject.graphics.Graphic;
import gameworld.ObjectCollection;
//TODO: THIS IS DUPLICATED AS FUCK FROM MOVERUSER AND THE IF STATEMENT TREE HAS TO BE REFACTORED AS SHIT.
public class UserStrategy extends BasicStrategy {

    private final int MAX_KEY_DELAY = 10;
    private int timeSincePressed = Integer.MAX_VALUE;
    private int firingTimeLimit;
    private int timeSinceFire;
    private KeyCode myKey;
    private Graphic myGraphic;
    private PointSimple myDirection;
    private FiringStrategy myStrategy;
    @XStreamOmitField
    private transient Node myNode;
    
    public UserStrategy(){
        myKey = KeyCode.SPACE;
        myGraphic = new Graphic();
        myDirection = new PointSimple(0, 0);
        firingTimeLimit = Integer.MAX_VALUE;
        timeSinceFire = 0;
        myStrategy = new SingleProjectile();
    }
    
    public UserStrategy(KeyCode myKey){
        this();
        this.myKey = myKey;
    }
    
    /**
     * Set firing rate such that it will fire @param per second
     * @param firePerSecond
     */
    public void setFiringRate(double firePerSecond){
        firingTimeLimit = (int) (60/firePerSecond);
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
    
    @Settable
    public void setFiringNumber(int number){
        if (number > 1){
            myStrategy = new MultipleProjectile(number);
        }
    }
    
    @Override
    public void execute (ObjectCollection world,
                         GameObject target,
                         PointSimple location,
                         GameObject prototype) {
        timeSinceFire ++;
        timeSincePressed ++;
        if (myNode == null)
            initializeNode(myGraphic);
        if (canFire()){
            PointSimple newLocation = location.add(myDirection.multiply(10));
            GameObject dummyObject = new GameObjectSimple();
            dummyObject.setPoint(newLocation);
            myStrategy.execute(world, dummyObject, location, prototype);
            timeSincePressed = Integer.MAX_VALUE/2;
            timeSinceFire = 0;
        }
    }

    private boolean canFire(){
        return timeSinceFire > firingTimeLimit && timeSincePressed < MAX_KEY_DELAY;
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
            timeSincePressed = 0;
        }
    }
}
