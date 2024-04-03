package gui;

import utils.*;
import config.GameConfiguration;
import game.GameSettings;
import utils.entities.*;
import status.TeamStatusDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;

import status.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;


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
        teamStatusDisplay = new TeamStatusDisplay(new TeamStatus());
        
    }

    private class MapPanel extends JPanel {
        private Integer[][] maps = MapGenerator.generateMap();
        private Point[] adventurers;
        private Image playerImage;
        private Image[] terrainImages = new Image[7];

        public MapPanel() throws IOException {
            loadImages();
            loadEntities(MAP_WIDTH, MAP_HEIGHT);
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
        
     // 假设你的地图的宽度为 mapWidth，高度为 mapHeight
        private void loadEntities(int mapWidth, int mapHeight) throws IOException {
            bears = EntityLoader.loadBears(60, mapWidth, mapHeight);
            foxes = EntityLoader.loadFoxes(3, mapWidth, mapHeight);
            tigers = EntityLoader.loadTigers(2, mapWidth, mapHeight);
            dragon = EntityLoader.loadDragon();
        }


        // 初始化探险者数组Point
        private void initializeAdventurers(int count) {
            adventurers = new Point[count];
            for (int i = 0; i < count; i++) {
                adventurers[i] = new Point(0, MAP_HEIGHT - 1);
            }
        }

        private void startAdventurersMovement() {
            for (int i = 0; i < adventurers.length; i++) {
                final int index = i;
                new Thread(() -> {
                    while (!gameEnded) {
                        moveAdventurer(index);
                        try {
                            Thread.sleep(GameConfiguration.GAME_SPEED); // 每个冒险者的移动间隔
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
//        
//        private void moveAdventurer(int index) {
//            synchronized(this) {
//                Point treasurePosition = findTreasurePosition();
//                Point adventurer = adventurers[index];
//                int tries = 0;
//                if (gameEnded || treasurePosition == null) {
//                    return;
//                }
//
//                // 获取当前策略
//                String currentStrategy = GameSettings.getInstance().getCurrentStrategy();
//
//
//                Point newLocation;
//                switch (currentStrategy) {
//                    case "Radical":
//                        newLocation = game.Strategy.moveTowardsTreasure(adventurer, treasurePosition);
//                        break;
//                    case "Conservative":
//                        newLocation = game.Strategy.stayPut(adventurer);
//                        break;
//                    case "Random":
//                        newLocation = game.Strategy.moveRandomly(adventurer);
//                        break;
//                    default:
//                        newLocation = game.Strategy.moveTowardsTreasure(adventurer, treasurePosition); // 基础的策略
//                }
//
//                int newX = newLocation.x;
//                int newY = newLocation.y;
//                
//                int deltaX = Integer.compare(newLocation.x, adventurer.x);
//                int deltaY = Integer.compare(newLocation.y, adventurer.y);
//                
//                while (tries < 5) { // 在同一位置最多尝试5次
//                    if (Math.random() < 0.5) {
//                        newX += deltaX;
//                        if (isValidMove(newX, newY) && !isOccupied(newX, newY)) {
//                            break; // 移动成功，跳出循环
//                        }
//                        newX -= deltaX; // 移动失败，恢复位置
//                    } else {
//                        newY += deltaY;
//                        if (isValidMove(newX, newY) && !isOccupied(newX, newY)) {
//                            break; // 移动成功，跳出循环
//                        }
//                        newY -= deltaY; // 移动失败，恢复位置
//                    }
//                    tries++; // 增加尝试次数
//                }
//                // 如果尝试了三次仍然无法移动，则随机选择一个新的方向
//                if (tries == 3) {
//                    int[] directions = {0, 1, 2, 3}; // 0: 上, 1: 下, 2: 左, 3: 右
//                    Collections.shuffle(Arrays.asList(directions)); // 随机打乱
//                    for (int dir : directions) {
//                        if (dir == 0) { // 上
//                            newY = adventurer.y - 1;
//                        } else if (dir == 1) { // 下
//                            newY = adventurer.y + 1;
//                        } else if (dir == 2) { // 左
//                            newX = adventurer.x - 1;
//                        } else { // 右
//                            newX = adventurer.x + 1;
//                        }
//                        if (isValidMove(newX, newY) && !isOccupied(newX, newY)) {
//                            break; // 跳出循环
//                        }
//                    }
//                }
//                
//                
//                // 检查新位置是否可用
//                while (!isPositionAvailable(newX, newY)) {
//                    try {
//                        wait(); // 如果位置不可用，等待
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                checkEncounterWithAnimals(newX, newY, index);
//                // 移动到新位置
//                moveToNewPosition(index, newX, newY);
//                notifyAll(); // 通知所有等待的线程位置可能已经变更
//
//                if (newX == treasurePosition.x && newY == treasurePosition.y) {
//                    JOptionPane.showMessageDialog(this, "Congratulations! You found the treasure!");
//                    gameEnded = true; // 游戏结束
//                }
//            }
//            repaint();
//        }

        
        private void checkEncounterWithAnimals(int x, int y, int adventurerIndex) {
            Animal encounteredAnimal = findAnimalAtPosition(x, y);
            if (encounteredAnimal != null) {
                // 假设TeamStatus类有一个方法可以获取指定索引的探险者，并且CharacterStatus有reduceHealth方法
                CharacterStatus adventurer = TeamStatus.getMember(adventurerIndex);
                adventurer.reduceHealth(30); // 探险者损失30点生命值
                removeAnimal(encounteredAnimal); // 从地图上移除动物
                teamStatusDisplay.updateDisplay();
            }
        }
        
        private void removeAnimal(Animal animal) {
            if (animal instanceof Bear) {
                bears.remove(animal);
                System.out.println("kill Bear!");
                
            } else if (animal instanceof Fox) {
                foxes.remove(animal);
                System.out.println("kill Fox!");
            } else if (animal instanceof Tiger) {
                tigers.remove(animal);
                System.out.println("kill tiger!");
            } else if (animal instanceof Dragon) {
                dragon = null;
            }
        }

        private Animal findAnimalAtPosition(int x, int y) {
            for (Bear bear : bears) {
                if (bear.getPosition().x == x && bear.getPosition().y == y) {
                    return bear;
                }
            }
            for (Fox fox : foxes) {
                if (fox.getPosition().x == x && fox.getPosition().y == y) {
                    return fox;
                }
            }
            for (Tiger tiger : tigers) {
                if (tiger.getPosition().x == x && tiger.getPosition().y == y) {
                    return tiger;
                }
            }
            if (dragon != null && dragon.getPosition().x == x && dragon.getPosition().y == y) {
                return dragon;
            }
            return null;
        }

        private synchronized boolean isPositionAvailable(int x, int y) {
            for (Point adventurer : adventurers) {
                if (adventurer.x == x && adventurer.y == y) {
                    return false; // 位置已被占用
                }
            }
            return true; // 位置可用
        }

        
        // 尝试将探险者移动到新位置
        private synchronized void moveToNewPosition(int index, int newX, int newY) {
            while (!isPositionAvailable(newX, newY)) {
                try {
                    wait(); // 等待位置变为可用
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 更新探险者位置
            adventurers[index].setLocation(newX, newY);
            notifyAll(); // 通知其他等待的线程检查位置状态
        }
        
        private void moveAdventurer(int index) {
            Point treasurePosition = findTreasurePosition(); // 获取宝藏位置
            Point adventurer = adventurers[index];
            int newX = adventurer.x, newY = adventurer.y;
            int tries = 0; // 记录尝试移动的次数
            if (gameEnded) {
                return; // 如果游戏已经结束，则立即返回
            }
            if (treasurePosition != null) {
                // 计算朝着宝藏方向的增量
                int deltaX = Integer.compare(treasurePosition.x, adventurer.x);
                int deltaY = Integer.compare(treasurePosition.y, adventurer.y);
                // 尝试朝着宝藏方向移动
                while (tries < 3) { // 在同一位置最多尝试三次
                    if (Math.random() < 0.5) {
                        newX += deltaX;
                        if (isValidMove(newX, newY) && isPositionAvailable(newX, newY)) {
                            break; // 移动成功，跳出循环
                        }
                        newX -= deltaX; // 移动失败，恢复位置
                    } else {
                        newY += deltaY;
                        if (isValidMove(newX, newY) && isPositionAvailable(newX, newY)) {
                            break; // 移动成功，跳出循环
                        }
                        newY -= deltaY; // 移动失败，恢复位置
                    }
                    tries++; // 增加尝试次数
                }
                // 如果尝试了三次仍然无法移动，则随机选择一个新的方向
                if (tries == 3) {
                    int[] directions = {0, 1, 2, 3}; // 0: 上, 1: 下, 2: 左, 3: 右
                    Collections.shuffle(Arrays.asList(directions)); // 随机打乱
                    for (int dir : directions) {
                        if (dir == 0) { // 上
                            newY = adventurer.y - 1;
                        } else if (dir == 1) { // 下
                            newY = adventurer.y + 1;
                        } else if (dir == 2) { // 左
                            newX = adventurer.x - 1;
                        } else { // 右
                            newX = adventurer.x + 1;
                        }
                        if (isValidMove(newX, newY) && isPositionAvailable(newX, newY)) {
                            break; // 跳出循环
                        }
                    }
                }
                // 宝藏
                if (newX == treasurePosition.x && newY == treasurePosition.y) {
                    JOptionPane.showMessageDialog(this, "Congratulations! You found the treasure!");
                    treasureFound(); // 调用宝藏找到的方法
                    return; // 游戏结束
                }
                // 移动到新位置
                moveToNewPosition(index, newX, newY);
                // 打印冒险者的移动路径
                System.out.println("adventurer " + index + " move to (" + newX + ", " + newY + ")");
                
                // 检查移动后是否遇到了动物
                checkEncounterWithAnimals(newX, newY, index);
            }
            // 重新绘制地图
            repaint();
        }

        private synchronized void treasureFound() {
            gameEnded = true;
            notifyAll(); // 通知所有等待的线程
        }
        

        private boolean isOccupied(int x, int y) {
            for (Point adventurer : adventurers) {
                int index = 0;
				if (adventurer != adventurers[index] && adventurer.x == x && adventurer.y == y) {
                    return true; // 目标位置已经被其他冒险者占据
                }
            }
            return false;
        }

        private boolean isValidMove(int x, int y) {
            if (x < 0 || x >= MAP_WIDTH || y < 0 || y >= MAP_HEIGHT ||
                maps[y][x] == MapElement.MOUNTAIN.getValue() ||
                maps[y][x] == MapElement.MARSHLAND.getValue()) {
                return false; // 如果移动后越界或遇到障碍物，则移动无效
            }
            return true;
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
                g.drawImage(bear.getImage(), bear.getPosition().x * BLOCK_WIDTH, bear.getPosition().y * BLOCK_HEIGHT, this);
            }
            for (Fox fox : foxes) {
                g.drawImage(fox.getImage(), fox.getPosition().x * BLOCK_WIDTH, fox.getPosition().y * BLOCK_HEIGHT, this);
            }
            for (Tiger tiger : tigers) {
                g.drawImage(tiger.getImage(), tiger.getPosition().x * BLOCK_WIDTH, tiger.getPosition().y * BLOCK_HEIGHT, this);
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
        for (CharacterStatus adventurer : TeamStatus.getMembers()) { 
            for (Tiger tiger : tigers) {
                if (adventurer.getPosition().equals(tiger.getPosition())) {
                    int damage = adventurer.hasArmor() ? 10 : 20; // 如果有护甲，损失10生命值，否则20
                    adventurer.setHealth(adventurer.getHealth() - damage);
                    if (adventurer.getHealth() <= 0) {
                        // 处理冒险者生命值降至0或以下的情况
                        JOptionPane.showMessageDialog(null, adventurer.getName() + " has been defeated by a tiger!"); 
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
