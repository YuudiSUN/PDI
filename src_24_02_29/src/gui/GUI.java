package gui;

//GUI.class
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

 // Attributes
 private Game game; 
 private JPanel panel; // 窗口的面板对象
 private JLabel label; // 用于显示信息的标签对象
 private JButton[][] buttons; // 用于表示地图的按钮数组
 private int size = 40; // 地图的大小
 private int explorerNum = 0; // 探险家的数量
 private int current = 0; // 当前操作的探险家的索引
 private String action = ""; // 当前选择的行动

 // Constructor
 public GUI() {
     super("Explorer Game");
     game = new Game();
     panel = new JPanel();
     // 布局为网格布局
     panel.setLayout(new GridLayout(size + 1, size));
     label = new JLabel("Welcome to Explorer Games!");

     label.setFont(new Font("Times New Roman", Font.BOLD, 20));
     label.setHorizontalAlignment(SwingConstants.CENTER);

     panel.add(label);

     buttons = new JButton[size][size];

     for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {

             JButton button = new JButton();

             button.setFont(new Font("Times New Roman", Font.BOLD, 10));
             button.setBackground(Color.WHITE);
             // 根据地图的内容，设置按钮的文本和前景颜色
             switch (game.getMap().getGrid()[i][j]) {
                 case 0:
                     // 空地，不显示文本
                     button.setText("");
                     break;
                 case 1:
                     // 障碍，显示#，颜色为灰色
                     button.setText("#");
                     button.setForeground(Color.GRAY);
                     break;
                 case 2:
                     // 森林，显示*，颜色为绿色
                     button.setText("*");
                     button.setForeground(Color.GREEN);
                     break;
                 case 3:
                     // 野生动物，显示A，颜色为红色
                     button.setText("A");
                     button.setForeground(Color.RED);
                     break;
                 case 4:
                     // 宝藏，显示T，颜色为黄色
                     button.setText("T");
                     button.setForeground(Color.YELLOW);
                     break;
                 case 5:
                     // 探险家，显示E，颜色为蓝色
                     button.setText("E");
                     button.setForeground(Color.BLUE);
                     break;
             }
             // 添加动作监听器
             button.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     buttonClicked(e);
                 }
             });
             // 把按钮对象添加到按钮数组中
             buttons[i][j] = button;
             // 把按钮对象添加到面板中
             panel.add(button);
         }
     }
     // 把面板添加到窗口中
     this.add(panel);
     // 关闭方式，大小，位置和可见性
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     this.setSize(800, 800);
     this.setLocationRelativeTo(null);
     this.setVisible(true);
     // 调用创建探险家的方法
     createExplorers();
 }

 // Methods
 // 创建探险家的方法，用于让用户输入探险家的数量和信息
 public void createExplorers() {
     // 打印提示信息
     label.setText("Number of Explorers（1-4）：");
     // 获取用户输入的数量
     explorerNum = Integer.parseInt(JOptionPane.showInputDialog("请输入探险家的数量（1-4）："));
     // 判断数量是否合法
     if (explorerNum < 1 || explorerNum > 4) {
         // 如果不合法，就打印错误信息，并重新输入
         label.setText("Error！ Retry: ");
         createExplorers();
     } else {
         // 如果合法，就根据数量创建探险家
         for (int i = 0; i < explorerNum; i++) {
             // 打印提示信息
             label.setText("No." + (i + 1) + "'s explorer info（name，x，y，str）：");
             // 获取用户输入的信息
             String input = JOptionPane.showInputDialog("No." + (i + 1) + "'s explorer info（name，x，y，str）");
             // 用逗号分隔输入的信息
             String[] info = input.split(",");
             // 获取探险家的姓名，坐标和战略
             String name = info[0];
             int x = Integer.parseInt(info[1]);
             int y = Integer.parseInt(info[2]);
             String strategy = info[3];
             // 判断坐标是否合法
             if (game.getMap().canMove(x, y)) {
                 // 如果合法，就创建一个探险家对象
                 Explorer explorer = new Explorer(name, x, y, strategy);
                 // 把探险家添加到地图上
                 game.getMap().addExplorer(explorer);
                 // 更新按钮的文本和颜色
                 buttons[x][y].setText("E");
                 buttons[x][y].setForeground(Color.BLUE);
             } else {
                 // 如果不合法，就打印错误信息，并重新输入
                 label.setText("Retry!");
                 i--;
             }
         }
         // 创建探险家完成，打印提示信息
         label.setText("Start!");
     }
 }

 // 按钮的点击事件的方法，用于处理用户点击按钮的操作
 public void buttonClicked(ActionEvent e) {
     // 获取点击的按钮对象
     JButton button = (JButton) e.getSource();
     // 获取按钮的文本
     String text = button.getText();
     // 判断文本的内容
     switch (text) {
         case "":
             // 如果是空文本，表示是空地或森林，就调用移动的方法
             move(button);
             break;
         case "#":
             // 如果是#，表示是障碍，就打印错误信息
             label.setText("Can't");
             break;
         case "A":
             // 如果是A，表示是野生动物，就调用攻击的方法
             attack(button);
             break;
         case "T":
             // 如果是T，表示是宝藏，就调用移动的方法
             move(button);
             break;
         case "E":
             // 如果是E，表示是探险家，就调用选择的方法
             select(button);
             break;
     }
 }

 // 移动的方法，用于让探险家移动到指定的按钮
 public void move(JButton button) {
     // 判断当前是否有选择的探险家
     if (current >= 0 && current < explorerNum) {
         // 如果有，就获取当前的探险家对象
         Explorer explorer = game.getMap().getExplorers().get(current);
         // 获取探险家的坐标
         int x = explorer.getX();
         int y = explorer.getY();
         // 获取目标按钮的坐标
         int tx = panel.getComponentZOrder(button) / size - 1;
         int ty = panel.getComponentZOrder(button) % size;
         // 判断目标坐标是否合法
         if (game.getMap().canMove(tx, ty)) {
             // 如果合法，就更新地图
             game.getMap().updateGrid(x, y, tx, ty);
             // 更新探险家的坐标
             explorer.setX(tx);
             explorer.setY(ty);
             // 更新按钮的文本和颜色
             buttons[x][y].setText("");
             buttons[x][y].setForeground(Color.WHITE);
             buttons[tx][ty].setText("E");
             buttons[tx][ty].setForeground(Color.BLUE);
             // 打印移动信息
             label.setText(explorer.getName() + "移动到了(" + tx + ", " + ty + ")");
             // 判断是否到达宝藏
             if (game.getMap().getGrid()[tx][ty] == 4) {
                 // 如果到达宝藏，就获取宝藏
                 game.getMap().getTreasure(explorer);
                 // 打印获取宝藏信息
                 label.setText(explorer.getName() + "获取了宝藏！");
                 // 通知其他探险家宝藏的坐标
                 game.getMap().notifyTreasure(x, y, tx, ty);
             }
         } else {
             // 如果不合法，就打印错误信息
             label.setText("无法移动到(" + tx + ", " + ty + ")");
         }
     } else {
         // 如果没有，就打印错误信息
         label.setText("请先选择一个探险家！");
     }
 }

 // 攻击的方法，用于让探险家攻击周围的野生动物
 public void attack(JButton button) {
     // 判断当前是否有选择的探险家
     if (current >= 0 && current < explorerNum) {
         // 如果有，就获取当前的探险家对象
         Explorer explorer = game.getMap().getExplorers().get(current);
         // 获取探险家的坐标
         int x = explorer.getX();
         int y = explorer.getY();
         // 获取目标按钮的坐标
         int tx = panel.getComponentZOrder(button) / size - 1;
         int ty = panel.getComponentZOrder(button) % size;
         // 判断目标坐标是否合法
         if (Math.abs(tx - x) <= 1 && Math.abs(ty - y) <= 1) {
             // 如果合法，就遍历所有野生动物
             for (Animal animal : game.getMap().getAnimals()) {
                 // 获取野生动物的坐标
                 int ax = animal.getX();
                 int ay = animal.getY();
                 // 判断野生动物是否在目标坐标
                 if (ax == tx && ay == ty) {
                     // 如果在目标坐标，就攻击野生动物
                     animal.setHP(animal.getHP() - explorer.getAttack());
                     // 打印攻击信息
                     label.setText(explorer.getName() + "攻击了" + animal);
                     // 判断野生动物是否死亡
                     if (animal.getHP() <= 0) {
                         // 如果死亡，就杀死野生动物
                         game.getMap().killAnimal(animal);
                         // 打印杀死信息
                         label.setText(explorer.getName() + "杀死了" + animal);
                         // 更新按钮的文本和颜色
                         buttons[tx][ty].setText("");
                         buttons[tx][ty].setForeground(Color.WHITE);
                     }
                     // 跳出循环
                     break;
                 }
             }
         } else {
             // 如果不合法，就打印错误信息
             label.setText("无法攻击(" + tx + ", " + ty + ")");
         }
     } else {
         // 如果没有，就打印错误信息
         label.setText("请先选择一个探险家！");
     }
 }

 // 选择的方法，用于让用户选择一个探险家
 public void select(JButton button) {
     // 获取目标按钮的坐标
     int tx = panel.getComponentZOrder(button) / size - 1;
     int ty = panel.getComponentZOrder(button) % size;
     // 遍历所有探险家
     for (int i = 0; i < explorerNum; i++) {
         // 获取探险家对象
         Explorer explorer = game.getMap().getExplorers().get(i);
         // 获取探险家的坐标
         int x = explorer.getX();
         int y = explorer.getY();
         // 判断探险家是否在目标坐标
         if (x == tx && y == ty) {
             // 如果在目标坐标，就把当前的探险家索引设为i
             current = i;
             // 打印选择信息
             label.setText("你选择了" + explorer.getName());
             // 跳出循环
             break;
         }
     }
 }
}
