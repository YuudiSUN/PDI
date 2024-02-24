package gui;
import javax.swing.*;

public class BasicGUI extends JFrame {

    public BasicGUI() {
        setTitle("Basic GUI Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // 创建一个按钮
        JButton button = new JButton("Click Me");

        // 将按钮添加到窗口
        add(button);

        setVisible(true);
    }

    public static void main(String[] args) {
        new BasicGUI();
    }
}
