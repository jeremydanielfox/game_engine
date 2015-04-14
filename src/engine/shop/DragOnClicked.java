package engine.shop;

public class DragOnClicked implements Command {
    private ShopModel myShopModel;
    private String name;
    
    public DragOnClicked(ShopModel shopModel, String name) {
        myShopModel = shopModel;
        this.name = name;
    }
    
    
    @Override
    public void execute () {
        myShopModel.getTransitionGameObject(name);
    }

}
