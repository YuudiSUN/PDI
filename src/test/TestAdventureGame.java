package test;

import game.GameController;

public class TestAdventureGame {

    public static void main(String[] args) {
        // 创建游戏控制器
        GameController gameController = new GameController();
        
        // 初始化游戏数据
        gameController.initializeGame();

        // 启动游戏
        gameController.startGame();


    }
}
