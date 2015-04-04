package gae.gridView;
/**
 * In the process of trying to put the Anchors and the curve into one object
 */
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;


public class PathSet {
    private Group root;
    private Scene myScene;
    private int increment;
    private boolean makePath;
    private double startX;
    private double startY;
    private Anchor start;
    private CubicCurve curve;
    private ArrayList<Anchor> anchorList;

    public PathSet () {
        anchorList = new ArrayList<>();
    }

    public void setCurve(CubicCurve curve) {
        this.curve = curve;
    }
    public void setStart(double x, double y) {
        start = new Anchor(Color.PALEGREEN, startX, startY);
        addAnchor(start);
        increment++;
    }
    private void choosePoint (CubicCurve curve) {
        myScene.setOnMouseClicked(e -> {
            if (increment == 0 && makePath) {
                startX = e.getX();
                startY = e.getY();
                start = new Anchor(Color.PALEGREEN, startX, startY);
                addAnchor(start);
                increment++;
            }
                else if (increment == 1 && makePath) {
                    Anchor end = new Anchor(Color.TOMATO, e.getX(), e.getY());

                    curve.setStartX(startX);
                    curve.setStartY(startY);
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