package GAE.changingfields;

import javafx.stage.Stage;

/**
 * A class used to represent one workspace, a sub-program with unique configurations, variables, and commands
 * @author OWNER
 *
 */
public class WorkspaceManager {

    public WorkspaceManager () {
    }

    public void createWorkspace (Stage s) {
        if (s == null)
            s = new Stage();

        try {
            Controller master = new Controller();
            master.init(s);
            s.show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
