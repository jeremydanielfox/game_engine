package gae.gridView;

/**
 * In the process of trying to put the Anchors and the curve into one object
 */
import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import engine.gameobject.PointSimple;
import exception.ObjectOutOfBoundsException;


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
    private ArrayList<Node> visibleList;
    private ObjectProperty<Integer> addPath = new SimpleObjectProperty<Integer>();
    private ObjectProperty<String> addPathInstructions = new SimpleObjectProperty<String>();

    public PathSet (ArrayList<Anchor> anchorList,
                    StackPane scene,
                    int index,
                    ContainerWrapper container) {
        root = new Group();
        root.setManaged(false);
        this.container = container;
        getChildren().add(root);
        this.anchorList = anchorList;
        stack = scene;
        this.index = index;
        curve = new CubicCurve();
        curve.setFill(Color.TRANSPARENT);
        makePath = true;
        choosePoint(curve);
        visibleList = new ArrayList<>();
    }

    public AuthoringPath getPathObject () {
        // EDIT: We could just use Point2D --> this is JavaFX so not recommended
        PointSimple start =
                container.convertCoordinates(curve.getStartX(), curve.getStartY());
        PointSimple end =
                container.convertCoordinates(curve.getEndX(), curve.getEndY());
        PointSimple control1 =
                container.convertCoordinates(curve.getControlX1(), curve.getControlY1());
        PointSimple control2 =
                container.convertCoordinates(curve.getControlX2(), curve.getControlY2());
        return new AuthoringPath(start, end, control1, control2);
    }

    public void changeColor (Color color) {
        curve.setStroke(color);
    }

    public void changeIndex (int value) {
        pathLabel.changeValue(value);
    }

    public void makeVisible (boolean visible) {
        for (Node node : visibleList) {
            node.setVisible(visible);
        }
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
        if (container.checkBounds(x, y)) {
            throw new ObjectOutOfBoundsException();
        }
    }

    private void choosePoint (CubicCurve curve) {
        stack.setOnMouseClicked(e -> {
            if (increment == 0 && makePath) {
                startX = e.getX();
                startY = e.getY();
                checkBounds(startX, startY);
                pathLabel = new PathLabel(index);
                start = new Anchor(Color.GREEN, startX, startY, pathLabel);
                root.getChildren().add(pathLabel);
                addAnchor(start);
                addPath.setValue(increment);
                addPathInstructions.setValue("Place Ending Point");
                increment++;

            }
            else if (increment == 1 && makePath) {
                addPath.setValue(increment);
                addPathInstructions.setValue("Make Path");
                Anchor end = new Anchor(Color.RED, e.getX(), e.getY());
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

                curve.setStroke(Color.BLUE);
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
                visibleList.addAll(Arrays.asList(new Node[] { control1, control2, controlLine1,
                                                              controlLine2 }));
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
