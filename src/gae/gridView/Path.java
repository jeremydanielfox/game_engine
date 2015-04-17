package gae.gridView;

import engine.gameobject.PointSimple;
import javafx.geometry.Point2D;


/**
 * Path object is used to store the four points (start, end, control1, control2) per path segment
 * 
 * @author Kei
 *
 */
public class Path {
    private PointSimple start;
    private PointSimple end;
    private PointSimple control1;
    private PointSimple control2;

    public Path (PointSimple start, PointSimple end, PointSimple control1, PointSimple control2) {
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

    public PointSimple getStart () {
        return start;
    }

    public PointSimple getEnd () {
        return end;
    }

    public PointSimple getControlOne () {
        return control1;
    }

    public PointSimple getControlTwo () {
        return control2;
    }

}
