package usecases.usecase_GAE2;

import game.Game;
import game.Level;
import gameobject.Editable;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;

import com.thoughtworks.xstream.XStream;

import usecases.usecase_GAE1.LibraryData;

// In this use case, we are creating a level. Obviously, the first thing that has to happen is to create the level.

public class GameManager {
	LibraryData library = new LibraryData();
	List<LevelManager> LevelManagerList = new ArrayList<>();
	XStream xstream = new XStream();

	public void exportXML() {
		String xml = xstream.toXML(this);
	}

	public void importXML(String path) {
		xstream.fromXML(path);
	}

	public void setEditableInGame(Editable editable) {

	}

	public void addLevelToGame() {
		// When the user selects add new level, either the Author class or
		// another class will call this method.
		LevelManager manager = new LevelManager();
		LevelManagerList.add(manager);
	}

	public void getGameFromPreferences(Game game) {

	}

	public void createLevel() {

	}

	public WorldData getWorldData() {
		return null;
	}

	public ObservableList<Editable> getLibraryDataList() {
		return null;
	}
}
