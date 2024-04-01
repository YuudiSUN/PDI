package gui;

import utils.GameRules;
import status.CharacterStatus;
import status.TeamStatus;
import javax.swing.*;
import java.awt.*;
import config.GameConfiguration;
import game.GameSettings;

public class TeamConfig extends JFrame {
    private int budget = GameRules.INITIAL_BUDGET;
    private JLabel budgetLabel;
    private JButton addMemberButton, addPotionButton, enterMapButton, addWeaponButton, addArmorButton;
    private JComboBox<String> strategyComboBox;
    private JComboBox<String> SpeedComboBox;
    private static int teamMembers = 0;
    private int potions = 0;
    private boolean hasWeapon = false, hasArmor = false;
    private TeamStatus teamStatus = new TeamStatus();
    private int nextCharacterId = 1; // Moved this here to keep track properly
    private String selectedStrategy = "Radical";//用于套用策略在map中
    public TeamConfig() {
        setTitle("Configure Your Team");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new FlowLayout());
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5); // 设置组件之间的间距
        gbc.gridx = 0; // Grid X Position
        gbc.gridy = 0; // Grid Y Position
        gbc.weightx = 1; // 水平空间分配权重
        gbc.weighty = 1; // 垂直空间分配权重
        gbc.fill = GridBagConstraints.HORIZONTAL; // 水平填充
        
        gbc.gridwidth = 1; // 占用一列
        
        
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // 如果 Nimbus 不可用，设置成其他 Look and Feel
            try {
                // 设置为 Swing 的默认 Look and Feel
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                // 如果发生错误，打印错误信息
                ex.printStackTrace();
            }
        }
     

        budgetLabel = new JLabel("Budget: " + budget, SwingConstants.CENTER);
//        add(budgetLabel);
        budgetLabel.setFont(new Font("Serif", Font.BOLD, 16));
        budgetLabel.setForeground(new Color(50, 50, 255));
        gbc.gridx = 0; // 第一列
        gbc.gridy = 0; // 第一行
        gbc.gridwidth = GridBagConstraints.REMAINDER; // 让 budgetLabel 占据整行
        gbc.anchor = GridBagConstraints.CENTER; // 组件在其单元格内居中对齐
        gbc.fill = GridBagConstraints.HORIZONTAL; // 水平填充，让 label 横向扩展以填充整行
        
        add(budgetLabel, gbc);
        
        // Initialize and set up action listener for addMemberButton
        addMemberButton = new JButton("Buy Adventurer (" + GameRules.COST_PER_MEMBER + ")");
        gbc.gridx = 0; // 第一列
        gbc.gridy = 1; // 第一行
        gbc.weightx = 0; // 不让按钮随着 budgetLabel 的宽度变化
        gbc.gridwidth = 1; // 每个按钮占据一列
        add(addMemberButton, gbc);
        
        // 加载图像并创建 ImageIcon 对象
        ImageIcon memberIcon = new ImageIcon(getClass().getResource("/image/adventurer_icon.png"));
        // 为 addWeaponButton 设置图标
        addMemberButton.setIcon(memberIcon);
        
        addMemberButton.addActionListener(e -> {
            if (budget >= GameRules.COST_PER_MEMBER) {
                budget -= GameRules.COST_PER_MEMBER;
                CharacterStatus newMember = new CharacterStatus("Hunter" + nextCharacterId++, 100, hasWeapon, hasArmor, null);
                teamStatus.addMember(newMember);
                teamMembers++;
                updateDisplay();
            } else {
                JOptionPane.showMessageDialog(TeamConfig.this, "Not enough money!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        
        addPotionButton = new JButton("Buy Health Potion (" + GameRules.COST_PER_POTION + ")");
        ImageIcon potionButton = new ImageIcon(getClass().getResource("/image/HP_icon.png"));
        addPotionButton.setIcon(potionButton);
        
        gbc.gridx = 1; 
        gbc.gridy = 1; // 第一行
        add(addPotionButton, gbc);
        addPotionButton.addActionListener(e -> {
            if (budget >= GameRules.COST_PER_POTION) {
                budget -= GameRules.COST_PER_POTION;
                potions++;
                updateDisplay();
            } else {
                JOptionPane.showMessageDialog(TeamConfig.this, "Not enough money!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

     
        addWeaponButton = new JButton("Buy Weapon (100)");
        ImageIcon weaponButton = new ImageIcon(getClass().getResource("/image/weapon_icon.png"));
        addWeaponButton.setIcon(weaponButton);
        
        gbc.gridx = 2; 
        gbc.gridy = 1; // 第一行
        add(addWeaponButton, gbc);
        addWeaponButton.addActionListener(e -> {
            if (budget >= GameRules.COST_PER_MEMBER) { // Use the right constant if different
                budget -= GameRules.COST_PER_MEMBER;
                hasWeapon = true;
                teamStatus.getMembers().forEach(member -> member.setHasWeapon(true));
                updateDisplay();
                addWeaponButton.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(TeamConfig.this, "Not enough money!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        
        addArmorButton = new JButton("Buy Armor (100)");
        ImageIcon armorButton = new ImageIcon(getClass().getResource("/image/armor_icon.png"));
        addArmorButton.setIcon(armorButton);
        
        gbc.gridx = 3; 
        gbc.gridy = 1; // 第一行
        add(addArmorButton, gbc);
        addArmorButton.addActionListener(e -> {
            if (budget >= GameRules.COST_PER_MEMBER) { // Use the right constant if different
                budget -= GameRules.COST_PER_MEMBER;
                hasArmor = true;
                teamStatus.getMembers().forEach(member -> member.setHasArmor(true));
                updateDisplay();
                addArmorButton.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(TeamConfig.this, "Not enough money!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

     // 对 "Select Strategy:" 标签和 strategyComboBox 的设置
        gbc.gridx = 0; // 第一列
        gbc.gridy = 2; 
        gbc.gridwidth = 1; // 占用一列宽度
        gbc.anchor = GridBagConstraints.CENTER; // 将组件置于格局中心
        add(new JLabel("Select Strategy:", SwingConstants.CENTER), gbc);
        
        strategyComboBox = new JComboBox<>(new String[]{"Radical", "Conservative", "Random"});
        // add(new JLabel("Select Strategy:"));
        strategyComboBox.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1; 
        gbc.gridy = 2; // 第二行
        add(strategyComboBox, gbc);
     // 与gamesettings相互应
        strategyComboBox.addActionListener(e -> {
            String selectedStrategy = (String) strategyComboBox.getSelectedItem();
            GameSettings.getInstance().setCurrentStrategy(selectedStrategy);
        });

        gbc.gridx = 2; // 第一列
        gbc.gridy = 2; // 下一行
        gbc.gridwidth = 1; // 占用一列宽度
        gbc.anchor = GridBagConstraints.CENTER; // 将组件置于格局中心
        add(new JLabel("GameSpeed:", SwingConstants.CENTER), gbc);
        
        // GameSpeed
        SpeedComboBox = new JComboBox<>(new String[]{"High Speed", "Normal", "Low Speed"});
        // add(new JLabel("GameSpeed:"));
        add(SpeedComboBox);
        gbc.gridx = 3;
        gbc.gridy = 2; // 第二行
        add(SpeedComboBox, gbc);
        SpeedComboBox.setPreferredSize(new Dimension(200, 25));

        SpeedComboBox.addActionListener(e -> {
            String selectedSpeed = (String) SpeedComboBox.getSelectedItem();
            switch (selectedSpeed) {
                case "High Speed":
                    GameConfiguration.GAME_SPEED = 300;
                    break;
                case "Normal":
                    GameConfiguration.GAME_SPEED = 1000;
                    break;
                case "Low Speed":
                    GameConfiguration.GAME_SPEED = 2500;
                    break;
                default:
                    GameConfiguration.GAME_SPEED = 1000; // 默认值
            }
        });
        


        enterMapButton = new JButton("Start the expedition!");
        gbc.gridx = 1;
        gbc.gridy = 3; 
        gbc.gridwidth = 2; 
        add(enterMapButton, gbc);
        enterMapButton.addActionListener(e -> {
            setVisible(false);
            try {
                new gui.Map(); // This should correctly reference your map class
                new status.TeamStatusDisplay(teamStatus).setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to open the map due to an error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                setVisible(true);
            }
        });
        enterMapButton.setFont(new Font("Serif", Font.BOLD, 16));
        enterMapButton.setForeground(Color.RED);

        setVisible(true);
    }

    private void updateDisplay() {	
        budgetLabel.setText("Budget: " + budget + " | Team Members: " + teamMembers + " | Health Potions: " + potions +
                            " | Weapon: " + (hasWeapon ? "Yes" : "No") + " | Armor: " + (hasArmor ? "Yes" : "No"));
    }

    public static void main(String[] args) {
        new TeamConfig();
    }

	public static int getAdventurersCount() {
		// TODO Auto-generated method stub
		return teamMembers;
	}
}
