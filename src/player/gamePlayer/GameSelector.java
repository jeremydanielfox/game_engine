package player.gamePlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameSelector {
    
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final ImageView rightArrow = new ImageView("/images/right_arrow.jpg");
    private static final ImageView leftArrow = new ImageView("/images/left_arrow.jpg");
    
    private GridPane chooser;
    private ImageView right, left;
    private List<SelectOption> options;
    private Pane currentView;
    private int index;
    

    public GameSelector (Scene s) {
        chooser = new GridPane();
        chooser.setId("gameChooser");
        right = rightArrow;
        left = leftArrow;
        options = new ArrayList<>();
        
        //manually add new SelectOption for testing
        SelectOption s1 = new SelectOption(new ImageView("/images/bloonsdefense.jpg"), "Bloons");
        SelectOption s2 = new SelectOption(new ImageView("/images/desktopdefense.jpg"), "Desktop Tower Defense");
        SelectOption s3 = new SelectOption(new ImageView("/images/cartoonwars.jpg"), "Cartoon Wars");
        options.addAll(Arrays.asList(s1,s2,s3));
        
        index = 0;
        currentView = new Pane(options.get(index).getOption());
        setUpGrid();
        setUpFunctions();
    }
    
    @SuppressWarnings("static-access")
    private void setUpGrid() {
        List<Node> optionNodes = Arrays.asList(left, currentView, right);
        optionNodes.forEach(e -> {
            chooser.setRowIndex(e, 0);
            chooser.setColumnIndex(e, optionNodes.indexOf(e));
        });  
        chooser.getChildren().addAll(left, currentView, right);
    }
    
    private void setUpFunctions () {    
        right.setOnMouseClicked(e -> {
            swipe(RIGHT);
        });    
        left.setOnMouseClicked(e -> {
           swipe(LEFT);
        });
    }
    
    private void swipe(String s){
        if (s.equals(RIGHT) && options.get(index + 1) != null)
            index += 1;
        else if (s.equals(LEFT) && options.get(index - 1) != null)
            index -= 1;

        currentView.getChildren().clear();
        currentView.getChildren().add(options.get(index).getOption());
    }

    public Node getChooser() {
        return chooser;
    }

    
    private class SelectOption {

        private VBox display;
        private ImageView gamePicture;
        private Text gameName;

        public SelectOption (ImageView picture, String name) {
            display = new VBox(10);
            display.setId("selectOption");
            gamePicture = picture;
            gamePicture.setId("gameIcon");
            gameName = new Text(name);
            gameName.setId("gameName");
            createDisplay();
        }

        public Node getOption () {
            return display;
        }

        private void createDisplay () {
            display.getChildren().addAll(gameName, gamePicture);
        }
    }

}
