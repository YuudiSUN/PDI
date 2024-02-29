package game;

import gui.AdventureGameGUI;
import utils.GameRules;

public class GameController {

    private GameMap gameMap;
    private Explorer explorer;
    private AdventureGameGUI gameGUI;

    public GameController() {
        // 初始化游戏地图和玩家
        this.gameMap = new GameMap(null, null, null);
        this.explorer = new Explorer(null, 0, 0, 0, 0);
    }

    public void initializeGame() {
        // Initialize the game map, enemies, items, etc. 
        explorer.setName("Explorer1");
        explorer.setHealth(100); 
        explorer.setDamage(10); 
        explorer.setPosition(1, 1); 
    }

    public void startGame() {
    	// GUI
        this.gameGUI = new AdventureGameGUI(this);

        // 游戏循环
        while (!GameRules.isGameOver(explorer)) {
            // 处理玩家输入
            handleInput();

            // 更新游戏状态
            updateGame();

            // Update GUI
            gameGUI.updateGUI();
        }

        // 游戏结束时的处理
        handleGameOver();
    }

    private void handleInput() {
        // 处理玩家输入的逻辑
        // 例如：explorer.move(direction);
    }

    private void updateGame() {
        // 更新游戏状态的逻辑
        // 例如：gameMap.update();
        //      explorer.update();
    }

    private void handleGameOver() {
        // 游戏结束时的处理逻辑
        // 例如：显示游戏结束画面、分数统计等
    }

    // 其他可能需要的方法和逻辑
}
