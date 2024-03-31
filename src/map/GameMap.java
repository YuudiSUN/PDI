package map;

import java.util.Random;

public class GameMap {
    private Cell[][] cells;
    private static final double MOUNTAIN_AREA_PROBABILITY = 0.1;
    private static final double SWAMP_AREA_PROBABILITY = 0.1;
    private static final double FOREST_AREA_PROBABILITY = 0.2;
    private static final double  RIVER_AREA_PROBABILITY = 0.1;
	private static final int MAX_MOUNTAIN_AREA_SIZE = 4;
	private static final int MIN_MOUNTAIN_AREA_SIZE = 2;
	private static final int MAX_SWAMP_AREA_SIZE = 4;
	private static final int MIN_SWAMP_AREA_SIZE = 2;
	private static final int MAX_FOREST_AREA_SIZE = 4;
	private static final int MIN_FOREST_AREA_SIZE = 2;
	private static final int MAX_RIVER_AREA_SIZE = 4;
	private static final int MIN_RIVER_AREA_SIZE = 2;



    // 获取特定位置的Cell
    public Cell getCell(int x, int y) {
        if (x >= 0 && x < cells[0].length && y >= 0 && y < cells.length) {
            return cells[y][x];
        }
        return null;
    }

    // 获取地图的高度
    public int getHeight() {
        return cells.length;
    }

    // 获取地图的宽度
    public int getWidth() {
        return cells[0].length;
    }
    //生成地图主函数
    public GameMap(int width, int height) {
        cells = new Cell[height][width];
        fillMapWithGrass();
        generateRandomTerrain();
        placeTreasure(); // 生成宝藏
    }

 // 放置宝藏的方法
    public void placeTreasure() {
        Random random = new Random();
        int x, y;
        do {
            // 随机选择一个位置
            x = random.nextInt(getWidth());
            y = random.nextInt(getHeight());
        } while (!isTreasureLocationValid(x, y)); // 确保位置合法

        // 在选择的位置放置宝藏
        cells[y][x].setTerrain(Cell.TerrainType.TREASURE);
    }

    // 检查宝藏位置是否合法
    private boolean isTreasureLocationValid(int x, int y) {
        // 确保宝藏不会放在障碍物上，这里以山脉和沼泽为例
        Cell.TerrainType terrain = cells[y][x].getTerrain();
        return terrain != Cell.TerrainType.MOUNTAIN && terrain != Cell.TerrainType.SWAMP;
    }
    
//默认草地函数
	private void fillMapWithGrass() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(Cell.TerrainType.GRASS);
            }
        }
    }
//生产随机地形区域
    private void generateRandomTerrain() {
        Random random = new Random();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                double randomNumber = random.nextDouble();
                if (randomNumber < MOUNTAIN_AREA_PROBABILITY) {
                    generateMountainArea(i, j);
                } else if (randomNumber < MOUNTAIN_AREA_PROBABILITY + SWAMP_AREA_PROBABILITY) {
                    generateSwampArea(i, j);
                } else if (randomNumber < MOUNTAIN_AREA_PROBABILITY + SWAMP_AREA_PROBABILITY + FOREST_AREA_PROBABILITY) {
                    generateForestArea(i, j);
                } else if (randomNumber < MOUNTAIN_AREA_PROBABILITY + SWAMP_AREA_PROBABILITY + FOREST_AREA_PROBABILITY + RIVER_AREA_PROBABILITY) {
                    generateRiverArea(i, j);
                } else {
                    cells[i][j] = new Cell(Cell.TerrainType.GRASS); // 默认为草地
                }
            }
        }
    }
    //检查是否重复生产
    private boolean isAreaClear(int startX, int startY, int size) {
        for (int i = startX; i < startX + size && i < cells.length; i++) {
            for (int j = startY; j < startY + size && j < cells[i].length; j++) {
                if (cells[i][j] != null && cells[i][j].getTerrain() != Cell.TerrainType.GRASS) {
                    // 如果已经存在地形且不是草地，则返回false表示区域不清晰
                    return false;
                }
            }
        }
        // 如果区域内都为空或者是草地，则返回true表示区域清晰
        return true;
    }

    private void clearArea(int startX, int startY, int size) {
        for (int i = startX; i < startX + size && i < cells.length; i++) {
            for (int j = startY; j < startY + size && j < cells[i].length; j++) {
                cells[i][j] = null;
            }
        }
    }
    //生产随机地形区域（函数）
    private void generateMountainArea(int startX, int startY) {
        Random random = new Random();
		int size = random.nextInt(MAX_MOUNTAIN_AREA_SIZE - MIN_MOUNTAIN_AREA_SIZE) + MIN_MOUNTAIN_AREA_SIZE;
	    // 清除默认草地并检查目标区域是否清晰
	    clearArea(startX, startY, size);
	    if (!isAreaClear(startX, startY, size)) {
	        return;
	    }

        for (int i = startX; i < startX + size && i < cells.length; i++) {
            for (int j = startY; j < startY + size && j < cells[i].length; j++) {
                cells[i][j] = new Cell(Cell.TerrainType.MOUNTAIN);
            }
        }
    }
  
    private void generateSwampArea(int startX, int startY) {
        Random random = new Random();
        int size = random.nextInt(MAX_SWAMP_AREA_SIZE - MIN_SWAMP_AREA_SIZE) + MIN_SWAMP_AREA_SIZE;
        // 清除默认草地并检查目标区域是否清晰
        clearArea(startX, startY, size);
        if (!isAreaClear(startX, startY, size)) {
            return;
        }

        for (int i = startX; i < startX + size && i < cells.length; i++) {
            for (int j = startY; j < startY + size && j < cells[i].length; j++) {
                cells[i][j] = new Cell(Cell.TerrainType.SWAMP);
            }
        }
    }

    private void generateForestArea(int startX, int startY) {
        Random random = new Random();
        int size = random.nextInt(MAX_FOREST_AREA_SIZE - MIN_FOREST_AREA_SIZE) + MIN_FOREST_AREA_SIZE;
        // 清除默认草地并检查目标区域是否清晰
        clearArea(startX, startY, size);
        if (!isAreaClear(startX, startY, size)) {
            return;
        }

        for (int i = startX; i < startX + size && i < cells.length; i++) {
            for (int j = startY; j < startY + size && j < cells[i].length; j++) {
                cells[i][j] = new Cell(Cell.TerrainType.FOREST);
            }
        }
    }

    private void generateRiverArea(int startX, int startY) {
        Random random = new Random();
        int size = random.nextInt(MAX_RIVER_AREA_SIZE - MIN_RIVER_AREA_SIZE) + MIN_RIVER_AREA_SIZE;
        // 清除默认草地并检查目标区域是否清晰
        clearArea(startX, startY, size);
        if (!isAreaClear(startX, startY, size)) {
            return;
        }

        for (int i = startX; i < startX + size && i < cells.length; i++) {
            for (int j = startY; j < startY + size && j < cells[i].length; j++) {
                cells[i][j] = new Cell(Cell.TerrainType.RIVER);
            }
        }
    }

}
