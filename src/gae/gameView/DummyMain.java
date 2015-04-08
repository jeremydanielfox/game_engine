package gae.gameView;

import gae.backend.LibraryData;
import gae.backend.TempTower;
import gae.openingView.OpeningView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class DummyMain extends Application{
    
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
        BorderPane border=new BorderPane();
        LibraryData data=new LibraryData();
        data.addToList(new TempTower());
        Scene scene=new Scene(border);
        LibraryView view=new LibraryView(scene, data.getMap());
        border.setLeft(view.initialize());
        myStage.setScene(scene);
        myStage.show();
    }
}