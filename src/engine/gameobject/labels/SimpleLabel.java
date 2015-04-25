package engine.gameobject.labels;

import engine.fieldsetting.Settable;


public class SimpleLabel implements Label {

    private Label superLabel;
    private String myName;

    public SimpleLabel () {
        superLabel = new LabelBase();
        myName = "";
    }

    public SimpleLabel (String name) {
        myName = name;
        superLabel = new LabelBase();
    }

    public SimpleLabel (String name, Label parent) {
        myName = name;
        superLabel = parent;
    }

    @Override
    @Settable
    public void setName (String name) {
        myName = name;
    }

    @Override
    public String getName () {
        return myName;
    }

    @Override
    @Settable
    public void setSuperLabel (Label parent) {
        superLabel = parent;
    }

    @Override
    public Label getSuperLabel () {
        return superLabel;
    }

    @Override
    public Label clone () {
        return new SimpleLabel(myName, superLabel);
    }

    // TODO: equals method needs work... possibly should go all the way down to baselabel
    @Override
    public boolean equals (Object other) {
        if (other instanceof Label) {
            if (((Label) other).getName().equals(myName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode () {
        return myName.hashCode();
    }
}
