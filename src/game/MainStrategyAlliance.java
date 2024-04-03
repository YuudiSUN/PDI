package game;
import java.awt.Point;
import java.util.Random;

public class MainStrategyAlliance {
    // 定义大策略联盟中的小策略
    enum SubStrategy {
        NORMAL_WALK_AROUND_FOXES,
        AVOID_FOXES,
        RUN_AWAY_FROM_BEAR,
        TRY_TO_RUN_FROM_TIGER,
        CHANGE_DIRECTION_AROUND_TREE,
        STOP_MOVING_AT_MOUNTAIN
    }

    // 根据大策略选择合适的小策略执行
    public static Point executeMainStrategy(Point adventurerPosition, Point obstaclePosition, Point treasurePosition, String mainStrategy) {
        SubStrategy subStrategy = selectSubStrategy(mainStrategy, adventurerPosition, obstaclePosition, treasurePosition);
        switch (subStrategy) {
            case NORMAL_WALK_AROUND_FOXES:
                return Strategy.walkNormallyAroundFoxes(adventurerPosition, obstaclePosition, treasurePosition);
            case AVOID_FOXES:
                return Strategy.avoidFoxes(adventurerPosition, obstaclePosition, treasurePosition);
            case RUN_AWAY_FROM_BEAR:
                return Strategy.runAwayFromBear(adventurerPosition, obstaclePosition, treasurePosition);
            case TRY_TO_RUN_FROM_TIGER:
                return Strategy.tryToRunFromTiger(adventurerPosition, obstaclePosition, treasurePosition);
            case CHANGE_DIRECTION_AROUND_TREE:
                return Strategy.changeDirectionAroundTree(adventurerPosition, obstaclePosition, treasurePosition);
            case STOP_MOVING_AT_MOUNTAIN:
                return Strategy.stopMovingAtMountain(adventurerPosition, obstaclePosition, treasurePosition);
            default:
                return adventurerPosition; // 默认情况下不执行任何策略
        }
    }

    // 根据大策略选择合适的小策略
    private static SubStrategy selectSubStrategy(String mainStrategy, Point adventurerPosition, Point obstaclePosition, Point treasurePosition) {
        Random random = new Random();
        double chance = random.nextDouble(); // 生成一个[0, 1)之间的随机数
        switch (mainStrategy) {
            case "Radical":
                // 在激进策略下，选择随机策略的概率更大
                if (chance < 0.7) {
                    return SubStrategy.values()[random.nextInt(2) + 1]; // 随机选择随机策略或正常行走策略
                } else {
                    return SubStrategy.NORMAL_WALK_AROUND_FOXES; // 其他情况下正常行走策略
                }
            case "Random":
                // 在随机策略下，选择避开狐狸或正常行走的概率更大
                if (chance < 0.7) {
                    return SubStrategy.values()[random.nextInt(2)]; // 随机选择避开狐狸或正常行走策略
                } else {
                    return SubStrategy.NORMAL_WALK_AROUND_FOXES; // 其他情况下正常行走策略
                }
            default:
                // 默认情况下返回正常行走策略
                return SubStrategy.NORMAL_WALK_AROUND_FOXES;
        }
    }
}
