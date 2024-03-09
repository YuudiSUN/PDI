package status;

public class CharacterStatus {
    private String name;
    private int health;
    private boolean hasWeapon;
    private boolean hasArmor;

    // 构造器
    public CharacterStatus(String name, int health, boolean hasWeapon, boolean hasArmor) {
        this.name = name;
        this.health = health;
        this.hasWeapon = hasWeapon;
        this.hasArmor = hasArmor;
    }

    // Getter 和 Setter 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean hasWeapon() {
        return hasWeapon;
    }

    public void setHasWeapon(boolean hasWeapon) {
        this.hasWeapon = hasWeapon;
    }

    // 为 hasArmor 添加 getter 方法
    public boolean hasArmor() {
        return hasArmor;
    }

    public void setHasArmor(boolean hasArmor) {
        this.hasArmor = hasArmor;
    }

}
