package utils;

import java.awt.Point;
import java.util.Random;

public class MapGenerator {

    public static final int MAP_WIDTH = 20;
    public static final int MAP_HEIGHT = 20;
    private static final Random random = new Random();

    // 生成地图并确保包含所有必要的元素
    public static Integer[][] generateMap() {
        Integer[][] map = new Integer[MAP_HEIGHT][MAP_WIDTH];

        // 填充地图的初始元素
        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                map[i][j] = MapElement.GRASS.getValue(); // 默认为草地
            }
        }

        // 随机添加其他元素
        placeRandomElements(map, MapElement.FOREST, 40); // 示例: 放置30个森林
        placeRandomElements(map, MapElement.RIVER, 30);   // 放置5个河流段
        placeRandomElements(map, MapElement.MOUNTAIN, 40); // 放置10座山
        placeRandomElements(map, MapElement.MARSHLAND, 15); // 放置15个沼泽地
        // 确保宝藏放在难以到达的位置
        placeTreasure(map);

        return map;
    }

    private static void placeRandomElements(Integer[][] map, MapElement element, int count) {
        for (int i = 0; i < count; i++) {
            int x = random.nextInt(MAP_WIDTH);
            int y = random.nextInt(MAP_HEIGHT);
            map[y][x] = element.getValue();
        }
    }

    private static void placeTreasure(Integer[][] map) {
        while (true) {
            int x = random.nextInt(MAP_WIDTH);
            int y = random.nextInt(MAP_HEIGHT);
            // 假设宝藏被放在非草地和非初始位置
            if (map[y][x] != MapElement.GRASS.getValue() && !(x == 0 && y == MAP_HEIGHT - 1)) {
                map[y][x] = MapElement.TREASURE.getValue();
                break; // 找到放置宝藏的位置后停止
            }
        }
    }
}
