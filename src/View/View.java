package View;

import javafx.scene.Node;
import javafx.scene.control.Button;
import engine.game.Drawer;


public interface View {
    abstract void initializeGameWorld ();

    abstract void buildTimeline ();

    abstract void executeFrameActions ();

    abstract void displayShop ();
    
    abstract void pause ();
    
    abstract void play ();
    
    abstract void addButton (Button b,int x,int y);

    abstract Node initializeView ();
}
