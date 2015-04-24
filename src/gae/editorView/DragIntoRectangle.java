package gae.editorView;

import engine.gameobject.PointSimple;
import gae.gridView.ContainerWrapper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import View.ViewUtil;


public class DragIntoRectangle extends Group implements ContainerWrapper {
    private static final double LABEL_PROPORTIONS_Y = 0.4;
    // 11 ALPHABETS ACROSS
    private double width;
    private String type;
    private double rectSize;
    private Rectangle rect;
    private List<Node> nodesList;
    private Scene scene;

    public DragIntoRectangle (double width, String label, Scene scene) {
        this.width = width;
        this.scene = scene;
//        type = label;
        rectSize = width / 5;
        rect = new Rectangle(rectSize, rectSize, Color.TRANSPARENT);
        rect.setStyle("    -fx-stroke: black;\n" +
                "    -fx-stroke-width: 3;\n" +
                "    -fx-stroke-dash-array: 12 2 4 2;\n" +
                "    -fx-stroke-dash-offset: 6;\n" +
                "    -fx-stroke-line-cap: butt;");
//        Label name = createLabel(label);
        nodesList = Arrays.asList(new Node[] { rect });
        getChildren().addAll(nodesList);
        setManaged(false);
        setOnMouseEntered(e -> {
            rect.setFill(Color.YELLOW);
        });
        setOnMouseExited(e -> {
            rect.setFill(Color.TRANSPARENT);
        });
    }

    private Label createLabel (String label) {
        Label name = new Label(label);
        double center = Math.floor(label.length() / 2);
        double characterLength = width / 5 / 11;
        int offset = 6 - (int) center - 1;
        System.out.println(offset);
        name.setLayoutX(offset * characterLength);
        name.setLayoutY(width * LABEL_PROPORTIONS_Y / 5);
        name.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        return name;
    }

    public String getType () {
        return type;
    }

    public void setCorrect (ImageView image, String type) {
        getChildren().add(image);
        image.setLayoutX(rectSize / 2 + ViewUtil.getCenterOffsetX(image));
        image.setLayoutY(rectSize / 2 + ViewUtil.getCenterOffsetY(image));
        setUpEditorOpener(type);
        image.setOnMouseClicked(e -> {
            image.setEffect(new Glow(1));
            scene.setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
                    getChildren().remove(image);
                    setVisible();
                }
            });
            if (e.getClickCount()==2) {
                setUpEditorOpener(type);
                image.setEffect(null);
            }
        });
        setInvisible();
    }

    private void setUpEditorOpener (String type) {
        try {
            Class<?> className = Class.forName("gae.editorView." + type + "EditorOpener");
            Object instance = className.getConstructor().newInstance();
            Method setUpList = className.getMethod("initialize");
            setUpList.invoke(instance);
        }
        catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {

        }
    }

    private void setInvisible () {
        nodesList.forEach(node -> node.setVisible(false));
    }

    private void setVisible () {
        nodesList.forEach(node -> node.setVisible(true));
    }

    @Override
    public boolean checkBounds (double x, double y) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public PointSimple convertCoordinates (double x, double y) {
        // TODO Auto-generated method stub
        return null;
    }
}
