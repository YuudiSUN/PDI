package entities;

import map.Cell;

public class Snake extends Entity {
    public Snake(Cell startPosition, int health) {
        super(startPosition, health);
    }

    @Override
    public void move() {
        // 蛇的移动逻辑
        // 这里可以根据实际情况实现蛇的移动方式
    }

    @Override
    public void interact(Entity other) {
        // 蛇与其他实体的交互逻辑
        // 这里可以根据实际情况实现蛇与其他实体的交互方式
    }

    // 其他蛇的特有方法和属性
}
