package View;

import engine.game.Game;
import gae.gameView.Main;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import xml.DataManager;


public class TestEngine extends Application {
    private static final String FILE_SOURCE = "src/View/Game.xml";

    @Override
    public void start (Stage primaryStage) throws Exception {
        Group root = new Group();
        primaryStage.setHeight(Main.SCREEN_HEIGHT); // needs to account for scaling; add constants
        primaryStage.setWidth(Main.SCREEN_WIDTH);// needs to account for scaling; add constants
        // BorderPane pane = new BorderPane();
        // GameWorld world = makeWorld();
        // StoryBoard story = makeStoryBoard(world);

        Scene scene = new Scene(root);
        // Player myPlayer = makePlayer();
        // ConcreteLevelBoard board = makeLevelBoard(world, story, myPlayer);
        Game game = loadGame();
        System.out.println("Read");
        View view = new ViewConcrete2(game, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        // pane.setCenter(view.initializeView());

        // view.addButton(addWavesButtonTest(event,myPlayer), 0, 0);

        root.getChildren().add(view.initializeView());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Game loadGame () {
        return DataManager.readFromXML(Game.class, FILE_SOURCE);

    }

}
