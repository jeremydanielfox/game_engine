package engine.shop.tag;

import engine.fieldsetting.Settable;


@Settable
public class TagSimple implements Tag {
    private String name;

    public TagSimple () {
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
