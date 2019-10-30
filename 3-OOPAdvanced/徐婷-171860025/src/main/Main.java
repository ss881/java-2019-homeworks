//package main;

import creatures.*;
import ground.*;

public class Main{
    public static void main(String []args){
        //初始化队列
        HuluwaTeam huluwaTeam=new HuluwaTeam();
        SnakeTeam snakeTeam = new SnakeTeam();
        Ground ground=new Ground();
        //huluwaTeam.Sort(ground);
        ground.update(huluwaTeam.getTeamMembers());
        ground.update(snakeTeam.getTeamMembers());
        ground.print();
        huluwaTeam.Sort(ground);
        Grandpa grandpa=huluwaTeam.getGrandpa();
        Snake snake=snakeTeam.getSnake();
        int i=0;
        while(true){
            snakeTeam.arrange(ground);
            snake.cheer();
            grandpa.cheer();
            //ground.print();

        }

    }
}
