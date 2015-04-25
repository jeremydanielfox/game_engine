package engine.gameobject;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import engine.fieldsetting.Settable;
import engine.pathfinding.EndOfPathException;

@Settable
public class MoverUser extends BasicMover {
    private PointSimple none = new PointSimple(0, 0);
    private PointSimple currentDirection = none;
    private Graphic myGraphic;
    @XStreamOmitField
    private transient Node myNode;
    
    public MoverUser() {
        this.inherentSpeed=5;
    }

    @Override
    public PointSimple move (PointSimple current) throws EndOfPathException {
        if (myNode==null)
            initializeNode(myGraphic);
        PointSimple direction = new PointSimple(currentDirection);
        currentDirection = none;
        return current.add(direction);
    }
    @Settable
    public void setGraphic(Graphic graphic) {
        myGraphic = graphic;
    }

    /**
     * @param graphic
     */
    private void initializeNode (Graphic graphic) {
        myNode = graphic.getNode();
        myNode.setFocusTraversable(true);
        myNode.setOnKeyPressed(e -> handleKeyInput(e));
    }

    @Override
    public Mover clone () {
        // TODO Auto-generated method stub
        return null;
    }

    public void moveRight () {
        currentDirection = new PointSimple(this.inherentSpeed, 0);
    }

    public void moveLeft () {
        currentDirection = new PointSimple(-this.inherentSpeed, 0);
    }

    public void moveNorth () {
         currentDirection = new PointSimple(0, -this.inherentSpeed);
    }

    public void moveSouth () {
        currentDirection = new PointSimple(0, this.inherentSpeed);
    }

    private void handleKeyInput (KeyEvent e) {
        System.out.println("handled");
        KeyCode keyCode = e.getCode();
        if (keyCode == KeyCode.D) {
            System.out.println("RIGHT");
            moveRight();
        }
        else if (keyCode == KeyCode.A) {
            System.out.println("BOOM BABY LEFT");
            moveLeft();
        }
        else if (keyCode == KeyCode.W) {
            System.out.println("up");
            moveNorth();
        }
        else if (keyCode == KeyCode.S) {
            System.out.println("down");
            moveSouth();
        }
    }
}
