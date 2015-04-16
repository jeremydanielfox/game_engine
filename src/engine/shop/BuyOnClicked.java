package engine.shop;

public class BuyOnClicked implements Command {
    private ShopModel myShopModel;
    private String name;
    
    public BuyOnClicked(ShopModel shopModel, String name) {
        myShopModel = shopModel;
        this.name = name;
    }
    
    
    @Override
    public void execute () {
        myShopModel.purchaseUpgrade(name);
    }

}
