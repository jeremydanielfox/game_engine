package gae.backend;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Main class that is used to start the program; creates the first workspace
 * 
 * @author OWNER
 *
 */

public class Main extends Application {

    @Override
    public void start (Stage s) throws Exception {
        if (s == null)
            s = new Stage();

        try {
            Manager manager = new Manager();
            manager.init(s);
            s.show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        launch(args);
    }

}
