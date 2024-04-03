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
 // 遇到熊时的小策略：逃跑
    public static Point runAwayFromBear(Point adventurerPosition, Point bearPosition, Point treasurePosition) {
        // 如果冒险者和熊位置相同，向远离熊的方向移动
        if (adventurerPosition.equals(bearPosition)) {
            int deltaX = Integer.compare(adventurerPosition.x, bearPosition.x);
            int deltaY = Integer.compare(adventurerPosition.y, bearPosition.y);
            return new Point(adventurerPosition.x - deltaX, adventurerPosition.y - deltaY);
        }
        // 否则，向宝藏移动
        return moveTowardsTreasure(adventurerPosition, treasurePosition);
    }

    // 遇到老虎时的小策略：尝试逃跑，但如果逃跑失败则与老虎战斗
    public static Point tryToRunFromTiger(Point adventurerPosition, Point tigerPosition, Point treasurePosition) {
        // 如果冒险者和老虎位置相同，尝试逃跑
        if (adventurerPosition.equals(tigerPosition)) {
            // 随机移动尝试逃跑
            return moveRandomly(adventurerPosition);
        }
        // 否则，向宝藏移动
        return moveTowardsTreasure(adventurerPosition, treasurePosition);
    }
 // 遇到树时的小策略：改变方向绕过树
    public static Point changeDirectionAroundTree(Point adventurerPosition, Point treePosition, Point treasurePosition) {
        // 如果冒险者和树位置相同，随机选择一个新方向
        if (adventurerPosition.equals(treePosition)) {
            Random random = new Random();
            int deltaX = random.nextInt(3) - 1; // 随机生成-1、0或1
            int deltaY = random.nextInt(3) - 1;
            // 确保不是原地不动
            while (deltaX == 0 && deltaY == 0) {
                deltaX = random.nextInt(3) - 1;
                deltaY = random.nextInt(3) - 1;
            }
            return new Point(adventurerPosition.x + deltaX, adventurerPosition.y + deltaY);
        }
        // 否则，向宝藏移动
        return moveTowardsTreasure(adventurerPosition, treasurePosition);
    }

    // 遇到山时的小策略：停止移动
    public static Point stopMovingAtMountain(Point adventurerPosition, Point mountainPosition, Point treasurePosition) {
        // 如果冒险者和山位置相同，原地不动
        if (adventurerPosition.equals(mountainPosition)) {
            return stayPut(adventurerPosition);
        }
        // 否则，向宝藏移动
        return moveTowardsTreasure(adventurerPosition, treasurePosition);
    }

}

