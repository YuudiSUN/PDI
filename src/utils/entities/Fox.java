package utils.entities;

import java.awt.Point;
import java.awt.Image;

public class Fox implements Animal{
    private Point position;
    private Image image;

    public Fox(Point position, Image image) {
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
