package gae.levelPreferences;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import gae.gameView.CheckListItem;


public class TextCheckListItem implements CheckListItem {

    private String myText;
    private CheckBox myCheckBox;

    public TextCheckListItem (String string) {
        myText = string;
        myCheckBox = new CheckBox();
    }

    @Override
    public Node getNode () {
        Label label = new Label(myText);
        HBox hbox = new HBox(myCheckBox,label);
        return hbox;
    }

    @Override
    public CheckBox getCheckBox () {
        return myCheckBox;
    }

}
