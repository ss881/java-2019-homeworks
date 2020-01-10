package GameModule;

import javafx.scene.image.Image;

public class Unit {
    private Image image;
    private int x, y;
    private int width;
    private int height;
    private boolean alive = false;
    private float hpRatio = 0;
    public Unit(Image image, int x, int y, int width, int height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Unit(Image image, int x, int y, int width, int height, float hpRatio) {
        this(image, x, y, width, height);
        this.hpRatio = hpRatio;
        this.alive = true;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isAlive() {
        return alive;
    }

    public float getHpRatio() {
        return hpRatio;
    }
}
