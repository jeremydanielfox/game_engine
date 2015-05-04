// This entire file is part of my masterpiece.
// JEREMY FOX
package engine.shop;

import javafx.beans.property.DoubleProperty;
import engine.fieldsetting.Settable;
import engine.gameobject.graphics.Graphic;
import engine.gameobject.weapon.Weapon;


public class ShopComponentConcrete implements ShopComponent {
    private ShopTag myTag;
    private Weapon myWeapon;
    private DoubleProperty rangeProperty;

    public ShopComponentConcrete (Weapon wep) {
        myWeapon = wep;
        rangeProperty = wep.getRangeProperty();
        myTag = new ShopTagSimple();
    }

    @Override
    public String getName () {
        return myTag.getName();
    }

    @Override
    public String getDescription () {
        return myTag.getDescription();
    }

    @Override
    public Graphic getShopGraphic () {
        return myTag.getShopGraphic();
    }

    @Override
    public double getValue () {
        return myWeapon.getValue();
    }

    @Override
    public RangeDisplay getRangeDisplay () {
        return new RangeDisplay(getName(), getShopGraphic(), rangeProperty);
    }

    @Settable
    @Override
    public void setShopTag (ShopTag shopTag) {
        myTag = shopTag;
    }

}
