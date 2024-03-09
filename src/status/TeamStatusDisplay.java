package status;

import javax.swing.*;
import java.awt.*;

public class TeamStatusDisplay extends JFrame {
    private TeamStatus teamStatus; 

    public TeamStatusDisplay(TeamStatus status) {
        this.teamStatus = status;
        setTitle("Team Status");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // 使用边界布局

        // 初始化并展示队伍状态
        initializeDisplay();
    }

    private void initializeDisplay() {
        JPanel statusPanel = new JPanel(new GridLayout(0, 1)); // 使用网格布局
        if (teamStatus.getMembers().isEmpty()) {
            statusPanel.add(new JLabel("No team members yet."));
        } else {
            for (CharacterStatus member : teamStatus.getMembers()) {
                // 为每个队伍成员创建一个标签，包括名字、健康状况、武器和护甲
                JLabel memberLabel = new JLabel(member.getName() + " - Health: " + member.getHealth() +
                                                ", Weapon: " + (member.hasWeapon() ? "Yes" : "No") +
                                                ", Armor: " + (member.hasArmor() ? "Yes" : "No"));
                statusPanel.add(memberLabel);
            }
        }
        JScrollPane scrollPane = new JScrollPane(statusPanel); // 添加滚动条
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER); // 将面板添加到窗口中
    }
}
