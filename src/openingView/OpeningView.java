package openingView;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class OpeningView {

    // first view to appear when you are running the authoring environment. will allow user to set
    // basic info of the game such as the title or type
    
    public static final Screen SCREEN = Screen.getPrimary();

    private static final String CSS_FILE = "css/OpeningViewCSS.css";
    private static final double SCREEN_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
    private static final double SCREEN_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    
    private Stage myStage;
    private BorderPane myPane;
    private Scene myScene;
    private ImagePanel imagePanel;
    private DataForm dataForm;

    public OpeningView () {
        initialize();
    }

    public void run () {
        myStage.show();
    }

    private void initialize () {
        myStage = new Stage();
        myStage.setWidth(SCREEN_WIDTH);
        myStage.setHeight(SCREEN_HEIGHT);
        myPane = new BorderPane();
        myScene = new Scene(myPane);
        myScene.getStylesheets().add(CSS_FILE);
        imagePanel = new ImagePanel();
        dataForm = new DataForm();
        myPane.setRight(imagePanel.getPanel());
        myPane.setLeft(dataForm.getForm());
        myStage.setScene(myScene);
    }
}
