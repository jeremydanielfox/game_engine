package engine.shop;

import engine.fieldsetting.Settable;


@Settable
public class NameTag implements Tag {
    private String name;

    public NameTag () {
        name = "";
    }

    @Override
    public String getName () {
        return name;
    }

    @Settable
    public void setName (String name) {
        this.name = name;
    }
}
