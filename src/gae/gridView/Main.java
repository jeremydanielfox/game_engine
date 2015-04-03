package gae.gridView;

import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application{
    
    public static final Screen SCREEN = Screen.getPrimary();
    public static final double SCREEN_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
    public static final double SCREEN_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    
    public static void main (String [] args) {
        launch(args);
    }

    @Override
    public void start (Stage arg0) throws Exception {
        Stage myStage = new Stage();
        myStage.setWidth(SCREEN_WIDTH);
        myStage.setHeight(SCREEN_HEIGHT);
        TempScene scene = new TempScene();
        myStage.setScene(scene.getScene());
        myStage.show();
    }
}