package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import map.GameMap;
import map.Cell; // 导入Cell类

public class MapPanel extends JPanel {
    private static final int CELL_WIDTH = 32; // 假设每个单元格宽度为32像素
    private static final int CELL_HEIGHT = 32; // 假设每个单元格高度为32像素

    private GameMap gameMap;
    private Image grassImage;

    public MapPanel(GameMap gameMap) {
        this.gameMap = gameMap;
        loadImages();
    }


	private void loadImages() {
        // 加载草地图像，实际应用中应该处理异常
        grassImage = new ImageIcon("src/images/grass.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < gameMap.getHeight(); i++) {
            for (int j = 0; j < gameMap.getWidth(); j++) {
                Cell cell = gameMap.getCell(j, i);
                if (cell.getTerrain().equals("grass")) {
                    g.drawImage(grassImage, j * CELL_WIDTH, i * CELL_HEIGHT, this);
                }
                // 如果Cell有其他地形或实体，这里可以添加更多的绘制逻辑
            }
        }
    }
}
