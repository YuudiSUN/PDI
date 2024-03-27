package status;

import java.awt.Point;
import utils.entities.GameEntity;

public class CharacterStatus extends GameEntity {
    private String name;
    private int health;
    private boolean hasWeapon;
    private boolean hasArmor;

    // 构造函数
    public CharacterStatus(String name, int health, boolean hasWeapon, boolean hasArmor, Point position) {
        super(position, "C:/Users/yingb/Desktop/CY-L3II/InterProjet-Explorateur/PDI_Github/PDI/src/image/character.png"); // 假设所有角色使用同一张图片
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
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
    public void reduceHealth(int amount) {
    	this.health -= amount;
    }

    public boolean hasWeapon() {
        return hasWeapon;
    }

    public void setHasWeapon(boolean hasWeapon) {
        this.hasWeapon = hasWeapon;
    }

    public boolean hasArmor() {
        return hasArmor;
    }

    public void setHasArmor(boolean hasArmor) {
        this.hasArmor = hasArmor;
    }

    // 你可以在这里添加角色特有的其他方法
}
