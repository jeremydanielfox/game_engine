package animations;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;


/**
 * Animator holds all possible animations used within the authoring environment (and beyond, if
 * needed) such as a node shaking
 *
 * @author Brandon Choi
 *
 */
public class Animator {

    Timeline animator;

    public Animator () {
        animator = new Timeline();
        animator.setAutoReverse(true);
        animator.setOnFinished(e -> {
            animator.pause();
            animator.getKeyFrames().clear();
        });
    }

    /**
     * shakes whatever node is fed into the method as a parameter
     *
     * TODO: repeat function?
     *
     * @param node
     */
    public void shake (Node node) {
        animator.setCycleCount(10);
        animator.getKeyFrames().addAll(new KeyFrame(Duration.millis(20),
                                                    new KeyValue(node.translateXProperty(), 20,
                                                                 Interpolator.LINEAR)),
                                                                 new KeyFrame(Duration.millis(20), new KeyValue(node
                                                                                                                .translateXProperty(), -20, Interpolator.LINEAR)));
        animator.play();
    }

    /**
     * slowly brings a node into its full size
     *
     * @param node
     */
    public void zoomIn (Node node) {

    }

    /**
     * makes a node blink
     *
     * @param node
     */
    public void blink (Node node) {
        animator.setCycleCount(2);
        animator.getKeyFrames().addAll(new KeyFrame(Duration.millis(50),
                                                    new KeyValue(node.opacityProperty(), 0,
                                                                 Interpolator.EASE_IN)),
                                                                 new KeyFrame(Duration.millis(50), new KeyValue(node
                                                                                                                .opacityProperty(), 1, Interpolator.EASE_IN)));
        animator.play();
    }

}
