package game;

import java.util.Scanner; // 导入Scanner类

//Game.class
public class Game {

    // Attributes
    private Map map; // 游戏的地图对象
    private Scanner scanner; // 用于接收用户输入的扫描器对象

    // Constructor
    public Game() {
        // 创建一个40x40的地图
        map = new Map(40, 40);
        // 创建一个扫描器
        scanner = new Scanner(System.in);
    }

    // Methods
    // 游戏的开始方法，用于初始化游戏
    public void start() {
        // 打印欢迎信息
        System.out.println("欢迎来到探险家游戏！");
        // 创建探险家
        createExplorers();
        // 展示地图
        map.showMap();
        // 进入游戏循环
        gameLoop();
    }

    // 游戏的循环方法，用于控制游戏的流程
    public void gameLoop() {
        boolean isOver = false;
        while (!isOver) {
            for (Explorer explorer : map.getExplorers()) {
                if (explorer.isAlive()) {
                    System.out.println(explorer);
                    String action = getAction(explorer);
                    executeAction(explorer, action);
                    map.showMap();
                    isOver = map.isOver();
                    if (isOver) {
                        break;
                    }
                }
            }
        }
        System.out.println("游戏结束！");
    }

    // 创建探险家的方法，用于让用户输入探险家的数量和信息
    public void createExplorers() {
        System.out.println("请输入探险家的数量（1-4）：");
        int num = scanner.nextInt();
        if (num < 1 || num > 4) {
            System.out.println("输入错误，请重新输入！");
            createExplorers();
        } else {
            for (int i = 0; i < num; i++) {
                // 打印提示信息
                System.out.println("请输入第" + (i + 1) + "个探险家的信息（姓名，x坐标，y坐标，战略）：");
                // 获取用户输入的信息
                String name = scanner.next();
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                String strategy = scanner.next();

                // Judge position
                if (map.canMove(x, y)) {
                    // if possible, create an explorer
                    Explorer explorer = new Explorer("Tom", 10, 10, 100, 50, Strategy.AGGRESSIVE, map);
                    map.addExplorer(explorer);
                } else {
                    System.out.println("Error! Please retype.");
                    i--;
                }
            }
        }
    }

    // 执行探险家的行动的方法，用于根据探险家的行动执行相应的操作
    public void executeAction(Explorer explorer, String action) {
        // 根据行动的不同，执行不同的操作
        switch (action) {
            case "移动":
                // 如果是移动，就调用移动的方法
                move(explorer);
                break;
            case "攻击":
                // 如果是攻击，就调用攻击的方法
                attack(explorer);
                break;
            case "等待":
                // 如果是等待，就什么都不做
                break;
        }
    }

    // 移动的方法，用于让探险家移动到指定的坐标
    public void move(Explorer explorer) {
        // 获取探险家的坐标
        int x = explorer.getX();
        int y = explorer.getY();
        // 获取探险家的战略
        Strategy strategy = explorer.getStrategy();
        // 根据战略的不同，获取不同的目标坐标
        int[] target = strategy.getTarget(x, y, map);
        // 获取目标坐标
        int tx = target[0];
        int ty = target[1];
        // 判断目标坐标是否合法
        if (map.canMove(tx, ty)) {
            // 如果合法，就更新地图
            map.updateGrid(x, y, tx, ty);
            // 更新探险家的坐标
            explorer.setX(tx);
            explorer.setY(ty);
            // 打印移动信息
            System.out.println(explorer.getName() + "移动到了(" + tx + ", " + ty + ")");
            // 判断是否到达宝藏
            if (map.getGrid()[tx][ty] == 4) {
                // 如果到达宝藏，就获取宝藏
                map.getTreasure(explorer);
                // 打印获取宝藏信息
                System.out.println(explorer.getName() + "获取了宝藏！");
                // 通知其他探险家宝藏的坐标
                map.notifyTreasure(x, y, tx, ty);
            }
        } else {
            // 如果不合法，就打印错误信息
            System.out.println("无法移动到(" + tx + ", " + ty + ")");
        }
    }

    // 攻击的方法，用于让探险家攻击周围的野生动物
    public void attack(Explorer explorer) {
        // 获取探险家的坐标
        int x = explorer.getX();
        int y = explorer.getY();
        // 获取探险家的攻击力
        int attack = explorer.getAttack();
        // 定义一个布尔变量，表示是否攻击成功
        boolean success = false;
        // 遍历所有野生动物
        for (Animal animal : map.getAnimals()) {
            // 获取野生动物的坐标
            int ax = animal.getX();
            int ay = animal.getY();
            // 判断野生动物是否在探险家的周围
            if (Math.abs(ax - x) <= 1 && Math.abs(ay - y) <= 1) {
                // 如果在周围，就攻击野生动物
                animal.setHP(animal.getHP() - attack);
                // 打印攻击信息
                System.out.println(explorer.getName() + "攻击了" + animal);
                // 判断野生动物是否死亡
                if (animal.getHP() <= 0) {
                    // 如果死亡，就杀死野生动物
                    map.killAnimal(animal);
                    // 打印杀死信息
                    System.out.println(explorer.getName() + "杀死了" + animal);
                }
                // 攻击成功，把布尔变量设为true
                success = true;
                // 跳出循环
                break;
            }
        }
        // 如果攻击失败，就打印失败信息
        if (!success) {
            System.out.println(explorer.getName() + "没有找到可以攻击的野生动物");
        }
    }
}
