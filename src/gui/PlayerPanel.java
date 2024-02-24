package gui;

import game.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {

    private Player player;

    // 构造方法，接收玩家实例作为参数
    public PlayerPanel(Player player) {
        this.player = player;
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new GridLayout(2, 2));

        // 创建标签显示玩家信息
        JLabel nameLabel = new JLabel("Name: " + player.getName());
        JLabel healthLabel = new JLabel("Health: " + player.getHealth());
        JLabel damageLabel = new JLabel("Damage: " + player.getDamage());
        JLabel positionLabel = new JLabel("Position: (" + player.getPosX() + ", " + player.getPosY() + ")");

        // 添加标签到面板
        add(nameLabel);
        add(healthLabel);
        add(damageLabel);
        add(positionLabel);
    }

    // 更新玩家信息的方法
    public void updatePlayerInfo() {
        // 在这里更新显示的玩家信息
        // 例如：nameLabel.setText("Name: " + player.getName());
        //      healthLabel.setText("Health: " + player.getHealth());
        //      等等
    }

    // 其他可能需要的方法和逻辑
}
