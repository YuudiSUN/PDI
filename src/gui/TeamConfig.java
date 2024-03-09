package gui; // 修改为您的实际包名

import utils.GameRules;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TeamConfig extends JFrame {
    private int budget = GameRules.INITIAL_BUDGET;
    private JLabel budgetLabel;
    private JButton addMemberButton, addPotionButton, enterMapButton, addWeaponButton, addArmorButton;
    private JComboBox<String> strategyComboBox;
    private int teamMembers = 0;
    private int potions = 0;
    private boolean hasWeapon = false, hasArmor = false;

    public TeamConfig() {
        setTitle("Configure Your Team");
        setSize(600, 400); // 根据需要调整大小
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout()); // 设置布局管理器

        // 显示预算
        budgetLabel = new JLabel("Budget: " + budget);
        add(budgetLabel);

        // 添加队员按钮
        addMemberButton = new JButton("Buy Adventurer (" + GameRules.COST_PER_MEMBER + ")");
        add(addMemberButton);
        addMemberButton.addActionListener(e -> {
            if (budget >= GameRules.COST_PER_MEMBER) {
                budget -= GameRules.COST_PER_MEMBER;
                teamMembers++;
                updateDisplay();
            } else {
                JOptionPane.showMessageDialog(TeamConfig.this, "Not enough money!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 添加血瓶按钮
        addPotionButton = new JButton("Buy Health Potion (" + GameRules.COST_PER_POTION + ")");
        add(addPotionButton);
        addPotionButton.addActionListener(e -> {
            if (budget >= GameRules.COST_PER_POTION) {
                budget -= GameRules.COST_PER_POTION;
                potions++;
                updateDisplay();
            } else {
                JOptionPane.showMessageDialog(TeamConfig.this, "Not enough money!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 添加购买武器按钮
        addWeaponButton = new JButton("Buy Weapon (100)");
        add(addWeaponButton);
        addWeaponButton.addActionListener(e -> {
            if (budget >= 100 && !hasWeapon) {
                budget -= 100;
                hasWeapon = true;
                updateDisplay();
                addWeaponButton.setEnabled(false); // Disable button after purchase
            } else if (hasWeapon) {
                JOptionPane.showMessageDialog(TeamConfig.this, "You already have a weapon!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(TeamConfig.this, "Not enough money!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 添加购买护甲按钮
        addArmorButton = new JButton("Buy Armor (100)");
        add(addArmorButton);
        addArmorButton.addActionListener(e -> {
            if (budget >= 100 && !hasArmor) {
                budget -= 100;
                hasArmor = true;
                updateDisplay();
                addArmorButton.setEnabled(false); // Disable button after purchase
            } else if (hasArmor) {
                JOptionPane.showMessageDialog(TeamConfig.this, "You already have armor!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(TeamConfig.this, "Not enough money!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 添加冒险策略算法选择
        String[] strategies = {"Strategy 1", "Strategy 2", "Strategy 3"};
        strategyComboBox = new JComboBox<>(strategies);
        add(new JLabel("Select Strategy:"));
        add(strategyComboBox);

        // 进入地图按钮
        enterMapButton = new JButton("Enter Map");
        add(enterMapButton);
        enterMapButton.addActionListener(e -> {
            TeamConfig.this.setVisible(false); // 隐藏当前窗口
            try {
                new gui.Map(); // 假设 Map 是另一个窗口
            } catch (Exception ex) {
                ex.printStackTrace(); // 打印错误信息
                JOptionPane.showMessageDialog(TeamConfig.this, "Failed to open the map due to an error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                TeamConfig.this.setVisible(true); // 如果地图打开失败，重新显示配置窗口
            }
        });

        setVisible(true);
    }

    private void updateDisplay() {
        budgetLabel.setText("Budget: " + budget + " | Team Members: " + teamMembers + " | Health Potions: " + potions + " | Weapon: " + (hasWeapon ? "Yes" : "No") + " | Armor: " + (hasArmor ? "Yes" : "No"));
    }

    public static void main(String[] args) {
        new TeamConfig();
    }
}
