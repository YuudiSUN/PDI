package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MapPanel extends JPanel {

    private static final int GRID_WIDTH = 12; // 调整网格宽度
    private static final int GRID_HEIGHT = 8; // 调整网格高度
    private int cellSize;

    private boolean[][] obstacles;

    public MapPanel() {
        // 不要在这里初始化地图
    }

    private void initializeMap() {
        obstacles = new boolean[GRID_HEIGHT][GRID_WIDTH];

        // 随机放置障碍物
        Random random = new Random();
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                obstacles[i][j] = random.nextBoolean();
            }
        }

        // 计算绘制时的每个单元格的大小
        recalculateCellSize();
    }

    private void recalculateCellSize() {
        int width = getWidth();
        int height = getHeight();

        // 计算绘制时的每个单元格的大小
        cellSize = Math.min(width / GRID_WIDTH, height / GRID_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (obstacles == null) {
            initializeMap();
        }

        drawMap(g);
    }

    private void drawMap(Graphics g) {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (obstacles[i][j]) {
                    g.setColor(Color.BLACK); // 障碍物颜色
                } else {
                    g.setColor(Color.WHITE); // 空地颜色
                }

                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);

                g.setColor(Color.GRAY); // 网格线颜色
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // 设置首选大小，确保网格可以适应面板
        return new Dimension(600, 400);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Map Panel");
            MapPanel mapPanel = new MapPanel();

            frame.add(mapPanel);
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
