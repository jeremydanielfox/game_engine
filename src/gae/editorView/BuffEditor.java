package gae.editorView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import gae.editor.EditingParser;
import gae.editor.PopUpEditor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class BuffEditor {
    private static final String PROPERTY_FILE_PATH = "engine.fieldsetting.implementing_classes";
    private List<Object> buffList;

    public Node setLists (String title, BiConsumer<Class<?>, Object> biconsumer) {
        buffList = new ArrayList<>();
        VBox vbox = new VBox();
        Label label = new Label(title);
        vbox.getChildren().add(label);
        Map<String, ArrayList<String>> interfaceToClassMap =
                EditingParser.getInterfaceClasses(PROPERTY_FILE_PATH);
        for (String buffNames : interfaceToClassMap.get("engine.gameobject.units.Buff")) {
            Button button =
                    new Button(EditingParser.getUserFriendlyName(EditingParser.getInstanceFromName(buffNames).getClass()
                            .getSimpleName()));
            button.setOnAction(e -> {
                new PopUpEditor(EditingParser.getInstanceFromName(buffNames).getClass(),
                                getConsumer(),
                                biconsumer);
            });
            vbox.getChildren().add(button);
        }
        return vbox;
    }

    private Consumer<Object> getConsumer () {
        return e -> {
            buffList.add(e);
            System.out.println(buffList);
        };
    }
}
