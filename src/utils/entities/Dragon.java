package utils.entities;

import java.awt.Point;
import java.awt.Image;

public class Dragon {
    private Point position;
    private Image image;

    public Dragon(Point position, Image image) {
        this.position = position;
        this.image = image;
    }

    public Point getPosition() {
        return position;
    }

    public Image getImage() {
        return image;
    }
}
