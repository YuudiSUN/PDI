package map;

public class GameMap {
    private Cell[][] cells;

    public GameMap(int width, int height) {
        cells = new Cell[height][width];
        fillMapWithGrass();
    }

    // 使用草地填充地图
    private void fillMapWithGrass() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell("grass");
            }
        }
    }

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

    // 更多的方法，比如设置特定位置的Cell、添加实体等
}
