package gui;

import javax.swing.*;

import entities.Adventurer;
import game.GameEngine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import map.GameMap; // 导入GameMap类
public class TeamConfig extends JFrame {
    private int budget = 0;
    private JLabel budgetLabel;
    private JButton addMemberButton, addPotionButton, enterMapButton, addWeaponButton, addArmorButton;
    private JComboBox<String> strategyComboBox;
    private JComboBox<String> SpeedComboBox;
    private int teamMembers = 0;
    private int potions = 0;
    private boolean hasWeapon = false, hasArmor = false;

    public TeamConfig() {
        setTitle("Configure Your Team");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        budgetLabel = new JLabel("Budget: " + budget + " | Team Members: " + teamMembers + " | Health Potions: " + potions +
                " | Weapon: " + (hasWeapon ? "Yes" : "No") + " | Armor: " + (hasArmor ? "Yes" : "No"));
        add(budgetLabel);

        addMemberButton = new JButton("Buy Adventurer (" + 0 + ")");
        add(addMemberButton);

        addPotionButton = new JButton("Buy Health Potion (" + 0 + ")");
        add(addPotionButton);

        addWeaponButton = new JButton("Buy Weapon (100)");
        add(addWeaponButton);

        addArmorButton = new JButton("Buy Armor (100)");
        add(addArmorButton);

        strategyComboBox = new JComboBox<>(new String[]{"Radical", "Conservative", "Random"});
        add(new JLabel("Select Strategy:"));
        add(strategyComboBox);

        SpeedComboBox = new JComboBox<>(new String[]{"High Speed", "Normal", "Low Speed"});
        add(new JLabel("GameSpeed:"));
        add(SpeedComboBox);

        enterMapButton = new JButton("Enter Map");
        add(enterMapButton);
        enterMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 实例化地图
                GameMap gameMap = new GameMap(20, 20);
                
                // 设置探险者的初始位置在地图的最下角
                int initialX = gameMap.getWidth() - 1;
                int initialY = gameMap.getHeight() - 1;
                Adventurer adventurer = new Adventurer(gameMap.getCell(initialX, initialY), 100, gameMap); // 创建探险者实例
                
                // 创建游戏引擎并启动游戏循环
                GameEngine gameEngine = new GameEngine(gameMap, adventurer);
                gameEngine.start(); // 启动游戏
                
                // 创建一个新窗口来显示地图
                JFrame mapFrame = new JFrame("Game Map");
                mapFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                mapFrame.getContentPane().add(new MapPanel(gameMap));
                mapFrame.setSize(640, 668); // 根据实际地图大小调整
                mapFrame.setLocationRelativeTo(null);
                mapFrame.setVisible(true);

                // 关闭配置窗口
                dispose();
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new TeamConfig();
    }
}
