package entities;

import java.awt.Point;

public abstract class Entity {
    protected Point position; // 使用java.awt.Point表示位置
    protected int health;

    public Entity(Point position, int health) {
        this.position = position;
        this.health = health;
    }

    // 获取实体位置
    public Point getPosition() {
        return position;
    }

    // 设置实体位置
    public void setPosition(Point position) {
        this.position = position;
    }

    // 获取生命值
    public int getHealth() {
        return health;
    }

    // 设置生命值
    public void setHealth(int health) {
        this.health = health;
    }

    // 实体受到伤害
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    // 判断实体是否存活
    public boolean isAlive() {
        return this.health > 0;
    }

    // 实体移动的逻辑
    public abstract void move();

    // 实体与其他实体交互的逻辑
    public abstract void interact(Entity other);
}
