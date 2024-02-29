package jdbc;

public class Profession {
    private String professionId;
    private String professionName;
    private int professionHP;
    private int professionATK;
    private int professionActionPoint;
    private int professionWeightbearing;
    private double professionPrice;
    private String professionCharacteristics;

    public Profession(String professionId, String professionName, int professionHP, int professionATK,
            int professionActionPoint, int professionWeightbearing, double professionPrice,
            String professionCharacteristics) {
        this.professionId = professionId;
        this.professionName = professionName;
        this.professionHP = professionHP;
        this.professionATK = professionATK;
        this.professionActionPoint = professionActionPoint;
        this.professionWeightbearing = professionWeightbearing;
        this.professionPrice = professionPrice;
        this.professionCharacteristics = professionCharacteristics;
    }

    public Profession() {
    }

    public String getProfessionId() {
        return professionId;
    }

    public void setProfessionId(String professionId) {
        this.professionId = professionId;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public int getProfessionHP() {
        return professionHP;
    }

    public void setProfessionHP(int professionHP) {
        this.professionHP = professionHP;
    }

    public int getProfessionATK() {
        return professionATK;
    }

    public void setProfessionATK(int professionATK) {
        this.professionATK = professionATK;
    }

    public int getProfessionActionPoint() {
        return professionActionPoint;
    }

    public void setProfessionActionPoint(int professionActionPoint) {
        this.professionActionPoint = professionActionPoint;
    }

    public int getProfessionWeightbearing() {
        return professionWeightbearing;
    }

    public void setProfessionWeightbearing(int professionWeightbearing) {
        this.professionWeightbearing = professionWeightbearing;
    }

    public double getProfessionPrice() {
        return professionPrice;
    }

    public void setProfessionPrice(double professionPrice) {
        this.professionPrice = professionPrice;
    }

    public String getProfessionCharacteristics() {
        return professionCharacteristics;
    }

    public void setProfessionCharacteristics(String professionCharacteristics) {
        this.professionCharacteristics = professionCharacteristics;
    }

    @Override
    public String toString() {
        return "Profession [professionId=" + professionId + ", professionName=" + professionName + ", professionHP="
                + professionHP + ", professionATK=" + professionATK + ", professionActionPoint=" + professionActionPoint
                + ", professionWeightbearing=" + professionWeightbearing + ", professionPrice=" + professionPrice
                + ", professionCharacteristics=" + professionCharacteristics + "]";
    }
}
