package game;

import java.util.List;

public class Explorer {

    private String name;
    private int health;
    private int damage;
    private int posX; 
    private int posY; 

    public Explorer(String name, int health, int damage, int posX, int posY) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.posX = posX;
        this.posY = posY;
    }

    // 获取Explorer名称
    public String getName() {
        return name;
    }

    // 获取Explorer健康值
    public int getHealth() {
        return health;
    }

    // 获取Explorer攻击伤害值
    public int getDamage() {
        return damage;
    }

    // 获取Explorer当前 X 坐标
    public int getPosX() {
        return posX;
    }

    // 获取Explorer当前 Y 坐标
    public int getPosY() {
        return posY;
    }

    // Setter方法
    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setPosition(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
    
    
//    // Explorer移动的方法
//    public void move(int newX, int newY) {
//        // 在这里定义Explorer移动的逻辑
//        // 例如：更新Explorer的位置坐标
//        this.posX = newX;
//        this.posY = newY;
//    }
//
//    // Explorer受到伤害的方法
//    public void takeDamage(int amount) {
//        health -= amount;
//        if (health < 0) {
//            health = 0;
//        }
//    }

    public List<Item> getInventory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInventory'");
    }


}
