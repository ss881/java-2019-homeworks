/*
 * 这是造物主
 */

import field.*;
import items.*;
import formations.*;

public class Main {
    public static void main(String[] args){
        Field field=new Field();
        Elder elder=new Elder(new Position(0,0),field);
        field.draw();
        elder.embattleFormation(SnakeFormation.class);
        System.out.println("葫芦娃排队后");
        field.draw();
        SnakeDemon snakeDemon=new SnakeDemon(new Position(Field.N-1,0),
                field,8);
        System.out.println("加入妖精后");
        field.draw();
        snakeDemon.embattleFormation(SwingFormation.class);
        System.out.println("排布鹤翼阵型后");
        field.addLiving(elder,field.leftRandomPosition());
        field.addLiving(snakeDemon,field.rightRandomPosition());
        field.draw();
        snakeDemon.embattleFormation(ArrowFormation.class);
        System.out.println("排布锋矢阵型后");
        field.draw();
    }
}
