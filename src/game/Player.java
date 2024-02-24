package game;

import java.util.List;

public class Player {

    private String name;
    private int health;
    private int damage;
    private int posX; // 玩家当前 X 坐标
    private int posY; // 玩家当前 Y 坐标

    public Player(String name, int health, int damage, int posX, int posY) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.posX = posX;
        this.posY = posY;
    }

    // 获取玩家名称
    public String getName() {
        return name;
    }

    // 获取玩家健康值
    public int getHealth() {
        return health;
    }

    // 获取玩家攻击伤害值
    public int getDamage() {
        return damage;
    }

    // 获取玩家当前 X 坐标
    public int getPosX() {
        return posX;
    }

    // 获取玩家当前 Y 坐标
    public int getPosY() {
        return posY;
    }

    // 玩家移动的方法
    public void move(int newX, int newY) {
        // 在这里定义玩家移动的逻辑
        // 例如：更新玩家的位置坐标
        this.posX = newX;
        this.posY = newY;
    }

    // 玩家受到伤害的方法
    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
    }

    public List<Item> getInventory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInventory'");
    }

    // 其他可能需要的方法和逻辑
}
