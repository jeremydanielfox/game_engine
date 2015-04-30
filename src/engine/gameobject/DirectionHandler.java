package engine.gameobject;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class DirectionHandler {
    private Graphic myGraphic;
    private PointSimple myDirection;
    @XStreamOmitField
    private transient Node myNode;
    
    private final PointSimple ORIGIN = new PointSimple(0,0);
    
    public DirectionHandler(){
        myDirection = ORIGIN;
        myGraphic = new Graphic();
    }
    
    public void setGraphic (Graphic graphic) {
        myGraphic = graphic;
    }
    
    public PointSimple getDirection(){
        if (myNode == null)
        initializeNode(myGraphic);
        PointSimple ret = new PointSimple(myDirection.getX(), myDirection.getY());
        myDirection = ORIGIN;
        return ret;
    }
    
    /**
     * @param graphic
     */
    private void initializeNode (Graphic graphic) {
        myNode = graphic.getNode();
        myNode.setFocusTraversable(true);
        myNode.addEventHandler(KeyEvent.KEY_PRESSED, e -> handleKeyInput(e));
    }
    
    private void handleKeyInput (KeyEvent e) {
        KeyCode keyCode = e.getCode();
        if (keyCode == KeyCode.D)
            myDirection = new PointSimple(1, 0);
        else if (keyCode == KeyCode.A)
            myDirection = new PointSimple(-1, 0);
        else if (keyCode == KeyCode.W)
            myDirection = new PointSimple(0, -1);
        else if (keyCode == KeyCode.S)
            myDirection = new PointSimple(0, 1);
    }
    
    public DirectionHandler clone(){
        DirectionHandler clone = new DirectionHandler();
        clone.setGraphic(myGraphic);
        return clone;
    }
}
