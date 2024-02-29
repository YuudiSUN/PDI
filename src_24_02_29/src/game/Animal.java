package game;
// Animal.class
public class Animal {

    // Attributes
    private int x, y; 
    private int HP = 50; 
    private int attack = 20; 

    // Constructor
    public Animal(int x, int y, int HP, int attack) {
        this.x = x;
        this.y = y;
        this.HP = HP;
        this.attack = attack;
    }

    // Methods
    // Method to get the x-coordinate of a wild animal
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHP() {
        return HP;
    }

    public int getAttack() {
        return attack;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    // Override the toString method for printing information
    public String toString() {
        return "Animal (" + x + ", " + y + ") HP: " + HP + " Attack: " + attack;
    }
}
