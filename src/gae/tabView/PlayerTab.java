package gae.tabView;

import engine.game.Player;
import gae.builder.PlayerBuilder;
import javafx.scene.control.Tab;

public class PlayerTab implements ITab {
    private Tab baseNode;
    private PlayerBuilder editor;

    public PlayerTab () {
        initialize();
    }

    private void initialize () {
        baseNode = new Tab();
        baseNode.setText("Player");

        editor = new PlayerBuilder();
        baseNode.setContent(editor.getBuilder());
        baseNode.setClosable(false);
    }

    @Override
    public Tab getBaseTabNode () {
        return baseNode;
    }

    public Player getPlayer () {
        return (Player) editor.getData().getBuiltObject();
    }
}
