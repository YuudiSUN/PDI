// 文件：GameEngine.java
package game;

import entities.Adventurer;
import map.GameMap;

public class GameEngine {
    private GameMap gameMap;
    private Adventurer adventurer;

    public GameEngine(GameMap gameMap, Adventurer adventurer) {
        this.gameMap = gameMap;
        this.adventurer = adventurer;
    }

    public void start() {
        // 这里开始游戏循环，控制游戏流程
        new Thread(() -> {
            while (true) { // 用一个更合理的条件来替代无限循环
                adventurer.move();
                // 更新游戏状态，比如检查是否找到宝藏

                try {
                    Thread.sleep(1000); // 控制每次移动的时间间隔
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
