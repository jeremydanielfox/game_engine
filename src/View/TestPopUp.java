package View;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestPopUp extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        PopUpScreen popup=new PopUpScreen();
        popup.makeScreen("Hello dear friend i am some text dont mind me", "buttonz");
    }

    public static void main(String[] args) {
        launch(args);
}
    
}
