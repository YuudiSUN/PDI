package utils.entities;

import java.awt.Point;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public abstract class GameEntity {
    protected Point position;
    protected Image image;

    public GameEntity(Point position, String imagePath) {
        this.position = position;
        try {
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            this.image = null;
        }
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(String imagePath) {
        try {
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            this.image = null;
        }
    }

    // 你可以在这里添加其他与实体相关的通用方法，比如移动方法等
}
