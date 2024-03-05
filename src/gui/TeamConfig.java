package gui; // 修改为您的实际包名

import utils.GameRules;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeamConfig extends JFrame {
    private int budget = GameRules.INITIAL_BUDGET;
    private JLabel budgetLabel;
    private JButton addMemberButton, addPotionButton, enterMapButton;
    private int teamMembers = 0;
    private int potions = 0;

    public TeamConfig() {
        setTitle("Configure Your Team");
        setSize(500, 400); // 根据需要调整大小
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout()); // 设置布局管理器

        // 显示预算
        budgetLabel = new JLabel("Budget: " + budget);
        add(budgetLabel);

        // 添加队员按钮
        addMemberButton = new JButton("Buy Adventurer (" + GameRules.COST_PER_MEMBER + ")");
        add(addMemberButton);
        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (budget >= GameRules.COST_PER_MEMBER) {
                    budget -= GameRules.COST_PER_MEMBER;
                    teamMembers++;
                    updateDisplay();
                } else {
                    JOptionPane.showMessageDialog(TeamConfig.this, "Not enough money!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 添加血瓶按钮
        addPotionButton = new JButton("Buy Health Potion (" + GameRules.COST_PER_POTION + ")");
        add(addPotionButton);
        addPotionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (budget >= GameRules.COST_PER_POTION) {
                    budget -= GameRules.COST_PER_POTION;
                    potions++;
                    updateDisplay();
                } else {
                    JOptionPane.showMessageDialog(TeamConfig.this, "Not enough money!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 进入地图按钮
        enterMapButton = new JButton("Enter Map");
        add(enterMapButton);
        enterMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TeamConfig.this.setVisible(false); // 隐藏当前窗口
                try {
                    new gui.Map(); // 假设 Map 是另一个窗口
                } catch (Exception ex) {
                    ex.printStackTrace(); // 打印错误信息
                    JOptionPane.showMessageDialog(TeamConfig.this, 
                        "Failed to open the map due to an error: " + ex.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    TeamConfig.this.setVisible(true); // 如果地图打开失败，重新显示配置窗口
                }
            }
        });

        setVisible(true);
    }

    private void updateDisplay() {
        budgetLabel.setText("Budget: " + budget + " | Team Members: " + teamMembers + " | Health Potions: " + potions);
    }

    public static void main(String[] args) {
        new TeamConfig();
    }
}
