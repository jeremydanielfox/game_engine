package gameobject;

import java.util.Collection;


public interface Tower extends Mortal {
    
    public void addWeapon(Weapon weapon);

    public Collection<Weapon> getWeapons ();
}
