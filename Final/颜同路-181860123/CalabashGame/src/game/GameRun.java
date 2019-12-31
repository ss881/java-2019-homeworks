package game;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.LinkedList;

public class GameRun implements Runnable {
    public static Grandpa grandpa;
    public static Snake snake;
    public static LinkedList<Thread> threads;

    public void initial() throws IOException {   //初始化工作，布置战场

        grandpa = new Grandpa();
        snake = new Snake();
        threads = new LinkedList<Thread>();

        for (int i = 0;
             i < grandpa.we.size();
             i++)
            threads.add(new Thread(grandpa.we.get(i)));

        for (int i = 0;
             i < snake.we.size();
             i++)
            threads.add(new Thread(snake.we.get(i)));

        for (int i = 0;
             i < grandpa.we.size();
             i++)
            grandpa.conduct(grandpa.we.get(i), 1, i + 7);

        for (int i = 0;
             i < snake.we.size();
             i++)
            snake.conduct(snake.we.get(i), 18, i + 7);

        grandpa.go(0, 0);
        snake.go(19, 19);

        grandpa.cheer();
        snake.cheer();
    }


    @Override
    public void run() {   //运用多线程，进行一个基本上0互动的游戏场景。。。
        //游戏开始

        System.out.println("进入游戏\n");
        try {
            initial();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Thread t : threads)
            t.start();  //开始所有的线程

        int count = 0;  //回合计数器

        while (Game.running || Game.isplay) {   //回合控制器，主while循环
            if (!Game.pause) {
                count++;
                System.out.println("第【" + count + "】回合");

                try {   //双方指挥官随机获得能量
                    grandpa.mp += Game.getRandom(40);
                    snake.mp += Game.getRandom(40);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //鼓舞士气
                if (grandpa.mp >= 100) {
                    grandpa.mp -= 50;
                    try {
                        grandpa.cheer();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (snake.mp >= 100) {
                    snake.mp -= 100;
                    try {
                        snake.cheer();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                //所有线程响应
                for (int i = 0;
                     i < grandpa.we.size();
                     i++)
                    grandpa.we.get(i).wait = false;

                for (int i = 0;
                     i < snake.we.size();
                     i++)
                    snake.we.get(i).wait = false;

                //判胜利
                if (grandpa.we.size() == 0) {
                    System.out.println("您太蔡了！妖精们胜利了！");
                    Game.end("您太蔡了！妖精们胜利了！");
                } else if (snake.we.size() == 0) {
                    System.out.println("您太棒了！葫芦娃胜利了！");
                    Game.end("您太棒了！葫芦娃胜利了！");
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(50);   //什么都不干
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Main.gameview.update();
            }
        }
    }
}
