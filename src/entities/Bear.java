package entities;

import map.Cell;

public class Bear extends Entity {
    public Bear(Cell startPosition, int health) {
        super(startPosition, health);
    }

    @Override
    public void move() {
        // 熊的移动逻辑
        // 这里可以根据实际情况实现熊的移动方式
    }

    @Override
    public void interact(Entity other) {
        // 熊与其他实体的交互逻辑
        // 这里可以根据实际情况实现熊与其他实体的交互方式
    }

    // 其他熊的特有方法和属性
}
