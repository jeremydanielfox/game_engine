package gae.hudEditor;

import gae.openingView.UIObject;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;


public class HudEditor implements UIObject {
    private static final double ANCHOR_OFFSET = 5.0;

    private static final double SCREEN_HEIGHT =
            Screen.getPrimary().getVisualBounds().getHeight() - 150;

    private List<EdittableHudLocation> locations;
    private List<Node> locationNodes;
    private AnchorPane rootNode;
    private ImageView bImage;

    public HudEditor () {
        initialize();
    }

    private void initialize () {
        rootNode = new AnchorPane();
        rootNode.setMinHeight(SCREEN_HEIGHT);
        
        bImage = new ImageView();
        bImage.fitWidthProperty().bind(rootNode.widthProperty());
        bImage.fitHeightProperty().bind(rootNode.heightProperty());
        rootNode.getChildren().add(bImage);

        Button done = new Button("Save HUD");
        done.setOnAction(e -> export());
        rootNode.getChildren().add(done);

        setLocations();
        locationNodes = extractNodesFromEHLList(locations);
        rootNode.getChildren().addAll(locationNodes);

        setAnchorPoints(done);
    }

    private void setLocations () {
        locations = new ArrayList<EdittableHudLocation>();
        locations.add(new EdittableHudLocation(Color.YELLOW));
        locations.add(new EdittableHudLocation(Color.GREEN));
        locations.add(new EdittableHudLocation(Color.RED));
        locations.add(new EdittableHudLocation(Color.BLUE));
    }

    private void setAnchorPoints (Button done) {
        AnchorPane.setBottomAnchor(locationNodes.get(0), ANCHOR_OFFSET);
        AnchorPane.setLeftAnchor(locationNodes.get(0), ANCHOR_OFFSET);

        AnchorPane.setRightAnchor(locationNodes.get(1), ANCHOR_OFFSET);
        AnchorPane.setTopAnchor(locationNodes.get(1), ANCHOR_OFFSET);

        AnchorPane.setTopAnchor(locationNodes.get(2), ANCHOR_OFFSET);
        AnchorPane.setLeftAnchor(locationNodes.get(2), ANCHOR_OFFSET);

        AnchorPane.setRightAnchor(locationNodes.get(3), ANCHOR_OFFSET);
        AnchorPane.setBottomAnchor(locationNodes.get(3), ANCHOR_OFFSET);

        AnchorPane.setBottomAnchor(done, rootNode.getHeight() / 2);
        AnchorPane.setLeftAnchor(done, rootNode.getWidth() / 2 - 50);
    }

    private List<Node> extractNodesFromEHLList (List<EdittableHudLocation> ehlList) {
        List<Node> ret = new ArrayList<Node>();

        for (EdittableHudLocation ehl : ehlList) {
            ret.add(ehl.getObject());
        }

        return ret;
    }

    private void export () {
        System.out.println("HUD Elements:");
        for (EdittableHudLocation ehl : locations) {
            System.out.println("----------");
            for (String s : ehl.getFields()) {
                System.out.println(s);
            }
        }
    }

    @Override
    public Node getObject () {
        return rootNode;
    }

    public void setBackgroundImage (Image backgroundImage) {
        bImage.setImage(backgroundImage);
    }
}
