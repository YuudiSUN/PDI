package jdbc;

public class Monster {
    private String monsterId;
    private String treasureId;

    public Monster(String monsterId, String treasureId) {
        this.monsterId = monsterId;
        this.treasureId = treasureId;
    }

    public Monster() {
    }

    public String getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(String monsterId) {
        this.monsterId = monsterId;
    }

    public String getTreasureId() {
        return treasureId;
    }

    public void setTreasureId(String treasureId) {
        this.treasureId = treasureId;
    }

    @Override
    public String toString() {
        return "Monster [monsterId=" + monsterId + ", treasureId=" + treasureId + "]";
    }
}
