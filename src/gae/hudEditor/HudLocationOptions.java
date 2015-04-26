package gae.hudEditor;

import gae.openingView.UIObject;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The individual fields that can be included in a hud location
 * @author JohnGilhuly
 *
 */

public class HudLocationOptions implements UIObject {

    private VBox baseNode;
    private EdittableHudLocation myLocation;
    private List<CheckBox> fields;
    private Stage myStage;

    public HudLocationOptions (Stage s, EdittableHudLocation myLocation, List<String> startingFields) {
        this.myLocation = myLocation;
        myStage = s;
        initialize(startingFields);
    }

    private void initialize (List<String> startingFields) {
        baseNode = new VBox();

        // TODO: make these pull the available options from the engine (like other editor)

        fields = new ArrayList<CheckBox>();

        makeCheckBox("Score", startingFields);
        makeCheckBox("Wave", startingFields);
        makeCheckBox("Time", startingFields);
        makeCheckBox("Store Icon", startingFields);

        Button okButton = new Button("Ok");
        okButton.setOnAction(e -> setFields());

        baseNode.setSpacing(5);
        baseNode.getChildren().add(okButton);

    }

    private void makeCheckBox (String name, List<String> startingFields) {
        CheckBox cBox = new CheckBox(name);
        fields.add(cBox);

        if (!startingFields.contains(name)) {
            cBox.setSelected(false);
        }
        else {
            cBox.setSelected(true);
        }

        baseNode.getChildren().add(cBox);
    }

    private void setFields () {
        List<String> onFields = new ArrayList<String>();

        for (CheckBox cb : fields) {
            if (cb.isSelected()) {
                onFields.add(cb.getText());
                System.out.println(cb.getText());
            }
        }

        myLocation.setFields(onFields);

        myStage.close();
    }

    @Override
    public Node getObject () {
        return baseNode;
    }
}
