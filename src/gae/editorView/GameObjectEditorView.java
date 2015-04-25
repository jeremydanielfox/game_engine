package gae.editorView;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import engine.gameobject.GameObjectSimple;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import gae.editor.ComponentEditor;
import gae.editor.ObjectComponentEditor;
import gae.editor.SimpleEditor;
import gae.openingView.UIObject;


public class GameObjectEditorView implements UIObject {
    private Group root;
    private Scene scene;
    private static final int TAB_HEIGHT = 160;
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
    private Class<?> clazz;

    public GameObjectEditorView (Scene scene, Consumer<Object> consumer, BiConsumer<Class<?>, Object> biConsumer) {
        simpleEditor = new SimpleEditor(DEFAULT_CLASS, biConsumer);
        clazz = DEFAULT_CLASS;
        init(scene, consumer, biConsumer);
    }

    public GameObjectEditorView (Scene scene, Consumer<Object> consumer, BiConsumer<Class<?>, Object> biConsumer, Class<?> klass) {
        simpleEditor = new SimpleEditor(klass, biConsumer);
        clazz = klass;
        init(scene, consumer, biConsumer);
    }

    private void init (Scene scene, Consumer<Object> consumer, BiConsumer<Class<?>, Object> biConsumer) {
        root = new Group();
        root.setManaged(false);
        this.scene = scene;

        List<ComponentEditor> simpleList = simpleEditor.getSimpleComponentEditors();
        SimpleEditorView simpleEditorView = new SimpleEditorView(simpleList);
        top = (VBox) simpleEditorView.getObject();
        top.setPrefSize(vboxWidth, vboxHeight);
        // imageLocationMap =
        // ResourceBundleUtil.useResourceBundle("gae/editorView/ObjectPathProperties");
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            Object obj = createObject();
            consumer.accept(obj);
            biConsumer.accept(clazz, obj);
        });
        addButton().accept(addButton);
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
                    new GenericObjectList(edit, bottom, bottom, root);
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
