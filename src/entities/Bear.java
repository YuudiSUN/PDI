package entities;

import java.awt.Point;
import java.awt.Image;

public class Bear extends Entity{
    public Bear(Point position, int health) {
		super(position, health);
		// TODO Auto-generated constructor stub
	}

	private Point position;
    private Image image;

    @Override
    public Point getPosition() {
        return position;
    }

    public Image getImage() {
        return image;
    }

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void interact(Entity other) {
		// TODO Auto-generated method stub
		
	}
}
