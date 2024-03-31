package entities;

import map.Cell;

public class Tiger extends Entity {
    public Tiger(Cell startPosition, int health) {
        super(startPosition, health);
    }

    @Override
    public void move() {
        // 老虎的移动逻辑
        // 这里可以根据实际情况实现老虎的移动方式
    }

    @Override
    public void interact(Entity other) {
        // 老虎与其他实体的交互逻辑
        // 这里可以根据实际情况实现老虎与其他实体的交互方式
    }

    // 其他老虎的特有方法和属性
}
