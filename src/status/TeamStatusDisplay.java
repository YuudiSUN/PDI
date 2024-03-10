package status;

import javax.swing.*;
import java.awt.*;

public class TeamStatusDisplay extends JFrame {
    private TeamStatus teamStatus;
    private JPanel statusPanel; // 将状态面板设为类成员变量，以便可以更新它

    public TeamStatusDisplay(TeamStatus status) {
        this.teamStatus = status;
        setTitle("Team Status");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // 使用边界布局

        statusPanel = new JPanel(new GridLayout(0, 1)); // 初始化状态面板
        JScrollPane scrollPane = new JScrollPane(statusPanel); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER); // 将面板添加到窗口中

        updateDisplay(); // 使用updateDisplay方法初始化显示
    }

    public void updateDisplay() {
        statusPanel.removeAll(); // 移除所有现有组件

        if (teamStatus.getMembers().isEmpty()) {
            statusPanel.add(new JLabel("No team members yet."));
        } else {
            for (CharacterStatus member : teamStatus.getMembers()) {
                JLabel memberLabel = new JLabel(member.getName() + " - Health: " + member.getHealth() +
                                                ", Weapon: " + (member.hasWeapon() ? "Yes" : "No") +
                                                ", Armor: " + (member.hasArmor() ? "Yes" : "No"));
                statusPanel.add(memberLabel);
            }
        }
        statusPanel.revalidate(); // 重新验证组件
        statusPanel.repaint(); // 重绘组件
    }
}
