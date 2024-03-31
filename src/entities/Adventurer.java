package entities;

import map.Cell;
import map.GameMap;

import java.util.Random;

public class Adventurer extends Entity {
    private GameMap gameMap;

    public Adventurer(Cell startPosition, int health, GameMap gameMap) {
        super(startPosition, health);
        this.gameMap = gameMap;
    }

    @Override
    public void move() {
        Random rand = new Random();
        int[] dx = {0, 1, 0, -1}; // 方向数组，表示横向移动：不动，右移，不动，左移
        int[] dy = {1, 0, -1, 0}; // 方向数组，表示纵向移动：下移，不动，上移，不动
        int direction = rand.nextInt(4); // 随机选择一个方向
        int nextX = currentPosition.getX() + dx[direction];
        int nextY = currentPosition.getY() + dy[direction];

        // 检查是否可以移动到下一个位置
        if (gameMap.isValidPosition(nextX, nextY)) {
            Cell nextCell = gameMap.getCell(nextX, nextY);
            if (nextCell.getTerrain() != Cell.TerrainType.MOUNTAIN && nextCell.getTerrain() != Cell.TerrainType.RIVER) {
                this.currentPosition = nextCell; // 更新探险者的位置
            } else {
                // 遇到不可通过的地形，可以在这里处理
            }
        }
        // 这里可以添加与其他实体交互的逻辑
    }

    @Override
    public void interact(Entity other) {
        // 实现与其他实体的交互逻辑
    }
}
