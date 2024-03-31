package entities;

import map.Cell;

public class Dragon extends Entity {
    public Dragon(Cell startPosition, int health) {
        super(startPosition, health);
    }

    @Override
    public void move() {
        // 龙的移动逻辑
        // 这里可以根据实际情况实现龙的移动方式
    }

    @Override
    public void interact(Entity other) {
        // 龙与其他实体的交互逻辑
        // 这里可以根据实际情况实现龙与其他实体的交互方式
    }

    // 其他龙的特有方法和属性
}
