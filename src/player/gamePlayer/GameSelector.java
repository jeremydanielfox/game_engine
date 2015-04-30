package player.gamePlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * The middle section of the PlayerOpener with the images of the game and the arrows that allow the
 * user to navigate around the player
 *
 * @author Brandon Choi
 *
 */

public class GameSelector {

    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    private static final ImageView rightArrow = new ImageView("/images/right_arrow.jpg");
    private static final ImageView leftArrow = new ImageView("/images/left_arrow.jpg");

    private Scene myScene;
    private GridPane chooser;
    private ImageView right, left;
    private List<SelectOption> options;
    private Pane currentView;
    private int index;

    public GameSelector (Scene s) {
        myScene = s;
        chooser = new GridPane();
        chooser.setAlignment(Pos.CENTER);
        right = rightArrow;
        left = leftArrow;
        options = new ArrayList<>();

        chooser.setId("gameChooser");
        right.setId("arrow");
        left.setId("arrow");

        /*
         * manually adding select options to test out player for the three games we made
         */
        SelectOption s1 = new SelectOption(new ImageView("/images/custombloons.jpg"), "Custom Bloons", "src/xml/CS308BloonsDemo.xml");
        SelectOption s2 = new SelectOption(new ImageView("/images/pineapplegame.png"), "(Pine)Apples", "src/xml/(pine)apple.xml");
        SelectOption s3 = new SelectOption(new ImageView("/images/rpgmode.jpg"), "Box Head 2.0", "src/SuperAwesomeDemo/SuperAwesomeGame.xml");
        options.addAll(Arrays.asList(s1, s2, s3));

        index = 0;
        currentView = new Pane(options.get(index).getOption());
        setUpGrid();
        setUpFunctions();
    }

    public ImageView getCurrentImage() {
        ImageView img=new ImageView();
        img.setImage(options.get(index).getGamePicture().getImage());
        return img;
    }
    
    public String getSelectedGamePath() {
        return options.get(index).getFilePath();
    }
    
    public Node getChooser () {
        return chooser;
    }

    /**
     * for adding an option to the game selector once the user loads in a new game
     */
    public void addOption () {

    }

    /**
     * sets up the gridpane of the arrows and the select options
     */
    @SuppressWarnings("static-access")
    private void setUpGrid () {
        List<Node> optionNodes = Arrays.asList(left, currentView, right);
        optionNodes.forEach(e -> {
            GridPane.setRowIndex(e, 0);
            GridPane.setColumnIndex(e, optionNodes.indexOf(e));
        });
        chooser.getChildren().addAll(left, currentView, right);
    }

    /**
     * sets up key board functionalities and clickables for the player UI
     */
    private void setUpFunctions () {
        right.setOnMouseClicked(e -> {
            swipe(RIGHT);
        });
        left.setOnMouseClicked(e -> {
            swipe(LEFT);
        });

        myScene.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.RIGHT)) {
                swipe(RIGHT);
            }
            else if (e.getCode().equals(KeyCode.LEFT)) {
                swipe(LEFT);
            }
        });
    }

    /**
     * swipes the images to the next option in the respective direction (left or right). loops
     * around if it reaches an end
     *
     * @param s
     */
    private void swipe (int i) {
        if (index == options.size() - 1 && i == RIGHT) {
            index = 0;
        }
        else if (index == 0 && i == LEFT) {
            index = options.size() - 1;
        }
        else {
            index += i;
        }
        currentView.getChildren().clear();
        currentView.getChildren().add(options.get(index).getOption());
    }

    
    /**
     * Represents one option the user can select from. Comprised of an image of the game and its
     * label.
     *
     * @author Brandon Choi
     *
     */
    private class SelectOption {

        private VBox display;
        private ImageView gamePicture;
        private HBox textBox;
        private Text gameName;
        private String filePath;

        public SelectOption (ImageView picture, String name, String file) {
            display = new VBox(10);
            gamePicture = picture;
            filePath = file;

            /*
             * set width and height to ratio of the screen
             */
            gamePicture.setFitWidth(400);
            gamePicture.setFitHeight(300);

            textBox = new HBox();
            gameName = new Text(name);
            textBox.getChildren().add(gameName);
            textBox.setAlignment(Pos.CENTER);

            display.setId("selectOption");
            gamePicture.setId("gameIcon");
            gameName.setId("gameName");
            textBox.setId("textBox");

            createDisplay();
        }
        
        public String getFilePath() {
            return filePath;
        }

        public Node getOption () {
            return display;
        }

        /**
         * adds all necessary nodes to the display VBox
         */
        private void createDisplay () {
            display.getChildren().addAll(textBox, gamePicture);
        }
        
        public ImageView getGamePicture(){
            return gamePicture;
        }
    }

}
