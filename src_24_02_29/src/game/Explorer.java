package game;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

// Explorer.class
public class Explorer implements Runnable {

    // Attributes
    private String name;
    private int x, y; 
    private int HP = 100;
    private int attack = 50; 
    private Strategy strategy; 
    private Map map; 
    private List<point> treasure;
    

    // Constructor
    public Explorer(String name, int x, int y, int HP, int attack, Strategy strategy, Map map) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.HP = HP;
        this.attack = attack;
        this.strategy = strategy;
        this.map = map;
    }

    // Methods
    public void move() {
        // Get an 8x8 map of the area around the explorer
        int[][] info = map.getGridInfo(x, y, 8);
        // Judge
        boolean foundTreasure = false;
        // Position of treasurer
        int tx = -1, ty = -1;
        // Parcours
        for (int i = 0; i < info.length; i++) {
            for (int j = 0; j < info[i].length; j++) {
                // If find
                if (info[i][j] == 4) {
                    // Record pos of tre.
                    tx = x - 4 + i;
                    ty = y - 4 + j;
                    // Mark
                    foundTreasure = true;
                    break;
                }
            }
            if (foundTreasure) {
                break;
            }
        }
        // Found
        if (foundTreasure) {
            // Notify and move
            map.notifyTreasure(x, y, tx, ty);
            moveTo(tx, ty);
        } else {
            // 如果没有发现宝藏,选择一个随机的方向进行移动
            moveRandom();
        }
    }
    
    public void setTreasure(int[][] treasure) {
    	// 定义一个空的List<Point>
    	List<Point> list = new ArrayList<>();
    	// 遍历int[][]的每个元素
    	for (int i = 0; i < treasure.length; i++) {
    	    for (int j = 0; j < treasure[i].length; j++) {
    	        // 创建一个Point对象，用int[][]的元素作为坐标
    	        Point point = new Point(treasure[i][j], treasure[i][j]);
    	        // 把Point对象添加到List中
    	        list.add(point);
    	    }
    	}
    	// 把List赋值给treasure属性
    	this.treasure = list;

    }
    
    // 探寻宝藏方法
    public void findTreasure() {
        // 判断是否有其他探险家通知宝藏的坐标
        Point treasure = map.getTreasure(x, y);
        // 如果有
        if (treasure != null) {
            // 往宝藏方向前进
            moveTo(treasure.x, treasure.y);
        }
    }

    // 主要逻辑循环，包括移动和寻宝
    public void run() {
        // 当游戏没有结束，且探险家没有死亡时
        while (!map.isOver() && HP > 0) {
            // 移动
            move();
            // 寻宝
            findTreasure();
        }
    }

    // 与野生动物战斗的方法
    public void fight(Animal animal) {
        // 如果探险家的战略是激进的
        if (strategy == Strategy.AGGRESSIVE) {
            // 如果探险家的攻击力大于或等于野生动物的血量
            if (attack >= animal.getHP()) {
                // 杀死野生动物
                map.killAnimal(animal);
            } else {
                // 否则，探险家掉血
                HP -= animal.getAttack();
            }
        } else {
            // 如果探险家的战略是保守的
            // 避开野生动物，选择一个随机的方向进行移动
            moveRandom();
        }
    }

    // 往指定的坐标移动的方法
    public void moveTo(int tx, int ty) {
        // 计算移动的方向
        int dx = tx - x;
        int dy = ty - y;
        // 如果x坐标不相等
        if (dx != 0) {
            // 如果x坐标需要增加
            if (dx > 0) {
                // 向右移动一个格子
                moveRight();
            } else {
                // 向左移动一个格子
                moveLeft();
            }
        } else {
            // 如果x坐标相等, 如果y坐标不相等
            if (dy != 0) {
                // 如果y坐标需要增加
                if (dy > 0) {
                    // 向下移动一个格子
                    moveDown();
                } else {
                    // 如果y坐标需要减少, 向上移动一个格子
                    moveUp();
                }
            }
        }
    }

    // 选择一个随机的方向进行移动的方法
    public void moveRandom() {
        // 生成一个0到3的随机数
        int r = (int) (Math.random() * 4);
        // 根据随机数选择一个方向
        switch (r) {
            case 0:
                // 向上移动
                moveUp();
                break;
            case 1:
                // 向下移动
                moveDown();
                break;
            case 2:
                // 向左移动
                moveLeft();
                break;
            case 3:
                // 向右移动
                moveRight();
                break;
        }
    }

    // 向上移动一个格子的方法
    public void moveUp() {
        // 判断是否可以向上移动
        if (map.canMove(x, y - 1)) {
            // 更新地图的网格
            map.updateGrid(x, y, x, y - 1);
            // 更新探险家的坐标
            y--;
            // 停顿0.5秒
            pause(500);
        }
    }

    // 向下移动一个格子的方法
    public void moveDown() {
        // 判断是否可以向下移动
        if (map.canMove(x, y + 1)) {
            // 更新地图的网格
            map.updateGrid(x, y, x, y + 1);
            // 更新探险家的坐标
            y++;
            // 停顿0.5秒
            pause(500);
        }
    }

    // 向左移动一个格子的方法
    public void moveLeft() {
        // 判断是否可以向左移动
        if (map.canMove(x - 1, y)) {
            // 更新地图的网格
            map.updateGrid(x, y, x - 1, y);
            // 更新探险家的坐标
            x--;
            // 停顿0.5秒
            pause(500);
        }
    }

    // 向右移动一个格子的方法
    public void moveRight() {
        // 判断是否可以向右移动
        if (map.canMove(x + 1, y)) {
            // 更新地图的网格
            map.updateGrid(x, y, x + 1, y);
            // 更新探险家的坐标
            x++;
            // 停顿0.5秒
            pause(500);
        }
    }

    // 停顿一定时间的方法
    public void pause(int time) {
        // 使用Thread.sleep方法
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 定义一个setX方法，传入一个整数，表示x坐标
    public void setX(int x) {
        // 把参数赋值给x属性
        this.x = x;
    }

    // 定义一个setY方法，传入一个整数，表示y坐标
    public void setY(int y) {
        // 把参数赋值给y属性
        this.y = y;
    }
    
    // 获取探险家的名字的方法
    public String getName() {
        return name;
    }

    // 获取探险家的x坐标的方法
    public int getX() {
        return x;
    }

    // 获取探险家的y坐标的方法
    public int getY() {
        return y;
    }

    // 获取探险家的生命值的方法
    public int getHP() {
        return HP;
    }

    // 获取探险家的攻击力的方法
    public int getAttack() {
        return attack;
    }

    // 获取探险家的战略的方法
    public Strategy getStrategy() {
        return strategy;
    }

    // 设置探险家的战略的方法
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    // 重写toString方法，用于打印探险家的信息
    public String toString() {
        return name + " (" + x + ", " + y + ") HP: " + HP + " Attack: " + attack + " Strategy: " + strategy;
    }

    public boolean isAlive() {
        // 如果探险家的血量大于0，就返回true，表示还活着
        if (HP > 0) {
            return true;
        } else {
            // 否则，返回false，表示已经死亡
            return false;
        }
    }
}
