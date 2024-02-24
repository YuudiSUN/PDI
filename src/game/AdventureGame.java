package game;

import gui.AdventureGameGUI;

public class AdventureGame {

    public static void main(String[] args) {
        // 创建游戏控制器
        GameController gameController = new GameController();

        // 初始化游戏数据
        gameController.initializeGame();

        // 创建游戏图形用户界面
        AdventureGameGUI gameGUI = new AdventureGameGUI(gameController);
        gameGUI.initializeGUI();
        // 启动游戏
        gameController.startGame();
    }
}
