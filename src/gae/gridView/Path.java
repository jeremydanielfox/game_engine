package gae.gridView;

import javafx.geometry.Point2D;



public class Path {
    private Pair start;
    private Pair end;
    private Pair control1;
    private Pair control2;

    public Path (Point2D start, Point2D end, Point2D control1, Point2D control2) {
        this.start = new Pair(start.getX(), start.getY());
        this.end = new Pair(end.getX(), end.getY());
        this.control1 = new Pair(control1.getX(), control1.getY());
        this.control2 = new Pair(control2.getX(), control2.getY());
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
