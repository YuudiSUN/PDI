package gui;

import game.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdventureGameGUI {

    private JFrame frame;
    private GameController gameController;

    // 可以根据需要添加其他 GUI 组件的引用，例如地图面板、玩家信息面板等

    public AdventureGameGUI(GameController gameController) {
        this.gameController = gameController;
        initializeGUI();
    }

    public void initializeGUI() {
    	frame = new JFrame("Explorateur");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置背景图片
        ImageIcon backgroundImage = new ImageIcon("src/images/map.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);

        // 创建面板，并将按钮添加到面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridBagLayout());

        // 创建开始游戏按钮
        JButton startButton = new JButton("开始游戏");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "游戏已开始！");
            }
        });
        startButton.setPreferredSize(new Dimension(150, 50));  // 设置按钮大小

        // 创建退出游戏按钮
        JButton exitButton = new JButton("退出游戏");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(frame, "确定退出游戏吗？", "确认退出", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        exitButton.setPreferredSize(new Dimension(150, 50));  // 设置按钮大小

        // 使用GridBagConstraints使按钮居中
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(startButton, gbc);
        gbc.gridy = 1;
        buttonPanel.add(exitButton, gbc);

        // 将面板添加到背景标签上
        backgroundLabel.setLayout(new GridBagLayout());
        backgroundLabel.add(buttonPanel);

        // 将背景标签添加到窗口
        frame.setContentPane(backgroundLabel);

        // 设置窗口居中显示
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // 更新图形用户界面的方法，可以根据需要进行调整
    public void updateGUI() {
        // 在这里实现图形用户界面的更新逻辑
        // 例如：更新地图、玩家信息等
    }

    // 其他可能需要的方法和逻辑
}
