package gae.openingView;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class OpeningView {

    // first view to appear when you are running the authoring environment. will allow user to set
    // basic info of the game such as the title or type

    private static final String OPENINGVIEW_CSS = "css/OpeningViewCSS.css";
    
    private BorderPane myPane;
    private Scene myScene;
    private ImagePanel imagePanel;
    private DataForm dataForm;

    public OpeningView (Stage myStage) {
        dataForm = new DataForm(myStage); //edit!
        
        initialize();
    }
    
    public Scene getScene() {
        return myScene;
    }

    private void initialize () {
        myPane = new BorderPane();
        myScene = new Scene(myPane);
        myScene.getStylesheets().add(OPENINGVIEW_CSS);
        imagePanel = new ImagePanel();
        //dataForm = new DataForm();
        myPane.setRight(imagePanel.getPanel());
        myPane.setLeft(dataForm.getForm());
    }
}
