package engine.shop;

public class TransitionOnClicked implements OnClickedCommand {
    private ShopModel myShopModel;
    private String name;

    public TransitionOnClicked (ShopModel shopModel, String name) {
        myShopModel = shopModel;
        this.name = name;
    }

    @Override
    public RangeDisplay execute () {
        return myShopModel.getRangeDisplay(name);
    }

}
