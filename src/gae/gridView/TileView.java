package gae.gridView;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class TileView extends Group {
    private TileData data;
    private Rectangle rect;
    private BooleanProperty walkableProperty=new SimpleBooleanProperty();

    public TileView (double size, TileData data) {
        this.data = data;
        walkableProperty.bind(data.getWalkableProperty());
        rect = new Rectangle(size, size, Color.TRANSPARENT);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(1);
        this.getChildren().addAll(rect);
        setUp(rect);
    }

    private void setUp (Rectangle rect) {
        this.setOnMouseClicked(e -> {
            if(e.getClickCount()==2){
                data.changeState();
            }
        });
        walkableProperty.addListener((observable, oldValue, newValue) ->{
             if(!newValue){
                 rect.setFill(Color.web("red", .5));
             }
             else{
                 rect.setFill(Color.TRANSPARENT);
             }
             
        });
    }
    
    public void handleSelected(Node node, boolean walkable){
        if(this.getBoundsInParent().intersects(node.getBoundsInParent())){
            data.setWalkable(walkable);
        }           
    }
}
