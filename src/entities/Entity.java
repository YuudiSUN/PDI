package entities;

import map.Cell;

public abstract class Entity {
    protected Cell currentPosition; // 使用 map.Cell 表示位置
    protected int health;

    public Entity(Cell startPosition, int health) {
        this.currentPosition = startPosition;
        this.health = health;
    }

    // 获取实体位置
    public Cell getPosition() {
        return currentPosition;
    }

    // 设置实体位置
    public void setPosition(Cell position) {
        this.currentPosition = position;
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
