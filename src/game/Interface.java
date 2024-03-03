package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// 引入地图类
import gui.Map;


public class Interface extends JFrame {
    private JButton startButton;
    private JButton exitButton;

    public Interface() {
        setTitle("Treasure - Hunter");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建背景面板，并设置布局为空布局
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("src/image/Treasure - Hunter.png"); // 请将图片路径替换为实际的背景图片路径
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null); // 设置为空布局，使得按钮的位置可以自由设置

        startButton = new JButton(" ");
        startButton.setBounds(368, 150, 100, 50); // 设置按钮位置和大小
        startButton.setOpaque(false);//隐藏按钮背景
        startButton.setBorderPainted(false); // 隐藏按钮边框
        startButton.setContentAreaFilled(false);//按钮依旧可以点击触发
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 创建地图对象并显示
                    Map map = new Map();
                    map.setVisible(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        exitButton = new JButton("");
        exitButton.setBounds(368, 177, 100, 50); // 设置按钮位置和大小
        exitButton.setOpaque(false);//隐藏按钮背景
        exitButton.setBorderPainted(false); // 隐藏按钮边框
        exitButton.setContentAreaFilled(false);//按钮依旧可以点击触发
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(Interface.this, "Sure you want to quit the game?", "Exit Game", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // 将按钮添加到背景面板中
        backgroundPanel.add(startButton);
        backgroundPanel.add(exitButton);
        // 将背景面板添加到窗口中
        add(backgroundPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interface();
            }
        });
    }
}
