package gui;

import utils.*;
import utils.entities.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import status.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Map extends JFrame {
    private static final long serialVersionUID = 1L;
    private final int MAP_WIDTH = 20;
    private final int MAP_HEIGHT = 20;
    private final int BLOCK_WIDTH = 40;
    private final int BLOCK_HEIGHT = 40;
    private MapPanel mapPanel;
    private boolean gameEnded = false;
    private java.util.List<Bear> bears;
    private java.util.List<Fox> foxes;
    private java.util.List<Tiger> tigers;
    private Dragon dragon;
    private TeamStatusDisplay teamStatusDisplay; 

    public Map() throws IOException {
        setTitle("Treasure Hunter");
        mapPanel = new MapPanel();
        setSize(MAP_WIDTH * BLOCK_WIDTH, MAP_HEIGHT * BLOCK_HEIGHT + 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addKeyListener(new KeyMonitor());
        add(mapPanel);
        setVisible(true);
    }

    private class MapPanel extends JPanel {
        private Integer[][] maps = MapGenerator.generateMap(); // 使用 MapGenerator 生成地图
        private Point[] adventurers; // 存储冒险者的位置
        private Image playerImage;
        private Image[] terrainImages = new Image[7];

        public MapPanel() throws IOException {
            loadImages(); // 加载所有图像
            loadEntities(); // 加载所有实体
            initializeAdventurers(TeamConfig.getAdventurersCount()); // 根据 TeamConfig 初始化冒险者位置
        }

        private void loadImages() throws IOException {
            playerImage = ImageIO.read(new File("src/image/character.png"));
            terrainImages[MapElement.GRASS.getValue() - 1] = ImageIO.read(new File("src/image/grass.png"));
            terrainImages[MapElement.FOREST.getValue() - 1] = ImageIO.read(new File("src/image/forest.png"));
            terrainImages[MapElement.BRIDGE.getValue() - 1] = ImageIO.read(new File("src/image/bridge.png"));
            terrainImages[MapElement.RIVER.getValue() - 1] = ImageIO.read(new File("src/image/river.png"));
            terrainImages[MapElement.MOUNTAIN.getValue() - 1] = ImageIO.read(new File("src/image/mountain.png"));
            terrainImages[MapElement.MARSHLAND.getValue() - 1] = ImageIO.read(new File("src/image/marshland.png"));
            terrainImages[MapElement.TREASURE.getValue() - 1] = ImageIO.read(new File("src/image/treasure.png"));
        }
        private void loadEntities() throws IOException {
            bears = EntityLoader.loadBears(5); // Load 5 bears as an example
            foxes = EntityLoader.loadFoxes(3); // Load 3 foxes
            tigers = EntityLoader.loadTigers(2); // Load 2 tigers
            dragon = EntityLoader.loadDragon(); // Load the dragon
        }
        private void initializeAdventurers(int count) {
            adventurers = new Point[count];
            for (int i = 0; i < count; i++) {
                adventurers[i] = new Point(0, MAP_HEIGHT - 1); // 假设所有冒险者开始时都位于地图左下角
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < MAP_HEIGHT; i++) {
                for (int j = 0; j < MAP_WIDTH; j++) {
                    int terrainIndex = maps[i][j] - 1;
                    g.drawImage(terrainImages[terrainIndex], j * BLOCK_WIDTH, i * BLOCK_HEIGHT, this);
                }
            }
            for (Point point : adventurers) {
                g.drawImage(playerImage, point.x * BLOCK_WIDTH, point.y * BLOCK_HEIGHT, this);
            }
            // Draw all bears
            for (Bear bear : bears) {
                g.drawImage(bear.getImage(), bear.getPosition().x * BLOCK_WIDTH, bear.getPosition().y * BLOCK_HEIGHT + 30, this);
            }
            // Draw all foxes
            for (Fox fox : foxes) {
                g.drawImage(fox.getImage(), fox.getPosition().x * BLOCK_WIDTH, fox.getPosition().y * BLOCK_HEIGHT + 30, this);
            }
            // Draw all tigers
            for (Tiger tiger : tigers) {
                g.drawImage(tiger.getImage(), tiger.getPosition().x * BLOCK_WIDTH, tiger.getPosition().y * BLOCK_HEIGHT + 30, this);
            }
            if (dragon != null) {
                g.drawImage(dragon.getImage(), dragon.getPosition().x * BLOCK_WIDTH, dragon.getPosition().y * BLOCK_HEIGHT, this);
            }
        }

        public void updateAdventurerPosition(int index, int newX, int newY) {
            if (index >= 0 && index < adventurers.length) {
                adventurers[index].move(newX, newY);
            }
        }

        public Integer[][] getMaps() {
            return maps;
        }
    }
    public void checkCollisions() {
        for (CharacterStatus adventurer : TeamStatus.getMembers()) { // 确保你是从正确的实例或类访问成员列表
            for (Tiger tiger : tigers) {
                if (adventurer.getPosition().equals(tiger.getPosition())) {
                    int damage = adventurer.hasArmor() ? 10 : 20; // 如果有护甲，损失10生命值，否则20
                    adventurer.setHealth(adventurer.getHealth() - damage);
                    if (adventurer.getHealth() <= 0) {
                        // 处理冒险者生命值降至0或以下的情况
                        JOptionPane.showMessageDialog(null, adventurer.getName() + " has been defeated by a tiger!"); // 确保正确使用JOptionPane
                    }
                }
            }
        }
        // 更新显示，假设teamStatusDisplay是TeamStatusDisplay实例的变量名
        teamStatusDisplay.updateDisplay();
    }


    private class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (gameEnded) return;

            Point current = mapPanel.adventurers[0]; // Get the first adventurer's position
            int newX = current.x, newY = current.y;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP: newY--; break;
                case KeyEvent.VK_DOWN: newY++; break;
                case KeyEvent.VK_LEFT: newX--; break;
                case KeyEvent.VK_RIGHT: newX++; break;
            }
            Integer[][] maps = mapPanel.getMaps();
            if (newX >= 0 && newX < MAP_WIDTH && newY >= 0 && newY < MAP_HEIGHT &&
                maps[newY][newX] != MapElement.MOUNTAIN.getValue() &&
                maps[newY][newX] != MapElement.MARSHLAND.getValue()) {
                mapPanel.updateAdventurerPosition(0, newX, newY);
                if (maps[newY][newX] == MapElement.TREASURE.getValue()) {
                    gameEnded = true;
                    JOptionPane.showMessageDialog(Map.this, "Congratulations! You found the treasure and won the game!");
                }
                mapPanel.repaint();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Map();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
