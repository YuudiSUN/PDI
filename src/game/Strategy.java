package game;


import java.awt.Point;
import java.util.Random;

import status.CharacterStatus;
import utils.MapElement;

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

    //遇到血量低于30时的小策略：避战
    public static Point decideNextMove(CharacterStatus adventurer, Point animalPosition, Point treasurePosition) {
        // 检查血量
        if (adventurer.getHealth() < 30) {
            // 如果血量低于30，尝试避免与动物交战
            return findSafePosition(adventurer.getPosition(), animalPosition, treasurePosition);
        } else {
            // 否则，正常行动，可能包括向宝藏移动或与动物交战
            return moveTowardsTreasure(animalPosition, treasurePosition);
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
    
    //遇到龙时的小策略：在两个内就避开，不与之交战
    public static Point avoidDragon(Point adventurerPosition, Point dragonPosition, Point treasurePosition) {
	    // 计算冒险者与龙之间的距离
	    int distanceToDragon = Math.max(Math.abs(adventurerPosition.x - dragonPosition.x), Math.abs(adventurerPosition.y - dragonPosition.y));
	    
	    // 如果冒险者与龙的距离小于等于2格
	    if (distanceToDragon <= 2) {
	        // 检查宝箱是否在龙的周围，如果是，则直接向宝箱移动
	        if (treasurePosition.equals(dragonPosition)) {
	            return moveTowardsTreasure(adventurerPosition, treasurePosition);
	        } else {
	            // 尝试找到一个安全的方向来避开龙
	            Point safePosition = findSafePosition(adventurerPosition, dragonPosition, treasurePosition);
	            return safePosition != null ? safePosition : adventurerPosition; // 如果找不到安全位置，则原地不动
	        }
	    } else {
	        // 如果冒险者与龙的距离超过2格，正常朝宝藏移动
	        return moveTowardsTreasure(adventurerPosition, treasurePosition);
	    }
	}

	private static Point findSafePosition(Point adventurerPosition, Point dragonPosition, Point treasurePosition) {
	    // 假设有一个方法可以检查给定位置是否安全（不在龙的两格内）
	    for (int dx = -2; dx <= 2; dx++) {
	        for (int dy = -2; dy <= 2; dy++) {
	            Point potentialPosition = new Point(adventurerPosition.x + dx, adventurerPosition.y + dy);
	            if (isSafeFromDragon(potentialPosition, dragonPosition) && isValidMove(potentialPosition)) {
	                // 检查这个潜在的位置是否靠近宝箱
	                if (Math.abs(potentialPosition.x - treasurePosition.x) < Math.abs(adventurerPosition.x - treasurePosition.x) ||
	                    Math.abs(potentialPosition.y - treasurePosition.y) < Math.abs(adventurerPosition.y - treasurePosition.y)) {
	                    return potentialPosition; // 返回靠近宝箱的安全位置
	                }
	            }
	        }
	    }
	    return null; // 如果找不到安全的位置，则返回null
	}

	private static boolean isValidMove(Point potentialPosition) {
		return false;
	}

	
	private static boolean isSafeFromDragon(Point position, Point dragonPosition) {
	    // 计算给定位置与龙之间的距离，如果超过2格，则认为是安全的
	    return Math.max(Math.abs(position.x - dragonPosition.x), Math.abs(position.y - dragonPosition.y)) > 2;
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


	//遇到河流时的小策略：减慢移动速度
	public static Point slowDownNearRiver(Point adventurerPosition, Point riverPosition, Point treasurePosition) {
	 // 判断冒险者是否已经非常靠近河流，这里简化为判断是否在同一位置
	 if (adventurerPosition.equals(riverPosition)) {
	     // 如果冒险者在河流旁，尝试以减半的速度移动
	 // 计算朝宝藏方向的一半距离
	 int deltaX = (int) Math.signum(treasurePosition.x - adventurerPosition.x);
	 int deltaY = (int) Math.signum(treasurePosition.y - adventurerPosition.y);
	 
	 // 使用Math.signum确保每次移动都只是1步或0步
	 // 这样处理模拟了减慢的移动速度
	 Point newPosition = new Point(adventurerPosition.x + deltaX, adventurerPosition.y + deltaY);
	 
	 // 检查新位置是否仍然在河流上，如果是，可以选择停留或寻找其他路线
	 // 这里简化处理，只返回新位置
	     return newPosition;
	 } else {
	     // 如果冒险者不在河流旁，正常朝宝藏移动
	     return moveTowardsTreasure(adventurerPosition, treasurePosition);
	 }
	}
	
	//遇到森林时的小策略：减慢移动速度
	public static Point slowDownNearForest(Point adventurerPosition, Point forestPosition, Point treasurePosition) {
	    // 判断冒险者是否靠近森林，这里简化为判断是否在相邻的位置
	    if (isAdjacentToForest(adventurerPosition)) {
	        // 如果冒险者在森林附近，尝试以减半的速度移动
	        // 计算朝宝藏方向的一半距离
	        int deltaX = (int) Math.signum(treasurePosition.x - adventurerPosition.x) / 2; // 减速效果
	        int deltaY = (int) Math.signum(treasurePosition.y - adventurerPosition.y) / 2; // 减速效果

	        // 由于上述减速可能导致deltaX和deltaY为0（即位置不变），为保证至少有一定的移动，我们将选择非零的最小移动
	        deltaX = deltaX == 0 ? (treasurePosition.x != adventurerPosition.x ? 1 : 0) : deltaX;
	        deltaY = deltaY == 0 ? (treasurePosition.y != adventurerPosition.y ? 1 : 0) : deltaY;

	        Point newPosition = new Point(adventurerPosition.x + deltaX, adventurerPosition.y + deltaY);

	        // 这里没有检查新位置是否仍然在森林上，因为我们假设靠近森林即减速
	        // 可以选择停留或寻找其他路线
	        // 这里简化处理，只返回新位置
	        return newPosition;
	    } else {
	        // 如果冒险者不在森林附近，正常朝宝藏移动
	        return moveTowardsTreasure(adventurerPosition, treasurePosition);
	    }
	}
    
	private static boolean isAdjacentToForest(Point adventurerPosition) {
		return false;
	}

	//遇到森林时的小策略：尽量沿着沙漠移动
	public static Point walkAroundDesert(Point adventurerPosition, Point treasurePosition) {
	    // 首先检查冒险者前方是否为沙漠
	    int deltaX = (int) Math.signum(treasurePosition.x - adventurerPosition.x);
	    int deltaY = (int) Math.signum(treasurePosition.y - adventurerPosition.y);

	    Point nextPosition = new Point(adventurerPosition.x + deltaX, adventurerPosition.y + deltaY);
	    
	    // 如果冒险者前方是沙漠
	    if (isDesert(nextPosition)) {
	        // 尝试沿着沙漠边缘行走，首先检查水平方向
	        if (!isDesert(new Point(adventurerPosition.x + deltaX, adventurerPosition.y))) {
	            nextPosition = new Point(adventurerPosition.x + deltaX, adventurerPosition.y);
	        } else if (!isDesert(new Point(adventurerPosition.x, adventurerPosition.y + deltaY))) {
	            // 如果水平方向不行，尝试垂直方向
	            nextPosition = new Point(adventurerPosition.x, adventurerPosition.y + deltaY);
	        } else {
	            // 如果水平和垂直方向都是沙漠，尝试对角线方向
	            nextPosition = tryDiagonalDirection(adventurerPosition, deltaX, deltaY);
	        }
	    } else {
	        // 如果前方不是沙漠，正常朝宝藏移动
	        return moveTowardsTreasure(adventurerPosition, treasurePosition);
	    }
	    return nextPosition;
	}


	private static Point tryDiagonalDirection(Point adventurerPosition, int deltaX, int deltaY) {
	    // 尝试所有对角线方向，直到找到一个非沙漠的方向
	    for (int xShift : new int[]{-1, 1}) {
	        for (int yShift : new int[]{-1, 1}) {
	            Point diagonalPosition = new Point(adventurerPosition.x + xShift, adventurerPosition.y + yShift);
	            if (!isDesert(diagonalPosition)) {
	                return diagonalPosition;
	            }
	        }
	    }
	    // 如果所有方向都被沙漠阻挡，就原地不动
	    return adventurerPosition;
	}

	private static boolean isDesert(Point diagonalPosition) {
		return false;
	}
}



