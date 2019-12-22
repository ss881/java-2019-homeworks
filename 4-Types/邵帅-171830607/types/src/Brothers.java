package types.src;
import java.util.*;

public class Brothers extends Organism {
    Color color;
    public Brothers(MyMap map, Color color1) {
        super(map, color1.getColorName());
        this.color = color1;
    }

    public Color getColor() {
        return color;
        // }
        // public static void main(String []args){
        // System.out.println(Color.红色.ordinal());

        // Organism brother0 = new Organism(new MyMap(2,3),"大娃");

        // System.out.println(brother0.Getpos().x);
        // Brothers brother1 = new Brothers(new MyMap(2,3),Color.红色);
        // //Brothers brother1 = new Brothers(new MyMap(2,3),red);
        // System.out.println(brother1.Getpos().x);
        // System.out.println(brother1.getColor().getColorName());

        // System.out.println(brother1.getColor().getIndex());
        // }
    }
}