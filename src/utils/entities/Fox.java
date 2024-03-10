package utils.entities;

import java.awt.Point;
import java.awt.Image;

public class Fox {
    private Point position;
    private Image image;

    public Fox(Point position, Image image) {
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
