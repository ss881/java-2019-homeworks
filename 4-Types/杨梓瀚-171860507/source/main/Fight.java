package main;

import Creature.*;

public class Fight {
    private Grandpa grandpa;

    private Snake snake;

    private Scorpion scorpion;

    Fight(){
        grandpa = new Grandpa();
        snake = new Snake();
        scorpion = new Scorpion();
    }

    private void init(){
        grandpa.init();
    }

    private void ready(){   //葫芦娃站好站位
        grandpa.longsnake();
        grandpa.set();

        snake.set();
    }

    private void run(){
        for (int i = 0; i < 7; i++){
            scorpion.go();
            print();
            Battle.reset();
        }
    }

    public static void main(String[] argc){
        Fight fight = new Fight();
        fight.init();

        fight.ready();

        fight.run();
    }

    private static void print() {
        Battle.print();
        /*for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (war[i][j] == null){
                    System.out.print(' ' + " ");
                }else {
                    KIND k = war[i][j].getKind();
                    if (k == KIND.calaBro){
                        System.out.print('C' + " ");
                    }else if(k == KIND.grandpa) {
                        System.out.print('G' + " ");
                    }else if(k == KIND.minion) {
                        System.out.print('M' + " ");
                    }else if(k == KIND.scorpion) {
                        System.out.print('S' + " ");
                    }else if(k == KIND.snake) {
                        System.out.print('N' + " ");
                    }
                }
            }
            System.out.print('\n');
        }*/
    }

}
