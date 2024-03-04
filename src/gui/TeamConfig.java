package gui; // 修改为您的实际包名

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeamConfig extends JFrame {
    public TeamConfig() {
        setTitle("Configure Your Team");
        setSize(400, 300); // 根据需要调整大小
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 在这里添加配置队伍的界面元素
        // 例如，使用JButton, JLabel, JTextField等。
        
        setVisible(true);
    }
}
