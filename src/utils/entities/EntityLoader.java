package utils.entities;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EntityLoader {
	
    public static List<Bear> loadBears(int count) throws IOException {
        List<Bear> bears = new ArrayList<>();
        Image image = ImageIO.read(new File("src/image/bear(1).png"));
        for (int i = 0; i < count; i++) {
            // Generate random positions or predefined positions for each bear
            Point position = new Point((int) (Math.random() * 20), (int) (Math.random() * 20));
            bears.add(new Bear(position, image));
        }
        return bears;
    }

    public static List<Fox> loadFoxes(int count) throws IOException {
        List<Fox> foxes = new ArrayList<>();
        Image image = ImageIO.read(new File("src/image/fox(1).png"));
        for (int i = 0; i < count; i++) {
            Point position = new Point((int) (Math.random() * 20), (int) (Math.random() * 20));
            foxes.add(new Fox(position, image));
        }
        return foxes;
    }

    public static List<Tiger> loadTigers(int count) throws IOException {
        List<Tiger> tigers = new ArrayList<>();
        Image image = ImageIO.read(new File("src/image/tiger.png"));
        for (int i = 0; i < count; i++) {
            Point position = new Point((int) (Math.random() * 20), (int) (Math.random() * 20));
            tigers.add(new Tiger(position, image));
        }
        return tigers;
    }
    public static Dragon loadDragon() throws IOException {
        Image image = ImageIO.read(new File("src/image/dragon.png"));
        // 假设龙出现在接近宝藏的地方，根据你的游戏逻辑修改
        Point position = new Point(18, 3);  // 仅作示例，根据你的地图设计调整
        return new Dragon(position, image);
    }

    // Similar method for Dragons if you need multiple dragons
}
