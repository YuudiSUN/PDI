package game;

public class Enemy {

    private String name;
    private int health;
    private int damage;

    public Enemy(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    // 获取敌人的名称
    public String getName() {
        return name;
    }

    // 获取敌人的健康值
    public int getHealth() {
        return health;
    }

    // 获取敌人的攻击伤害值
    public int getDamage() {
        return damage;
    }

    // 敌人受到伤害的方法
    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
    }

    // 其他可能需要的方法和逻辑
}
