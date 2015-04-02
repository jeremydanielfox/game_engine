package gameManager;

import javafx.collections.ObservableList;
import com.sun.glass.ui.Window.Level;
import engine.game.Game;
import engine.gameobject.Editable;


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
