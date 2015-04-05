package View;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * This class acts as a heads up display, which contains player stats and the shop for the game.
 * It can display "displayable" objects as well as any other nodes.
 * 
 * @author Sierra Smith
 *
 */
public class HUD implements Observer {

    // note: this should change with screen size, fix it later
    private static final double TEXT_SPACING = 10;

    private Map<Displayable, Text> myDisplayFields;
    private VBox myDisplay;

    public HUD(){
        initialize();
    }
    
    public HUD (Displayable ... displays) {
        initialize();
        for (Displayable d : displays) {
            addPairedDisplay(d);
        }
    }
    
    private void initialize(){
        myDisplay = new VBox();
        myDisplayFields = new HashMap<>();
    }

    public void addPairedDisplay (Displayable d) {
        d.addObserver(this);
        HBox newBox = new HBox(TEXT_SPACING);
        Text label = new Text(d.getLabel());
        Text value = new Text(d.getValue() + "");
        newBox.getChildren().addAll(label, value);
        myDisplayFields.put(d, value);
        myDisplay.getChildren().add(newBox);
    }

    public Node getDisplay () {
        return myDisplay;
    }

    @Override
    public void update (Observable o, Object arg) {
        for (Displayable d : myDisplayFields.keySet()) {
            if (d.equals(o)) {
                myDisplayFields.get(d).setText(d.getValue() + "");
                break;
            }
        }
    }

}
