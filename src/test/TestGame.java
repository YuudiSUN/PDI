package test;

import game.Interface; // 引入Interface类

public class TestGame {
    public static void main(String[] args) {
        // 在事件调度线程中启动游戏界面
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Interface(); // 创建并显示游戏的主界面
            }
        });
    }
}
