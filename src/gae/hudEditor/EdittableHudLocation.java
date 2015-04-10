package gae.hudEditor;

import gae.openingView.UIObject;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EdittableHudLocation implements UIObject {
    private static final double RECTANGLE_SIZE = 200;
    private StackPane baseNode;
    private VBox textHolder;
    private List<String> myFields;
    
    public EdittableHudLocation (Color color) {
        initialize(color);
    }

    private void initialize (Color color) {
        myFields = new ArrayList<String>();
        baseNode = new StackPane();
        textHolder = new VBox();
        
//        ResizableRectangle r = new ResizableRectangle();
        Rectangle r = new Rectangle();
        
        r.setWidth(RECTANGLE_SIZE);
        r.setHeight(RECTANGLE_SIZE);
        r.setFill(color);
        r.setOnMousePressed(e -> showOptions(e));
        
        textHolder.setOnMousePressed(e -> showOptions(e));
        
        baseNode.getChildren().addAll(r, textHolder);
    }

    private void showOptions (MouseEvent e) {
        if (e.getClickCount() == 2) {
            Stage optionsStage = new Stage();
            HudLocationOptions options = new HudLocationOptions(optionsStage, this, myFields);
            Scene optionsScene = new Scene(new Pane(options.getObject()));
            optionsStage.setScene(optionsScene);
            optionsStage.show();
        }
    }

    @Override
    public Node getObject () {
        return baseNode;
    }
    
    public List<String> getFields() {
        return myFields;
    }

    public void setFields (List<String> onFields) {
        textHolder.getChildren().clear();
        
        for (String s : onFields) {
            myFields.add(s);
            Text newText = new Text(s);
            newText.setFont(new Font(12));
            textHolder.getChildren().add(newText);
        }
    }
}