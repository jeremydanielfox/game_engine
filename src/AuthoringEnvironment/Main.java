package AuthoringEnvironment;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 * Main class that is used to start the program; creates the first workspace
 * @author OWNER
 *
 */

public class Main extends Application {

    @Override
    public void start (Stage s) throws Exception {
        WorkspaceManager wm = new WorkspaceManager();
        wm.createWorkspace(s);
    }

    public static void main (String[] args) {
        launch(args);
    }

}
