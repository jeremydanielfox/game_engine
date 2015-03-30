package AuthoringEnvironment;

import javafx.stage.Stage;

public class Controller {
	Stage s;
	public Controller(){
	}
	public void init(Stage s) {
		this.s = s;
		LibraryData data = new LibraryData();
		LibraryView view = new LibraryView(s);
		view.setObservableList(data.getObservableList());
	}
}
