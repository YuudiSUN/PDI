package gui;

import game.Explorer;

import javax.swing.*;
import java.awt.*;
public class ExplorerPanel extends JPanel {
    private Explorer Explorer;
    // 将标签声明为成员变量
    private JLabel nameLabel;
    private JLabel healthLabel;
    private JLabel damageLabel;
    private JLabel positionLabel;

    public ExplorerPanel(Explorer Explorer) {
        this.Explorer = Explorer;
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new GridLayout(2, 2));
        // 初始化标签
        nameLabel = new JLabel("Name: " + Explorer.getName());
        healthLabel = new JLabel("Health: " + Explorer.getHealth());
        damageLabel = new JLabel("Damage: " + Explorer.getDamage());
        positionLabel = new JLabel("Position: (" + Explorer.getPosX() + ", " + Explorer.getPosY() + ")");
        // 添加标签到面板
        add(nameLabel);
        add(healthLabel);
        add(damageLabel);
        add(positionLabel);
    }

    public void updateExplorerInfo() {
        // 在这里更新显示的玩家信息
        nameLabel.setText("Name: " + Explorer.getName());
        healthLabel.setText("Health: " + Explorer.getHealth());
        damageLabel.setText("Damage: " + Explorer.getDamage());
        positionLabel.setText("Position: (" + Explorer.getPosX() + ", " + Explorer.getPosY() + ")");
    }
}
