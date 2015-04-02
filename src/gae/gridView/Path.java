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
        System.out.println("Start coordinates: (" + start.x + " , " + start.y + ")");
        System.out.println("End coordinates: (" + end.x + " , " + end.y + ")");
        System.out.println("Control 1 coordinates: (" + control1.x + " , " + control1.y + ")");
        System.out.println("Control 2 coordinates: (" + control2.x + " , " + control2.y + ")");
    }
}
