package gae.gameView;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.VBox;

public class GenericObjectsPane {

    private VBox baseNode;

    public GenericObjectsPane() {
        initialize();
    }
    
    private void initialize () {
        baseNode = new VBox();
        baseNode.setStyle("-fx-background-color: #FFFFFF;");
        baseNode.setStyle("-fx-border-color: red;");
        baseNode.setSpacing(10);
        baseNode.setPadding(new Insets(15, 12, 15, 12));

        // Make these classes?
        Label towerLabel = makeNewClickableText("Tower", e -> newCustomObject("Tower"));
        Label enemyLabel = makeNewClickableText("Enemy", e -> newCustomObject("Enemy"));
        Label obstacleLabel = makeNewClickableText("Obstacle", e -> newCustomObject("Obstacle"));
        baseNode.getChildren().addAll(new Label("Custom Object Types"), towerLabel, enemyLabel, obstacleLabel);
    }  

    private Label makeNewClickableText(String title, EventHandler<MouseEvent> action) {
        Label ret = new Label(title);
        ret.setOnMouseClicked(action);
        return ret;
    }
    
    private void newCustomObject (String type) {
        // TODO This opens a new custom object window
        System.out.println(type);
    }

    public Node getBaseNode () {
        return baseNode;
    }

}
