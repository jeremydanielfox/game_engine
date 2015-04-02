package shop;

import javafx.scene.Node;


public class CenterOffset {

    public static double getX (Node node) {
        return -node.getBoundsInLocal().getWidth() / 2;
    }

    public static double getY (Node node) {
        return -node.getBoundsInLocal().getHeight() / 2;
    }
    
//    public static double getRadius (Node node) {
//        return Math.sqrt(Math.)
//    }
}
