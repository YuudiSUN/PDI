package jdbc;

public class Unit {
    private String unitId;
    private String unitName;
    private int unitHP;
    private int unitATK;
    private String unitLocation;
    private int timeId;

    public Unit(String unitId, String unitName, int unitHP, int unitATK, String unitLocation, int timeId) {
        this.unitId = unitId;
        this.unitName = unitName;
        this.unitHP = unitHP;
        this.unitATK = unitATK;
        this.unitLocation = unitLocation;
        this.timeId = timeId;
    }

    public Unit() {
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getUnitHP() {
        return unitHP;
    }

    public void setUnitHP(int unitHP) {
        this.unitHP = unitHP;
    }

    public int getUnitATK() {
        return unitATK;
    }

    public void setUnitATK(int unitATK) {
        this.unitATK = unitATK;
    }

    public String getUnitLocation() {
        return unitLocation;
    }

    public void setUnitLocation(String unitLocation) {
        this.unitLocation = unitLocation;
    }

    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    @Override
    public String toString() {
        return "Unit [unitId=" + unitId + ", unitName=" + unitName + ", unitHP=" + unitHP + ", unitATK=" + unitATK
                + ", unitLocation=" + unitLocation + ", timeId=" + timeId + "]";
    }
}
