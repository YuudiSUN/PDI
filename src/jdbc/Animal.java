package jdbc;

public class Animal {
    private String animalId;
    private double animalHitRate;
    private String animalTerrain;
    private String animalTypeId;

    public Animal(String animalId, double animalHitRate, String animalTerrain, String animalTypeId) {
        this.animalId = animalId;
        this.animalHitRate = animalHitRate;
        this.animalTerrain = animalTerrain;
        this.animalTypeId = animalTypeId;
    }

    public Animal() {
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public double getAnimalHitRate() {
        return animalHitRate;
    }

    public void setAnimalHitRate(double animalHitRate) {
        this.animalHitRate = animalHitRate;
    }

    public String getAnimalTerrain() {
        return animalTerrain;
    }

    public void setAnimalTerrain(String animalTerrain) {
        this.animalTerrain = animalTerrain;
    }

    public String getAnimalTypeId() {
        return animalTypeId;
    }

    public void setAnimalTypeId(String animalTypeId) {
        this.animalTypeId = animalTypeId;
    }

    @Override
    public String toString() {
        return "Animal [animalId=" + animalId + ", animalHitRate=" + animalHitRate + ", animalTerrain=" + animalTerrain
                + ", animalTypeId=" + animalTypeId + "]";
    }
}
