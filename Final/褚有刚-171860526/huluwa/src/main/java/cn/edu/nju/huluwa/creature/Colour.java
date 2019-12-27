package cn.edu.nju.huluwa.creature;

import javafx.scene.paint.Color;

public enum Colour {
    RED(Color.RED),
    ORANGE(Color.ORANGE),
    YELLOW(Color.YELLOW),
    GREEN(Color.GREEN),
    CYAN(Color.CYAN),
    BLUE(Color.BLUE),
    PURPLE(Color.PURPLE), // 葫芦娃
    MAGENTA(Color.MAGENTA),    // 老爷爷
    DARKGREEN(Color.DARKGREEN),  // 蛇精
    DARkBLUE(Color.DARKBLUE),  // 蝎子精
    DARKGRAY(Color.DARKGREEN);

    private Color color;

    Colour(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
