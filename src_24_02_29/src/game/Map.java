package game;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

//Map.class
public class Map {

 // Attributes
 private int width = 40; 
 private int height = 40;
 private int[][] grid; // 表示地图的每个单元格的内容，用一个二维数组表示。0表示空地，1表示障碍，2表示森林，3表示野生动物，4表示宝藏，5表示探险家。
 private List<Animal> animals; 
 private List<Explorer> explorers; 
 private List<Point> treasures; 
 private int treasureNum = 3; 

 // Constructor
 public Map(int width, int height) {
     this.width = width;
     this.height = height;
     grid = new int[width][height];
     animals = new ArrayList<>();
     explorers = new ArrayList<>();
     treasures = new ArrayList<>();
     generateMap(); 
 }

 // Methods
// // 地图的更新方法，用于根据探险家的移动和战斗更新地图的网格和状态
// public void updateGrid(int x1, int y1, int x2, int y2) {
//     // 交换两个单元格的内容
//     int temp = grid[x1][y1];
//     grid[x1][y1] = grid[x2][y2];
//     grid[x2][y2] = temp;
// }

 // 判断游戏是否结束的方法
 public boolean isOver() {
     if (treasureNum == 0) {
         return true;
     }
     if (explorers.size() == 0) {
         return true;
     }
     return false;
 }

 // 生成地图的方法,随机放置障碍、森林、野生动物和宝藏
 public void generateMap() {
     // 随机放置障碍，占地图的10%
     int obstacleNum = (int) (width * height * 0.1);
     for (int i = 0; i < obstacleNum; i++) {
         // 随机生成一个坐标
         int x = (int) (Math.random() * width);
         int y = (int) (Math.random() * height);
         // 如果该坐标是空地，就放置一个障碍
         if (grid[x][y] == 0) {
             grid[x][y] = 1;
         }
     }
     // 随机放置森林，占地图的20%
     int forestNum = (int) (width * height * 0.2);
     for (int i = 0; i < forestNum; i++) {
         // 随机生成一个坐标
         int x = (int) (Math.random() * width);
         int y = (int) (Math.random() * height);
         // 如果该坐标是空地，就放置一个森林
         if (grid[x][y] == 0) {
             grid[x][y] = 2;
         }
     }
     // 随机放置野生动物，占地图的5%
     int animalNum = (int) (width * height * 0.05);
     for (int i = 0; i < animalNum; i++) {
         // 随机生成一个坐标
         int x = (int) (Math.random() * width);
         int y = (int) (Math.random() * height);
         // 如果该坐标是空地，就放置一个野生动物
         if (grid[x][y] == 0) {
             grid[x][y] = 3;
             // 创建一个野生动物对象，并添加到列表中
             Animal animal = new Animal(x, y, 50, 20);
             animals.add(animal);
         }
     }
     // 随机放置宝藏，数量为treasureNum
     for (int i = 0; i < treasureNum; i++) {
         // 随机生成一个坐标
         int x = (int) (Math.random() * width);
         int y = (int) (Math.random() * height);
         // 如果该坐标是空地，就放置一个宝藏
         if (grid[x][y] == 0) {
             grid[x][y] = 4;
             // 创建一个坐标对象，并添加到列表中
             Point treasure = new Point(x, y);
             treasures.add(treasure);
         }
     }
 }

 // 展示地图的方法
 public void showMap() {
     // 遍历地图的每个单元格
     for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
             // 根据单元格的内容，打印不同的符号
             switch (grid[i][j]) {
                 case 0:
                     // 空地，打印空格
                     System.out.print(" ");
                     break;
                 case 1:
                     // 障碍，打印#
                     System.out.print("#");
                     break;
                 case 2:
                     // 森林，打印*
                     System.out.print("*");
                     break;
                 case 3:
                     // 野生动物，打印A
                     System.out.print("A");
                     break;
                 case 4:
                     // 宝藏，打印T
                     System.out.print("T");
                     break;
                 case 5:
                     // 探险家，打印E
                     System.out.print("E");
                     break;
             }
         }
         // 换行
         System.out.println();
     }
 }

 // 设置地图上宝藏数量的方法
 public void setTreasureNum(int treasureNum) {
     this.treasureNum = treasureNum;
 }

 // 获取地图的宽度的方法
 public int getWidth() {
     return width;
 }

 // 获取地图的高度的方法
 public int getHeight() {
     return height;
 }

 // 获取地图的网格的方法
 public int[][] getGrid() {
     return grid;
 }

 // 获取地图上所有野生动物的列表的方法
 public List<Animal> getAnimals() {
     return animals;
 }

 // 获取地图上所有探险家的列表的方法
 public List<Explorer> getExplorers() {
     return explorers;
 }

 // 获取所有宝藏坐标的列表的方法
 public List<Point> getTreasures() {
     return treasures;
 }

 // 获取地图上宝藏的数量的方法
 public int getTreasureNum() {
     return treasureNum;
 }

 // 判断是否可以移动到指定的坐标的方法，如果该坐标是空地或森林，就返回true，否则返回false
 public boolean canMove(int x, int y) {
     // 判断坐标是否越界
     if (x < 0 || x >= width || y < 0 || y >= height) {
         return false;
     }
     // 判断坐标是否是空地或森林
     if (grid[x][y] == 0 || grid[x][y] == 2) {
         return true;
     }
     // 否则，不能移动
     return false;
 }

//继续上一段代码
 // 获取探险家周围n*n的地图信息的方法，返回一个二维数组
 public int[][] getGridInfo(int x, int y, int n) {
     // 创建一个n*n的二维数组
     int[][] info = new int[n][n];
     // 遍历二维数组的每个元素
     for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
             // 计算对应的地图坐标
             int mx = x - n / 2 + i;
             int my = y - n / 2 + j;
             // 判断坐标是否越界
             if (mx < 0 || mx >= width || my < 0 || my >= height) {
                 // 如果越界，就赋值为-1
                 info[i][j] = -1;
             } else {
                 // 如果不越界，就赋值为地图的内容
                 info[i][j] = grid[mx][my];
             }
         }
     }
     // 返回二维数组
     return info;
 }

 // 通知其他探险家宝藏的坐标的方法，用一个二维数组表示宝藏的坐标和发现者的坐标
 public void notifyTreasure(int x, int y, int tx, int ty) {
     // 创建一个2x2的二维数组
     int[][] treasure = new int[2][2];
     // 第一行存放宝藏的坐标
     treasure[0][0] = tx;
     treasure[0][1] = ty;
     // 第二行存放发现者的坐标
     treasure[1][0] = x;
     treasure[1][1] = y;
     // 遍历所有探险家
     for (Explorer explorer : explorers) {
         // 如果不是发现者本人，就通知他宝藏的坐标
         if (explorer.getX() != x || explorer.getY() != y) {
             explorer.setTreasure(treasure);
         }
     }
 }

 // 获取其他探险家通知的宝藏的坐标的方法，如果有，就返回一个坐标对象，否则返回null
 public Point getTreasure(int x, int y) {
     // 遍历所有探险家
     for (Explorer explorer : explorers) {
         // 如果不是自己，就获取他的宝藏信息
         if (explorer.getX() != x || explorer.getY() != y) {
             int[][] treasure = explorer.getTreasure();
             // 如果宝藏信息不为空，就判断是否是自己在的
             if (treasure != null) {
                 // 如果宝藏的坐标和自己的坐标不同，就表示自己还没有到达
                 if (treasure[0][0] != x || treasure[0][1] != y) {
                     // 返回一个坐标对象
                     return new Point(treasure[0][0], treasure[0][1]);
                 }
             }
         }
     }
     // 如果没有找到宝藏信息，就返回null
     return null;
 }

 // 杀死野生动物的方法，传入一个野生动物对象
 public void killAnimal(Animal animal) {
     // 获取野生动物的坐标
     int x = animal.getX();
     int y = animal.getY();
     // 把地图的网格改为空地
     grid[x][y] = 0;
     // 从列表中移除野生动物对象
     animals.remove(animal);
 }

 // 获取宝藏的方法，传入一个探险家对象
 public void getTreasure(Explorer explorer) {
     // 获取探险家的坐标
     int x = explorer.getX();
     int y = explorer.getY();
     // 遍历所有宝藏坐标
     for (Point treasure : treasures) {
         // 如果宝藏的坐标和探险家的坐标相同，就表示探险家到达了宝藏
         if (treasure.x == x && treasure.y == y) {
             // 把地图的网格改为空地
             grid[x][y] = 0;
             // 从列表中移除宝藏坐标
             treasures.remove(treasure);
             // 宝藏的数量减一
             treasureNum--;
             // 跳出循环
             break;
         }
     }
 }

 // 添加探险家的方法，传入一个探险家对象
 public void addExplorer(Explorer explorer) {
     // 获取探险家的坐标
     int x = explorer.getX();
     int y = explorer.getY();
     // 把地图的网格改为探险家
     grid[x][y] = 5;
     // 把探险家对象添加到列表中
     explorers.add(explorer);
 }

 // 移除探险家的方法，传入一个探险家对象
 public void removeExplorer(Explorer explorer) {
     // 获取探险家的坐标
     int x = explorer.getX();
     int y = explorer.getY();
     // 把地图的网格改为空地
     grid[x][y] = 0;
     // 把探险家对象从列表中移除
     explorers.remove(explorer);
 }
}
