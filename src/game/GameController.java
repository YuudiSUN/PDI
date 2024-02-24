package game;

import gui.AdventureGameGUI;
import utils.GameRules;

public class GameController {

    private GameMap gameMap;
    private Player player;
    private AdventureGameGUI gameGUI;

    public GameController() {
        // 初始化游戏地图和玩家
        this.gameMap = new GameMap(null, null, null);
        this.player = new Player(null, 0, 0, 0, 0);
    }

    public void initializeGame() {
        // 在这里初始化游戏地图、敌人、物品等
        // 例如：gameMap.initialize();
        //      player.initialize();
    }

    public void startGame() {
        // 显示游戏图形用户界面
        this.gameGUI = new AdventureGameGUI(this);

        // 游戏循环
        while (!GameRules.isGameOver(player)) {
            // 处理玩家输入
            handleInput();

            // 更新游戏状态
            updateGame();

            // 更新图形用户界面
            gameGUI.updateGUI();
        }

        // 游戏结束时的处理
        handleGameOver();
    }

    private void handleInput() {
        // 处理玩家输入的逻辑
        // 例如：player.move(direction);
    }

    private void updateGame() {
        // 更新游戏状态的逻辑
        // 例如：gameMap.update();
        //      player.update();
    }

    private void handleGameOver() {
        // 游戏结束时的处理逻辑
        // 例如：显示游戏结束画面、分数统计等
    }

    // 其他可能需要的方法和逻辑
}
