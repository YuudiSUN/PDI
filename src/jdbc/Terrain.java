package jdbc;

public class Terrain {
    private String terrainId;
    private int terrainTraveSpeed;
    private double terrainCombatEffectiveness;
    private int terrainAnimalQuantity;
    private int terrainPrizeQuantity;
    private String animalId;

    public Terrain(String terrainId, int terrainTraveSpeed, double terrainCombatEffectiveness, int terrainAnimalQuantity,
            int terrainPrizeQuantity, String animalId) {
        this.terrainId = terrainId;
        this.terrainTraveSpeed = terrainTraveSpeed;
        this.terrainCombatEffectiveness = terrainCombatEffectiveness;
        this.terrainAnimalQuantity = terrainAnimalQuantity;
        this.terrainPrizeQuantity = terrainPrizeQuantity;
        this.animalId = animalId;
    }

    public Terrain() {
    }

    public String getTerrainId() {
        return terrainId;
    }

    public void setTerrainId(String terrainId) {
        this.terrainId = terrainId;
    }

    public int getTerrainTraveSpeed() {
        return terrainTraveSpeed;
    }

    public void setTerrainTraveSpeed(int terrainTraveSpeed) {
        this.terrainTraveSpeed = terrainTraveSpeed;
    }

    public double getTerrainCombatEffectiveness() {
        return terrainCombatEffectiveness;
    }

    public void setTerrainCombatEffectiveness(double terrainCombatEffectiveness) {
        this.terrainCombatEffectiveness = terrainCombatEffectiveness;
    }

    public int getTerrainAnimalQuantity() {
        return terrainAnimalQuantity;
    }

    public void setTerrainAnimalQuantity(int terrainAnimalQuantity) {
        this.terrainAnimalQuantity = terrainAnimalQuantity;
    }

    public int getTerrainPrizeQuantity() {
        return terrainPrizeQuantity;
    }

    public void setTerrainPrizeQuantity(int terrainPrizeQuantity) {
        this.terrainPrizeQuantity = terrainPrizeQuantity;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    @Override
    public String toString() {
        return "Terrain [terrainId=" + terrainId + ", terrainTraveSpeed=" + terrainTraveSpeed + ", terrainCombatEffectiveness="
                + terrainCombatEffectiveness + ", terrainAnimalQuantity=" + terrainAnimalQuantity + ", terrainPrizeQuantity="
                + terrainPrizeQuantity + ", animalId=" + animalId + "]";
    }
}
