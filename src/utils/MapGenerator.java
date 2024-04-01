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
        placeRandomElements(map, MapElement.FOREST, 30); // 放置30片森林
        placeRandomElements(map, MapElement.RIVER, 8);   // 放置8条河流
        placeRandomElements(map, MapElement.MOUNTAIN, 15); // 放置10座山
        placeRandomElements(map, MapElement.MARSHLAND, 15); // 放置20片沼泽地
        // 确保宝藏放在难以到达的位置
        placeTreasure(map);

        return map;
    }

    private static void placeRandomElements(Integer[][] map, MapElement element, int count) {
        for (int i = 0; i < count; i++) {
            int x = random.nextInt(MAP_WIDTH - 2); // Ensure enough space for the elements
            int y = random.nextInt(MAP_HEIGHT - 2);

            // 检查地图上是否已有其他元素，如果有则重新生成位置
            while (map[y][x] != MapElement.GRASS.getValue()) {
                x = random.nextInt(MAP_WIDTH - 2);
                y = random.nextInt(MAP_HEIGHT - 2);
            }

            // 放置元素
            for (int dy = 0; dy < 2; dy++) {
                for (int dx = 0; dx < 2; dx++) {
                    map[y + dy][x + dx] = element.getValue();
                }
            }
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
