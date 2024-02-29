package jdbc;

public class Treasure {
    private String treasureId;
    private String treasureState;

    public Treasure(String treasureId, String treasureState) {
        this.treasureId = treasureId;
        this.treasureState = treasureState;
    }

    public Treasure() {
    }

    public String getTreasureId() {
        return treasureId;
    }

    public void setTreasureId(String treasureId) {
        this.treasureId = treasureId;
    }

    public String getTreasureState() {
        return treasureState;
    }

    public void setTreasureState(String treasureState) {
        this.treasureState = treasureState;
    }

    @Override
    public String toString() {
        return "Treasure [treasureId=" + treasureId + ", treasureState=" + treasureState + "]";
    }
}
