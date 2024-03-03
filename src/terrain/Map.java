package terrain;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * 绘画Java手游地图<br>
 * 下方未对地图长度作判断，使用的数组下标都是[0]开始<br>
 * 所以只要maps的长度大于[0][0]即可
 *
 * @author Wenchong PAN
 *
 */
public class Map extends JFrame  {
    private static final long serialVersionUID = 1L;
    /**
     * 20x20 地图:<br>
     * 1为草地：浅绿<br>
     * 2为森林：深绿<br>
     * 3为桥：棕色<br>
     * 4为河流：蓝色<br>
     * 5为山地：白色<br>
     * 6为沼泽地：褐色<br>
     * 1、2、3可通过，4、5、6不可通过
     *
     */
    private Integer[][] maps = { { 1, 1, 5, 2, 2, 5, 1, 5, 2, 2, 1, 2, 2, 1, 2, 2, 1, 1, 1, 1 },
            { 1, 2, 2, 2, 2, 2, 2, 1, 1, 4, 2, 2, 2, 2, 5, 2, 2, 2, 2, 1 },
            { 1, 2, 2, 2, 2, 2, 2, 1, 5, 4, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
            { 1, 2, 5, 2, 2, 2, 2, 1, 5, 4, 1, 2, 5, 5, 5, 3, 4, 4, 1, 2 },
            { 1, 2, 2, 2, 2, 2, 2, 1, 1, 4, 1, 2, 4, 4, 4, 2, 4, 4, 1, 1 },
            { 1, 2, 1, 2, 5, 2, 2, 1, 5, 4, 3, 4, 4, 4, 4, 3, 4, 2, 2, 2 },
            { 2, 1, 6, 1, 2, 2, 2, 1, 5, 4, 1, 5, 5, 6, 6, 3, 1, 1, 1, 1 },
            { 1, 6, 6, 6, 1, 1, 2, 2, 2, 4, 1, 2, 2, 1, 1, 2, 2, 2, 5, 1 },
            { 2, 1, 3, 3, 2, 1, 2, 2, 5, 4, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
            { 1, 6, 6, 6, 6, 1, 2, 2, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 6, 6, 6, 1, 1, 2, 5, 5, 4, 1, 2, 2, 2, 2, 2, 2, 5, 4, 1 },
            { 1, 2, 1, 2, 1, 2, 2, 2, 1, 4, 1, 2, 2, 2, 2, 2, 2, 4, 5, 1 },
            { 1, 2, 1, 6, 2, 2, 2, 1, 1, 4, 1, 1, 2, 2, 2, 2, 2, 4, 4, 1 },
            { 1, 2, 1, 6, 2, 1, 6, 5, 5, 4, 5, 1, 1, 1, 1, 1, 1, 4, 4, 2 },
            { 1, 2, 1, 2, 1, 1, 2, 5, 1, 4, 5, 5, 5, 1, 1, 5, 1, 3, 3, 1 },
            { 1, 2, 6, 6, 1, 1, 2, 1, 1, 4, 4, 1, 1, 1, 1, 1, 2, 4, 4, 2 },
            { 1, 2, 2, 6, 2, 1, 2, 2, 1, 4, 6, 2, 6, 6, 2, 1, 2, 4, 4, 2 },
            { 1, 2, 6, 4, 4, 4, 4, 1, 5, 4, 6, 2, 6, 6, 2, 2, 1, 1, 4, 2 },
            { 1, 1, 6, 6, 2, 2, 2, 2, 5, 4, 1, 3, 1, 6, 2, 2, 2, 1, 4, 1 },
            { 1, 1, 1, 1, 2, 2, 1, 1, 1, 3, 2, 2, 2, 1, 1, 2, 2, 1, 1, 1 }, };

    /** 列数 */
    private final int MAP_WIDTH = 20;
    /** 行数 */
    private final int MAP_HEIGHT = 20;
    /** 方块宽度 */
    private final int BLOCK_WIDTH = 40;
    /** 方块高度 */
    private final int BLOCK_HEIGHT = 40;
    /** 玩家横坐标 */
    private Integer x, x2, x3;
    /** 玩家纵坐标 */
    private Integer y, y2, y3;
    /** 其他单位坐标 */
    private Integer treasure_x, dragon_x, wolf1_x, wolf2_x, wolf3_x, wolf4_x, bear1_x, bear2_x, tiger_x, snake1_x, snake2_x, snake3_x, snake4_x, fox1_x, fox2_x, fox3_x, fox4_x;
    private Integer treasure_y, dragon_y, wolf1_y, wolf2_y, wolf3_y, wolf4_y, bear1_y, bear2_y, tiger_y, snake1_y, snake2_y, snake3_y, snake4_y, fox1_y, fox2_y, fox3_y, fox4_y;
    /** 窗口宽度 */
    private Integer width;
    /** 窗口高度 */
    private Integer height;
    /** 玩家图片 */
    private Image player1 = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/character.png"));
    private Image player2 = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/soldier.png"));
    private Image player3 = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/barbarian.png"));
    /** 宝箱图片 */
    private Image treasure = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/treasure.png"));
    /** 龙图片 */
    private Image dragon = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/dragon.png"));
    /** 狼图片 */
    private Image wolf = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/wolf.png"));
    /** 熊图片（1） */
    private Image bear1 = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/bear(1).png"));
    /** 熊图片（2）*/
    private Image bear2 = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/bear(2).png"));
    /** 虎图片*/
    private Image tiger = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/tiger.png"));
    /** 蛇图片（1）*/
    private Image snake1 = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/snake(1).png"));
    /** 蛇图片（2）*/
    private Image snake2 = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/snake(2).png"));
    /** 狐狸图片（1）*/
    private Image fox1 = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/fox(1).png"));
    /** 狐狸图片（2）*/
    private Image fox2 = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/fox(2).png"));
    /** 狐狸图片（3）*/
    private Image fox3 = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/fox(3).png"));
    /** 地形图片 */
    private Image image1 = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/grass.png"));
    private Image image2 = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/forest.png"));
    private Image image3 = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/bridge.png"));
    private Image image4 = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/river.png"));
    private Image image5 = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/mountain.png"));
    private Image image6 = ImageIO.read(new File("E:/document university/learning/major france/projet integration/MAP/src/image/marshland.png"));

    public Map() throws IOException {
        // 玩家位置初始化
        x = 0; y = 19;
        x2 = 0; y2 = 19;
        x3 = 0; y3 = 19;
        treasure_x = 15; treasure_y = 4;
        dragon_x = 14; dragon_y = 4;
        wolf1_x = 6 ; wolf1_y = 17;
        wolf2_x = 3 ; wolf2_y = 4;
        wolf3_x = 4 ; wolf3_y = 5;
        wolf4_x = 7 ; wolf4_y = 16;
        bear1_x = 11  ; bear1_y = 14  ;
        bear2_x = 1 ; bear2_y =  11;
        tiger_x = 9 ;  tiger_y = 9 ;
        snake1_x = 7 ;  snake1_y = 13 ;
        snake2_x = 18 ;  snake2_y = 10 ;
        snake3_x = 6 ;  snake3_y = 3 ;
        snake4_x = 15 ;  snake4_y = 13 ;
        fox1_x = 16 ;  fox1_y = 8;
        fox2_x = 7;  fox2_y = 6 ;
        fox3_x = 17;  fox3_y = 1;
        fox4_x = 0;  fox4_y = 1;
        setTitle("Treasure - Hunter");
        // 获取屏幕宽度
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = MAP_WIDTH * BLOCK_WIDTH;
        height = MAP_HEIGHT * BLOCK_HEIGHT + 30;
        // 使窗口居中
        setBounds(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2, width, height);

        setLayout(null);
        // 设置默认退出方式
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        // 添加按键监听
        addKeyListener(new KeyMoniton());
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Map();
    }

    /**
     * 按键监听类
     */
    private class KeyMoniton extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (maps[x][y - 1] > 3) {
                        return;
                    }
                    y -= 1;
                    break;
                case KeyEvent.VK_DOWN:
                    if (maps[x][y + 1] > 3) {
                        return;
                    }
                    y += 1;
                    break;
                case KeyEvent.VK_RIGHT:
                    if (maps[x + 1][y] > 3) {
                        return;
                    }
                    x += 1;
                    break;
                case KeyEvent.VK_LEFT:
                    if (maps[x - 1][y] > 3) {
                        return;
                    }
                    x -= 1;
                    break;
            }
            repaint();
        }

    }

    /**
     * 此处绘画地图的方式：<br>
     * 先找出地图左上角坐标，然后往右和下方画指定格数
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        // 默认从最左边开始画
        int drawingx = 0;
        int drawingy = 0;
        int minx = MAP_WIDTH / 2;
        int miny = MAP_HEIGHT / 2;
        // 如果一行的个数为偶数个，最后不用再次减1
        int maxx = maps.length - minx - (MAP_WIDTH % 2 == 0 ? 0 : 1);
        // 如果行的个数为偶数个，最后不用再次减1
        int maxy = maps[0].length - miny - (MAP_HEIGHT % 2 == 0 ? 0 : 1);
        if (x > minx) {// 如果当前位置大于最小位置，则人物固定在中间，起始x为当前位置减去最小位置
            drawingx = x - minx;
        }
        if (x > maxx) {// 如果当前位置大于最大位置则起始x为固定值
            drawingx = maxx - minx;
        }
        if (y > miny) {
            drawingy = y - miny;
        }
        if (y > maxy) {
            drawingy = maxy - miny;
        }
        Image image = null;
        for (int i = 0; i < MAP_WIDTH * MAP_HEIGHT; i++) {
            // 每次绘制完一行，则行数下移
            if (i > 0 && i % MAP_WIDTH == 0) {
                drawingy++;
            }
            image = getImage(maps[drawingx + i % MAP_WIDTH][drawingy]);
            g.drawImage(image, i % MAP_WIDTH * BLOCK_WIDTH, i / MAP_WIDTH * BLOCK_HEIGHT + 30, BLOCK_WIDTH, BLOCK_HEIGHT,
                    null);
        }

        // 绘制单位
        g.drawImage(treasure, treasure_x * BLOCK_WIDTH, treasure_y * BLOCK_HEIGHT + 30, BLOCK_WIDTH, BLOCK_HEIGHT, null);
        g.drawImage(dragon, dragon_x * BLOCK_WIDTH, dragon_y * BLOCK_HEIGHT + 30, 2 * BLOCK_WIDTH, 2 * BLOCK_HEIGHT, null);
        g.drawImage(wolf, wolf1_x * BLOCK_WIDTH, wolf1_y * BLOCK_HEIGHT + 30,  BLOCK_WIDTH,  BLOCK_HEIGHT, null);
        g.drawImage(wolf, wolf2_x * BLOCK_WIDTH, wolf2_y * BLOCK_HEIGHT + 30,  BLOCK_WIDTH,  BLOCK_HEIGHT, null);
        g.drawImage(wolf, wolf3_x * BLOCK_WIDTH, wolf3_y * BLOCK_HEIGHT + 30,  BLOCK_WIDTH,  BLOCK_HEIGHT, null);
        g.drawImage(wolf, wolf4_x * BLOCK_WIDTH, wolf4_y * BLOCK_HEIGHT + 30,  BLOCK_WIDTH,  BLOCK_HEIGHT, null);
        g.drawImage(bear1, bear1_x * BLOCK_WIDTH, bear1_y * BLOCK_HEIGHT + 30,  BLOCK_WIDTH, 2 * BLOCK_HEIGHT, null);
        g.drawImage(bear2, bear2_x * BLOCK_WIDTH, bear2_y * BLOCK_HEIGHT + 30, 2 *  BLOCK_WIDTH,  BLOCK_HEIGHT, null);
        g.drawImage(tiger, tiger_x * BLOCK_WIDTH, tiger_y * BLOCK_HEIGHT + 30, BLOCK_WIDTH, 2 * BLOCK_HEIGHT, null);
        g.drawImage(snake1, snake1_x * BLOCK_WIDTH, snake1_y * BLOCK_HEIGHT + 30, BLOCK_WIDTH, BLOCK_HEIGHT, null);
        g.drawImage(snake1, snake2_x * BLOCK_WIDTH, snake2_y * BLOCK_HEIGHT + 30, BLOCK_WIDTH, BLOCK_HEIGHT, null);
        g.drawImage(snake2, snake3_x * BLOCK_WIDTH, snake3_y * BLOCK_HEIGHT + 30, BLOCK_WIDTH, BLOCK_HEIGHT, null);
        g.drawImage(snake2, snake4_x * BLOCK_WIDTH, snake4_y * BLOCK_HEIGHT + 30, BLOCK_WIDTH, BLOCK_HEIGHT, null);
        g.drawImage(fox1, fox1_x * BLOCK_WIDTH, fox1_y * BLOCK_HEIGHT + 30, BLOCK_WIDTH, BLOCK_HEIGHT, null);
        g.drawImage(fox2, fox2_x * BLOCK_WIDTH, fox2_y * BLOCK_HEIGHT + 30, BLOCK_WIDTH, BLOCK_HEIGHT, null);
        g.drawImage(fox3, fox3_x * BLOCK_WIDTH, fox3_y * BLOCK_HEIGHT + 30, BLOCK_WIDTH, BLOCK_HEIGHT, null);
        g.drawImage(fox3, fox4_x * BLOCK_WIDTH, fox4_y * BLOCK_HEIGHT + 30, BLOCK_WIDTH, BLOCK_HEIGHT, null);

        // 绘制人物
        if (x <= minx ) {
            minx = x;
        }
        if (x > maxx) {
            minx = MAP_WIDTH - maps.length + x;
        }
        if (y < miny) {
            miny = y;
        }
        if (y > maxy) {
            miny = MAP_HEIGHT - maps[0].length + y;
        }
        g.drawImage(player1, minx * BLOCK_WIDTH, miny * BLOCK_HEIGHT + 30, BLOCK_WIDTH, BLOCK_HEIGHT, null);
        g.drawImage(player2, x2 * BLOCK_WIDTH, y2 * BLOCK_HEIGHT + 30, BLOCK_WIDTH, BLOCK_HEIGHT, null);
        g.drawImage(player3, x3 * BLOCK_WIDTH, y3 * BLOCK_HEIGHT + 30, BLOCK_WIDTH, BLOCK_HEIGHT, null);
    }

    private Image getImage(Integer i) {
        switch (i) {
            case 1:
                return image1;
            case 2:
                return image2;
            case 3:
                return image3;
            case 4:
                return image4;
            case 5:
                return image5;
            case 6:
                return image6;
            default:
                return null;
        }
    }

    /**
     * 获取文件路径
     *
     * @param fileName
     * @return
     */
    private String getFilePath(String fileName) {
        return Map.class.getResource(fileName).getPath();
    }

}




