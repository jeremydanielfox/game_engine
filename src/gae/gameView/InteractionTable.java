package gae.gameView;

import javafx.scene.control.Button;

/**
 * table that will allow the user to define specific interactions between game objects such as what
 * happens when a tower and enemy collide, etc.
 * 
 * @author Brandon Choi
 *
 */

public class InteractionTable {

    private static final String ADD_TEXT = "Add New Interaction";
    private Button adder;

    public InteractionTable () {
        adder = new Button(ADD_TEXT);
    }

    private void setUpButtons () {
        adder.setOnMouseClicked(e -> {
            
        });
    }
}
