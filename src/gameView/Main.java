package gameView;

import openingView.OpeningView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    
    public static void main (String [] args) {
        launch(args);
    }

    @Override
    public void start (Stage arg0) throws Exception {
        OpeningView opener = new OpeningView();
        opener.run();
    }

}
