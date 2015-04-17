package gae.gridView;

/**
 * In the process of trying to put the Anchors and the curve into one object
 */
import java.util.ArrayList;
import exception.ObjectOutOfBoundsException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;


public class PathSet extends Region {
    private int increment;
    private boolean makePath;
    private double startX;
    private double startY;
    private Anchor start;
    private ArrayList<Anchor> anchorList;
    private Group root;
    private CubicCurve curve;
    private int index;
    private PathLabel pathLabel;
    private StackPane stack;
    private ContainerWrapper container;
    private ObjectProperty<Integer> addPath = new SimpleObjectProperty<Integer>();
    private ObjectProperty<String> addPathInstructions = new SimpleObjectProperty<String>();

    public PathSet (ArrayList<Anchor> anchorList,
                    StackPane scene,
                    int index,
                    ContainerWrapper container) {
        root = new Group();
        root.setManaged(false);
        this.container = container;
        this.getChildren().add(root);
        this.anchorList = anchorList;
        this.stack = scene;
        this.index = index;
        init();
    }

    public Path getPathObject () {
        // EDIT: We could just use Point2D --> this is JavaFX so not recommended
        Point2D start =
                container.convertCoordinates(curve.getStartX(), curve.getStartY());
        Point2D end =
                container.convertCoordinates(curve.getEndX(), curve.getEndY());
        Point2D control1 =
                container.convertCoordinates(curve.getControlX1(), curve.getControlY1());
        Point2D control2 =
                container.convertCoordinates(curve.getControlX2(), curve.getControlY2());
        return new Path(start, end, control1, control2);
    }

    public void changeColor (Color color) {
        curve.setStroke(color);
    }

    public void changeIndex (int value) {
        pathLabel.changeValue(value);
    }

    private void init () {
        curve = new CubicCurve();
        curve.setFill(Color.TRANSPARENT);
        makePath = true;
        choosePoint(curve);
    }

    public CubicCurve getCurve () {
        return curve;
    }

    public ObjectProperty<Integer> addPathProperty () {
        return addPath;
    }

    public ObjectProperty<String> addPathInstructionsProperty () {
        return addPathInstructions;
    }

    private void checkBounds (double x, double y) {
        if (container.checkBounds(x, y))
            throw new ObjectOutOfBoundsException();
    }

    private void choosePoint (CubicCurve curve) {
        stack.setOnMouseClicked(e -> {
            if (increment == 0 && makePath) {
                startX = e.getX();
                startY = e.getY();
                checkBounds(startX, startY);
                pathLabel = new PathLabel(index);
                start = new Anchor(Color.PALEGREEN, startX, startY, pathLabel);
                root.getChildren().add(pathLabel);
                addAnchor(start);
                addPath.setValue(increment);
                addPathInstructions.setValue("Place Ending Point");
                increment++;

            }
                else if (increment == 1 && makePath) {
                    addPath.setValue(increment);
                    addPathInstructions.setValue("Make Path");
                    Anchor end = new Anchor(Color.TOMATO, e.getX(), e.getY());

                    curve.setStartX(startX);
                    curve.setStartY(startY);
                    double endX = e.getX();
                    double endY = e.getY();
                    checkBounds(endX, endY);

                    curve.setEndX(e.getX());
                    curve.setEndY(e.getY());
                    addAnchor(end);
                    Anchor control1 =
                            new Anchor(Color.GOLD, (startX + e.getX()) / 2, (startY + e.getY()) / 2);
                    Anchor control2 =
                            new Anchor(Color.GOLDENROD, (startX + e.getX()) / 2,
                                       (startY + e.getY()) / 2);

                    bindProperties(curve, start, end, control1, control2);

                    curve.setStroke(Color.FORESTGREEN);
                    curve.setStrokeWidth(4);
                    curve.setStrokeLineCap(StrokeLineCap.ROUND);

                    increment = 0;
                    makePath = false;

                    Line controlLine1 =
                            new BoundLine(curve.controlX1Property(), curve.controlY1Property(),
                                          curve.startXProperty(), curve.startYProperty());
                    Line controlLine2 =
                            new BoundLine(curve.controlX2Property(), curve.controlY2Property(),
                                          curve.endXProperty(), curve.endYProperty());

                    root.getChildren()
                            .addAll(curve, control1, control2, controlLine1, controlLine2);
                }
            });
    }

    private void addAnchor (Anchor anchor) {
        checkForIntersect(anchor);
        anchor.setOnMouseReleased(f -> {
            checkForIntersect(anchor);
        });
        anchorList.add(anchor);
        root.getChildren().add(anchor);
    }

    private void bindProperties (CubicCurve curve,
                                 Anchor start,
                                 Anchor end,
                                 Anchor control1,
                                 Anchor control2) {
        start.bind(curve.startXProperty(), curve.startYProperty());
        end.bind(curve.endXProperty(), curve.endYProperty());
        control1.bind(curve.controlX1Property(), curve.controlY1Property());
        control2.bind(curve.controlX2Property(), curve.controlY2Property());
    }

    private void checkForIntersect (Anchor point) {
        for (Anchor anchor : anchorList) {
            if (anchor.checkIntersect(point)) {
                point.setCenterX(anchor.getCenterX());
                point.setCenterY(anchor.getCenterY());
            }
        }
    }

}
