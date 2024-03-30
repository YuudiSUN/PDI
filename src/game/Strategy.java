package game;

import java.awt.Point;
import java.util.Random;

public class Strategy {

    // 激进策略：朝着宝藏移动
    public static Point moveTowardsTreasure(Point adventurerPosition, Point treasurePosition) {
        int deltaX = Integer.compare(treasurePosition.x, adventurerPosition.x);
        int deltaY = Integer.compare(treasurePosition.y, adventurerPosition.y);
        return new Point(adventurerPosition.x + deltaX, adventurerPosition.y + deltaY);
    }

    // 保守策略：原地不动
    public static Point stayPut(Point adventurerPosition) {
        // 直接返回原始位置，表示没有移动
        return new Point(adventurerPosition.x, adventurerPosition.y);
    }

    // 随机策略：随机移动
    public static Point moveRandomly(Point adventurerPosition) {
        Random random = new Random();
        // Generate -1, 0, or 1 for both x and y directions to move randomly
        int deltaX = random.nextInt(3) - 1; // This will produce -1, 0, or 1
        int deltaY = random.nextInt(3) - 1; // Similarly here
        return new Point(adventurerPosition.x + deltaX, adventurerPosition.y + deltaY);
    }
}
