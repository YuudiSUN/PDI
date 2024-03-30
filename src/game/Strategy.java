package game;

import java.awt.Point;

public class Strategy {

    // 定义八种移动策略
    public static Point moveTowardsTreasure(Point adventurerPosition, Point treasurePosition) {
        int deltaX = Integer.compare(treasurePosition.x, adventurerPosition.x);
        int deltaY = Integer.compare(treasurePosition.y, adventurerPosition.y);
        return new Point(adventurerPosition.x + deltaX, adventurerPosition.y + deltaY);
    }

    public static Point moveAwayFromTreasure(Point adventurerPosition, Point treasurePosition) {
        int deltaX = Integer.compare(adventurerPosition.x, treasurePosition.x);
        int deltaY = Integer.compare(adventurerPosition.y, treasurePosition.y);
        return new Point(adventurerPosition.x + deltaX, adventurerPosition.y + deltaY);
    }

    // 其他六种策略类似，根据具体需求实现

}
