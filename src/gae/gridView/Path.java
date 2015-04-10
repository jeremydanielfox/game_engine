package gae.gridView;

import javafx.geometry.Point2D;



public class Path {
    private Pair start;
    private Pair end;
    private Pair control1;
    private Pair control2;

    public Path (Pair start, Pair end, Pair control1, Pair control2) {
        this.start = start;
        this.end = end;
        this.control1 = control1;
        this.control2 = control2;
    }

    public void printInfo () {
        System.out.println("Start coordinates: (" + start.getX() + " , " + start.getY() + ")");
        System.out.println("End coordinates: (" + end.getX() + " , " + end.getY() + ")");
        System.out.println("Control 1 coordinates: (" + control1.getX() + " , " + control1.getY() +
                           ")");
        System.out.println("Control 2 coordinates: (" + control2.getX() + " , " + control2.getY() +
                           ")");
    }
    
    public Point2D getStart() {
        return new Point2D(start.getX(),start.getY());
    }
    
    public Point2D getEnd() {
        return new Point2D(end.getX(),end.getY());
    }
    
    public Point2D getControlOne() {
        return new Point2D(control1.getX(),control1.getY());
    }
    
    public Point2D getControlTwo() {
        return new Point2D(control2.getX(),control2.getY());
    }
    
}
