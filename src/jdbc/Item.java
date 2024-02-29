package jdbc;

public class Item {
    private String itemId;
    private String itemName;
    private double itemPrice;
    private double itemWeight;
    private int itemUsesCount;
    private String itemDescription;

    public Item(String itemId, String itemName, double itemPrice, double itemWeight, int itemUsesCount, String itemDescription) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemWeight = itemWeight;
        this.itemUsesCount = itemUsesCount;
        this.itemDescription = itemDescription;
    }

    public Item() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(double itemWeight) {
        this.itemWeight = itemWeight;
    }

    public int getItemUsesCount() {
        return itemUsesCount;
    }

    public void setItemUsesCount(int itemUsesCount) {
        this.itemUsesCount = itemUsesCount;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Override
    public String toString() {
        return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemWeight=" + itemWeight
                + ", itemUsesCount=" + itemUsesCount + ", itemDescription=" + itemDescription + "]";
    }
}
