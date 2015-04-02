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
}
