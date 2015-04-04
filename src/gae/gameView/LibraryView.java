package gae.gameView;

import java.util.Arrays;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

// import gameobject.Editable;

public class LibraryView {
    // public void editEditableObject(Editable e);

    private VBox libraryView;
    
    public LibraryView () {
        initialize();
    }

    private void initialize () {
        
    }
    
    private class LibraryContent {
        
        private HBox content;
        private ImageView graphicIcon;
        private String label;
        
        public LibraryContent (ImageView image, String objectName) {
            content = new HBox(15);
            image = graphicIcon;
            label = objectName;
            
            setUpClick();
        }
        
        private void setUpClick() {
            content.setOnMouseClicked(e -> {
                
            });
        }
    }
}
