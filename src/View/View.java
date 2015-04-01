package View;

import game.Drawer;


public interface View {
    abstract void initializeGameWorld (Drawer drawer);

    abstract void buildTimeline ();

    abstract void executeFrameActions ();

    abstract void displayShop ();
    
    abstract void pause ();
    
    abstract void play ();
}
