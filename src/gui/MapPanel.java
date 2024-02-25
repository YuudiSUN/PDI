package gui;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    private static final int ROWS = 10;
    private static final int COLUMNS = 10;
    private static final int CELL_SIZE = 30; // 你可以调整方格的大小

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        // 绘制方格
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;
    
                g.fillRect(x, y, CELL_SIZE, CELL_SIZE);  // 使用 fillRect 填充整个方格
            }
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Map GUI");
            MapPanel mapPanel = new MapPanel();

            frame.add(mapPanel);
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
