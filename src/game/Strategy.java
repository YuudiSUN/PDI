package game;


import java.awt.Point;
import java.util.Random;

// 策略类
public class Strategy {
    // 激进策略：朝着宝藏移动
    public static Point moveTowardsTreasure(Point adventurerPosition, Point treasurePosition) {
        int deltaX = Integer.compare(treasurePosition.x, adventurerPosition.x);
        int deltaY = Integer.compare(treasurePosition.y, adventurerPosition.y);
        return new Point(adventurerPosition.x + deltaX, adventurerPosition.y + deltaY);
    }

    // 保守策略：原地不动
    public static Point stayPut(Point adventurerPosition) {
        return new Point(adventurerPosition.x, adventurerPosition.y);
    }

    // 随机策略：随机移动
    public static Point moveRandomly(Point adventurerPosition) {
        Random random = new Random();
        int deltaX = random.nextInt(3) - 1;
        int deltaY = random.nextInt(3) - 1;
        return new Point(adventurerPosition.x + deltaX, adventurerPosition.y + deltaY);
    }

    // 主要策略选择方法
    public static Point executeMainStrategy(Point adventurerPosition, Point treasurePosition, String mainStrategy) {
        switch (mainStrategy) {
            case "Radical":
                return moveTowardsTreasure(adventurerPosition, treasurePosition);
            case "Conservative":
                return stayPut(adventurerPosition);
            case "Random":
                return moveRandomly(adventurerPosition);
            default:
                return moveTowardsTreasure(adventurerPosition, treasurePosition); // 默认使用激进策略
        }
    }

    // 遇到狐狸时的小策略：正常行走
    public static Point walkNormallyAroundFoxes(Point adventurerPosition, Point foxPosition, Point treasurePosition) {
        // 如果冒险者和狐狸位置相同，随机移动
        if (adventurerPosition.equals(foxPosition)) {
            return moveRandomly(adventurerPosition);
        }
        // 否则，向宝藏移动
        return moveTowardsTreasure(adventurerPosition, treasurePosition);
    }

    // 遇到狐狸时的小策略：避开狐狸
    public static Point avoidFoxes(Point adventurerPosition, Point foxPosition, Point treasurePosition) {
        // 如果冒险者和狐狸位置相同，向远离狐狸的方向移动
        if (adventurerPosition.equals(foxPosition)) {
            int deltaX = Integer.compare(adventurerPosition.x, foxPosition.x);
            int deltaY = Integer.compare(adventurerPosition.y, foxPosition.y);
            return new Point(adventurerPosition.x - deltaX, adventurerPosition.y - deltaY);
        }
        // 否则，向宝藏移动
        return moveTowardsTreasure(adventurerPosition, treasurePosition);
    }
}
