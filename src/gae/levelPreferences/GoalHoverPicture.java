package gae.levelPreferences;

import gae.openingView.HoverPicture;
import gae.openingView.UIObject;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class GoalHoverPicture implements UIObject {
    private HoverPicture hoverPic;
    private HBox base;
    
    public GoalHoverPicture(ImageView image, Text text, Node options){
        hoverPic=new HoverPicture(image, text);
        base=new HBox(hoverPic.getObject(), options);
        options.setVisible(false);
        hoverPic.getObject().setOnMouseClicked(e->{
            hoverPic.changeSelectEffect();
            hoverPic.alter();
            options.setVisible(hoverPic.selected());
        });
    }
    
    public boolean isSelected(){
        return hoverPic.selected();
    }
    
    @Override
    public Node getObject () {
        return base;
    }
    
    
}
