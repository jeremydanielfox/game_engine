package gae.gridView;

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
}
