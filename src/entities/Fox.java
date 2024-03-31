package entities;

import map.Cell;

public class Fox extends Entity {
    public Fox(Cell startPosition, int health) {
        super(startPosition, health);
    }

    @Override
    public void move() {
        // 狐狸的移动逻辑
        // 这里可以根据实际情况实现狐狸的移动方式
    }

    @Override
    public void interact(Entity other) {
        // 狐狸与其他实体的交互逻辑
        // 这里可以根据实际情况实现狐狸与其他实体的交互方式
    }

    // 其他狐狸的特有方法和属性
}
