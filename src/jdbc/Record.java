package jdbc;

public class Record {
    private String timeId;
    private String treasureId;
    private String timeDiscovery;

    public Record(String timeId, String treasureId, String timeDiscovery) {
        this.timeId = timeId;
        this.treasureId = treasureId;
        this.timeDiscovery = timeDiscovery;
    }

    public Record() {
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public String getTreasureId() {
        return treasureId;
    }

    public void setTreasureId(String treasureId) {
        this.treasureId = treasureId;
    }

    public String getTimeDiscovery() {
        return timeDiscovery;
    }

    public void setTimeDiscovery(String timeDiscovery) {
        this.timeDiscovery = timeDiscovery;
    }

    @Override
    public String toString() {
        return "Record [timeId=" + timeId + ", treasureId=" + treasureId + ", timeDiscovery=" + timeDiscovery + "]";
    }
}
