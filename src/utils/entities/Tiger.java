package utils.entities;

import java.awt.Point;
import java.awt.Image;

public class Tiger implements Animal{
    private Point position;
    private Image image;

    public Tiger(Point position, Image image) {
        this.position = position;
        this.image = image;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    public Image getImage() {
        return image;
    }
}
