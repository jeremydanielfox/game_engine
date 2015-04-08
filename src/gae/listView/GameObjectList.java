package gae.listView;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.Editable;
import gae.backend.TempTower;


public class GameObjectList {
    /*
     * temporary list of EditableNodes for the generic game object types
     */
    private List<EditableNode> TowerList = new ArrayList<>();
    private List<EditableNode> EnemyList = new ArrayList<>();
    private List<EditableNode> WeaponList = new ArrayList<>();

    public GameObjectList () {
        TowerList.add(new EditableNode(new TempTower()));
    }

    public void addEditableToList (Editable editable) {
        /*
         * Need getType to determine whether it's a tower, enemy, etc.
         * Need getName to know what the name is so it can be displayed (FireTower)
         */
        String type = editable.getType();
        /*
         * temporary method to add to specific list
         * can do reflection and add to specific GameObjectList classes
         * might have to rename GameObjectList to something else cause that should be a super class
         * name
         */
        switch (type) {
            case "Tower":
                TowerList.add(new EditableNode(editable));
                break;
            case "Enemy":
                EnemyList.add(new EditableNode(editable));
                break;
            case "Weapon":
                WeaponList.add(new EditableNode(editable));
                break;
            default:
                break;
        }
    }

    public List<EditableNode> getListTemp () {
        return TowerList;
    }
}
