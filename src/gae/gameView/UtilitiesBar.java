package gae.gameView;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import View.EngineView;
import View.ViewConcrete2;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Screen;
import javafx.stage.Stage;
import xml.DataManager;
import engine.game.Game;
import engine.shop.ShopModel;


public class UtilitiesBar {

    private static final ImageView PLAY_IMAGE = new ImageView("/images/playButton.jpg");
    private static final ImageView IMPORT_IMAGE = new ImageView("/images/importButton.jpg");
    private static final ImageView EXPORT_IMAGE = new ImageView("/images/exportButton.jpg");
    private static final ImageView PAUSE_IMAGE = new ImageView("/images/pauseButton.jpg");
    private static final ImageView HELP_IMAGE = new ImageView("/images/helpButton.jpg");
    private static final ImageView FULLSCREEN_IMAGE = new ImageView("/images/fullscreenButton.jpg");

    private GridPane utilitiesBar;
    private Button playButton;
    private Button pauseButton;
    private Button importButton;
    private Button exportButton;
    private Button helpButton;
    private Button fullscreenButton;
    
    private Game game;

    public UtilitiesBar (Game gameIn) {
        game = gameIn;
        utilitiesBar = new GridPane();
        utilitiesBar.setId("utilitiesBar");
        utilitiesBar.setAlignment(Pos.CENTER);
        setUpButtons();
        setUpFunctions();
        placeButtons();
    }

    public Pane getUtilitiesBar () {
        return utilitiesBar;
    }

    private void setUpButtons () {
        playButton = new Button("", PLAY_IMAGE);
        pauseButton = new Button("", PAUSE_IMAGE);
        importButton = new Button("", IMPORT_IMAGE);
        exportButton = new Button("", EXPORT_IMAGE);
        helpButton = new Button("", HELP_IMAGE);
        fullscreenButton = new Button("", FULLSCREEN_IMAGE);

        Arrays.asList(playButton, pauseButton, importButton, exportButton, helpButton,
                      fullscreenButton).forEach(e -> {

                      });

        Arrays.asList(PLAY_IMAGE, PAUSE_IMAGE, IMPORT_IMAGE, EXPORT_IMAGE, HELP_IMAGE,
                      FULLSCREEN_IMAGE).forEach(e -> {
                          e.setFitWidth(25);
                          e.setFitHeight(25);
                      });
    }

    private void setUpFunctions () {
        playButton.setOnMousePressed(e -> {
            try {
                liveEdit();
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        pauseButton.setOnMousePressed(e -> {

        });

        importButton.setOnMousePressed(e -> {
            chooseFile();
        });

        exportButton.setOnMousePressed(e -> {
            export();
        });

        helpButton.setOnMousePressed(e -> {
            displayWebPage();
        });

        fullscreenButton.setOnMousePressed(e -> {
            maximizeWindow();
        });
    }

    /**
     * add exporting option
     * @throws IOException 
     */
    private void liveEdit () throws IOException {
        Stage primaryStage = new Stage();
        Group root = new Group();
        primaryStage.setHeight(800);
        primaryStage.setWidth(1050);
        Scene scene = new Scene(root);
        EngineView view = new ViewConcrete2(game.cloneGame(), Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        root.getChildren().add(view.initializeView());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void export () {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Game File");
        File chosen = fileChooser.showSaveDialog(utilitiesBar.getScene().getWindow());
        DataManager.writeToXML(game, chosen.getAbsolutePath());
//        ShopModel shop = game.getShop();
//        System.out.println("test");
    }

    private void chooseFile () {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select previous authoring environment file");
        fileChooser.getExtensionFilters().add(new ExtensionFilter(".xml"));
        // File chosen = fileChooser.showOpenDialog();
    }

    private void displayWebPage () {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load(getClass().getResource("/html/helpPage.html").toExternalForm());
        // temporary code to display help
        Stage stage = new Stage();
        stage.setTitle("Help Page");
        VBox helpRoot = new VBox();
        helpRoot.getChildren().add(browser);
        stage.setScene(new Scene(helpRoot, 800, 800));
        stage.show();
    }
    
    private void maximizeWindow () {
        utilitiesBar.getScene().getWindow().setWidth(Screen.getPrimary().getBounds().getWidth());
        utilitiesBar.getScene().getWindow().setHeight(Screen.getPrimary().getBounds().getHeight());
    }

    private void placeButtons () {
        List<Button> buttons = Arrays.asList(fullscreenButton, playButton, pauseButton,
                                             importButton, exportButton, helpButton);
        utilitiesBar.getChildren().addAll(buttons);
        for (int i = 0; i < buttons.size(); i++) {
            GridPane.setRowIndex(buttons.get(i), 0);
            GridPane.setColumnIndex(buttons.get(i), i);
        }
    }
}
