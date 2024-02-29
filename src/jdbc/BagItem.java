package jdbc;

public class BagItem {
    private String bagItemId;
    private int bagItemRemainingCount;
    private String itemId;
    private String playerId;

    public BagItem(String bagItemId, int bagItemRemainingCount, String itemId, String playerId) {
        this.bagItemId = bagItemId;
        this.bagItemRemainingCount = bagItemRemainingCount;
        this.itemId = itemId;
        this.playerId = playerId;
    }

    public BagItem() {
    }

    public String getBagItemId() {
        return bagItemId;
    }

    public void setBagItemId(String bagItemId) {
        this.bagItemId = bagItemId;
    }

    public int getBagItemRemainingCount() {
        return bagItemRemainingCount;
    }

    public void setBagItemRemainingCount(int bagItemRemainingCount) {
        this.bagItemRemainingCount = bagItemRemainingCount;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return "BagItem [bagItemId=" + bagItemId + ", bagItemRemainingCount=" + bagItemRemainingCount + ", itemId=" + itemId
                + ", playerId=" + playerId + "]";
    }
}
