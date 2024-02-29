package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import game.Explorer;

public class MapPanel extends JPanel {

    private static final int GRID_WIDTH = 12; // Map width
    private static final int GRID_HEIGHT = 8; // Map Height
    private int cellSize;
    private boolean[][] obstacles;
    private Explorer explorer; // 添加对 Explorer 的引用

    public MapPanel() {
       
    }

    private void initializeMap() {
        obstacles = new boolean[GRID_HEIGHT][GRID_WIDTH];

        // Randomly placed obstacles
        Random random = new Random();
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                obstacles[i][j] = random.nextDouble()< 0.2;
            }
        }
        placeExplorer(); 
        recalculateCellSize();
    }
    
    private void placeExplorer() {
        // 检查原点是否有障碍物
        if (obstacles[0][0]) {
            // 在原点周边寻找空位
            for (int i = 0; i < GRID_HEIGHT; i++) {
                for (int j = 0; j < GRID_WIDTH; j++) {
                    if (!obstacles[i][j]) {
                        explorer.setPosition(j, i); // 放置 Explorer
                        return;
                    }
                }
            }
        } else {
            explorer.setPosition(0, 0); // 原点没有障碍物，直接放置
        }
    }

    private void recalculateCellSize() {
        int width = getWidth();
        int height = getHeight();

        cellSize = Math.min(width / GRID_WIDTH, height / GRID_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        initializeMap(); // Generate a new random map

        drawMap(g);
    }
    
    private void drawMap(Graphics g) {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (obstacles[i][j]) {
                    g.setColor(Color.BLACK); // Obstacle Colour
                } else {
                    g.setColor(Color.WHITE); // Open space colour
                }
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.GRAY); // Grid line colour
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
        drawExplorer(g); // 绘制 Explorer
    }

    private void drawExplorer(Graphics g) {
        g.setColor(Color.BLUE); // 使用不同的颜色表示 Explorer
        int explorerX = explorer.getPosX() * cellSize;
        int explorerY = explorer.getPosY() * cellSize;
        g.fillRect(explorerX, explorerY, cellSize, cellSize);
    }
    
    @Override
    public Dimension getPreferredSize() {
        // Set the preferred size
        return new Dimension(600, 400);
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Map Panel");
            Explorer explorer = new Explorer("Explorer1", 100, 10, 0, 0); // 创建 Explorer 实例
            MapPanel mapPanel = new MapPanel(new Explorer("Explorer1", 100, 10, 0, 0)); // 正确声明和初始化 mapPanel
            frame.add(mapPanel);
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}