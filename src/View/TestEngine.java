package View;

import engine.game.Game;
import gae.gameView.Main;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import xml.DataManager;


public class TestEngine extends Application {

    private static final String FILE_SOURCE = "src/xml/GameFreePathZombie.xml";

    @Override
    public void start (Stage primaryStage) throws Exception {
        Group root = new Group();
        primaryStage.setHeight(600);
        primaryStage.setWidth(950);
        // primaryStage.setHeight(Main.SCREEN_HEIGHT); // needs to account for scaling; add
        // constants
        // primaryStage.setWidth(Main.SCREEN_WIDTH);// needs to account for scaling; add constants

        Scene scene = new Scene(root);
        Game game = loadGame();
        EngineView view = new ViewConcrete2(game, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        // pane.setCenter(view.initializeView());

        // view.addButton(addWavesButtonTest(event,myPlayer), 0, 0);

        root.getChildren().add(view.initializeView());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Game loadGame () {
        // GameWriter gw = new GameWriter();
        // GameWorld world = gw.makeWorld();
        // Player player = gw.makePlayer();
        // return gw.makeGame(player, world, gw.makeShop(player, world));
        // DataManager.addBtn();
        return DataManager.readFromXML(Game.class, FILE_SOURCE);

    }

    public static void main (String[] args) {
        launch(args);
    }

}
