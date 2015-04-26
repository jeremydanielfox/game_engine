package gae.editorView;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import gae.editor.EditingParser;
import gae.editor.ObjectComponentEditor;

/**
 * 
 * @author Eric Saba
 *
 *A view box that lets the user choose which of the concrete classes that implement the chosen interface to use for the 
 *concrete object.
 */
public class EditorIntermediateView {
    public EditorIntermediateView(ArrayList<String> concreteClasses, ObjectComponentEditor componentEditor, String title) {
        Stage editorStage = new Stage();
        VBox vbox = new VBox();
        vbox.setPrefSize(250, 80);
        Scene editorScene = new Scene(vbox);
        editorStage.setScene(editorScene);
        
        Label titleLabel = new Label(String.format("Choose form of %s", componentEditor.getInterfaceClass().getSimpleName()));
        
        ObservableList<String> observable = FXCollections.observableArrayList();
        observable.addAll(concreteClasses);
        ComboBox<String> comboBox = new ComboBox<String>(observable);
        
        Button addButton = new Button("Choose");
        addButton.setOnAction(e -> {
            String className = comboBox.getSelectionModel().getSelectedItem();
            if (className != null) {
                componentEditor.setObject(EditingParser.getInstanceFromName(className));
                componentEditor.popNewEditor(title);
                editorStage.close();
            }
            //TODO: show an error
        });
        
        vbox.getChildren().addAll(titleLabel, comboBox, addButton);
        
        editorStage.show();
    }
}
