package gae.editorView;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import engine.gameobject.GameObjectSimple;
import View.ImageUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import gae.backend.Placeable;
import gae.backend.ResourceBundleUtil;
import gae.backend.TempTower;
import gae.editor.ComponentEditor;
import gae.editor.ObjectComponentEditor;
import gae.editor.SimpleEditor;
import gae.gridView.ContainerWrapper;
import gae.listView.Authorable;
import gae.listView.DraggableUtilities;
import gae.listView.LibraryData;
import gae.listView.ListViewUtilities;
import gae.openingView.UIObject;


public class GameObjectEditorView implements UIObject {
    private Group root;
    private Scene scene;
    private StackPane stack;
    private static final int TAB_HEIGHT = 160;
    private static final int SIDE_WIDTH = 430;
    private static final double LIBRARY_EDITOR_PROPORTIONS = 0.68;
    private static final Class<?> DEFAULT_CLASS = GameObjectSimple.class;
    private double vboxHeight =
            (Screen.getPrimary().getVisualBounds().getHeight() - TAB_HEIGHT) / 2;
    private double vboxWidth =
            (Screen.getPrimary().getVisualBounds().getWidth()) *
                    LIBRARY_EDITOR_PROPORTIONS;
    private GameObjectContainer bottom;
    private VBox top;
    private AnchorPane anchor;
    private SimpleEditor simpleEditor;
    private Placeable editable;
    private Class<?> clazz;

    public GameObjectEditorView (Scene scene, Consumer<Object> consumer) {
        simpleEditor = new SimpleEditor(DEFAULT_CLASS);
        clazz = DEFAULT_CLASS;
        init(scene);
        Button addButton = new Button("Create Game Object");
        addButton.setOnAction(e -> {
            consumer.accept(createObject());
        });
        addButton().accept(addButton);
    }

    public GameObjectEditorView (Scene scene, Class<?> klass) {
        simpleEditor = new SimpleEditor(klass);
        clazz = klass;
        init(scene);
    }

    private void init (Scene scene) {
        root = new Group();
        root.setManaged(false);
        this.scene = scene;

        List<ComponentEditor> simpleList = simpleEditor.getSimpleComponentEditors();
        SimpleEditorView simpleEditorView = new SimpleEditorView(simpleList);
        top = (VBox) simpleEditorView.getObject();
        top.setPrefSize(vboxWidth, vboxHeight);
        // imageLocationMap =
        // ResourceBundleUtil.useResourceBundle("gae/editorView/ObjectPathProperties");
    }

    private BorderPane setUpBorder () {
        BorderPane border = new BorderPane();

        border.setCenter(setUpAnchor());
        border.setRight(setUpAccordion());
        LibraryList library = new LibraryList(scene);
        border.setLeft(library.initialize());
        return border;
    }

    private Node setUpAnchor () {
        bottom = new GameObjectContainer(vboxWidth, vboxHeight, scene);
        bottom.setPrefSize(vboxWidth, vboxHeight);
        bottom.getChildren().add(root);

        ScrollPane topHalf = new ScrollPane();
        topHalf.setContent(top);
        ScrollPane bottomHalf = new ScrollPane();
        bottomHalf.setContent(bottom);

        anchor = new AnchorPane(topHalf, bottomHalf);

        AnchorPane.setTopAnchor(topHalf, 0.0);
        AnchorPane.setTopAnchor(bottomHalf, vboxHeight);
        return anchor;
    }

    private Accordion setUpAccordion () {
        Accordion accordion = new Accordion();
        for (ObjectComponentEditor edit : simpleEditor.getObjectComponentEditors()) {
            GenericObjectList list =
                    new GenericObjectList(edit.getObjectClass(), bottom, bottom, root, edit);
            accordion.getPanes().add(list.getTitledPane());
        }
        return accordion;
    }

    @Override
    public Node getObject () {
        // TODO Auto-generated method stub
        return setUpBorder();
    }

    public Object createObject () {
        return simpleEditor.createObject(clazz);
    }

    public Consumer<Node> addButton () {
        Consumer<Node> consumer = node -> top.getChildren().add(node);
        return consumer;
    }
}
