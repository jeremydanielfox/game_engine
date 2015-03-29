package gameobject;

public interface GameObject extends Actor, Mover, Health{
    //public void updateGraphics ();//cannot implement yet
    public void addWeapon(Weapon weapon);
}