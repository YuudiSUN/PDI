package jdbc;

public class Prize {
    private String prizeId;
    private String prizeReward;

    public Prize(String prizeId, String prizeReward) {
        this.prizeId = prizeId;
        this.prizeReward = prizeReward;
    }

    public Prize() {
    }

    public String getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(String prizeId) {
        this.prizeId = prizeId;
    }

    public String getPrizeReward() {
        return prizeReward;
    }

    public void setPrizeReward(String prizeReward) {
        this.prizeReward = prizeReward;
    }

    @Override
    public String toString() {
        return "Prize [prizeId=" + prizeId + ", prizeReward=" + prizeReward + "]";
    }
}
