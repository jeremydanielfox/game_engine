package gae.editor;

import engine.gameobject.GameObjectSimple;

public class main {
    public static void main (String [] args) {
        Editor oe = new ObjectEditor();
        oe.getMethodsTree(GameObjectSimple.class, null);
    }
}
