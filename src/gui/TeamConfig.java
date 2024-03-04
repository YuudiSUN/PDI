package gui; // 修改为您的实际包名

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeamConfig extends JFrame {
    private JButton enterMapButton; // 定义一个按钮

    public TeamConfig() {
        setTitle("Configure Your Team");
        setSize(400, 300); // 根据需要调整大小
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout()); // 设置布局管理器，确保按钮可以显示

        // 初始化按钮并添加到界面
        enterMapButton = new JButton("Enter Map");
        add(enterMapButton);

        // 为按钮添加动作监听器
        enterMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 当按钮被点击时执行的操作
                // 例如：隐藏当前窗口并打开地图界面
                TeamConfig.this.setVisible(false); // 隐藏当前窗口
                // 这里应该是打开地图的代码，但如果Map构造器抛出异常，需要处理
                // 例如，创建并显示一个Map窗口
                try {
                    new gui.Map(); // 假设Map是另一个窗口，需要正确处理异常
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
}
