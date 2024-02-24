package game;

import java.util.List;

public class GameMap {

    private int[][] mapGrid; // 地图网格，可以是二维数组，表示地图的布局
    private List<Enemy> enemies; // 地图上的敌人列表
    private List<Item> items; // 地图上的物品列表

    public GameMap(int[][] mapGrid, List<Enemy> enemies, List<Item> items) {
        this.mapGrid = mapGrid;
        this.enemies = enemies;
        this.items = items;
    }

    // 获取地图网格
    public int[][] getMapGrid() {
        return mapGrid;
    }

    // 获取地图上的敌人列表
    public List<Enemy> getEnemies() {
        return enemies;
    }

    // 获取地图上的物品列表
    public List<Item> getItems() {
        return items;
    }

    // 在地图上添加敌人
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    // 在地图上移除敌人
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    // 在地图上添加物品
    public void addItem(Item item) {
        items.add(item);
    }

    // 在地图上移除物品
    public void removeItem(Item item) {
        items.remove(item);
    }

    // 其他可能需要的方法和逻辑
}
