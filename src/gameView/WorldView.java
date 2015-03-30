package gameView;

import gameManager.TileData;

import java.util.Collection;
import java.util.List;

public interface WorldView {

    public List<TileView> createTileViews(Collection<TileData> c);
    
}
