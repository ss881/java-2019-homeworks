/*
 * 这是造物主
 */

import field.*;
import items.*;

public class Main {
    public static void main(String[] args){
        Field field=new Field();
        Elder elder=new Elder(new Position(0,0),field);
        field.draw();
        elder.standAsSnake();
        System.out.println("葫芦娃排队后");
        field.draw();
        SnakeDemon snakeDemon=new SnakeDemon(new Position(Field.N-1,0),
                field,8);
        System.out.println("加入妖精后");
        field.draw();
        try {
            snakeDemon.standAsSwing();
        }catch (AssertionError a){
            a.printStackTrace();
            field.draw();
            return;
        }
        System.out.println("排布鹤翼阵型后");
        field.addLiving(elder,field.leftRandomPosition());
        field.addLiving(snakeDemon,field.rightRandomPosition());
        field.draw();
        try {
            snakeDemon.standAsArrow();
        }catch (AssertionError a){
            a.printStackTrace();
            field.draw();
            return;
        }
        System.out.println("排布锋矢阵型后");
        field.draw();
    }
}
