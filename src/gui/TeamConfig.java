package gui;

import utils.GameRules;
import status.CharacterStatus;
import status.TeamStatus;
import javax.swing.*;
import java.awt.*;
import config.GameConfiguration;

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

    public TeamConfig() {
        setTitle("Configure Your Team");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        budgetLabel = new JLabel("Budget: " + budget);
        add(budgetLabel);

        // Initialize and set up action listener for addMemberButton
        addMemberButton = new JButton("Buy Adventurer (" + GameRules.COST_PER_MEMBER + ")");
        add(addMemberButton);
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

        addWeaponButton = new JButton("Buy Weapon (100)");
        add(addWeaponButton);
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
        add(addArmorButton);
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

        strategyComboBox = new JComboBox<>(new String[]{"Radicalisation", "Conservative"});
        add(new JLabel("Select Strategy:"));
        add(strategyComboBox);
        
        
        // GameSpeed
        SpeedComboBox = new JComboBox<>(new String[]{"High Speed", "Normal", "Low Speed"});
        add(new JLabel("GameSpeed:"));
        add(SpeedComboBox);
        
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
        
        enterMapButton = new JButton("Enter Map");
        add(enterMapButton);
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
