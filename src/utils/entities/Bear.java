package utils.entities;

import java.awt.Point;
import java.awt.Image;

public class Bear {
    private Point position;
    private Image image;

    public Bear(Point position, Image image) {
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
