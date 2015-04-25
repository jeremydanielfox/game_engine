package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import engine.fieldsetting.Settable;


@Settable
public class MainMenuScreen {

    private String myText;
    private String myBackgroundImagePath;
    private ButtonWrapper myButton;
    private BorderPane myGroup;
    private VBox myVBox;

    public MainMenuScreen () {
        initialize("", "", "");
    }

    public MainMenuScreen (String text, String imagePath, String buttonText) {
        initialize(text, imagePath, buttonText);
    }

    private void initialize (String text, String imagePath, String buttonText) {
        myText = text;
        myBackgroundImagePath = imagePath;
        myButton = new ButtonWrapper();
        myButton.setLabel(buttonText);
        
        myVBox=new VBox();
        myVBox.setAlignment(Pos.CENTER);
        //myGroup.setCenter(myVBox);
        Text thisText=new Text(myText);
        myVBox.getChildren().addAll(thisText,myButton.getButton());
        myGroup=new BorderPane();
        //myGroup.set
        myGroup.setPadding(new Insets(200,200,200,200));
        myGroup.setCenter(myVBox);
        
        //myGroup.setCenter(myVBox);
        //myGroup.getChildren().add(myVBox);
    }

    @Settable
    public void setScreenText (String text) {
        myText = text;
    }

    @Settable
    public void setBackgroundImage (String imagePath) {
        myBackgroundImagePath = imagePath;
    }

    @Settable
    public void setButtonText (String text) {
        myButton.setLabel(text);
    }

    public Button getButton() {
        return myButton.getButton();
    }
    
    public Scene makeMenu() {
        Scene scene=new Scene(myGroup);
        
        return scene;
    }
}
