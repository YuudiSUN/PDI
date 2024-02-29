package jdbc;

public class AnimalType {
    private String animalTypeId;
    private String animalTypeName;
    private int animalTypeHP;
    private int animalTypeATK;

    public AnimalType(String animalTypeId, String animalTypeName, int animalTypeHP, int animalTypeATK) {
        this.animalTypeId = animalTypeId;
        this.animalTypeName = animalTypeName;
        this.animalTypeHP = animalTypeHP;
        this.animalTypeATK = animalTypeATK;
    }

    public AnimalType() {
    }

    public String getAnimalTypeId() {
        return animalTypeId;
    }

    public void setAnimalTypeId(String animalTypeId) {
        this.animalTypeId = animalTypeId;
    }

    public String getAnimalTypeName() {
        return animalTypeName;
    }

    public void setAnimalTypeName(String animalTypeName) {
        this.animalTypeName = animalTypeName;
    }

    public int getAnimalTypeHP() {
        return animalTypeHP;
    }

    public void setAnimalTypeHP(int animalTypeHP) {
        this.animalTypeHP = animalTypeHP;
    }

    public int getAnimalTypeATK() {
        return animalTypeATK;
    }

    public void setAnimalTypeATK(int animalTypeATK) {
        this.animalTypeATK = animalTypeATK;
    }

    @Override
    public String toString() {
        return "AnimalType [animalTypeId=" + animalTypeId + ", animalTypeName=" + animalTypeName + ", animalTypeHP=" + animalTypeHP
                + ", animalTypeATK=" + animalTypeATK + "]";
    }
}
