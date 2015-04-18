package engine.shop;

public class BuyOnClicked implements OnClickedCommand {
    private ShopModel myShopModel;
    private String name;
    
    public BuyOnClicked(ShopModel shopModel, String name) {
        myShopModel = shopModel;
        this.name = name;
    }
    
    
    @Override
    public TransitionGameObject execute () {
        myShopModel.purchaseUpgrade(name);
        return null;
    }

}
