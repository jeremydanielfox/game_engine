package engine.gameobject.test;

public class CloneTest {

    public static void main(String[] args){
        TestTower hello = new TestTower(4, 270, 270);
        TestTower hello2 = (TestTower) hello.clone();
        System.out.println(hello2 == null);
    }
}
