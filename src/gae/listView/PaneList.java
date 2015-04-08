package gae.listView;

import javafx.collections.ObservableList;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;


public abstract class PaneList {

    protected TitledPane getTitledPane (String text) {
        TitledPane pane = new TitledPane();
        pane.setText(text);
        pane.setTextFill(Color.RED);
        return pane;
    }

    protected ObservableList<TitledPane> setAccordion (TitledPane pane) {
        Accordion accordion = new Accordion();
        pane.setContent(accordion);
        return accordion.getPanes();
    }

    public abstract TitledPane setUpList ();

    public abstract void addToGenericList (EditableNode node);

    public abstract String getType ();
}
