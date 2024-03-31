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
    private Image forestImage;
    private Image mountainImage;
    private Image marshlandImage;
    private Image riverImage;
    private Image treasureImage;

    public MapPanel(GameMap gameMap) {
        this.gameMap = gameMap;
        loadImages();
    }


	private void loadImages() {
        // 加载草地图像，实际应用中应该处理异常
        grassImage = new ImageIcon("src/images/grass.png").getImage();
        // 加载地形图片，确保路径正确
        forestImage = new ImageIcon("src/images/forest.png").getImage();
        mountainImage = new ImageIcon("src/images/mountain.png").getImage();
        marshlandImage = new ImageIcon("src/images/marshland.png").getImage();
        riverImage = new ImageIcon("src/images/river.png").getImage();
        treasureImage = new ImageIcon("src/images/treasure.png").getImage();
    }

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    for (int y = 0; y < gameMap.getHeight(); y++) {
	        for (int x = 0; x < gameMap.getWidth(); x++) {
	            Cell cell = gameMap.getCell(x, y);
	            Image image = getImageForTerrain(cell.getTerrain());
	            g.drawImage(image, x * CELL_WIDTH, y * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, this);
	        }
	    }
	}

	private Image getImageForTerrain(Cell.TerrainType terrain) {
	    switch (terrain) {
	        case GRASS:
	            return grassImage;
	        case MOUNTAIN:
	            return mountainImage;
	        case SWAMP:
	            return marshlandImage;
	        case FOREST:
	            return forestImage;
	        case RIVER:
	            return riverImage;
	        case TREASURE:
	        	return treasureImage;
	        default:
	        	return grassImage;
	    }
	}


}
