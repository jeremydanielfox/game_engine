package gae.editorView;

import java.util.Arrays;
import java.util.List;
import View.ViewUtil;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class DragIntoRectangle extends Group {
    private static final double LABEL_PROPORTIONS_Y = 0.4;
    // 11 ALPHABETS ACROSS
    private double width;
    private String type;
    private double rectSize;
    private Rectangle rect;
    private List<Node> nodesList;

    public DragIntoRectangle (double width, String label) {
        this.width = width;
        type = label;
        rectSize = width / 5;
        rect = new Rectangle(rectSize, rectSize, Color.TRANSPARENT);
        rect.setStyle("    -fx-stroke: black;\n" +
                      "    -fx-stroke-width: 3;\n" +
                      "    -fx-stroke-dash-array: 12 2 4 2;\n" +
                      "    -fx-stroke-dash-offset: 6;\n" +
                      "    -fx-stroke-line-cap: butt;");
        Label name = createLabel(label);
        nodesList = Arrays.asList(new Node[] { rect, name });
        this.getChildren().addAll(nodesList);
        this.setManaged(false);
        this.setOnMouseEntered(e -> {
            rect.setFill(Color.YELLOW);
        });
        this.setOnMouseExited(e -> {
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

    public void setCorrect (ImageView image) {
        this.getChildren().add(image);
        image.setLayoutX(rectSize / 2 + ViewUtil.getCenterOffsetX(image));
        image.setLayoutY(rectSize / 2 + ViewUtil.getCenterOffsetY(image));
        setInvisible();
    }

    private void setInvisible () {
        nodesList.forEach(node -> node.setVisible(false));
    }
}
