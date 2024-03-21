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
        private Integer[][] maps = MapGenerator.generateMap();
        private Point[] adventurers;
        private Image playerImage;
        private Image[] terrainImages = new Image[7];

        public MapPanel() throws IOException {
            loadImages();
            loadEntities();
            initializeAdventurers(TeamConfig.getAdventurersCount());
            startAdventurersMovement();
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
            bears = EntityLoader.loadBears(5);
            foxes = EntityLoader.loadFoxes(3);
            tigers = EntityLoader.loadTigers(2);
            dragon = EntityLoader.loadDragon();
        }

        private void initializeAdventurers(int count) {
            adventurers = new Point[count];
            for (int i = 0; i < count; i++) {
                adventurers[i] = new Point(0, MAP_HEIGHT - 1);
            }
        }

        private void startAdventurersMovement() {
            new Thread(() -> {
                while (!gameEnded) {
                    moveAdventurers();
                    try {
                        Thread.sleep(100); // 将每次移动的时间间隔调整为0.5秒，可以根据需要进行调整
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


        private void moveAdventurers() {
            Point treasurePosition = findTreasurePosition(); // 获取宝藏位置
            for (Point adventurer : adventurers) {
                int newX = adventurer.x, newY = adventurer.y;
                if (treasurePosition != null) {
                    // 计算朝着宝藏方向的增量
                    int deltaX = Integer.compare(treasurePosition.x, adventurer.x);
                    int deltaY = Integer.compare(treasurePosition.y, adventurer.y);
                    // 根据增量决定移动方向，优先朝着宝藏方向移动
                    if (Math.random() < 0.5) {
                        newX += deltaX;
                        if (newX < 0 || newX >= MAP_WIDTH || maps[newY][newX] == MapElement.MOUNTAIN.getValue() ||
                            maps[newY][newX] == MapElement.MARSHLAND.getValue()) {
                            newX -= deltaX; // 如果移动后越界或遇到障碍物，则取消移动
                        }
                    } else {
                        newY += deltaY;
                        if (newY < 0 || newY >= MAP_HEIGHT || maps[newY][newX] == MapElement.MOUNTAIN.getValue() ||
                            maps[newY][newX] == MapElement.MARSHLAND.getValue()) {
                            newY -= deltaY; // 如果移动后越界或遇到障碍物，则取消移动
                        }
                    }
                    // 如果冒险者到达了宝藏位置，则显示消息
                    if (newX == treasurePosition.x && newY == treasurePosition.y) {
                        JOptionPane.showMessageDialog(this, "Congratulations! You found the treasure!");
                        gameEnded = true; // 游戏结束
                    }
                }
                // 更新冒险者位置
                adventurer.setLocation(newX, newY);
            }
            // 重新绘制地图
            repaint();
        }

        private Point findTreasurePosition() {
            for (int i = 0; i < MAP_HEIGHT; i++) {
                for (int j = 0; j < MAP_WIDTH; j++) {
                    if (maps[i][j] == MapElement.TREASURE.getValue()) {
                        return new Point(j, i);
                    }
                }
            }
            return null; // 如果找不到宝藏，则返回null
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
            for (Bear bear : bears) {
                g.drawImage(bear.getImage(), bear.getPosition().x * BLOCK_WIDTH, bear.getPosition().y * BLOCK_HEIGHT + 30, this);
            }
            for (Fox fox : foxes) {
                g.drawImage(fox.getImage(), fox.getPosition().x * BLOCK_WIDTH, fox.getPosition().y * BLOCK_HEIGHT + 30, this);
            }
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
