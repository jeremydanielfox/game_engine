package gae.gridView;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;


public class temp {
    private Group root;
    private Scene myScene;
    private int increment;
    private boolean makePath;
    private double startX;
    private double startY;
    private Anchor start;
    private ArrayList<CubicCurve> curveList;
    private ArrayList<Anchor> anchorList;

    public temp () {
        curveList = new ArrayList<>();
        anchorList = new ArrayList<>();
    }

    public Scene getScene () {
        root = new Group();
        root.getChildren().addAll(makeBezierCurve(), completePath());
        myScene = new Scene(root);
        return myScene;
    }

    private Button makeBezierCurve () {
        Button makeCurve = new Button("Make Path");
        makeCurve.setTranslateX(500);
        makeCurve.setTranslateY(0);
        makeCurve.setOnMouseClicked(e -> {
//            PathSet path = new PathSet();
            CubicCurve curve = new CubicCurve();
//            path.setCurve(curve);
            curve.setFill(Color.TRANSPARENT);
            curveList.add(curve);
            makePath = true;
            choosePoint(curve);
        });
        return makeCurve;
    }

    private Button completePath () {
        Button complete = new Button("Path Complete");
        complete.setTranslateX(600);
        complete.setTranslateY(0);
        complete.setOnMouseClicked(e -> createPathObjects());
        return complete;
    }

    private List<Path> createPathObjects () {
        /*
         * TODO: Make sure the use is able to delete specific ones and send the paths in order
         */
        List<Path> pathList = new ArrayList<>();
        for (CubicCurve curve : curveList) {
            Pair start = new Pair(curve.getStartX(), curve.getStartY());
            Pair end = new Pair(curve.getEndX(), curve.getEndY());
            Pair control1 = new Pair(curve.getControlX1(), curve.getControlY1());
            Pair control2 = new Pair(curve.getControlX2(), curve.getControlY2());
            Path path = new Path(start, end, control1, control2);
            pathList.add(path);
        }
        return pathList;
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
