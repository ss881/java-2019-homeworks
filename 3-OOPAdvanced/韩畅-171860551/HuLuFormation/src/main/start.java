package main;

import java.util.Random;

import Ground.Ground;
import Ground.Position;
import Unit.Follower;
import Unit.NormalFollowers;
import Unit.UnitCommander;

class GameConfigration {
    // public static String[] HuLuSeq = { "First", "Second", "Third", "Forth",
    // "Fifth", "Sixth", "Seventh" };
    public static String[] HuLuColor = { "RED", "ORANGE", "YELLOEW", "GREEN", "CYAN", "BLUE", "PURPLE" };
}
// 老师说葫芦娃出生时就已经被赋予了颜色属性
// 这个赋予颜色属性的过程，就权当游戏配置的过程吧

public class start {
    public static void main(String args[]) {
        Ground myGr = new Ground(11, 11);
        UnitCommander oldMan = new UnitCommander(100, "OLDMAN", 0, 300, myGr);
        UnitCommander snake = new UnitCommander(80, "SNAKE", 1, 5000, myGr);

        // oldMan.addFollowers(new Follower(oldMan, "穿山甲", 0 , 500, myGr));
        for (int i = 0; i < 7; ++i) {
            oldMan.addFollowers(
                    new NormalFollowers(oldMan, i, "HULUBABY", 0, 2000, myGr, GameConfigration.HuLuColor[i]));
        }
        // 蝎子精蛤蟆精
        snake.addFollowers(new Follower(snake, "XIEZI", 1, 3000, myGr));
        snake.addFollowers(new Follower(snake, "HAMA", 1, 3000, myGr));
        for (int i = 0; i < 20; ++i) {
            snake.addFollowers(new NormalFollowers(snake, i, "Louluo", 1, 500, myGr, "Unknown"));
        }
        // 小喽啰颜色只能先写不知道了
        oldMan.lookAround(true, true, true, true, true);
        oldMan.plantYourFollowersRandomly();
        oldMan.lookAround(true, true, true, true, true);
        oldMan.changeMyFormationToSnake();
        oldMan.lookAround(true, true, true, true, true);
        // 由于缓冲区大小不够，老师一开始可以先一帧一帧看
        // 想看结果，最好先切出去，然后等5秒左右再切回来，基本就到结果了
        snake.changeFollowerMyFormation(0);
        snake.lookAround(true, true, true, true, true);
        snake.changeFollowerMyFormation(1);
        snake.lookAround(true, true, true, true, true);
        snake.changeFollowerMyFormation(2);
        snake.lookAround(true, true, true, true, true);
        snake.changeFollowerMyFormation(3);
        snake.lookAround(true, true, true, true, true);
        snake.changeFollowerMyFormation(4);
        snake.lookAround(true, true, true, true, true);
        snake.changeFollowerMyFormation(5);
        snake.lookAround(true, true, true, true, true);
        snake.changeFollowerMyFormation(6);
        snake.lookAround(true, true, true, true, true);
        // 老师你想看第几个阵型就保留哪几个阵型就行
        // 其他不想看的就像底下这个阵型一样注释掉，最后一定要lookAround一下，不然有可能还没到位，不过一般应该不会，每走一步理论上输出都会刷新的
        // 默认第6个应该是锋矢阵
        // snake.changeFollowerMyFormation(0);
        // snake.lookAround(true, true, true, true, true);
        // 蛇精和老爷爷都是最后变完阵再站到地面上的，默认走到地面的左右下角

        boolean flag = false;
        for (int i = 0; i < 11; ++i) {
            for (int j = 0; j < 11; ++j) {
                flag = oldMan.findYourPathTo(new Position(10 - i, j));
                if (flag)
                    break;
            }
            if (flag)
                break;
        }

        flag = false;
        for (int i = 0; i < 11; ++i) {
            for (int j = 0; j < 11; ++j) {
                flag = snake.findYourPathTo(new Position(10 - i, 10 - j));
                if (flag)
                    break;
            }
            if (flag)
                break;
        }

    }
}