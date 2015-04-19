package View;

import engine.fieldsetting.Settable;


@Settable
public class MainMenuScreen {

    private String myText;
    private String myBackgroundImagePath;
    private ButtonWrapper myButton;

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

}
