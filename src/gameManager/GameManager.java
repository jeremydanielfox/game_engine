package gameManager;

import engine.gameobject.Editable;
import javafx.collections.ObservableList;
import game.Game;
import game.Level;

public interface GameManager {
    public void export();
    public void importFile();
    public void setEditableInGame(Editable e);
    public void addLevelToGame(Level l);
    public void getGameFromPreferences(Game g);
    public void createLevel();
    public WorldData getWorldData();
    public ObservableList<Editable> getLibraryDataList();  
}
